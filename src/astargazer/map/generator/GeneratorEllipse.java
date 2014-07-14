package astargazer.map.generator;

import java.util.Random;

import astargazer.map.WeightedPoint;

/**
 * Generate a 2D Boolean map with elliptical obstacles
 * 
 * @author Matt Yanos
 */
public class GeneratorEllipse extends MapGenerator
{
    @Override
    public void addObstacles(Random rnd, boolean[][] map, WeightedPoint start, WeightedPoint goal)
    {
        int r, c, w, h;
        final int ovalCount = Math.max(1, (map.length * map[0].length) / 100);
        for (int i = 0; i < ovalCount; i++)
        {
            // Generate these values whether they're used or not to keep the seed consistent 
            // for the start and goals whether there are obstacles or not 
            r = rnd.nextInt(Math.max(1, map.length));
            c = rnd.nextInt(Math.max(1, map[0].length));
            w = 2 + rnd.nextInt(15);
            h = 2 + rnd.nextInt(15);

            if (w < 4)
                w = 4;
            if (h < 4)
                h = 4;

            // Ensure that this block does not cover the start or goal points
            boolean coveringStart = isCoveringPoint(start.getRow(), start.getCol(), r, c, w, h);
            boolean coveringGoal = isCoveringPoint(goal.getRow(), goal.getCol(), r, c, w, h);
            if (!coveringStart && !coveringGoal)
            {
                insertEllipse(map, r, c, w, h);
            }
        }

    }

    /**
     * Determines whether a block specified by center point (x, y) of width w and height h will cover the specified point p
     * 
     * @param pr row of point to be tested against
     * @param pc column of point to be tested against
     * @param r center row of the block
     * @param c center column of the block
     * @param w width of the block
     * @param h height of the block
     * @return whether the point is covered
     */
    private boolean isCoveringPoint(float pr, float pc, float r, float c, float w, float h)
    {
        // Change the diameters into radiuses
        w /= 2.0f;
        h /= 2.0f;

        // Put the center coordinates in the center of the tile
        r -= 0.5f;
        c -= 0.5f;

        // Translate the point to be checked relative to the center of the ellipse
        pr -= r;
        pc -= c;

        // If the point is inside the equation of the ellipse, then mark the tile as intraversable
        return ( (pc * pc) / (w * w) + (pr * pr) / (h * h) < 1 );
    }

    /**
     * Insert an oval of non-traversable tiles onto the map
     * 
     * @param map
     * @param row
     * @param col
     * @param width
     * @param height
     */
    private void insertEllipse(boolean[][] map, int row, int col, int width, int height)
    {
        for (int r = Math.max(0, row - height / 2); r < Math.min(row + height / 2, map.length); r++)
        {
            for (int c = Math.max(0, col - width / 2); c < Math.min(col + width / 2, map[r].length); c++)
            {
                if (isCoveringPoint(r, c, row, col, width, height))
                {
                    map[r][c] = true;
                }
            }
        }
    }

    @Override
    public String getLabel()
    {
        return "Rounded Edges";
    }

}
