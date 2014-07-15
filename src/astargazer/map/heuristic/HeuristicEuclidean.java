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
        return "This is normal distance calculated by the Pythagorean formula:<br />" + 
               "d = sqrt( (a<sub>x</sub> - b<sub>x</sub>)<sup>2</sup> + (a<sub>y</sub> - b<sub>y</sub>)<sup>2</sup> )";
    }
}
