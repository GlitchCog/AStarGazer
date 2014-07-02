package astargazer.map;

import astargazer.map.WeightedPoint;

/**
 * A grid representing a map with traversable and non-traversable tiles
 * 
 * @author Matt Yanos
 */
public class TileMap
{
    /**
     * The grid of tiles where TRUE is non-traversable and FALSE is traversable
     */
    private boolean[][] map;

    /**
     * The starting point
     */
    private WeightedPoint start;

    /**
     * The goal point
     */
    private WeightedPoint goal;

    /**
     * Whether the start and goal points are swapped
     */
    private static boolean endPointSwap = false;

    /**
     * Construct a TileMap with the specified boolean map of TRUE non-traversable tiles
     * 
     * @param map
     */
    public TileMap(boolean[][] map, WeightedPoint start, WeightedPoint goal)
    {
        this.map = map;
        this.start = start;
        this.goal = goal;
    }

    /**
     * Construct a TileMap with the specified int map where all non-zero numbers are non-traversable tiles
     * 
     * @param imap
     */
    public TileMap(int[][] imap)
    {
        map = new boolean[imap.length][imap[0].length];
        for (int r = 0; r < imap.length; r++)
        {
            for (int c = 0; c < imap[r].length; c++)
            {
                map[r][c] = imap[r][c] != 0;
            }
        }
    }

    /**
     * Get the starting point for this tile map
     * 
     * @return start
     */
    public WeightedPoint getStart()
    {
        return TileMap.endPointSwap ? goal : start;
    }

    /**
     * Set the starting point for this tile map
     * 
     * @param start
     */
    public void setStart(WeightedPoint start)
    {
        this.start = start;
    }

    /**
     * Get the goal point for this tile map
     * 
     * @return goal
     */
    public WeightedPoint getGoal()
    {
        return TileMap.endPointSwap ? start : goal;
    }

    /**
     * Set the goal point for this tile map
     * 
     * @param goal
     */
    public void setGoal(WeightedPoint goal)
    {
        this.goal = goal;
    }

    /**
     * Set whether the end points should be swapped
     * 
     * @param endPointSwap
     */
    public static void setEndPointSwap(boolean endPointSwap)
    {
        TileMap.endPointSwap = endPointSwap;
    }

    /**
     * Whether the point is a traversable point on this tile map
     * 
     * @param wp the point to check
     * @return
     *         traversable
     */
    public boolean isTraversable(WeightedPoint wp)
    {
        return !map[wp.getRow()][wp.getCol()];
    }

    /**
     * Whether the move is a valid on this tile map
     * 
     * @param row
     * @param col
     * @return
     */
    public boolean isTraversable(int row, int col)
    {
        return !map[row][col];
    }

    /**
     * Set whether the move is a valid on this tile map
     * 
     * @param row
     * @param col
     * @param traversable
     */
    public void setTraversable(int row, int col, boolean traversable)
    {
        map[row][col] = !traversable;
    }

    /**
     * Get the number of rows
     * 
     * @return
     */
    public int getRows()
    {
        return map.length;
    }

    /**
     * Get the number of columns
     * 
     * @return
     */
    public int getCols()
    {
        return map[0].length;
    }

}
