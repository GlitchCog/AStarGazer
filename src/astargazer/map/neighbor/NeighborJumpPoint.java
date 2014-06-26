package astargazer.map.neighbor;

import java.util.ArrayList;
import java.util.List;

import astargazer.map.TileMap;
import astargazer.map.WeightedPoint;
import astargazer.map.heuristic.HeuristicScheme;

/**
 * Neighbor selector that returns a list of tiles found by extending rays outward in all directions from the cursor
 * until a certain distance or a non-traversable tile is hit. These "neighbors" are not necessarily adjacent, but represent the neighboring
 * step in the algorithm
 * 
 * @author Matt Yanos
 */
public class NeighborJumpPoint extends NeighborSelector
{
    private final static int SPOKE_COUNT = 64;

    @Override
    public List<WeightedPoint> getNeighbors(TileMap map, WeightedPoint cursor, HeuristicScheme distanceCalculator)
    {
        List<WeightedPoint> neighbors = new ArrayList<WeightedPoint>();

        final float maxDistance = Math.max(Math.min(map.getCols(), map.getRows()) / 4.0f, 2.0f); // One quarter the shortest map dimension (or at least 2)

        final double deltaAngle = 2.0 * Math.PI / (SPOKE_COUNT - 1);

        double testRow, testCol;
        WeightedPoint addition;
        for (float angle = 0.0f; angle < Math.PI * 2.0; angle += deltaAngle)
        {
            testRow = cursor.getRow();
            testCol = cursor.getCol();
            while (testCol > 0 && testCol < map.getCols() - 1 && testRow > 0 && testRow < map.getRows() - 1)
            {
                addition = new WeightedPoint((int)testRow, (int)testCol);

                testRow += Math.sin(angle);
                testCol += Math.cos(angle);

                if (map.getGoal().equals(addition) || 
                    (distanceCalculator.distance(cursor, addition) >= maxDistance) || 
                    (map.isTraversable(addition) && !map.isTraversable((int)testRow, (int)testCol)))
                {
                    if (!addition.equals(cursor) && !neighbors.contains(addition))
                        neighbors.add(addition);

                    break;
                }
            }
        }

        return neighbors;
    }

    @Override
    public String getLabel()
    {
        return "Jump Point";
    }

    @Override
    public String getExplanation()
    {
        return "Neighbors are selected by extending rays out from the " + 
               "current tile until an obstacle or the goal is hit. These " + 
               "tiles are \"neighbors\" in the sense that they are to be " + 
               "used in the next step of the algorithm, but are not " + 
               "necessarily adjacent.";
    }
}