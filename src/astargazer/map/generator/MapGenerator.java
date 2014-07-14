package astargazer.map.generator;

import java.util.Random;

import astargazer.map.WeightedPoint;

/**
 * Base class for generating maps
 *
 * @author Matt Yanos
 */
public class MapGenerator implements GenerationScheme
{
    @Override
    public void addObstacles(Random rnd, boolean[][] map, WeightedPoint start, WeightedPoint goal)
    {
        // The base generator class adds no non-border obstacles
    }

    @Override
    public String getLabel()
    {
        return "None";
    }

    /**
     * Add a border to the specified 2D Boolean array
     *
     * @param map
     */
    public void addBorder(boolean[][] map)
    {
        // Draw vertical borders
        for (int r = 0; r < map.length; r++)
        {
            map[r][0] = true; // left
            map[r][map[r].length - 1] = true; // right
        }
        // Draw horizontal borders
        for (int c = 0; c < map[0].length; c++)
        {
            map[0][c] = true; // top
            map[map.length - 1][c] = true; // bottom
        }
    }

    /**
     * Get a random traversable point on the map
     * 
     * @param map
     * @return point
     */
    public WeightedPoint generatePoint(Random rnd, boolean[][] map)
    {
        int r = rnd.nextInt(map.length - 2) + 1;
        int c = rnd.nextInt(map[0].length - 2) + 1;
        map[r][c] = false;
        return new WeightedPoint(r, c);
    }

    @Override
    public String toString()
    {
        return getLabel();
    }

    /**
     * Get all the map generation schemes that extend this base class
     * 
     * @return heuristicSchemes
     */
    public static GenerationScheme[] getAllGenerators()
    {
        return new GenerationScheme[] {new MapGenerator(), 
                                       new GeneratorRectangle(), 
                                       new GeneratorEllipse(), 
                                       new GeneratorLines(), 
                                       new GeneratorRandom(), 
                                       new GeneratorMaze()
                                      };
    }

}
