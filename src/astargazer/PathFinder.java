package astargazer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import astargazer.map.TileMap;
import astargazer.map.WeightedPoint;
import astargazer.map.heuristic.HeuristicScheme;
import astargazer.map.neighbor.NeighborSelector;

/**
 * Uses the A* path finding algorithm to determine the optimal path between two points on a map
 * 
 * @author Matt Yanos
 */
public class PathFinder
{
    /**
     * The state of the algorithm: running, completed with a result, completed with no result
     */
    private StatusEnum status;

    /**
     * The map: rows x columns, false = traversable, true = wall
     */
    private TileMap map;

    /**
     * Tail of path being built by running this algorithm
     */
    private WeightedPoint tail;

    /**
     * Cursor point
     */
    private WeightedPoint cursor;

    /**
     * Points that have yet to be visited stored as a min-heap based on the point cost (Note that this "set" is actually
     * a heap, so a manual test for whether the point being added should be used prior to calling push().) The heap is
     * used for the open set to quickly return the point with the minimum cost.
     */
    private Heap<WeightedPoint> openSet;

    /**
     * Points that have already been visited
     */
    private Set<WeightedPoint> closedSet;

    /**
     * The means for determining distances
     */
    private HeuristicScheme heuristic;

    /**
     * The means for determining valid neighbors
     */
    private NeighborSelector neighborSelector;

    /**
     * Whether to assign any h value (to cost) with the heuristic. If true, will find the optimal path. 
     */
    private boolean dijkstra;

    /**
     * Whether to shuffle the order of nodes with the same cost to avoid the bias of the default neighbor order
     */
    private boolean shuffle;

    /**
     * Get the Map
     * 
     * @return the map
     */
    public TileMap getMap()
    {
        return map;
    }

    /**
     * Get the Start point
     * 
     * @return the start
     */
    public WeightedPoint getStart()
    {
        return map.getStart();
    }

    /**
     * Get the Goal point
     * 
     * @return the goal
     */
    public WeightedPoint getGoal()
    {
        return map.getGoal();
    }

    /**
     * Get the Cursor point
     * 
     * @return the cursor
     */
    public WeightedPoint getCursor()
    {
        return cursor;
    }

    /**
     * Get the open set which contains points that have yet to be visited stored as a min-heap based on the point cost
     * (Note that this "set" is actually a heap, so a manual test for whether the point being added should be used prior
     * to calling push().) The heap is used for the open set to quickly return the point with the minimum cost.
     * 
     * @return the openSet
     */
    public Heap<WeightedPoint> getOpenSet()
    {
        return openSet;
    }

    /**
     * Get the Closed Set
     * 
     * @return the closedSet
     */
    public Set<WeightedPoint> getClosedSet()
    {
        return closedSet;
    }

    /**
     * Set the Heuristic
     * 
     * @param heuristic
     */
    public void setHeuristic(HeuristicScheme heuristic)
    {
        this.heuristic = heuristic;
    }

    /**
     * Set the Neighbor Selector
     * 
     * @param neighborSelector
     */
    public void setNeighborSelector(NeighborSelector neighborSelector)
    {
        this.neighborSelector = neighborSelector;
    }

    /**
     * Sets whether to assign any h value (to cost) with the heuristic. If true, will find the optimal path.
     * 
     * @param dijkstra
     */
    public void setDijkstra(boolean dijkstra)
    {
        this.dijkstra = dijkstra;
    }

    /**
     * Set whether to shuffle the order of nodes with the same cost to avoid the bias of the default neighbor order
     * 
     * @param shuffle
     */
    public void setShuffle(boolean shuffle)
    {
        this.shuffle = shuffle;
    }

    /**
     * Construct the PathFinder
     * 
     * @param map
     *            the map
     * @param start
     *            the point to begin at
     * @param goal
     *            the point to end at
     */
    public PathFinder(TileMap map)
    {
        reset(map);
    }

    /**
     * Reset the PathFinder using the original map
     */
    public void reset()
    {
        reset(this.map, this.heuristic, this.neighborSelector);
    }

    /**
     * Reset the PathFinder with a redefined map, start, and goal, but leaving the selected heuristic and neighbor selector
     * 
     * @param map
     */
    public void reset(TileMap map)
    {
        reset(map, this.heuristic, this.neighborSelector);
    }

    /**
     * Reset the PathFinder
     * 
     * @param map
     *            the map
     */
    public void reset(TileMap map, HeuristicScheme heuristic, NeighborSelector neighborSelector)
    {
        this.map = map;

        this.cursor = null;

        this.tail = map.getStart();

        this.heuristic = heuristic;
        this.neighborSelector = neighborSelector;

        this.status = StatusEnum.RUNNING;

        this.openSet = new Heap<WeightedPoint>();
        this.openSet.push(map.getStart());
        this.closedSet = new HashSet<WeightedPoint>();
    }

    /**
     * Run through the next step in the algorithm. Each step represents investigating another possible point on the path
     * 
     * @return
     *         the status after running the step
     */
    public void step()
    {
        status = stepInternal();
    }

    /**
     * Run through the next step in the algorithm. Each step represents investigating another possible point on the path
     * 
     * @return
     *         the status after running the step
     */
    private StatusEnum stepInternal()
    {
        if (status != StatusEnum.RUNNING)
            return status;

        cursor = openSet.pop(); // Pull the cursor off the open set min-heap
        if (cursor == null)
        {
            // The open set was empty, so although we have not reached the goal, there are no more points to investigate
            return StatusEnum.COMPLETED_NOT_FOUND;
        }

        while (closedSet.contains(cursor) || !map.isTraversable(cursor))
        {
            // The cursor is in the closed set (meaning it was already investigated) or the cursor point is non traversable on the map
            cursor = openSet.pop();
            if (cursor == null)
            {
                return StatusEnum.COMPLETED_NOT_FOUND;
            }
        }

        // The goal has been reached, the path is complete
        if (cursor.equals(map.getGoal()))
        {
            tail = cursor; // Set the member tail to be used in the reconstruction done in getPath()
            return StatusEnum.COMPLETED_FOUND;
        }

        // Add the cursor point to the closed set
        closedSet.add(cursor);

        // Get the list of neighboring points
        List<WeightedPoint> neighbors = neighborSelector.getNeighbors(map, cursor);

        // Link the neighbors to the cursor (for backtracking the path when the goal is reached) and calculate their weight
        for (WeightedPoint wp : neighbors)
        {
            if (map.isTraversable(wp.getRow(), wp.getCol()) && !closedSet.contains(wp))
            {
                wp.setFromCost(cursor.getFromCost() + heuristic.distance(cursor, wp));

                if (dijkstra)
                {
                    wp.setToCost(0);
                }
                else
                {
                    wp.setToCost(heuristic.distance(wp, map.getGoal()));
                }
                wp.setPrev(cursor);
            }
        }

        if (shuffle)
        {
            // Shuffle the neighbors to randomize the order of testing nodes with the same cost value
            Collections.shuffle( neighbors );
        }
        Collections.sort( neighbors );

        // Put the neighbors on the open set
        for (WeightedPoint wp : neighbors)
        {
            if (!openSet.contains(wp))
                openSet.push(wp);
        }

        return StatusEnum.RUNNING;
    }

    /**
     * Continue to call step until the algorithm is complete
     * 
     * @return
     *         status
     */
    public StatusEnum solve()
    {
        while (status == StatusEnum.RUNNING)
        {
            step();
        }
        return status;
    }

    /**
     * Get the path finder algorithm status
     * 
     * @return
     *         status
     */
    public StatusEnum getStatus()
    {
        return status;
    }

    /**
     * Constructs and returns a List version of the tail linked list generated by the algorithm
     * 
     * @return
     *         path
     */
    public List<WeightedPoint> getPath()
    {
        return getPath(this.tail);
    }

    /**
     * Constructs and returns a List version of the linked list generated by the algorithm from the specified cursor point
     * 
     * @param cursor
     * @return
     *         path
     */
    public List<WeightedPoint> getPath(WeightedPoint cursor)
    {
        List<WeightedPoint> path = new ArrayList<WeightedPoint>();
        while (cursor != null)
        {
            path.add(path.size(), cursor);
            cursor = cursor.getPrev();
        }
        return path;
    }

}
