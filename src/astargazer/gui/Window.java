package astargazer.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import astargazer.PathFinder;
import astargazer.map.generator.MapManager;
import astargazer.map.TileMap;

/**
 * The main window of the A* Gazer Algorithm Visualizer
 * 
 * @author Matt Yanos
 */
public class Window extends JFrame
{
    /**
     * Popup window that displays help information and handles calls to the About menu option
     */
    private HelpPopup helpPopup;

    /**
     * Panel of tools that is displayed on the left side of this window
     */
    private ToolboxPanel toolPanel;

    /**
     * Horizontal bar to display information below the map panel
     */
    private StatusBar statusBar;

    /**
     * The panel that displays the tilemap and the algorithm visualizations
     */
    private MapPanel mapPanel;

    /**
     * The PathFinder runs the algorithm
     */
    private PathFinder pathFinder;

    /**
     * Construct the main window
     */
    public Window()
    {
        TileMap map = MapManager.getInstance().generate(false);

        this.pathFinder = new PathFinder(map);

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

        everything.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e)
            {
                toolPanel.incrementZoom(-e.getWheelRotation());
            }
        });

        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);

        statusBar = new StatusBar();

        mapPanel = new MapPanel(pathFinder, statusBar);

        toolPanel = new ToolboxPanel(mapPanel, pathFinder);

        JPanel rightPanel = new JPanel(new GridBagLayout());

        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        rightPanel.add(mapPanel, gbc);

        gbc.gridy++;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        rightPanel.add(statusBar, gbc);

        gbc.weightx = 0.0f;
        gbc.weighty = 0.0f;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        everything.add(toolPanel, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx++;
        everything.add(rightPanel, gbc);

        add(everything);

        setMinimumSize(new Dimension(800, 670));
        setSize(1024, 768);

        setJMenuBar(new Menu(this));

        setVisible(true);
    }

    /**
     * Display the popup to input a new seed value
     */
    public void showSeedInput()
    {
        String seedStr = JOptionPane.showInputDialog(this, "Map Seed: ", pathFinder.getSeed());
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
            toolPanel.regenerateMap(seed);
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
