package astargazer.gui.component;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorButton extends JPanel
{
    private JLabel label;

    private JButton button;

    public ColorButton(String label, Color value)
    {
        super();
        this.button = new JButton();
        if (label == null)
            label = "";
        this.label = new JLabel(label);
        if (value == null)
            value = Color.BLACK;
        setColor(value);
        button.setBackground(value);
        value = Color.BLACK;

        add(this.label);
        add(this.button);
    }

    public Color getColor()
    {
        return button.getBackground();
    }

    public void setColor(Color value)
    {
        button.setBackground(value);
    }

    public String getLabel()
    {
        return label.getText();
    }

    public void addActionListener(ActionListener al)
    {
        button.addActionListener(al);
    }

}
