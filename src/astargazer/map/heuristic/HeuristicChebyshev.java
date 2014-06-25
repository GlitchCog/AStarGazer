package astargazer.map.heuristic;

import astargazer.map.WeightedPoint;

public class HeuristicChebyshev extends HeuristicScheme
{

    @Override
    public float distance( WeightedPoint one, WeightedPoint two )
    {
        int dx = Math.abs(one.getRow() - two.getRow());
        int dy = Math.abs(one.getCol() - two.getCol());
        return Math.max(dx, dy);
    }

    @Override
    public String getLabel()
    {
        return "Chebyshev";
    }

}