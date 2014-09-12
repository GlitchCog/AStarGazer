package astargazer.gui;

import java.awt.Color;
import java.util.HashMap;

/**
 * Color schemes for displaying the MapPanel
 * 
 * @author myanos
 */
public class ColorScheme extends HashMap<String, Color>
{
    public static final String COLOR_BACKGROUND = "Background";
    public static final String COLOR_BLOCK_HIGHLIGHT = "Block Highlight";
    public static final String COLOR_BLOCK = "Block";
    public static final String COLOR_BLOCK_SHADOW = "Block Shadow";
    public static final String COLOR_CURSOR = "Cursor";
    public static final String COLOR_OPEN = "Open";
    public static final String COLOR_OPEN_BORDER = "Open Border";
    public static final String COLOR_CLOSED = "Closed";
    public static final String COLOR_CLOSED_BORDER = "Closed Border";
    public static final String COLOR_TEXT = "Text";
    public static final String COLOR_GRID = "Grid";
    public static final String COLOR_PATH = "Path";
    public static final String COLOR_START = "Start";
    public static final String COLOR_START_BORDER = "Start Border";
    public static final String COLOR_GOAL = "Goal";
    public static final String COLOR_GOAL_BORDER = "Goal Border";

    public static final String[] COLOR_LABELS = new String[] { COLOR_BACKGROUND, 
                                                               COLOR_BLOCK_HIGHLIGHT, 
                                                               COLOR_BLOCK, 
                                                               COLOR_BLOCK_SHADOW, 
                                                               COLOR_CURSOR, 
                                                               COLOR_OPEN, 
                                                               COLOR_OPEN_BORDER, 
                                                               COLOR_CLOSED, 
                                                               COLOR_CLOSED_BORDER, 
                                                               COLOR_TEXT, 
                                                               COLOR_GRID, 
                                                               COLOR_PATH, 
                                                               COLOR_START, 
                                                               COLOR_START_BORDER, 
                                                               COLOR_GOAL, 
                                                               COLOR_GOAL_BORDER };

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

    public static final ColorScheme[] SCHEMES = {DEFAULT_SCHEME, INVERTED_SCHEME, GRASS_SCHEME, WATER_SCHEME, FIRE_SCHEME, ICE_SCHEME, TRON_SCHEME, GRAYSCALE_SCHEME, CUSTOM_SCHEME};

    public final String name;

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
        setColors(background, blockHighlight, block, blockShadow, cursor, open, openBorder, closed, closedBorder, text, grid, path, start, startBorder, goal, goalBorder);
    }

    public void setColors(Color background, 
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
        put(COLOR_BACKGROUND, background);
        put(COLOR_BLOCK_HIGHLIGHT, blockHighlight);
        put(COLOR_BLOCK, block);
        put(COLOR_BLOCK_SHADOW, blockShadow);
        put(COLOR_CURSOR, cursor);
        put(COLOR_OPEN, open);
        put(COLOR_OPEN_BORDER, openBorder);
        put(COLOR_CLOSED, closed);
        put(COLOR_CLOSED_BORDER, closedBorder);
        put(COLOR_TEXT, text);
        put(COLOR_GRID, grid);
        put(COLOR_PATH, path);
        put(COLOR_START, start);
        put(COLOR_START_BORDER, startBorder);
        put(COLOR_GOAL, goal);
        put(COLOR_GOAL_BORDER, goalBorder);
    }

    public ColorScheme(String name, ColorScheme copy)
    {
        this.name = name;
        setColors(copy);
    }

    public String toString()
    {
        return name;
    }

    public void setColors(ColorScheme copy)
    {
        put(COLOR_BACKGROUND, copy.get(COLOR_BACKGROUND));
        put(COLOR_BLOCK_HIGHLIGHT, copy.get(COLOR_BLOCK_HIGHLIGHT));
        put(COLOR_BLOCK, copy.get(COLOR_BLOCK));
        put(COLOR_BLOCK_SHADOW, copy.get(COLOR_BLOCK_SHADOW));
        put(COLOR_CURSOR, copy.get(COLOR_CURSOR));
        put(COLOR_OPEN, copy.get(COLOR_OPEN));
        put(COLOR_OPEN_BORDER, copy.get(COLOR_OPEN_BORDER));
        put(COLOR_CLOSED, copy.get(COLOR_CLOSED));
        put(COLOR_CLOSED_BORDER, copy.get(COLOR_CLOSED_BORDER));
        put(COLOR_TEXT, copy.get(COLOR_TEXT));
        put(COLOR_GRID, copy.get(COLOR_GRID));
        put(COLOR_PATH, copy.get(COLOR_PATH));
        put(COLOR_START, copy.get(COLOR_START));
        put(COLOR_START_BORDER, copy.get(COLOR_START_BORDER));
        put(COLOR_GOAL, copy.get(COLOR_GOAL));
        put(COLOR_GOAL_BORDER, copy.get(COLOR_GOAL_BORDER));
    }

    public void setColor(String name, Color color)
    {
        put(name, color);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ColorScheme other = (ColorScheme)obj;
        if (name == null)
        {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        return true;
    }

}
