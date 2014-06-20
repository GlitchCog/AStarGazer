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
                                                                     Color.WHITE, 
                                                                         Color.GRAY, 
                                                                         Color.WHITE, 
                                                                         Color.RED, 
                                                                         Color.CYAN.darker().darker(),
                                                                         Color.CYAN.darker(), 
                                                                         Color.ORANGE, 
                                                                         Color.ORANGE.darker(), 
                                                                     Color.BLACK, 
                                                                     Color.LIGHT_GRAY, 
                                                                         Color.BLACK, 
                                                                         Color.GREEN, 
                                                                         Color.GREEN.darker(), 
                                                                         Color.PINK, 
                                                                         Color.PINK.darker());

    public static final ColorScheme INVERTED_SCHEME = new ColorScheme("Inverted", 
                                                                      Color.BLACK, 
                                                                          Color.LIGHT_GRAY, 
                                                                          Color.GRAY, 
                                                                          Color.YELLOW, 
                                                                          Color.ORANGE.darker(),
                                                                          Color.ORANGE.darker().darker(), 
                                                                          Color.CYAN.darker(), 
                                                                          Color.CYAN, 
                                                                      Color.WHITE, 
                                                                      Color.DARK_GRAY, 
                                                                         Color.WHITE, 
                                                                         Color.PINK.darker(), 
                                                                         Color.PINK, 
                                                                         Color.GREEN.darker(), 
                                                                         Color.GREEN);

    public static final ColorScheme GREEN_SCHEME = new ColorScheme("Green", 
                                                                   new Color(200, 240, 160), 
                                                                       new Color(100, 200, 100), 
                                                                       Color.GRAY, 
                                                                       Color.RED, 
                                                                       Color.YELLOW.darker().darker(),
                                                                       Color.GREEN.darker(), 
                                                                       Color.GREEN, 
                                                                       Color.GREEN.darker(), 
                                                                   Color.RED.darker().darker(), 
                                                                   Color.GREEN.darker().darker().darker(), 
                                                                      Color.BLACK, 
                                                                      Color.PINK, 
                                                                      Color.RED, 
                                                                      Color.GREEN, 
                                                                      Color.GREEN.darker());

    public static final ColorScheme UGLY_SCHEME = new ColorScheme("Ugly", 
                                                                   new Color(230, 150, 255), 
                                                                       new Color(100, 100, 200), 
                                                                       Color.BLACK, 
                                                                       Color.GREEN, 
                                                                       Color.PINK,
                                                                       Color.BLUE.darker(), 
                                                                       Color.BLUE, 
                                                                       Color.BLUE.darker(), 
                                                                   Color.WHITE, 
                                                                   Color.BLUE.darker().darker().darker(), 
                                                                      Color.ORANGE, 
                                                                      Color.PINK, 
                                                                      Color.RED, 
                                                                      Color.BLUE, 
                                                                      Color.BLUE.brighter());

    public static final ColorScheme TRON_SCHEME = new ColorScheme("Tron", 
                                                                 Color.BLACK, 
                                                                     Color.CYAN, 
                                                                     Color.CYAN.darker(), 
                                                                     Color.ORANGE, 
                                                                     Color.CYAN.darker(), 
                                                                     Color.CYAN.darker().darker(), 
                                                                     Color.ORANGE.darker(), 
                                                                     Color.ORANGE.darker().darker(), 
                                                                 Color.ORANGE, 
                                                                 Color.CYAN, 
                                                                     Color.ORANGE.brighter(), 
                                                                     Color.MAGENTA.darker(), 
                                                                     Color.MAGENTA, 
                                                                     Color.ORANGE, 
                                                                     Color.ORANGE.darker());

    public static final ColorScheme GRAYSCALE_SCHEME = new ColorScheme("Grayscale", 
                                                                       Color.WHITE, 
                                                                           Color.LIGHT_GRAY, 
                                                                           Color.DARK_GRAY, 
                                                                           Color.BLACK, 
                                                                           Color.DARK_GRAY, 
                                                                           Color.LIGHT_GRAY, 
                                                                           Color.GRAY, 
                                                                           Color.LIGHT_GRAY, 
                                                                           Color.BLACK, 
                                                                           Color.BLACK, 
                                                                           Color.BLACK, 
                                                                           Color.WHITE, 
                                                                           Color.BLACK, 
                                                                           Color.WHITE, 
                                                                           Color.BLACK);

    public static final ColorScheme[] SCHEMES = {DEFAULT_SCHEME, INVERTED_SCHEME, GREEN_SCHEME, UGLY_SCHEME, TRON_SCHEME, GRAYSCALE_SCHEME};

    public String name;

    public final Color background;

    public final Color block;
    public final Color blockBorder;

    public final Color cursor;

    public final Color open;
    public final Color openBorder;
    public final Color closed;
    public final Color closedBorder;

    public final Color text;
    public final Color grid;
    public final Color path;

    public final Color start;
    public final Color startBorder;
    public final Color goal;
    public final Color goalBorder;

    private ColorScheme(String name, 
                        Color background, 
                        Color block, 
                        Color blockBorder, 
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

        this.block = block;
        this.blockBorder = blockBorder;

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

    public String toString()
    {
        return name;
    }
}