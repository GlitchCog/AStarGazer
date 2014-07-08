package astargazer.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    private HelpPopup helpPopup;

    private ToolboxPanel sidePanel;

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

        helpPopup = new HelpPopup(this);

        JPanel everything = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);

        mapPanel = new MapPanel(pf);

        sidePanel = new ToolboxPanel(mapPanel, pf);

        gbc.weighty = 0.0f;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        everything.add(sidePanel, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        everything.add(mapPanel, gbc);

        add(everything);

        setMinimumSize(new Dimension(800, 800));
        setSize(1024, 800);

        setJMenuBar(new Menu(this));

        setVisible(true);
    }

    /**
     * Display the popup to input a new seed value
     */
    public void showSeedInput()
    {
        String seedStr = JOptionPane.showInputDialog(this, "Map Seed: ", pf.getSeed());
        int seed;
        if (seedStr != null)
        {
            try
            {
                seed = Integer.parseInt(seedStr);
            }
            catch (Exception e)
            {
                seed = seedStr.trim().toUpperCase().hashCode();
            }
            sidePanel.regenerateMap(seed);
        }
    }

    public void showInformation()
    {
        helpPopup.setVisible(true);
    }

    public void showAbout()
    {
        helpPopup.showAbout(this);
    }
}
