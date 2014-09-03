package astargazer.map.heuristic;

import astargazer.map.WeightedPoint;

/**
 * Calculates distance for use in determining the heuristic for the path finding algorithm
 * 
 * @author Matt Yanos
 */
public abstract class HeuristicScheme
{
    /**
     * Get the distance between the specified points for the extended heuristic scheme
     * 
     * @param one
     * @param two
     * @return distance
     */
    public abstract float distance(WeightedPoint one, WeightedPoint two);

    /**
     * Get the label for the extended heuristic scheme
     * 
     * @return label
     */
    public abstract String getLabel();

    /**
     * Get a String explaining the heuristic
     * 
     * @return explanation
     */
    public abstract String getExplanation();

    public String toString()
    {
        return getLabel();
    }

    /**
     * Get all the heuristic schemes that extend this base abstract class
     * 
     * @return heuristicSchemes
     */
    public static HeuristicScheme[] getAllHeuristics()
    {
        return new HeuristicScheme[] {new HeuristicManhattan(), new HeuristicChebyshev(), new HeuristicDiagonal(), new HeuristicEuclidean(), new HeuristicSquared()};
    }
}