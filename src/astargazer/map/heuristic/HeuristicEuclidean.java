package astargazer.map.heuristic;

import astargazer.map.WeightedPoint;

/**
 * Euclidean distance heuristic
 * 
 * @author Matt Yanos
 */
public class HeuristicEuclidean extends HeuristicScheme
{
    @Override
    public float distance(WeightedPoint one, WeightedPoint two)
    {
        return (float)Math.sqrt((one.getCol() - two.getCol()) * (one.getCol() - two.getCol()) + (one.getRow() - two.getRow()) * (one.getRow() - two.getRow()));
    }

    @Override
    public String getLabel()
    {
        return "Euclidean";
    }

    @Override
    public String getExplanation()
    {
        return "This is normal distance calculated by the Pythagorean formula:<br />d = sqrt((a_x-b_x)<sup>2</sup>+(a_y-b_y)<sup>2</sup>)";
    }
}