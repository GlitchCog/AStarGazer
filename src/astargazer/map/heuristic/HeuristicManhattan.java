package astargazer.map.heuristic;

import astargazer.map.WeightedPoint;

/**
 * Manhattan distance heuristic
 * 
 * @author Matt Yanos
 */
public class HeuristicManhattan extends HeuristicScheme
{
    @Override
    public float distance(WeightedPoint one, WeightedPoint two)
    {
        return Math.abs(two.getCol() - one.getCol()) + Math.abs(two.getRow() - one.getRow());
    }

    @Override
    public String getLabel()
    {
        return "Manhattan";
    }

    @Override
    public String getExplanation()
    {
        return "Manhattan (or Taxicab) distance only permits travelling along " + 
               "the x and y axes, so the distance between (0, 0) and (1, 1) is " + 
               "2 from travelling along the X axis 1 unit and then up the Y " + 
               "axis 1 unit, and not the hypotenuse, which would be sqrt(2)/2. " + 
               "It's so named because in a city you're bound to the streets. " + 
               "You can't cut diagonally through a building to go north/south " + 
               "one block and east/west one block.";
    }
}