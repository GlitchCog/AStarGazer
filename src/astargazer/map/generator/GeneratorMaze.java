package astargazer.map.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import astargazer.map.WeightedPoint;

/**
 * Generate a 2D Boolean map with a set of obstacles representing a maze
 * 
 * @author Matt Yanos
 */
public class GeneratorMaze extends MapGenerator
{
    /**
     * MazeNode is used in the recursive backtracker maze generation algorithm
     */
    private class MazeNode
    {
        public final int row;

        public final int col;

        public boolean visited;

        public boolean northOpen;

        public boolean eastOpen;

        public boolean southOpen;

        public boolean westOpen;

        public MazeNode(int row, int col)
        {
            this.row = row;
            this.col = col;

            this.visited = false;

            this.northOpen = false;
            this.eastOpen = false;
            this.southOpen = false;
            this.westOpen = false;
        }
    }

    @Override
    public void addObstacles(Random rnd, boolean[][] map, WeightedPoint start, WeightedPoint goal)
    {
        // If the start or goal points are not on odd indexes, shift them
        makePointsOdd(start);
        makePointsOdd(goal);

        int mazeRows = map.length / 2;
        int mazeCols = map[0].length / 2;

        MazeNode[][] maze = new MazeNode[mazeRows][mazeCols];

        for (int r = 0; r < maze.length; r++)
        {
            for (int c = 0; c < maze[r].length; c++)
            {
                maze[r][c] = new MazeNode(r, c);
            }
        }

        MazeNode cursor;
        Stack<MazeNode> stack = new Stack<MazeNode>();

        cursor = getRandomUnvisitedNode(rnd, maze);

        // Generate a maze using the recursive backtracker maze generation algorithm
        while (anyUnvisited(maze))
        {
            if (anyUnvisitedNeighbors(maze, cursor))
            {
                stack.push(cursor);
                cursor = getUnvisitedNeighbor(rnd, maze, cursor);
            }
            else if (!stack.isEmpty())
            {
                cursor = stack.pop();
            }
            else
            {
                cursor = getRandomUnvisitedNode(rnd, maze);
            }
        }

        // Take the collection of maze nodes and put them into a tile map
        mazeToMap(maze, map);
    }

    /**
     * Make the coordinate index values of the specified point odd
     *
     * @param p
     */
    private void makePointsOdd(WeightedPoint p)
    {
        if (p.getRow() % 2 != 1)
        {
            p.setRow(p.getRow() + (p.getRow() > 0 ? -1 : 1));
        }
        if (p.getCol() % 2 != 1)
        {
            p.setCol(p.getCol() + (p.getCol() > 0 ? -1 : 1));
        }
    }

    /**
     * Gets a random unvisited node and marks it visited before returning it
     * 
     * @param rnd
     * @param maze
     * @return node
     */
    private MazeNode getRandomUnvisitedNode(Random rnd, MazeNode[][] maze)
    {
        MazeNode node = null;
        if (anyUnvisited(maze))
        {
            List<MazeNode> unvisited = new ArrayList<MazeNode>();
            for (int r = 0; r < maze.length; r++)
                for (int c = 0; c < maze[r].length; c++)
                    if (!maze[r][c].visited)
                        unvisited.add(maze[r][c]);
            node = unvisited.get(rnd.nextInt(unvisited.size()));
            node.visited = true;
        }
        return node;
    }

    /**
     * Get a randomly selected unvisited neighbor, mark the path between it and the cursor as open, and return it
     * 
     * @param rnd
     * @param maze
     * @param cursor
     * @return neighbor
     */
    private MazeNode getUnvisitedNeighbor(Random rnd, MazeNode[][] maze, MazeNode cursor)
    {
        List<MazeNode> unvisitedNeighbors = new ArrayList<MazeNode>();
        if (cursor.row > 0 && !maze[cursor.row - 1][cursor.col].visited)
            unvisitedNeighbors.add(maze[cursor.row - 1][cursor.col]);
        if (cursor.row < maze.length - 1 && !maze[cursor.row + 1][cursor.col].visited)
            unvisitedNeighbors.add(maze[cursor.row + 1][cursor.col]);
        if (cursor.col > 0 && !maze[cursor.row][cursor.col - 1].visited)
            unvisitedNeighbors.add(maze[cursor.row][cursor.col - 1]);
        if (cursor.col < maze[cursor.row].length - 1 && !maze[cursor.row][cursor.col + 1].visited)
            unvisitedNeighbors.add(maze[cursor.row][cursor.col + 1]);
        MazeNode neighbor = unvisitedNeighbors.get(rnd.nextInt(unvisitedNeighbors.size()));

        if (neighbor.row < cursor.row)
        {
            cursor.northOpen = true;
            neighbor.southOpen = true;
        }
        else if (neighbor.row > cursor.row)
        {
            cursor.southOpen = true;
            neighbor.northOpen = true;
        }
        else if (neighbor.col < cursor.col)
        {
            cursor.westOpen = true;
            neighbor.eastOpen = true;
        }
        else if (neighbor.col > cursor.col)
        {
            cursor.eastOpen = true;
            neighbor.westOpen = true;
        }

        neighbor.visited = true;

        return neighbor;
    }

    /**
     * Check if there are any unvisited neighbor nodes
     * 
     * @param maze
     * @param cursor
     * @return whether there are any unvisited neighbor nodes
     */
    private boolean anyUnvisitedNeighbors(MazeNode[][] maze, MazeNode cursor)
    {
        if (cursor.row > 0 && !maze[cursor.row - 1][cursor.col].visited)
            return true;
        if (cursor.row < maze.length - 1 && !maze[cursor.row + 1][cursor.col].visited)
            return true;
        if (cursor.col > 0 && !maze[cursor.row][cursor.col - 1].visited)
            return true;
        if (cursor.col < maze[cursor.row].length - 1 && !maze[cursor.row][cursor.col + 1].visited)
            return true;
        return false;
    }

    private boolean anyUnvisited(MazeNode[][] maze)
    {
        for (int r = 0; r < maze.length; r++)
            for (int c = 0; c < maze[r].length; c++)
                if (!maze[r][c].visited)
                    return true;
        return false;
    }

    /**
     * Fill the map with the maze
     * 
     * @param maze
     * @param map
     */
    private void mazeToMap(MazeNode[][] maze, boolean[][] map)
    {
        int mazeRow, mazeCol;
        for (int r = 1; r < map.length - 1; r += 2)
        {
            for (int c = 1; c < map[r].length - 1; c += 2)
            {
                mazeRow = (r - 1) / 2;
                mazeCol = (c - 1) / 2;

                map[r - 1][c - 1] = true;
                map[r - 1][c + 0] = !maze[mazeRow][mazeCol].northOpen;
                map[r - 1][c + 1] = true;

                map[r + 0][c - 1] = !maze[mazeRow][mazeCol].westOpen;
                map[r + 0][c + 0] = false;
                map[r + 0][c + 1] = !maze[mazeRow][mazeCol].eastOpen;

                map[r + 1][c - 1] = true;
                map[r + 1][c + 0] = !maze[mazeRow][mazeCol].southOpen;
                map[r + 1][c + 1] = true;
            }
        }
    }

    @Override
    public String getLabel()
    {
        return "Maze";
    }

}
