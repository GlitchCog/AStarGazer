package astargazer.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

import astargazer.PathFinder;
import astargazer.map.MapGenerator;
import astargazer.map.TileMap;

/**
 * The main window of the A* Gazer Algorithm Visualizer
 * 
 * @author Matt Yanos
 */
public class Window extends JFrame
{
    /**
     * The panel that displays the tilemap and the algorithm visualizations
     */
    private MapPanel mapPanel;

    /**
     * The PathFinder runs the algorithm
     */
    private PathFinder pf;

    /**
     * Construct the main window
     */
    public Window()
    {
        TileMap map = MapGenerator.getInstance().generate(false);

        this.pf = new PathFinder(map);
        buildGui();
    }

    /**
     * Build the GUI
     */
    private void buildGui()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("A* Gazer");

        JPanel everything = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);

        mapPanel = new MapPanel(pf);
        JPanel sidepanel = new ToolboxPanel(mapPanel, pf);

        gbc.weighty = 0.0f;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        everything.add(sidepanel, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        everything.add(mapPanel, gbc);

        add(everything);

        pack();
        setMinimumSize(getSize());

        setSize(1024, 768);

        setVisible(true);
    }

}