package astargazer.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import astargazer.PathFinder;
import astargazer.StatusEnum;
import astargazer.gui.component.ButtonPanel;
import astargazer.gui.component.CheckboxPanel;
import astargazer.gui.component.Dropdown;
import astargazer.gui.component.DropdownPanel;
import astargazer.gui.component.InfoPanel;
import astargazer.gui.component.Slider;
import astargazer.gui.component.SliderPanel;
import astargazer.map.generator.MapGenerator;
import astargazer.map.generator.MapManager;
import astargazer.map.TileMap;
import astargazer.map.heuristic.HeuristicScheme;
import astargazer.map.neighbor.NeighborSelector;

/**
 * The toolbox GUI component for interacting with the algorithm
 * 
 * @author Matt Yanos
 */
public class ToolboxPanel extends JPanel
{
    /**
     * The display panel for the map
     */
    private MapPanel mp;

    /**
     * The PathFinder runs the algorithm
     */
    private PathFinder pf;

    private final String BUTTON_TEXT_GENERATE = "Generate";
    private final String BUTTON_TEXT_STEP = "Step";
    private final String BUTTON_TEXT_SOLVE = "Solve";
    private final String BUTTON_TEXT_RESET = "Reset";

    private final String DROPDOWN_TEXT_HEURISTICS = "Heuristics";
    private final String DROPDOWN_TEXT_NEIGHBORS = "Neighbors";
    private final String DROPDOWN_TEXT_COLORS = "Colors";
    private final String DROPDOWN_TEXT_OBSTACLES = "Obstacles";

    private final String SLIDER_TEXT_SPEED = "Solve Delay";
    private final String SLIDER_TEXT_SIZE = "Zoom";

    private final String CHECKBOX_TEXT_DIJKSTRA = "Full Dijkstra Search (h=0)";
    private final String CHECKBOX_TEXT_RANDOMIZE = "Randomize Equicost Nodes";
    private final String CHECKBOX_TEXT_GRID = "Show Grid";
    private final String CHECKBOX_TEXT_SWAP = "Swap Start and Goal Points";

    /**
     * The slider to zoom in or out of the map panel, made a member so the scroll wheel listener can modify it
     */
    private Slider zoomSlider;

    /**
     * Timer to increment the steps in the algorithm
     */
    private Timer solveTimer;

    /**
     * The panel of buttons
     */
    private ButtonPanel buttonPanel;

    /**
     * The panel of dropdown menus
     */
    private DropdownPanel dropdownPanel;

    /**
     * The panel of sliders
     */
    private SliderPanel sliderPanel;

    /**
     * The panel of checkboxes
     */
    private CheckboxPanel checkboxPanel;

    /**
     * The panel for displaying information about the algorithm
     */
    private InfoPanel infoPanel;

    /**
     * Construct a ToolboxPanel for the specified MapPanel and PathFinder
     * 
     * @param mp MapPanel
     * @param pf PathFinder
     */
    public ToolboxPanel(MapPanel mp, PathFinder pf)
    {
        this.mp = mp;
        this.pf = pf;
        buildGui();
    }

    /**
     * Build the GUI
     */
    private void buildGui()
    {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 8, 8, 8), 0, 0);

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                if (BUTTON_TEXT_GENERATE.equals( ((JButton)e.getSource()).getText()) )
                {
                    regenerateMap(true);
                }
                else if (BUTTON_TEXT_STEP.equals( ((JButton)e.getSource()).getText()) )
                {
                    pf.step();
                }
                else if (BUTTON_TEXT_SOLVE.equals( ((JButton)e.getSource()).getText()) )
                {
                    if (solveTimer.isRunning())
                    {
                        solveTimer.stop();
                    }
                    else
                    {
                        if (pf.getStatus() != StatusEnum.RUNNING)
                            pf.reset();
                        solveTimer.start();
                    }
                }
                else if (BUTTON_TEXT_RESET.equals( ((JButton)e.getSource()).getText()) )
                {
                    pf.reset();
                    solveTimer.stop();
                }
                mp.updateDrawing();
                infoPanel.updateStats(pf);
            }
        };

        buttonPanel = new ButtonPanel(new String[] {BUTTON_TEXT_STEP, 
                                                    BUTTON_TEXT_SOLVE, 
                                                    BUTTON_TEXT_RESET,
                                                    BUTTON_TEXT_GENERATE}, 
                                      al);

        solveTimer = new Timer(2, new ActionListener(){
            public void actionPerformed( ActionEvent e )
            {
                pf.step();
                mp.updateDrawing();
                infoPanel.updateStats(pf);
                if (pf.getStatus() != StatusEnum.RUNNING)
                    solveTimer.stop();
            }
        });

        Dropdown[] dropdowns = new Dropdown[4];

        ActionListener dal = new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                Dropdown d = ((Dropdown)e.getSource());
                if (DROPDOWN_TEXT_HEURISTICS.equals(d.getLabel()))
                {
                    pf.setHeuristic((HeuristicScheme)d.getSelectedItem());
                }
                else if (DROPDOWN_TEXT_NEIGHBORS.equals(d.getLabel()))
                {
                    pf.setNeighborSelector((NeighborSelector)d.getSelectedItem());
                }
                else if (DROPDOWN_TEXT_COLORS.equals(d.getLabel()))
                {
                    mp.setColorScheme((ColorScheme)d.getSelectedItem());
                    mp.updateDrawing();
                }
                else if (DROPDOWN_TEXT_OBSTACLES.equals(d.getLabel()))
                {
                    MapManager.getInstance().setGenerator((MapGenerator)d.getSelectedItem());
                    regenerateMap(false);
                }
            }
        };

        dropdowns[0] = new Dropdown(DROPDOWN_TEXT_HEURISTICS, HeuristicScheme.getAllHeuristics() );
        dropdowns[1] = new Dropdown(DROPDOWN_TEXT_NEIGHBORS, NeighborSelector.getAllNeighborSelectors() );
        dropdowns[2] = new Dropdown(DROPDOWN_TEXT_COLORS, ColorScheme.SCHEMES);
        dropdowns[3] = new Dropdown(DROPDOWN_TEXT_OBSTACLES, MapGenerator.getAllGenerators() );

        dropdownPanel = new DropdownPanel(dropdowns, dal);

        pf.setHeuristic((HeuristicScheme)(dropdownPanel.getDropdowns()[0].getSelectedItem()));
        pf.setNeighborSelector((NeighborSelector)(dropdownPanel.getDropdowns()[1].getSelectedItem()));

        Slider[] sliders = new Slider[2];

        ChangeListener cl = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                Slider s = (Slider)e.getSource();
                if (SLIDER_TEXT_SPEED.equals(s.getLabel()))
                {
                    setSolveDelay(s.getValue());
                }
                else if (SLIDER_TEXT_SIZE.equals(s.getLabel()))
                {
                    mp.setTileSize(s.getValue());
                    mp.enforceBoundaries();
                    mp.updateDrawing();
                }
            }
        };

        sliders[0] = new Slider(SLIDER_TEXT_SPEED, 0, 1000, 0);
        zoomSlider = new Slider(SLIDER_TEXT_SIZE, 10, 32, 16);
        sliders[1] = zoomSlider;

        setSolveDelay(sliders[0].getValue());
        mp.setTileSize(sliders[1].getValue());

        sliderPanel = new SliderPanel(sliders, cl);

        ItemListener il = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                JCheckBox cb = (JCheckBox)e.getSource();
                if (CHECKBOX_TEXT_DIJKSTRA.equals(cb.getText()))
                {
                    pf.setDijkstra(cb.isSelected());
                }
                else if (CHECKBOX_TEXT_RANDOMIZE.equals(cb.getText()))
                {
                    pf.setShuffle(cb.isSelected());
                }
                else if (CHECKBOX_TEXT_GRID.equals(cb.getText()))
                {
                    mp.setDisplayGrid(cb.isSelected());
                    mp.updateDrawing();
                }
                else if (CHECKBOX_TEXT_SWAP.equals(cb.getText()))
                {
                    TileMap.setEndPointSwap(cb.isSelected());
                    mp.updateDrawing();
                }
            }
        };

        checkboxPanel = new CheckboxPanel(new String[] {CHECKBOX_TEXT_DIJKSTRA, 
                                                        CHECKBOX_TEXT_RANDOMIZE, 
                                                        CHECKBOX_TEXT_GRID, 
                                                        CHECKBOX_TEXT_SWAP}, 
                                                        il);

        infoPanel = new InfoPanel(pf);

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(buttonPanel, gbc);
        gbc.gridy++;
        add(dropdownPanel, gbc);
        gbc.gridy++;
        add(sliderPanel, gbc);
        gbc.gridy++;
        add(checkboxPanel, gbc);
        gbc.gridy++;
        gbc.weighty = 1.0f;
        add(infoPanel, gbc);

    }

    /**
     * Set the solve delay time
     * 
     * @param delay
     */
    private void setSolveDelay(int delay)
    {
        solveTimer.setDelay(delay);
    }

    /**
     * Randomly generate a tilemap
     * 
     * @param reseed
     */
    private void regenerateMap(boolean reseed)
    {
        pf.reset(MapManager.getInstance().generate(reseed));
        mp.centerMap();
        mp.updateDrawing();
        infoPanel.updateStats(pf);
    }

    /**
     * Randomly generate a tilemap using the specified random number generator seed
     * 
     * @param seed
     */
    public void regenerateMap(int seed)
    {
        pf.reset(MapManager.getInstance().generate(seed));
        mp.updateDrawing();
        infoPanel.updateStats(pf);
    }

    /**
     * Increment the zoom
     * 
     * @param amt
     */
    public void incrementZoom(int amt)
    {
        int newValue = zoomSlider.getValue() + amt;
        if (newValue >= zoomSlider.getMinimum() && newValue <= zoomSlider.getMaximum())
        {
            zoomSlider.setValue(newValue);
        }
    }

}
