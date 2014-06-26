package astargazer.gui.component;

import javax.swing.JSlider;

/**
 * A slider with a label
 * 
 * @author Matt Yanos
 */
public class Slider extends JSlider
{
    private String label;

    public Slider(String label, int min, int max, int value)
    {
        super(min, max, value);
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }

}