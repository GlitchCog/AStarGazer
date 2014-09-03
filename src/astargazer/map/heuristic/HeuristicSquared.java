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

    @Override
    public String getExplanation()
    {
        return "This heuristic is an optimized version of the " + 
               "Euclidean heuristic. It is the normal distance formula, but " + 
               "without taking the square root. Because the distance is only " + 
               "used to compare, comparing the squares of two values still " + 
               "works to determine which path is shorter. Is does weight the " + 
               "from-cost (g) much less than the to-cost (h) because the from-" + 
               "cost is added up incrementally while the to-cost is calculated " + 
               "in one big piece. This is caused because the distance between " + 
               "the start and the cursor is many very small steps squared " + 
               "individually before being added up (often just 1, which is not " + 
               "increased by being squared at all) so the Euclidean squared " + 
               "heuristic is much greedier about getting to its goal. " + 
               "Sometimes this imbalance will cause it to select a less " + 
               "optimal path closer to the start in favor of a better path " + 
               "closer to the goal.";
    }
}
