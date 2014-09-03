package astargazer.gui.component;

import java.awt.GridLayout;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * A panel of checkboxes
 * 
 * @author Matt Yanos
 */
public class CheckboxPanel extends JPanel
{
    private JCheckBox[] checkboxes;

    public CheckboxPanel(String[] checkboxText, ItemListener il)
    {
        super(new GridLayout(checkboxText.length, 1));
        checkboxes = new JCheckBox[checkboxText.length];

        for (int i = 0; i < checkboxes.length; i++)
        {
            checkboxes[i] = new JCheckBox(checkboxText[i]);
            checkboxes[i].addItemListener(il);
            add(checkboxes[i]);
        }
    }
}