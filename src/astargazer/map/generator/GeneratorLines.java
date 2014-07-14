package astargazer.map.generator;

import java.util.Random;

import astargazer.map.WeightedPoint;

/**
 * Generate a 2D Boolean map with line obstacles
 * 
 * @author Matt Yanos
 */
public class GeneratorLines extends MapGenerator
{
    @Override
    public void addObstacles(Random rnd, boolean[][] map, WeightedPoint start, WeightedPoint goal)
    {
        boolean vertical = rnd.nextBoolean();
        final int lineCount = Math.max(1, (map.length * map[0].length) / 100);
        for (int li = 0; li < lineCount; li++)
        {
            if (li == lineCount - 2)
            {
                // Have a chance at having one or two perpendicular lines
                if (rnd.nextBoolean())
                {
                    vertical = !vertical;
                }
            }

            int placementIndex = rnd.nextInt(vertical ? map[0].length : map.length);

            if ( (vertical && (placementIndex == start.getCol() || placementIndex == goal.getCol())) || 
                 (!vertical && (placementIndex == start.getRow() || placementIndex == goal.getRow())) )
            {
                continue;
            }

            int a = rnd.nextInt(vertical ? map.length : map[0].length);
            int b = rnd.nextInt(vertical ? map.length : map[0].length);
            int beg = Math.min(a, b);
            int end = Math.max(a, b);

            if (rnd.nextBoolean() && beg < (vertical ? map.length / 5 : map[0].length / 5))
            {
                beg = 1;
            }
            else if (rnd.nextBoolean() && end < (vertical ? map.length / 5 : map[0].length / 5))
            {
                end = vertical ? map.length - 2 : map[0].length - 2;;
            }

            for (int j = beg; j < end; j++)
            {
                if (vertical)
                {
                    map[j][placementIndex] = true;
                }
                else
                {
                    map[placementIndex][j] = true;
                }
            }
        }

    }

    @Override
    public String getLabel()
    {
        return "Lines";
    }

}
