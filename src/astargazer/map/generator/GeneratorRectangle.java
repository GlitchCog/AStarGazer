package astargazer.map.generator;

import java.util.Random;

import astargazer.map.WeightedPoint;

/**
 * Generate a 2D Boolean map with rectangular obstacles
 * 
 * @author Matt Yanos
 */
public class GeneratorRectangle extends MapGenerator
{
    @Override
    public void addObstacles(Random rnd, boolean[][] map, WeightedPoint start, WeightedPoint goal)
    {
        int r, c, w, h;
        final int blockCount = Math.max(1, (map.length * map[0].length) / 100);
        for (int i = 0; i < blockCount; i++)
        {
            // Generate these values whether they're used or not to keep the seed consistent 
            // for the start and goals whether there are obstacles or not 
            r = rnd.nextInt(Math.max(1, map.length));
            c = rnd.nextInt(Math.max(1, map[0].length));
            w = 2 + rnd.nextInt(15);
            h = 2 + rnd.nextInt(15);

            // Ensure that this block does not cover the start or goal points
            boolean coveringStart = isCoveringPoint(start, r, c, w, h);
            boolean coveringGoal = isCoveringPoint(goal, r, c, w, h);
            if (!coveringStart && !coveringGoal)
            {
                insertBlock(map, r, c, w, h);
            }
        }

    }

    /**
     * Determines whether a block specified by center point (x, y) of width w and height h will cover the specified point p
     * 
     * @param p point to be tested against
     * @param r center row of the block
     * @param c center column of the block
     * @param w width of the block
     * @param h height of the block
     * @return whether the point is covered
     */
    private boolean isCoveringPoint(WeightedPoint p, int r, int c, int w, int h)
    {
        return c - (w / 2) <= p.getCol() && p.getCol() < c + (w / 2) && 
               r - (h / 2) <= p.getRow() && p.getRow() < r + (h / 2);

    }

    /**
     * Insert a block of non-traversable tiles onto the map
     * 
     * @param map
     * @param row
     * @param col
     * @param width
     * @param height
     */
    private void insertBlock(boolean[][] map, int row, int col, int width, int height)
    {
        for (int r = Math.max(0, row - height / 2); r < Math.min(row + height / 2, map.length); r++)
        {
            for (int c = Math.max(0, col - width / 2); c < Math.min(col + width / 2, map[r].length); c++)
            {
                map[r][c] = true;
            }
        }
    }

    @Override
    public String getLabel()
    {
        return "Blocks";
    }

}
