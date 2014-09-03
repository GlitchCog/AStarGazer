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

    @Override
    public String getExplanation()
    {
        return "Chebyshev distance considers the movement on both axis " +
               "and takes the maximum between the two differences. This " +
               "heuristic can be paired with the 8-directional neighbor.<br /> " +
               "To visualize the usefulness of this heuristic imagine a " +
               "claw machine. While you're moving in one direction you can " + 
               "simultaneously move in the other direction at no cost in time " +
               "(unless you move more in this direction, which makes this the max).<br />" +
               "Sometimes in poorly programmed video games you notice that your " + 
               "character can move faster on the diagonal. This is because the " + 
               "motion is working in Chebyshev-space, but the world map you're " + 
               "running around on is in Euclidean space. Say the character has " + 
               "a set speed of one unit per update cycle. The up and the left " + 
               "buttons are pressed on the controller simultaneously, so the " + 
               "character moves up one unit and left one unit. The effect on " + 
               "the screen in Euclidean space is that the character gets a " + 
               "speed boost. The character should have been limited to one unit " + 
               "of motion, but by moving in two dimensions at once, the " + 
               "character traveled sqrt(2)/2 units in one update. In Chebyshev " + 
               "space these movements are considered equal, and the discrepancy " + 
               "between the Euclidean space of the game world and the Chebyshev " + 
               "space of the poorly chosen input scheme betray this.";
    }
}
