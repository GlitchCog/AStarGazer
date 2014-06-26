package astargazer.map.neighbor;

import java.util.List;

import astargazer.map.WeightedPoint;
import astargazer.map.TileMap;
import astargazer.map.heuristic.HeuristicScheme;

/**
 * Neighbor selector that returns all eight adjacent tiles: N, S, E, W, NE, SE, NW, SW
 * 
 * @author Matt Yanos
 */
public class NeighborEightDirections extends NeighborSelector
{
    @Override
    public List<WeightedPoint> getNeighbors(TileMap map, WeightedPoint cursor, HeuristicScheme distanceCalculator)
    {
        List<WeightedPoint> neighbors = (new NeighborFourDirections()).getNeighbors(map, cursor, distanceCalculator);

        // If the neighbor is not out of bounds and that the neighbor is traversable, then add it to the list
        if (cursor.getRow() > 0 && map.isTraversable(cursor.getRow() - 1, cursor.getCol() - 1) )
        {
            neighbors.add( new WeightedPoint(cursor.getRow() - 1, cursor.getCol() - 1) ); // Northwest
        }
        if (cursor.getCol() < map.getCols() - 1 && map.isTraversable(cursor.getRow() + 1, cursor.getCol() + 1) )
        {
            neighbors.add( new WeightedPoint(cursor.getRow() + 1, cursor.getCol() + 1) ); // Southeast
        }
        if (cursor.getRow() < map.getRows() - 1 && map.isTraversable(cursor.getRow() + 1, cursor.getCol() - 1))
        {
            neighbors.add( new WeightedPoint(cursor.getRow() + 1, cursor.getCol() - 1) ); // Southwest
        }
        if (cursor.getCol() > 0 && map.isTraversable(cursor.getRow() - 1, cursor.getCol() + 1) )
        {
            neighbors.add( new WeightedPoint(cursor.getRow() - 1, cursor.getCol() + 1) ); // Northeast
        }

        return neighbors;
    }

    @Override
    public String getLabel()
    {
        return "8-Directional";
    }

    @Override
    public String getExplanation()
    {
        return "Neighbors are selected from diagonally adjacent tiles in " + 
               "addition to the north, east, south, and west directions. " + 
               "This works best with the Euclidean heuristic. Combining " + 
               "this neighbor selection scheme with the Manhattan " + 
               "heuristic can yield strange results because a diagonal " + 
               "path over a single tile is counted as a distance of 2.";
    }
}