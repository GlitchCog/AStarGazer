package astargazer.gui.component;

import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
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
        super(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);

        for (int i = 0; i < dropdowns.length; i++)
        {
            dropdowns[i].addActionListener(dal);

            gbc.anchor = GridBagConstraints.EAST;
            gbc.gridx = 0;
            add( new JLabel( dropdowns[i].getLabel() + ": " ), gbc );

            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridx = 1;
            add(dropdowns[i], gbc);
            gbc.gridy++;
        }

        this.dropdowns = dropdowns;
    }

    public Dropdown[] getDropdowns()
    {
        return dropdowns;
    }
}
