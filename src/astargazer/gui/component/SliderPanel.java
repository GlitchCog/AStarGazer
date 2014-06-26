package astargazer.gui.component;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

/**
 * A panel of sliders
 * 
 * @author Matt Yanos
 */
public class SliderPanel extends JPanel
{
    private Slider[] sliders;

    public SliderPanel(Slider[] sliders, ChangeListener cl)
    {
        super(new GridLayout(sliders.length * 2, 1));

        for (int i = 0; i < sliders.length; i++)
        {
            add( new JLabel( sliders[i].getLabel() + ": " ) );
            sliders[i].addChangeListener(cl);
            add(sliders[i]);
        }

        this.sliders = sliders;
    }

    public Slider[] getSliders()
    {
        return sliders;
    }
}