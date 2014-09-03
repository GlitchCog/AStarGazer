package astargazer.map.generator;

import java.util.Random;

import astargazer.map.WeightedPoint;

/**
 * Generates the 2D Boolean array for a tile map
 * 
 * @author Matt Yanos
 */
public interface GenerationScheme
{
    /**
     * Add obstacles to the specified 2D Boolean array
     * 
     * @param rnd The random number generator for the map to be generated
     * @param map The 2D Boolean array for the map to be generated
     * @param start The start point for the map to be generated
     * @param goal The goal point for the map to be generated
     */
    public void addObstacles(Random rnd, boolean[][] map, WeightedPoint start, WeightedPoint goal);

    /**
     * Get the label for this tile map obstacle generation scheme
     * 
     * @return label
     */
    public String getLabel();

}
