package astargazer.gui.component;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import astargazer.PathFinder;
import astargazer.map.WeightedPoint;

/**
 * A panel to display information about the algorithm's process
 * 
 * @author Matt Yanos
 */
public class InfoPanel extends JPanel
{
    /**
     * Holds all the text to be displayed
     */
    private JLabel info;

    /**
     * Formatter for displaying floats on the info panel
     */
    DecimalFormat formatter;

    /**
     * Constructs an InfoPanel to display information about the algorithm's process
     * 
     * @param pf
     */
    public InfoPanel(PathFinder pf)
    {
        super(new GridBagLayout());
        formatter = new DecimalFormat("00.00");
        info = new JLabel("Info", JLabel.LEFT);
        info.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
        add(info, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, 
                                         GridBagConstraints.NORTHWEST, 
                                         GridBagConstraints.HORIZONTAL, 
                                         new Insets(0, 0, 0, 0), 
                                         0, 0));
         updateStats(pf);
    }

    /**
     * Update the information being displayed
     * 
     * @param pf
     */
    public void updateStats(PathFinder pf)
    {
        String toCost = "";
        String fromCost = "";
        String totalCost = "";

        if (pf.getCursor() != null)
        {
            toCost = formatter.format(pf.getCursor().getToCost());
            fromCost = formatter.format(pf.getCursor().getFromCost());
            totalCost = formatter.format(pf.getCursor().getCost());
        }

        String text = "<html><pre>";
        text += WeightedPoint.toLabeledString("Start", pf.getStart()) + "<br>";
        text += WeightedPoint.toLabeledString("Goal", pf.getGoal()) + "<br>";
        text += WeightedPoint.toLabeledString("Cursor", pf.getCursor()) + "<br>";
        text += "Open Set: " + pf.getOpenSet().size() + " points<br>";
        text += "Closed Set: " + pf.getClosedSet().size() + " points<br>";
        text += "Cursor Cost (f=g+h): <br>" + 
        " g (from) =  " + fromCost + "<br>" + 
        " h (to) =    " + toCost + "<br>" + 
        " f (total) = " + totalCost + "<br>";
        text += "</pre></html>";
        info.setText(text);
    }

}