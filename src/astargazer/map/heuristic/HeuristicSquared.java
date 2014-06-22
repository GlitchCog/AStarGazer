package astargazer.map.heuristic;

import astargazer.map.WeightedPoint;

/**
 * Euclidean distance squared heuristic
 * 
 * @author Matt Yanos, Philip Diffenderfer
 */
public class HeuristicSquared extends HeuristicScheme
{
    @Override
    public float distance(WeightedPoint one, WeightedPoint two)
    {
        float dx = one.getCol() - two.getCol();
        float dy = one.getRow() - two.getRow();

        return dx * dx + dy * dy;
    }

    @Override
    public String getLabel()
    {
        return "Euclidian Squared";
    }

}
