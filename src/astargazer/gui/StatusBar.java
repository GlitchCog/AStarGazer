package astargazer.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Horizontal status bar to display message information on the left and coordinate information on the right
 * 
 * @author Matt Yanos
 */
public class StatusBar extends JPanel
{
    /**
     * The label that displays the message information
     */
    private JLabel message;

    /**
     * The label that displays the coordinate information
     */
    private JLabel pointLabel;

    /**
     * Construct a Status Bar
     */
    public StatusBar()
    {
        super(new GridBagLayout());

        this.pointLabel = new JLabel(" ");

        this.pointLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        this.message = new JLabel(" ", JLabel.LEFT);

        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        this.message.setFont(font);
        this.pointLabel.setFont(font);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        add(this.message, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx++;
        gbc.weightx = 0.0;
        add(this.pointLabel, gbc);
    }

    /**
     * Set the message
     * 
     * @param msg
     */
    public void setMessage(String msg)
    {
        message.setText(msg);
    }

    /**
     * Set the coordinate information
     * 
     * @param text
     */
    public void setPointLabelText(String text)
    {
        if (text == null || text.isEmpty())
        {
            text = " ";
        }
        pointLabel.setText(text);
    }

}
