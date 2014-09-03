package astargazer.map.heuristic;

import astargazer.map.WeightedPoint;

public class HeuristicDiagonal extends HeuristicScheme
{
    
    public static final float DIAGONAL_SCALE = (float)Math.sqrt( 2.0 );

    @Override
    public float distance( WeightedPoint one, WeightedPoint two )
    {
        int dx = Math.abs(one.getRow() - two.getRow());
        int dy = Math.abs(one.getCol() - two.getCol());
        
        return (dx < dy) ?
               DIAGONAL_SCALE * dx + (dy - dx) :
               DIAGONAL_SCALE * dy + (dx - dy);
    }

    @Override
    public String getLabel()
    {
        return "Diagonal";
    }

    @Override
    public String getExplanation()
    {
        return "Diagonal distance is a perfect pair with the 8-directional " +
               "neighbor selection scheme. It performs a perfect measure in " + 
               "distance when movement is restricted entirely to horizontal, " + 
               "vertical, and diagonal motion.";
    }
}
