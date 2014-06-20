package astargazer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple heap data structure, configurable as minimum or maximum
 * 
 * @author Matt Yanos
 * 
 * @param <T> The object type to be stored in the heap
 */
public class Heap<T extends Comparable<? super T>>
{
    /**
     * Test for the heap
     */
    public static void main(String[] args)
    {
        Heap<Integer> h = new Heap<Integer>(false);
        java.util.Random rnd = new java.util.Random();
        for (int i = 15; i > 0; i--)
            h.push(Integer.valueOf(rnd.nextInt(32)));
        while (!h.isEmpty())
            System.out.println(h.pop() + " ");
    }

    /**
     * The underlying data structure for the heap, should never be null, cannot
     * be publicly exposed without compromising the heap
     */
    protected Object[] array = new Object[0];


    /**
     * Whether the heap type is minimum or maximum
     */
    protected boolean isMaxHeap; // true = max, false = min

    /**
     * Constructs an empty minimum heap
     */
    public Heap()
    {
        this(false);
    }

    /**
     * Constructs an empty minimum or maximum heap
     * 
     * @param isMaxHeap
     *            Whether the heap is a maximum heap
     */
    public Heap(boolean isMaxHeap)
    {
        this.isMaxHeap = isMaxHeap;
    }

    /**
     * Creates a copy of the heap. Members of the heap will be shallow copied
     * from the copy heap. This constructor assumes that the copy heap is
     * already heapified.
     * 
     * @param copy
     *            The heap to be copied
     */
    public Heap(Heap<T> copy)
    {
        if (copy != null)
        {
            this.isMaxHeap = copy.isMaxHeap;
            this.array = new Object[copy.array.length];
            for (int i = 0; i < copy.array.length; i++)
                array[i] = copy.array[i];
        }
    }

    /**
     * Sets the heap type to a minimum heap and reorders appropriately
     */
    public void setHeapTypeMin()
    {
        setMinMax(false);
    }

    /**
     * Sets the heap type to a maximum heap and reorders appropriately
     */
    public void setHeapTypeMax()
    {
        setMinMax(true);
    }

    /**
     * Sets whether the heap is a minimum heap or a maximum heap and reorders
     * appropriately
     * 
     * @param isMaxHeap
     *            Whether the heap is a maximum heap
     */
    protected void setMinMax(boolean isMaxHeap)
    {
        this.isMaxHeap = isMaxHeap;
        heapify();
    }

    /**
     * The size of the heap
     * 
     * @return The number of elements in the heap
     */
    public int size()
    {
        return array.length;
    }

    /**
     * Whether the heap contains any elements
     * 
     * @return Whether the heap contains any elements
     */
    public boolean isEmpty()
    {
        return array.length == 0;
    }

    /**
     * Expose the underlying array for for demonstration
     * 
     * @return
     *         the array
     */
    public List<T> getList()
    {
        List<T> output = new ArrayList<T>();
        for (int i = 0; i < size(); i++)
            output.add(get(i));
        return output;
    }

    /**
     * Add an element to the heap
     * 
     * @param addition
     *            The element to be added to the heap
     */
    public void push(T addition)
    {
        Object[] temp = new Object[array.length + 1];
        temp[0] = addition;
        for (int i = 0; i < array.length; i++)
            temp[i + 1] = array[i];
        array = temp;
        heapify();
    }

    /**
     * Returns the most prioritized element of the heap
     * 
     * @return The most prioritized element
     */
    public T peek()
    {
        return get(0);
    }

    /**
     * Removes and returns the most prioritized element of the heap or null if
     * the heap is empty
     * 
     * @return The most prioritized element
     */
    public T pop()
    {
        if (array.length == 0)
            return null;
        T value = get(0);
        array[0] = array[array.length - 1];
        Object[] temp = new Object[array.length - 1];
        for (int i = 0; i < temp.length; i++)
            temp[i] = array[i];
        array = temp;
        heapify();
        return value;
    }

    public boolean contains(T test)
    {
        if (test == null)
        {
            for (int i = 0; i < array.length; i++)
                if (array[i] == null)
                    return true;
        }
        else
        {
            for (int i = 0; i < array.length; i++)
                if (test.equals(get(i)))
                    return true;
        }
        return false;
    }

    /**
     * Returns a cast element in the heap array
     * 
     * @param i
     *            the index of the element to be returned
     * @return the element in the array at index i
     */
    @SuppressWarnings("unchecked")
    protected T get(int i)
    {
        if (i < array.length)
            return (T)array[i];
        return null;
    }

    /**
     * Heapify the whole heap from the root
     */
    protected void heapify()
    {
        heapify(0);
    }

    /**
     * Heapify starting at node i
     * 
     * @param i
     *            Node at which to start heapification
     */
    protected void heapify(int i)
    {
        int left = 2 * i;      // Index of left child
        int right = 2 * i + 1; // Index of right child
        int prioritized = i;   // Index of whichever child is prioritized
        if (left < array.length && // Make sure the index is in-bounds
           (isMaxHeap ? 
                get(left).compareTo(get(prioritized)) > 0 : // Percolate greater than value for max heap
                get(left).compareTo(get(prioritized)) < 0)) // Percolate less than value for min heap
            prioritized = left;
        if (right < array.length && // Make sure the index is in-bounds
           (isMaxHeap ? 
                get(right).compareTo(get(prioritized)) > 0 : // Percolate greater than value for max heap
                get(right).compareTo(get(prioritized)) < 0)) // Percolate less than value for min heap
            prioritized = right;
        if (prioritized != i)
        {
            T swap = get(i);
            array[i] = array[prioritized];
            array[prioritized] = swap;
            heapify(prioritized); // Recursive call
        }
    }

    /**
     * Empty the heap
     */
    public void clear()
    {
        array = new Object[0];
    }

    public String toString()
    {
        String output = "";
        int line = 1;
        for (int i = 0; i < array.length; i++)
        {
            if (i == line - 1)
            {
                output += "\n";
                line *= 2;
            }
            output += (array[i] == null ? "null" : array[i].toString()) + (i < array.length - 1 ? ", " : "");
        }
        return output;
    }

}