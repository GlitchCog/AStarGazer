package astargazer.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
public class MapPanel extends JPanel implements MouseInputListener, ComponentListener
{
    /**
     * The PathFinder that determines the path between the start and the goal points on the tilemap
     */
    private PathFinder pf;

    /**
     * The horizontal status bar show below this map panel to display information about the map
     */
    private StatusBar sb;

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
     * Map drawing offset on the X axis
     */ 
    private int offsetX;

    /**
     * Map drawing offset on the Y axis
     */
    private int offsetY;

    /**
     * X coordinate when the mouse is clicked to drag the map
     */
    private int startOffsetX;

    /**
     * Y coordinate when the mouse is clicked to drag the map
     */
    private int startOffsetY;

    /**
     * X coordinate of the previous offset when dragging the map
     */
    private int tempOffsetX;

    /**
     * Y coordinate of the previous offset when dragging the map
     */
    private int tempOffsetY;

    /**
     * Construct a MapPanel for the specified PathFinder
     * 
     * @param pf PathFinder
     */
    public MapPanel(PathFinder pf, StatusBar sb)
    {
        this(pf, sb, 12, 12, ColorScheme.SCHEMES[0]);
    }

    /**
     * Construct a MapPanel for the specified PathFinder with specified tile width, tile height, and color scheme
     * 
     * @param pf PathFinder
     * @param tileWidth
     * @param tileHeight
     * @param colorScheme
     */
    public MapPanel(PathFinder pf, StatusBar sb, int tileWidth, int tileHeight, ColorScheme colorScheme)
    {
        this.pf = pf;
        this.sb = sb;

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        this.stroke = new BasicStroke(4.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        setColorScheme(colorScheme);

        if (isInteractive())
        {
            updateStatusBar();
            addMouseMotionListener(this);
            addMouseListener(this);
        }
        else
        {
            centerMap();
        }

        addComponentListener(this);
    }

    /**
     * Returns whether the mouse can be used to modify the panel contents (Used for the example map on the custom color preference popup)
     * 
     * @return interactive
     */
    public boolean isInteractive()
    {
        return sb != null;
    }

    /**
     * Reset the offsets for the top left corner to center the map
     */
    public void centerMap()
    {
        offsetX = (getWidth() - getMapPixelWidth()) / 2;
        offsetY = (getHeight() - getMapPixelHeight()) / 2;
    }

    /**
     * Get the offset distance for the labels on the axes
     * 
     * @return axisLabelOffset
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
        updateStatusBar();
        repaint();
    }

    /**
     * Updates the message displayed on the status bar
     */
    private void updateStatusBar()
    {
        sb.setMessage(pf.getMap().getMapStats());
    }

    /**
     * Get the X coordinate of the upper left corner of the tilemap
     * 
     * @return x
     */
    private int getXCoor()
    {
        return offsetX;//(getWidth() - (pf.getMap().getCols() * tileWidth + getAxisLabelOffset())) / 2 + offsetX;
    }

    /**
     * Get the Y coordinate of the upper left corner of the tilemap
     * 
     * @return y
     */
    private int getYCoor()
    {
        return offsetY;//(getHeight() - (pf.getMap().getRows() * tileHeight + getAxisLabelOffset())) / 2 + offsetY;
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(colorScheme.get(ColorScheme.COLOR_BACKGROUND));
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

        paintGridLabels(g2d, x, y);

        if (pf.getCursor() != null)
        {
            paintPath(g2d, x, y);
        }

        drawPoint(g2d, pf.getStart(), colorScheme.get(ColorScheme.COLOR_START), x, y, true);
        drawPoint(g2d, pf.getStart(), colorScheme.get(ColorScheme.COLOR_START_BORDER), x, y, false);
        drawPoint(g2d, pf.getGoal(), colorScheme.get(ColorScheme.COLOR_GOAL), x, y, true);
        drawPoint(g2d, pf.getGoal(), colorScheme.get(ColorScheme.COLOR_GOAL_BORDER), x, y, false);

        if (pf.getCursor() != null)
        {
            drawPoint(g2d, pf.getCursor(), colorScheme.get(ColorScheme.COLOR_CURSOR), x, y, false);
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
        g.setColor(colorScheme.get(ColorScheme.COLOR_GRID));
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
            fillTile(g2d, wp, colorScheme.get(ColorScheme.COLOR_OPEN), colorScheme.get(ColorScheme.COLOR_OPEN_BORDER), x, y);
        }
        for (WeightedPoint wp : pf.getClosedSet())
        {
            fillTile(g2d, wp, colorScheme.get(ColorScheme.COLOR_CLOSED), colorScheme.get(ColorScheme.COLOR_CLOSED_BORDER), x, y);
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

        for (int row = 0; row < map.getRows(); row++)
        {
            for (int col = 0; col < map.getCols(); col++)
            {
                if (!map.isTraversable(row, col))
                {
                    drawBlock(g, x, y, row, col);
                }
            }
        }
        g.setColor(colorScheme.get(ColorScheme.COLOR_GRID)); // Draw outer border
        g.drawRect(x, y, pf.getMap().getCols() * tileWidth + getAxisLabelOffset(), pf.getMap().getRows() * tileHeight + getAxisLabelOffset());
    }

    /**
     * Draw the labels along the top and left side of the tilemap
     * 
     * @param g2d
     * @param x the X coordinate of the upper left corner of the tilemap
     * @param y the Y coordinate of the upper left corner of the tilemap
     */
    private void paintGridLabels(Graphics g2d, int x, int y)
    {
        TileMap map = pf.getMap();

        int textSize = Math.min(tileWidth, tileHeight);
        textSize = textSize / 4 + 4;
        g2d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, textSize));

        for (int row = 0; row < map.getRows(); row++)
        {
            g2d.setColor(colorScheme.get(ColorScheme.COLOR_TEXT));
            String str = "" + Integer.toString(row);
            while (str.length() < 2) str = '0' + str;
            g2d.drawString(str, x + (getAxisLabelOffset() - g2d.getFontMetrics().charsWidth(str.toCharArray(), 0, str.length())) / 2, 
                         y + getAxisLabelOffset() + (row * tileHeight) + (g2d.getFontMetrics().getHeight() + tileHeight) / 2);
            for (int col = 0; col < map.getCols(); col++)
            {
                if (row == 0)
                {
                    g2d.setColor(colorScheme.get(ColorScheme.COLOR_TEXT));
                    str = "" + Integer.toString(col);
                    while (str.length() < 2) str = '0' + str;
                    g2d.drawString(str, x + getAxisLabelOffset() + col * tileWidth + (tileWidth - g2d.getFontMetrics().charsWidth(str.toCharArray(), 0, str.length())) / 2, 
                                      y + (g2d.getFontMetrics().getHeight() + getAxisLabelOffset()) / 2);
                }
            }
        }
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
        g2d.setColor(colorScheme.get(ColorScheme.COLOR_PATH));
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
        g.setColor(colorScheme.get(ColorScheme.COLOR_BLOCK));
        g.fillRect(dx, dy, dw, dh);

        g.setColor(colorScheme.get(ColorScheme.COLOR_BLOCK_SHADOW));
        for (int i = 0; i < Math.max(tileHeight / 6, 1); i++)
            g.drawLine(dx, dy + dh - i, dx + dw - 1 - i, dy + dh - i);
        for (int i = 0; i < Math.max(tileWidth / 6, 1); i++)
            g.drawLine(dx + dw - i, dy, dx + dw - i, dy + dh - 1 - i);

        g.setColor(colorScheme.get(ColorScheme.COLOR_BLOCK_HIGHLIGHT));
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

    /**
     * Get the width of the tile map in pixels
     * 
     * @return mapPixelWidth
     */
    private int getMapPixelWidth()
    {
        return pf.getMap().getCols() * tileWidth + getAxisLabelOffset();
    }

    /**
     * Get the height of the tile map in pixels
     * 
     * @return mapPixelHeight
     */
    private int getMapPixelHeight()
    {
        return pf.getMap().getRows() * tileHeight + getAxisLabelOffset();
    }

    /**
     * Ensure the map stays on the screen when resizes and zooming happens
     */
    public void enforceBoundaries()
    {
        if (getMapPixelWidth() < getWidth())
        {
            offsetX = Math.min(getWidth() - getMapPixelWidth(), Math.max(offsetX, 0));
        }
        else
        {
            offsetX = Math.min(0, Math.max(offsetX, getWidth() - getMapPixelWidth()));
        }

        if (getHeight() > getMapPixelHeight())
        {
            offsetY = Math.min(getHeight() - getMapPixelHeight(), Math.max(offsetY, 0));
        }
        else
        {
            offsetY = Math.min(0, Math.max(offsetY, getHeight() - getMapPixelHeight()));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        startOffsetX = e.getX();
        startOffsetY = e.getY();
        tempOffsetX = offsetX;
        tempOffsetY = offsetY;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        selectedTile = null;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (getMapPixelWidth() < getWidth())
        {
            offsetX = Math.min(getWidth() - getMapPixelWidth(), Math.max(tempOffsetX + e.getX() - startOffsetX, 0));
        }
        else
        {
            offsetX = Math.min(0, Math.max(tempOffsetX + e.getX() - startOffsetX, getWidth() - getMapPixelWidth()));
        }

        if (getHeight() > getMapPixelHeight())
        {
            offsetY = Math.min(getHeight() - getMapPixelHeight(), Math.max(tempOffsetY + e.getY() - startOffsetY, 0));
        }
        else
        {
            offsetY = Math.min(0, Math.max(tempOffsetY + e.getY() - startOffsetY, getHeight() - getMapPixelHeight()));
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent  e)
    {
        int row = (e.getY() - getYCoor()) / tileHeight - 1;
        int col = (e.getX() - getXCoor()) / tileWidth - 1;

        if ((row < 0 || col < 0 || row > pf.getMap().getRows() - 1 || col > pf.getMap().getCols() - 1)) // Out of bounds
        {
            selectedTile = null;
            sb.setPointLabelText("");
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

            sb.setPointLabelText(selectedTile.toString() + "=" + selectedTile.getCost());
        }
    }

    @Override
    public void componentResized(ComponentEvent e)
    {
        if (isInteractive())
        {
            enforceBoundaries();
        }
        else
        {
            centerMap();
        }
    }

    @Override
    public void componentHidden(ComponentEvent e)
    {
    }

    @Override
    public void componentMoved(ComponentEvent e)
    {
    }

    @Override
    public void componentShown(ComponentEvent e)
    {
    }
}
