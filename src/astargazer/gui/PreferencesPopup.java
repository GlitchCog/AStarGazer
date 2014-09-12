package astargazer.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import astargazer.PathFinder;
import astargazer.gui.component.ColorButton;
import astargazer.map.TileMap;
import astargazer.map.WeightedPoint;
import astargazer.map.heuristic.HeuristicEuclidean;
import astargazer.map.neighbor.NeighborEightDirections;

/**
 * Preferences Popup dialog to edit the custom color scheme
 * 
 * @author Matt Yanos
 */
public class PreferencesPopup extends JDialog
{
    /**
     * The example map panel, to show the color selections
     */
    private MapPanel example;

    /**
     * The toolbox panel, used to set the scheme to custom on save
     */
    private ToolboxPanel toolboxPanel;

    /**
     * The temporary color scheme reference for storing the color selections prior to saving
     */
    private ColorScheme scheme;

    /**
     * The button to save the temporary scheme values to the CUSTOM_SCHEME variable and close the popup
     */
    private JButton save;

    /**
     * The button to discard changes to the temporary scheme and close the popup
     */
    private JButton cancel;

    /**
     * The buttons used to select colors for the temporary scheme
     */
    private ColorButton[] colorButtons;

    /**
     * Construct a PreferencesPopup window
     * 
     * @param window
     * @param mapPanel
     */
    public PreferencesPopup(Window window, ToolboxPanel toolboxPanel)
    {
        super(window, "Set Custom Colors", true);

        setLocationRelativeTo(window);

        this.toolboxPanel = toolboxPanel;

        scheme = new ColorScheme(ColorScheme.CUSTOM_SCHEME.toString(), ColorScheme.CUSTOM_SCHEME);

        loadExampleMap();

        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);

        JPanel content = new JPanel(new GridBagLayout());

        JPanel buttonBar = new JPanel(new GridBagLayout());

        colorButtons = new ColorButton[ColorScheme.COLOR_LABELS.length];

        for (int i = 0; i < colorButtons.length; i++)
        {
            String colorLabel = ColorScheme.COLOR_LABELS[i];
            final ColorButton cb = new ColorButton(colorLabel, scheme.get(colorLabel));
            colorButtons[i] = cb;

            ActionListener al = new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    Color selection = JColorChooser.showDialog(getParent(), "Select Color", scheme.get(cb.getLabel()));
                    if (selection != null)
                    {
                        scheme.setColor(cb.getLabel(), selection);
                        cb.setColor(selection);
                    }
                    example.repaint();
                }
            };

            cb.addActionListener(al);
            buttonBar.add(cb, gbc);
            gbc.gridy++;
        }

        save = new JButton("Save");
        cancel = new JButton("Cancel");

        ActionListener saveCancelListener = new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (save.equals(e.getSource()))
                {
                    saveScheme();
                }
                else if (cancel.equals(e.getSource()))
                {
                    loadScheme();
                }
                setVisible(false);
            }
        };

        save.addActionListener(saveCancelListener);
        cancel.addActionListener(saveCancelListener);

        JPanel saveCancelPanel = new JPanel();
        saveCancelPanel.add(save);
        saveCancelPanel.add(cancel);

        buttonBar.add(saveCancelPanel, gbc);

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 0.0f;
        content.add(buttonBar, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0f;
        gbc.weighty = 1.0f;
        content.add(example, gbc);

        add(new JScrollPane(content));

        pack();
        setSize(640, getHeight());
        setMinimumSize(getSize());
        setResizable(false);
    }

    @Override
    public void setVisible(boolean visible)
    {
        loadScheme();
        super.setVisible(visible);
    }

    private void loadExampleMap()
    {
        int[][] mapData = new int[][] { {1, 1, 1, 1, 1, 1, 1, 1}, 
                                        {1, 0, 0, 0, 0, 0, 0, 1}, 
                                        {1, 0, 0, 0, 0, 0, 0, 1}, 
                                        {1, 0, 0, 1, 1, 0, 0, 1}, 
                                        {1, 0, 1, 1, 1, 1, 0, 1}, 
                                        {1, 0, 0, 1, 1, 0, 0, 1}, 
                                        {1, 0, 0, 0, 0, 0, 0, 1}, 
                                        {1, 0, 0, 0, 0, 0, 0, 1}, 
                                        {1, 1, 1, 1, 1, 1, 1, 1} };
        TileMap exampleMap = new TileMap(mapData, new WeightedPoint(1, 1), new WeightedPoint(mapData.length - 2, mapData[0].length - 2), 0);
        PathFinder examplePathFinder = new PathFinder(exampleMap);
        examplePathFinder.setHeuristic(new HeuristicEuclidean());
        examplePathFinder.setNeighborSelector(new NeighborEightDirections());
        examplePathFinder.solve();
        example = new MapPanel(examplePathFinder, null);
        example.setDisplayGrid(true);
        example.setTileSize(32);
        example.setColorScheme(scheme);
    }

    private void updateButtonColors()
    {
        for (int i = 0; i < colorButtons.length; i++)
        {
            colorButtons[i].setColor(scheme.get(ColorScheme.COLOR_LABELS[i]));
        }
    }

    private void loadScheme()
    {
        scheme.setColors(ColorScheme.CUSTOM_SCHEME);
        updateButtonColors();
    }

    private void saveScheme()
    {
        ColorScheme.CUSTOM_SCHEME.setColors(scheme);
        toolboxPanel.setColorScheme(ColorScheme.CUSTOM_SCHEME);
    }

}
