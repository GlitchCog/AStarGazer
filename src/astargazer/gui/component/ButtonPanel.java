package astargazer.gui.component;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A panel of buttons
 * 
 * @author Matt Yanos
 */
public class ButtonPanel extends JPanel
{
    private JButton[] buttons;

    public ButtonPanel(String[] buttonText, ActionListener al)
    {
        super(new GridLayout(buttonText.length, 1));
        buttons = new JButton[buttonText.length];

        for (int i = 0; i < buttons.length; i++)
        {
            buttons[i] = new JButton(buttonText[i]);
            buttons[i].addActionListener(al);
            add(buttons[i]);
        }
    }

    public JButton[] getButtons()
    {
        return buttons;
    }
}