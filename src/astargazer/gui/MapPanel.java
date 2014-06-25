package astargazer.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import astargazer.PathFinder;
import astargazer.map.TileMap;
import astargazer.map.WeightedPoint;

/**
 * A visual representation of a map being traversed by the A* path finding algorithm
 * 
 * @author Matt Yanos
 */
public class MapPanel extends JPanel implements MouseInputListener
{
    /**
     * The PathFinder that determines the path between the start and the goal points on the tilemap
     */
    private PathFinder pf;

    /**
     * How wide to draw the tiles
     */
    private int tileWidth;

    /**
     * How tall to draw the tiles
     */
    private int tileHeight;

    /**
     * Tile that the mouse is currently hovering over
     */
    private WeightedPoint selectedTile;

    /**
     * The color scheme to use when drawing the tilemap
     */
    private ColorScheme colorScheme;

    /**
     * Whether to display the grid
     */
    private boolean displayGrid;

    /**
     * The stroke with which to draw the lines of the tilemap
     */
    private Stroke stroke;

    /**
     * Construct a MapPanel for the specified PathFinder
     * 
     * @param pf PathFinder
     */
    public MapPanel(PathFinder pf)
    {
        this(pf, 12, 12, ColorScheme.SCHEMES[0]);
    }

    /**
     * Construct a MapPanel for the specified PathFinder with specified tile width, tile height, and color scheme
     * 
     * @param pf PathFinder
     * @param tileWidth
     * @param tileHeight
     * @param colorScheme
     */
    public MapPanel(PathFinder pf, int tileWidth, int tileHeight, ColorScheme colorScheme)
    {
        this.pf = pf;

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        this.stroke = new BasicStroke(4.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        setColorScheme(colorScheme);

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    /**
     * Get the offset distance for the labels on the axes
     * 
     * @return
     */
    private int getAxisLabelOffset()
    {
        return Math.max(tileWidth, tileHeight);
    }

    /**
     * Set the size (both width and height) of the tiles to be drawn on the tilemap
     * 
     * @param size
     */
    public void setTileSize(int size)
    {
        this.tileWidth = size;
        this.tileHeight = size;
    }

    /**
     * Set the color scheme for the display of the tilemap
     * 
     * @param colorScheme
     */
    public void setColorScheme(ColorScheme colorScheme)
    {
        this.colorScheme = colorScheme;
    }

    public void setDisplayGrid(boolean displayGrid)
    {
        this.displayGrid = displayGrid;
    }

    /**
     * Update the image of the tilemap
     */
    public void updateDrawing()
    {
        repaint();
    }

    /**
     * Get the X coordinate of the upper left corner of the tilemap
     * 
     * @return x
     */
    private int getXCoor()
    {
        return (getWidth() - (pf.getMap().getCols() * tileWidth + getAxisLabelOffset())) / 2;
    }

    /**
     * Get the Y coordinate of the upper left corner of the tilemap
     * 
     * @return y
     */
    private int getYCoor()
    {
        return (getHeight() - (pf.getMap().getRows() * tileHeight + getAxisLabelOffset())) / 2;
    }

    public void paint(Graphics g)
    {
        g.setColor(colorScheme.background);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (pf == null)
        {
            return;
        }

        Graphics2D g2d = (Graphics2D)g;

        int x = getXCoor();
        int y = getYCoor();

        if (displayGrid)
        {
            paintGrid(g, x, y);
        }

        paintVisualization(g2d, x, y);

        paintMap(g, x, y);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(stroke);

        if (pf.getCursor() != null)
        {
            paintPath(g2d, x, y);
        }

        drawPoint(g2d, pf.getStart(), colorScheme.start, x, y, true);
        drawPoint(g2d, pf.getStart(), colorScheme.startBorder, x, y, false);
        drawPoint(g2d, pf.getGoal(), colorScheme.goal, x, y, true);
        drawPoint(g2d, pf.getGoal(), colorScheme.goalBorder, x, y, false);

        if (pf.getCursor() != null)
        {
            drawPoint(g2d, pf.getCursor(), colorScheme.cursor, x, y, false);
        }

        if (selectedTile != null)
        {
            paintStats(g2d);
        }
    }

    /**
     * Draw the grid for the tiles to be laid out on
     * 
     * @param g
     * @param x the X coordinate of the upper left corner of the tilemap
     * @param y the Y coordinate of the upper left corner of the tilemap
     */
    private void paintGrid(Graphics g, int x, int y)
    {
        g.setColor(colorScheme.grid);
        for (int row = 0; row < pf.getMap().getRows(); row++)
        {
            for (int col = 0; col < pf.getMap().getCols(); col++)
            {
                g.drawRect(x + col * tileWidth + getAxisLabelOffset(), y + row * tileHeight + getAxisLabelOffset(), tileWidth, tileHeight);
            }
        }

    }

    /**
     * Draw the open and closed sets of the A* algorithm as it processes
     * 
     * @param g2d
     * @param x the X coordinate of the upper left corner of the tilemap
     * @param y the Y coordinate of the upper left corner of the tilemap
     */
    private void paintVisualization(Graphics2D g2d, int x, int y)
    {
        List<WeightedPoint> openSet = pf.getOpenSet().getList();
        for (WeightedPoint wp : openSet)
        {
            fillTile(g2d, wp, colorScheme.open, colorScheme.openBorder, x, y);
        }
        for (WeightedPoint wp : pf.getClosedSet())
        {
            fillTile(g2d, wp, colorScheme.closed, colorScheme.closedBorder, x, y);
        }
    }

    /**
     * Draw the tilemap
     * 
     * @param g
     * @param x the X coordinate of the upper left corner of the tilemap
     * @param y the Y coordinate of the upper left corner of the tilemap
     */
    private void paintMap(Graphics g, int x, int y)
    {
        TileMap map = pf.getMap();

        int textSize = Math.min(tileWidth, tileHeight);
        textSize = textSize / 4 + 4;
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, textSize));

        for (int row = 0; row < map.getRows(); row++)
        {
            g.setColor(colorScheme.text);
            String str = "" + Integer.toHexString(row).toUpperCase();
            while (str.length() < 2) str = '0' + str;
            g.drawString(str, x + (getAxisLabelOffset() - g.getFontMetrics().charsWidth(str.toCharArray(), 0, str.length())) / 2, 
                         y + getAxisLabelOffset() + (row * tileHeight) + (g.getFontMetrics().getHeight() + tileHeight) / 2);
            for (int col = 0; col < map.getCols(); col++)
            {
                if (row == 0)
                {
                    g.setColor(colorScheme.text);
                    str = "" + Integer.toHexString(col).toUpperCase();
                    while (str.length() < 2) str = '0' + str;
                    g.drawString(str, x + getAxisLabelOffset() + col * tileWidth + (tileWidth - g.getFontMetrics().charsWidth(str.toCharArray(), 0, str.length())) / 2, 
                                      y + (g.getFontMetrics().getHeight() + getAxisLabelOffset()) / 2);
                }
                if (!map.isTraversable(row, col))
                {
                    drawBlock(g, x, y, row, col);
                }
            }
        }
        g.setColor(colorScheme.grid); // Draw outer border
        g.drawRect(x, y, pf.getMap().getCols() * tileWidth + getAxisLabelOffset(), pf.getMap().getRows() * tileHeight + getAxisLabelOffset());
    }

    /**
     * Draw the path being tested as the algorithm runs or the final path after the algorithm has finished
     * 
     * @param g2d
     * @param x the X coordinate of the upper left corner of the tilemap
     * @param y the Y coordinate of the upper left corner of the tilemap
     */
    private void paintPath(Graphics2D g2d, int x, int y)
    {
        List<WeightedPoint> path = pf.getPath(pf.getCursor());
        WeightedPoint prev = null;
        g2d.setColor(colorScheme.path);
        for (WeightedPoint wp : path)
        {
            if (prev != null)
            {
                g2d.drawLine(x + getAxisLabelOffset() + wp.getCol() * tileWidth + tileWidth / 2, 
                             y + getAxisLabelOffset() + wp.getRow() * tileHeight + tileHeight / 2, 
                             x + getAxisLabelOffset() + prev.getCol() * tileWidth + tileWidth / 2, 
                             y + getAxisLabelOffset() + prev.getRow() * tileHeight + tileHeight / 2);
            }
            prev = wp;
        }
    }

    /**
     * Draw the tile statistics for whichever tile the mouse is currently hovering over
     * 
     * @param g
     */
    private void paintStats(Graphics2D g)
    {
        String msg = selectedTile.toString() + "=" + selectedTile.getCost();
        int w = g.getFontMetrics().charsWidth(msg.toCharArray(), 0, msg.length());
        int h = g.getFontMetrics().getHeight();
        int x = getXCoor() + (selectedTile.getCol() + 2) * tileWidth;
        int y = getYCoor() + (selectedTile.getRow() + 2) * tileHeight;
        g.setColor(colorScheme.background);
        g.fillRect(x, y - (tileHeight + h) / 2, w + 10, h * 2);
        g.setColor(colorScheme.grid);
        g.drawRect(x, y - (tileHeight + h) / 2, w + 10, h * 2);
        g.setColor(colorScheme.text);
        g.drawString(msg, x + 5, y);
    }

    /**
     * Draw the specified point
     * 
     * @param g2d
     * @param point
     * @param c color
     * @param x
     * @param y
     * @param filled whether the point should be filled in or just the outline
     */
    private void drawPoint(Graphics2D g2d, WeightedPoint point, Color c, int x, int y, boolean filled)
    {
        g2d.setColor(c);
        if (filled)
        {
            g2d.fillOval(x + getAxisLabelOffset() + point.getCol() * tileWidth, y + getAxisLabelOffset() + point.getRow() * tileHeight, tileWidth, tileHeight);
        }
        else
        {
            g2d.drawOval(x + getAxisLabelOffset() + point.getCol() * tileWidth, y + getAxisLabelOffset() + point.getRow() * tileHeight, tileWidth, tileHeight);
        }
    }

    /**
     * Draws a block with highlights and shadows
     * 
     * @param g
     * @param x
     * @param y
     * @param row
     * @param col
     */
    private void drawBlock(Graphics g, int x, int y, int row, int col)
    {
        int inset = 2;
        int dx = x + col * tileWidth + getAxisLabelOffset() + inset;
        int dy = y + row * tileHeight + getAxisLabelOffset() + inset;
        int dw = tileWidth - inset * 2;
        int dh = tileHeight - inset * 2;
        g.setColor(colorScheme.block);
        g.fillRect(dx, dy, dw, dh);

        g.setColor(colorScheme.blockShadow);
        for (int i = 0; i < Math.max(tileHeight / 6, 1); i++)
            g.drawLine(dx, dy + dh - i, dx + dw - 1 - i, dy + dh - i);
        for (int i = 0; i < Math.max(tileWidth / 6, 1); i++)
            g.drawLine(dx + dw - i, dy, dx + dw - i, dy + dh - 1 - i);

        g.setColor(colorScheme.blockHighlight);
        for (int i = 0; i < Math.max(tileHeight / 6, 1); i++)
            g.drawLine(dx, dy + i, dx + dw - 1 - i, dy + i);
        for (int i = 0; i < Math.max(tileWidth / 6, 1); i++)
            g.drawLine(dx + i, dy, dx + i, dy + dh - 1 - i);
    }

    /**
     * Fill the specified tile
     * 
     * @param g2d
     * @param point
     * @param fill
     * @param border
     * @param x
     * @param y
     */
    private void fillTile(Graphics2D g2d, WeightedPoint point, Color fill, Color border, int x, int y)
    {
        int dx = x + getAxisLabelOffset() + point.getCol() * tileWidth + 4;
        int dy = y + getAxisLabelOffset() + point.getRow() * tileHeight + 4;
        int dw = tileWidth - 9;
        int dh = tileHeight - 9;
        g2d.setColor(fill);
        g2d.fillRect(dx, dy, dw, dh);
        g2d.setColor(border);
        g2d.drawLine(dx, dy + dh, dx + dw, dy + dh);
        g2d.drawLine(dx + dw, dy, dx + dw, dy + dh);
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
        selectedTile = null;
    }

    public void mouseDragged(MouseEvent e)
    {
    }

    public void mouseMoved(MouseEvent  e)
    {
        int row = (e.getY() - getYCoor()) / tileHeight - 1;
        int col = (e.getX() - getXCoor()) / tileWidth - 1;

        if ((row < 0 || col < 0 || row > pf.getMap().getRows() - 1 || col > pf.getMap().getCols() - 1)) // Out of bounds
        {
            selectedTile = null;
        }
        else
        {
            WeightedPoint loc = new WeightedPoint(row, col);

            if (loc.equals(selectedTile))
            {
                List<WeightedPoint> set = pf.getOpenSet().getList();
                for (WeightedPoint wp : set)
                {
                    if (wp.equals(loc))
                    {
                        selectedTile = wp;
                    }
                }
                for (WeightedPoint wp : pf.getClosedSet())
                {
                    if (wp.equals(loc))
                    {
                        selectedTile = wp;
                    }
                }
            }
            else
            {
                selectedTile = loc;
            }
        }

        updateDrawing();
    }

}