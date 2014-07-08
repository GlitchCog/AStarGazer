package astargazer.map;

import java.util.Random;

/**
 * Randomly generates a tilemap
 * 
 * @author Matt Yanos
 */
public class MapGenerator
{
    private int mapSeed = new Random().nextInt(10000);

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

        return generate(mapSeed);
    }

    /**
     * Generate a random TileMap
     * 
     * @param seed The random number generator seed to use
     * @return
     *         new TileMap
     */
    public TileMap generate(int seed)
    {
        rnd = new Random(seed);
        this.mapSeed = seed;

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

        WeightedPoint start = generatePoint(map);
        WeightedPoint goal = generatePoint(map);

        // Generate random blocks on the map
        if (obstacles)
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

        return new TileMap(map, start, goal, seed);
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

    /**
     * Get a random traversable point on the map
     * 
     * @param map
     * @return
     */
    private WeightedPoint generatePoint(boolean[][] map)
    {
        int r = rnd.nextInt(map.length - 2) + 1;
        int c = rnd.nextInt(map[0].length - 2) + 1;
        map[r][c] = false;
        return new WeightedPoint(r, c);
    }

}
