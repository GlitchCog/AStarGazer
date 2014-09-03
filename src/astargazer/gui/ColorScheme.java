package astargazer.gui;

import java.awt.Color;

/**
 * Color schemes for displaying the MapPanel
 * 
 * @author myanos
 */
public class ColorScheme
{
    public static final ColorScheme DEFAULT_SCHEME = new ColorScheme("Default", 
                                                                     Color.WHITE,                  // background
                                                                     Color.LIGHT_GRAY,             // blockHighlight
                                                                     Color.GRAY,                   // block
                                                                     Color.DARK_GRAY,              // blockShadow
                                                                     Color.RED,                    // cursor
                                                                     Color.CYAN.darker().darker(), // open
                                                                     Color.CYAN.darker(),          // openBorder
                                                                     Color.ORANGE,                 // closed
                                                                     Color.ORANGE.darker(),        // closedBorder
                                                                     Color.BLACK,                  // text
                                                                     Color.LIGHT_GRAY,             // grid
                                                                     Color.BLACK,                  // path
                                                                     Color.GREEN,                  // start
                                                                     Color.GREEN.darker(),         // startBorder
                                                                     Color.PINK,                   // goal
                                                                     Color.PINK.darker());         // goalBorder

    public static final ColorScheme INVERTED_SCHEME = new ColorScheme("Inverted", 
                                                                     Color.BLACK,                    // background
                                                                     new Color(220, 220, 220),       // blockHighlight
                                                                     Color.LIGHT_GRAY,               // block
                                                                     Color.GRAY,                     // blockShadow
                                                                     Color.YELLOW,                   // cursor
                                                                     Color.ORANGE.darker(),          // open
                                                                     Color.ORANGE.darker().darker(), // openBorder
                                                                     Color.CYAN.darker(),            // closed
                                                                     Color.CYAN,                     // closedBorder
                                                                     Color.WHITE,                    // text
                                                                     Color.DARK_GRAY,                // grid
                                                                     Color.WHITE,                    // path
                                                                     Color.PINK.darker(),            // start
                                                                     Color.PINK,                     // startBorder
                                                                     Color.GREEN.darker(),           // goal
                                                                     Color.GREEN);                   // goalBorder

    public static final ColorScheme GRASS_SCHEME = new ColorScheme("Grassy", 
                                                                     new Color(200, 240, 160),      // background
                                                                     new Color(150, 250, 150),      // blockHighlight
                                                                     new Color(100, 200, 100),      // block
                                                                     Color.GRAY,                    // blockShadow
                                                                     Color.RED,                     // cursor
                                                                     Color.YELLOW.darker().darker(),// open
                                                                     Color.GREEN.darker(),          // openBorder
                                                                     Color.GREEN,                   // closed
                                                                     Color.GREEN.darker(),          // closedBorder
                                                                     Color.RED.darker().darker(),   // text
                                                                     Color.GREEN.darker().darker().darker(), // grid
                                                                     Color.BLACK,                   // path
                                                                     Color.PINK,                    // start
                                                                     Color.RED,                     // startBorder
                                                                     Color.GREEN,                   // goal
                                                                     Color.GREEN.darker());         // goalBorder

    public static final ColorScheme WATER_SCHEME = new ColorScheme("Oceanic", 
                                                                     new Color(50, 150, 255),       // background
                                                                     new Color(100, 150, 250),      // blockHighlight
                                                                     new Color(50, 100, 200),       // block
                                                                     Color.BLACK,                   // blockShadow
                                                                     new Color(255, 200, 150),      // cursor
                                                                     new Color(120, 100, 155),      // open
                                                                     new Color(150, 130, 155),      // openBorder
                                                                     new Color(200, 200, 255),      // closed
                                                                     new Color(230, 230, 255),      // closedBorder
                                                                     Color.BLACK,                   // text
                                                                     Color.BLUE.darker().darker().darker(), // grid
                                                                     Color.CYAN,                    // path
                                                                     Color.CYAN.darker().darker(),  // start
                                                                     Color.CYAN.darker(),           // startBorder
                                                                     Color.ORANGE,                  // goal
                                                                     Color.ORANGE.darker());        // goalBorder

    public static final ColorScheme FIRE_SCHEME = new ColorScheme("Firey", 
                                                                     new Color(255, 150, 50),       // background
                                                                     new Color(255, 220, 100),      // blockHighlight
                                                                     new Color(240, 120, 50),       // block
                                                                     Color.BLACK,                   // blockShadow
                                                                     new Color(150, 200, 255),      // cursor
                                                                     new Color(255, 50, 30),        // open
                                                                     new Color(255, 80, 30),        // openBorder
                                                                     new Color(255, 200, 0),        // closed
                                                                     new Color(255, 230, 0),        // closedBorder
                                                                     Color.BLACK,                   // text
                                                                     Color.RED.darker().darker().darker(), // grid
                                                                     Color.RED,                     // path
                                                                     Color.MAGENTA.darker().darker(), // start
                                                                     Color.MAGENTA.darker(),        // startBorder
                                                                     Color.CYAN,                    // goal
                                                                     Color.CYAN.darker());          // goalBorder

    public static final ColorScheme ICE_SCHEME = new ColorScheme("Frosty", 
                                                                     new Color(245, 250, 255),      // background
                                                                     Color.WHITE,                   // blockHighlight
                                                                     new Color(235, 240, 255),      // block
                                                                     Color.CYAN.darker(),           // blockShadow
                                                                     new Color(150, 200, 255),      // cursor
                                                                     Color.GRAY,                    // open
                                                                     Color.LIGHT_GRAY,              // openBorder
                                                                     Color.DARK_GRAY,               // closed
                                                                     Color.GRAY,                    // closedBorder
                                                                     Color.DARK_GRAY,               // text
                                                                     Color.CYAN.darker(),           // grid
                                                                     Color.BLUE,                    // path
                                                                     Color.CYAN,                    // start
                                                                     Color.CYAN.darker(),           // startBorder
                                                                     Color.BLUE,                    // goal
                                                                     Color.BLUE.darker());          // goalBorder

    public static final ColorScheme TRON_SCHEME = new ColorScheme("Tron", 
                                                                     Color.BLACK,                   // background
                                                                     new Color(100, 255, 255),      // blockHighlight
                                                                     Color.CYAN,                    // block
                                                                     Color.CYAN.darker(),           // blockShadow
                                                                     Color.ORANGE,                  // cursor
                                                                     Color.CYAN.darker(),           // open
                                                                     Color.CYAN.darker().darker(),  // openBorder
                                                                     Color.ORANGE.darker(),         // closed
                                                                     Color.ORANGE.darker().darker(), // closedBorder
                                                                     Color.ORANGE,                  // text
                                                                     Color.CYAN,                    // grid
                                                                     Color.ORANGE.brighter(),       // path
                                                                     Color.MAGENTA.darker(),        // start
                                                                     Color.MAGENTA,                 // startBorder
                                                                     Color.ORANGE,                  // goal
                                                                     Color.ORANGE.darker());        // goalBorder

    public static final ColorScheme GRAYSCALE_SCHEME = new ColorScheme("Grayscale", 
                                                                     Color.WHITE,                   // background
                                                                     new Color(220, 220, 220),      // blockHighlight
                                                                     Color.LIGHT_GRAY,              // block
                                                                     Color.DARK_GRAY,               // blockShadow
                                                                     Color.BLACK,                   // cursor
                                                                     Color.DARK_GRAY,               // open
                                                                     Color.LIGHT_GRAY,              // openBorder
                                                                     Color.GRAY,                    // closed
                                                                     Color.LIGHT_GRAY,              // closedBorder
                                                                     Color.BLACK,                   // text
                                                                     Color.BLACK,                   // grid
                                                                     Color.BLACK,                   // path
                                                                     Color.WHITE,                   // start
                                                                     Color.BLACK,                   // startBorder
                                                                     Color.WHITE,                   // goal
                                                                     Color.BLACK);                  // goalBorder

    public static final ColorScheme CUSTOM_SCHEME = new ColorScheme("Custom", DEFAULT_SCHEME);

    public static final ColorScheme[] SCHEMES = {DEFAULT_SCHEME, INVERTED_SCHEME, GRASS_SCHEME, WATER_SCHEME, FIRE_SCHEME, ICE_SCHEME, TRON_SCHEME, GRAYSCALE_SCHEME};

    public final String name;

    private Color background;

    private Color blockHighlight;
    private Color block;
    private Color blockShadow;

    private Color cursor;

    private Color open;
    private Color openBorder;
    private Color closed;
    private Color closedBorder;

    private Color text;
    private Color grid;
    private Color path;

    private Color start;
    private Color startBorder;
    private Color goal;
    private Color goalBorder;

    private ColorScheme(String name, 
                        Color background, 
                        Color blockHighlight, 
                        Color block, 
                        Color blockShadow, 
                        Color cursor, 
                        Color open, 
                        Color openBorder, 
                        Color closed, 
                        Color closedBorder, 
                        Color text, 
                        Color grid, 
                        Color path, 
                        Color start, 
                        Color startBorder, 
                        Color goal, 
                        Color goalBorder)
    {
        this.name = name;

        this.background = background;

        this.blockHighlight = blockHighlight;
        this.block = block;
        this.blockShadow = blockShadow;

        this.cursor = cursor;

        this.open = open;
        this.openBorder = openBorder;
        this.closed = closed;
        this.closedBorder = closedBorder;

        this.text = text;
        this.grid = grid;

        this.path = path;
        this.start = start;
        this.startBorder = startBorder;
        this.goal = goal;
        this.goalBorder = goalBorder;
    }

    public ColorScheme(String name, ColorScheme copy)
    {
        this.name = name;

        this.background = copy.background;

        this.blockHighlight = copy.blockHighlight;
        this.block = copy.block;
        this.blockShadow = copy.blockShadow;

        this.cursor = copy.cursor;

        this.open = copy.open;
        this.openBorder = copy.openBorder;
        this.closed = copy.closed;
        this.closedBorder = copy.closedBorder;

        this.text = copy.text;
        this.grid = copy.grid;

        this.path = copy.path;
        this.start = copy.start;
        this.startBorder = copy.startBorder;
        this.goal = copy.goal;
        this.goalBorder = copy.goalBorder;
    }

    public String toString()
    {
        return name;
    }

    public Color getBackground()
    {
        return background;
    }

    public Color getBlockHighlight()
    {
        return blockHighlight;
    }

    public Color getBlock()
    {
        return block;
    }

    public Color getBlockShadow()
    {
        return blockShadow;
    }

    public Color getCursor()
    {
        return cursor;
    }

    public Color getOpen()
    {
        return open;
    }

    public Color getOpenBorder()
    {
        return openBorder;
    }

    public Color getClosed()
    {
        return closed;
    }

    public Color getClosedBorder()
    {
        return closedBorder;
    }

    public Color getText()
    {
        return text;
    }

    public Color getGrid()
    {
        return grid;
    }

    public Color getPath()
    {
        return path;
    }

    public Color getStart()
    {
       return start;
    }

    public Color getStartBorder()
    {
        return startBorder;
    }

    public Color getGoal()
    {
        return goal;
    }

    public Color getGoalBorder()
    {
        return goalBorder;
    }

}
