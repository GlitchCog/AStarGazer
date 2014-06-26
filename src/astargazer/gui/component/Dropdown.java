package astargazer.gui.component;

import javax.swing.JComboBox;

/**
 * A dropdown menu with an associated label
 * 
 * @author Matt Yanos
 */
public class Dropdown extends JComboBox
{
    private String label;

    public Dropdown(String label, Object[] list)
    {
        super(list);
        this.label = label;
        this.setEditable(false);
    }

    public String getLabel()
    {
        return label;
    }

}