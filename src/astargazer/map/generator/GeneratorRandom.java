package astargazer.map.generator;

import java.util.Random;

import astargazer.map.WeightedPoint;

/**
 * Generate a 2D Boolean map with random point obstacles
 * 
 * @author Matt Yanos
 */
public class GeneratorRandom extends MapGenerator
{
    @Override
    public void addObstacles(Random rnd, boolean[][] map, WeightedPoint start, WeightedPoint goal)
    {
        final int pointCount = Math.max(1, (map.length * map[0].length) / (2 + rnd.nextInt(4)));
        int r, c;
        for (int i = 0; i < pointCount; i++)
        {
            r = rnd.nextInt(Math.max(1, map.length));
            c = rnd.nextInt(Math.max(1, map[0].length));
            if ((start.getCol() != c || start.getRow() != r) && (goal.getCol() != c || goal.getRow() != r))
            {
                map[r][c] = true;
            }
        }
    }

    @Override
    public String getLabel()
    {
        return "Random Points";
    }

}
