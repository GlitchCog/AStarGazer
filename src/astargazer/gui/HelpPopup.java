package astargazer.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.tree.TreeSelectionModel;

import astargazer.map.heuristic.HeuristicScheme;
import astargazer.map.neighbor.NeighborSelector;

public class HelpPopup extends JDialog
{
    private JTree toc;

    private JTextPane contentPane;

    private JScrollPane contentScrollPane;

    private JEditorPane aboutPane;

    public static final String HTML_FONT_NAME = "Arial, Helvetica";

    private static final String ABOUT_CONTENTS = "<html><table bgcolor=#EEEEEE width=100% border=1><tr><td><center><font face=\"" + HTML_FONT_NAME + "\"><b>AStarGazer</b> is a program for visualizing<br />the A* search algorithm on a tile map<br /><br />By Matt Yanos<br /><br /><a href=\"www.github.com/GlitchCog/AStarGazer\">www.github.com/GlitchCog/AStarGazer</a></font></center></td></tr></table></html>";

    public HelpPopup(Window window)
    {
        super(window, "AStarGazer Information", true);

        constructAboutPopup();

        toc = new JTree(buildNodes());
        toc.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        TreeSelectionListener tsl = new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                HelpNode node = (HelpNode)toc.getLastSelectedPathComponent();
                if (node != null && node.getUserObject() != null)
                {
                    String header = node.getUserObject().toString();
                    String content = node instanceof HelpNode ? ((HelpNode)node).getHelpText() : header;
                    contentPane.setText( formatHtml(header, content) );
                    contentPane.setCaretPosition(0);
                }
            }
        };

        toc.addTreeSelectionListener(tsl);

        JScrollPane tocScrollPane = new JScrollPane(toc, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        contentPane = new JTextPane(new HTMLDocument());
        contentPane.setContentType("text/html");
        contentPane.setEditable(false);

        contentScrollPane = new JScrollPane(contentPane);

        JSplitPane splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, tocScrollPane, contentScrollPane );
        splitPane.setResizeWeight(0.20);
        splitPane.setContinuousLayout(true);
        splitPane.setDividerSize(4);

        add(splitPane);

        setSize(600, 400);
        setMinimumSize(getSize());
    }

    /**
     * Construct the popup dialog containing the About message
     */
    private void constructAboutPopup()
    {
        aboutPane = new JEditorPane("text/html", ABOUT_CONTENTS);
        aboutPane.addHyperlinkListener(new HyperlinkListener()
        {
            public void hyperlinkUpdate(HyperlinkEvent e)
            {
                if (EventType.ACTIVATED.equals(e.getEventType()))
                {
                    try
                    {
                        if (Desktop.isDesktopSupported())
                        {
                            Desktop.getDesktop().browse(new URI(e.getDescription()));
                        }
                    }
                    catch( IOException e1 )
                    {
                        e1.printStackTrace();
                    }
                    catch( URISyntaxException e1 )
                    {
                        e1.printStackTrace();
                    }
                }
            }
        });
        aboutPane.setEditable(false);
    }

    private String formatHtml(String header, String text)
    {
        String formattedText = "<html><font face=\"" + HTML_FONT_NAME + "\"><center><h2>" + header + "</h2></center><br />" +  text + "</font></html>";
        return formattedText;
    }

    private HelpNode buildNodes()
    {
        HelpNode top = new HelpNode("A* Search Algorithm", "The A* search algorithm is used to traverse a connection of nodes " + 
                                                           "called a graph. This implementation uses it to find a path between a starting " + 
                                                           "point and a goal point on a two-dimensional grid, a problem often encountered " + 
                                                           "in tile-based video game AI. It also displays the inner workings of the algorithm " + 
                                                           "by coloring the open and closed sets of nodes on the map and drawing a line " + 
                                                           "representing the current path being investigated.<br /><br />" + 
                                                           "<b>Open set</b> - The collection of nodes that have yet to be investigated<br />" + 
                                                           "<b>Closed set</b> - The collection of nodes that have already been investigated<br />" + 
                                                           "<b>Heuristic</b> - The method for calculating the cost of a node<br />" + 
                                                           "<b>Neighbor Selection</b> - The method for determining which nodes can be reached from the current node<br />" + 
                                                           "<br />" + 
                                                           "With each step, neighboring nodes are selected for analysis. If a neighbor is " + 
                                                           "considered a valid move, its cost is calculated. The cost (<i>f</i>) is the sum of two parts: <br />" + 
                                                           "<ul type=1>" + 
                                                           "<li>the cost already spent to get to the current node (<i>g</i>), which is the current location's " + 
                                                           "cost plus whatever incremental cost is required to get to the neighboring node; and </li>" + 
                                                           "<li>the estimated cost (<i>h</i>) to get to the goal.</li>" + 
                                                           "</ul>" + 
                                                           "All these neighbors are added to the open set, which is the collection of nodes to be investigated, " + 
                                                           "and the current location is added to the closed set, as it has already been analyzed. " + 
                                                           "From all the points stored on the open set, the one with the lowest cost is investigated next.<br /><br />" + 
                                                           "The A* search algorithm can be used to search through graphs that are not representations " + 
                                                           "of physical space. A common example is the sliding number puzzle where 8 or 15 numbers are " + 
                                                           "stored in a square grid. The numbers can be moved using the single empty spot, and each arrangement of " + 
                                                           "puzzle pieces can be considered a node in the graph with an associated heuristic of the number of moves " + 
                                                           "taken so far added to the estimated distance the numbers are from their ordered locations.<br />"
                                   );
        HelpNode heuristicNode = new HelpNode("Heuristics", "A heuristic defines how the distance between two points be evaluated in the algorithm.<br /><br />" + 
                                                            "The calculation for the cost of a node coming from the starting position and going to the goal position is: <br />" + 
                                                            "<b>f</b> (<i>total cost</i>) = <b>g</b> (<i>from cost</i>) + <b>h</b> (<i>estimated to cost</i>)<br /><br />" + 
                                                            "If the estimated cost of reaching the goal from the present location is set to zero, the A* search " + 
                                                            "algorithm becomes the more general Dijkstra's algorithm, which simply investigates all possible paths " + 
                                                            "to find the optimal solution based on the shortest distance traveled from the starting position."
                                             );
        HelpNode neighborNode = new HelpNode("Neighbor Selection", "A neighbor selection scheme determines the method by which neighboring tiles are chosen. " + 
                                                                   "The type of neighbor selection implies how the graph can be traversed, for example, whether " + 
                                                                   "tiles diagonal from the current location can be reached or whether multiple tiles " + 
                                                                   "can be cleared in a single step. The neighbor selection scheme can also take into account obstacles, " + 
                                                                   "not returning neighbors that represent impassible terrain."
                                            );

        top.add(heuristicNode);
        top.add(neighborNode);

        HeuristicScheme[] heuristics = HeuristicScheme.getAllHeuristics();
        NeighborSelector[] neighborSelectors = NeighborSelector.getAllNeighborSelectors();

        for (int i = 0; i < heuristics.length; i++)
        {
            heuristicNode.add(new HelpNode(heuristics[i].getLabel(), heuristics[i].getExplanation()));
        }

        for (int i = 0; i < neighborSelectors.length; i++)
        {
            neighborNode.add(new HelpNode(neighborSelectors[i].getLabel(), neighborSelectors[i].getExplanation()));
        }

        return top;
    }

    public void showAbout(JFrame parent)
    {
        JOptionPane.showMessageDialog(parent, aboutPane, "About", JOptionPane.PLAIN_MESSAGE);
    }
}