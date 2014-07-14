package astargazer.map.generator;

import java.util.Random;

import astargazer.map.TileMap;
import astargazer.map.WeightedPoint;

/**
 * Randomly generates a tilemap
 * 
 * @author Matt Yanos
 */
public class MapManager
{
    /**
     * The stored seed for map generation, initialized to some random value and incremented when the generate button is pressed
     */
    private int mapSeed = new Random().nextInt(10000);

    // Minimum and maximum tile map dimensions
    private static final int MIN_HEIGHT = 30;
    private static final int MAX_HEIGHT = 40;
    private static final int MIN_WIDTH = 40;
    private static final int MAX_WIDTH = 60;

    /**
     * The random number generator used to produce the varience in the tile maps
     */
    private Random rnd;

    /**
     * The single permitted instance of this class
     */
    private static MapManager onlyOne;

    /**
     * Holds the implementation of the map generator scheme that is currently being used
     */
    private static MapGenerator generator;

    /**
     * Private default constructor
     */
    private MapManager()
    {
        this.rnd = new Random(mapSeed);
        this.generator = new MapGenerator();
    }

    /**
     * Get the one instance of the MapManager class
     * 
     * @return
     *         onlyOne
     */
    public static MapManager getInstance()
    {
        if (onlyOne == null)
        {
            onlyOne = new MapManager();
        }
        return onlyOne;
    }

    /**
     * Set the map generator
     *
     * @param generator
     */
    public void setGenerator(MapGenerator generator)
    {
        this.generator = generator;
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

        WeightedPoint start = generator.generatePoint(rnd, map);
        WeightedPoint goal = generator.generatePoint(rnd, map);

        // Generate random blocks on the map
        generator.addObstacles(rnd, map, start, goal);

        // Put a border on all maps
        generator.addBorder(map);

        return new TileMap(map, start, goal, seed);
    }
}
