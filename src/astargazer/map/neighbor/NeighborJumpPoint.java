package astargazer.map.neighbor;

import java.util.ArrayList;
import java.util.List;

import astargazer.map.TileMap;
import astargazer.map.WeightedPoint;

/**
 * Neighbor selector that returns a list of tiles found by extending rays outward in all directions from the cursor
 * until a non-traversable tile is hit. These "neighbors" are not necessarily adjacent, but represent the neighboring
 * step in the algorithm
 * 
 * @author Matt Yanos
 */
public class NeighborJumpPoint extends NeighborSelector
{
    @Override
    public List<WeightedPoint> getNeighbors(TileMap map, WeightedPoint cursor)
    {
        List<WeightedPoint> neighbors = new ArrayList<WeightedPoint>();

        final int spokeCount = 12;
        final double deltaAngle = 2.0 * Math.PI / (spokeCount - 1);

        int row = 0;
        int col = 0;
        double testRow, testCol;
        WeightedPoint addition;
        for (float angle = 0.0f; angle < Math.PI * 2.0; angle += deltaAngle)
        {
            testRow = cursor.getRow();
            testCol = cursor.getCol();
            while (testCol > 0 && testCol < map.getCols() - 1 && testRow > 0 && testRow < map.getRows() - 1)
            {
                testRow += Math.sin(angle);
                testCol += Math.cos(angle);

                row = (int)testRow;
                col = (int)testCol;

                if (map.isTraversable((int)testRow, (int)testCol))
                {
                    addition = new WeightedPoint(row, col);

                    if (!addition.equals(cursor) && !neighbors.contains(addition))
                        neighbors.add(addition);
                }
                else
                    break;
            }
        }

        return neighbors;
    }

    @Override
    public String getLabel()
    {
        return "Jump Point";
    }
}