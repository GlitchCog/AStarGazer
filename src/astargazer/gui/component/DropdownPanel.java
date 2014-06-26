package astargazer.gui.component;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A panel of dropdown menus
 * 
 * @author Matt Yanos
 */
public class DropdownPanel extends JPanel
{
    private Dropdown[] dropdowns;

    public DropdownPanel(Dropdown[] dropdowns, ActionListener dal)
    {
        super(new GridLayout(dropdowns.length * 2, 1));

        for (int i = 0; i < dropdowns.length; i++)
        {
            add( new JLabel( dropdowns[i].getLabel() + ": " ) );
            dropdowns[i].addActionListener(dal);
            add(dropdowns[i]);
        }

        this.dropdowns = dropdowns;
    }

    public Dropdown[] getDropdowns()
    {
        return dropdowns;
    }
}