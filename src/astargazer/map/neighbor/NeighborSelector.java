package astargazer.map.neighbor;

import java.util.List;

import astargazer.map.WeightedPoint;
import astargazer.map.TileMap;

/**
 * Selects the group of neighboring points to be considered in the next step of the algorithm
 * 
 * @author Matt Yanos
 */
public abstract class NeighborSelector
{
    /**
     * Returns a list of neighboring points to be considered in the next step of the algorithm
     * 
     * @param map
     * @param cursor
     * @return
     *         neighbors
     */
    public abstract List<WeightedPoint> getNeighbors(TileMap map, WeightedPoint cursor);

    /**
     * Get the label
     * 
     * @return label
     */
    public abstract String getLabel();

    public String toString()
    {
        return getLabel();
    }
}