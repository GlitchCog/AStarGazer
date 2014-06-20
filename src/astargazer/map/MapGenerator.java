package astargazer.map;

import java.util.Random;

/**
 * Randomly generates a tilemap
 * 
 * @author Matt Yanos
 */
public class MapGenerator
{
    private int mapSeed = new Random().nextInt();

    private static final int MIN_HEIGHT = 30;
    private static final int MAX_HEIGHT = 40;

    private static final int MIN_WIDTH = 40;
    private static final int MAX_WIDTH = 60;

    /**
     * Whether a generated map contains obstacles
     */
    private boolean obstacles;

    private Random rnd;

    private static MapGenerator onlyOne;

    /**
     * Private default constructor
     */
    private MapGenerator()
    {
        rnd = new Random(mapSeed);
    }

    /**
     * Get the one instance of the MapGenerator class
     * 
     * @return
     *         onlyOne
     */
    public static MapGenerator getInstance()
    {
        if (onlyOne == null)
        {
            onlyOne = new MapGenerator();
        }
        return onlyOne;
    }

    /**
     * Set whether a generated map contains obstacles
     * 
     * @param obstacles
     */
    public void setObstacles(boolean obstacles)
    {
        this.obstacles = obstacles;
    }

    /**
     * Generate a random TileMap
     * 
     * @param increment whether to increment the random seed
     * @return
     *         new TileMap
     */
    public TileMap generate(boolean increment)
    {
        if (increment)
        {
            mapSeed++;
        }

        rnd = new Random(mapSeed);

        boolean[][] map = new boolean[rnd.nextInt(MAX_HEIGHT - MIN_HEIGHT) + MIN_HEIGHT][rnd.nextInt(MAX_WIDTH - MIN_WIDTH) + MIN_WIDTH];

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

        // Generate 20 random blocks on the map
        int x, y, w, h;
        for (int i = 0; i < 10; i++)
        {
            // Generate these values whether they're used or not to keep the seed consistent 
            // for the start and goals whether there are obstacles or not 
            x = 10 + rnd.nextInt(Math.max(1, map.length - 11));
            y = rnd.nextInt(Math.max(1,10 + map[0].length - 11));
            w = 2 + rnd.nextInt(15);
            h = 2 + rnd.nextInt(15);
            if (obstacles)
                insertBlock(map, x, y, w, h);
        }

        return new TileMap(map, generatePoint(map), generatePoint(map));
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

    /**
     * Get a random traversable point on the map
     * 
     * @param map
     * @return
     */
    private WeightedPoint generatePoint(boolean[][] map)
    {
        int attempts = 0;
        int r, c;
        do
        {
            r = rnd.nextInt(map.length - 2) + 1;
            c = rnd.nextInt(map[0].length - 2) + 1;
            attempts++;
        } while (map[r][c] && attempts < 1000);
        if (attempts == 1000)
        {
            // Gave up, so just set the point to traversable, maybe it's on the edge of a block
            map[r][c] = false;
        }
        return new WeightedPoint(r, c);
    }

}