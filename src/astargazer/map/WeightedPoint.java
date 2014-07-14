package astargazer.map;

/**
 * A point comprised of a row and a column with an associated cost comprised of a to cost and a from cost for use in an
 * A* search algorithm
 * 
 * @author Matt Yanos
 */
public class WeightedPoint implements Comparable<WeightedPoint>
{
    /**
     * The row for this point
     */
    private int row;

    /**
     * The column for this point
     */
    private int col;

    /**
     * The cost from a starting point to have reached this point (g)
     */
    private float fromCost;

    /**
     * The estimated cost to go from this point to a goal (h)
     */
    private float toCost;

    /**
     * A link to the previous point in a linked list of points tracing the path from this point back to the start
     */
    private WeightedPoint prev;

    /**
     * Construct a weighted point without assigning a from cost
     * 
     * @param row
     * @param col
     */
    public WeightedPoint(int row, int col)
    {
        this(row, col, 0);
    }

    /**
     * Construct a weighted point
     * 
     * @param row
     * @param col
     * @param fromCost
     */
    public WeightedPoint(int row, int col, int fromCost)
    {
        this.row = row;
        this.col = col;
        this.fromCost = fromCost;
    }

    /**
     * Get the row for this point
     * 
     * @return row
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Set the row for this point
     * 
     * @param row
     */
    public void setRow(int row)
    {
        this.row = row;
    }

    /**
     * Get the column for this point
     * 
     * @return col
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Set the column for this point
     * 
     * @param col
     */
    public void setCol(int col)
    {
        this.col = col;
    }

    /**
     * Get the total cost (f)<br />
     * <i>fromCost + toCost</i><br />
     * (f=g+h)
     * 
     * @return totalCost
     */
    public float getCost()
    {
        return fromCost + toCost;
    }

    /**
     * Get the from cost (g)
     * 
     * @return fromCost
     */
    public float getFromCost()
    {
        return fromCost;
    }

    /**
     * Set the from cost (g)
     * 
     * @param fromCost
     */
    public void setFromCost(float fromCost)
    {
        this.fromCost = fromCost;
    }

    /**
     * Get the to cost (h)
     * 
     * @return toCost
     */
    public float getToCost()
    {
        return toCost;
    }

    /**
     * Set the to cost (h)
     * 
     * @param toCost
     */
    public void setToCost(float toCost)
    {
        this.toCost = toCost;
    }

    /**
     * Get the link to the previous point in a linked list of points tracing the path from this point back to the start
     * 
     * @return prev
     */
    public WeightedPoint getPrev()
    {
        return prev;
    }

    /**
     * Set the link to the previous point in a linked list of points tracing the path from this point back to the start
     * 
     * @param prev
     */
    public void setPrev(WeightedPoint prev)
    {
        this.prev = prev;
    }

    @Override
    public int compareTo(WeightedPoint other)
    {
        if (other == null || this.getCost() > other.getCost())
            return 1;
        else if (this.getCost() < other.getCost())
            return -1;
        return 0;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + col;
        result = prime * result + row;
        return result;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
            return false;
        else if (other instanceof WeightedPoint)
        {
            WeightedPoint otherWp = (WeightedPoint)other;
            return (otherWp.row == row && otherWp.col == col);
        }
        return false;
    }

    public String toString()
    {
        return "(r" + row + ", c" + col + ")";
    }

    /**
     * Returns a String starting with the specified label and the weighted point toString method in a null-safe manner
     * 
     * @param label
     * @param wp
     * @return labeledString
     */
    public static String toLabeledString(String label, WeightedPoint wp)
    {
        return label + ": " + (wp == null ? "" : wp.toString());
    }
}
