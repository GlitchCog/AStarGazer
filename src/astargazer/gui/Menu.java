package astargazer.gui;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Menu extends JMenuBar
{
    private Window window;

    private static final String[] MAIN_MENU_ITEMS = {"File", "Edit", "Help"};
    private static final int[] MAIN_MNOMONICS = {KeyEvent.VK_F, KeyEvent.VK_E, KeyEvent.VK_H};

    private static final String MENU_SEED = "Seed Map Generator";
    private static final String MENU_EXIT = "Exit";
    private static final String MENU_PREFERENCES = "Preferences";
    private static final String MENU_INFO = "Information";
    private static final String MENU_ABOUT = "About";

    private static final MenuComponents[][] MENU_OPTIONS = 
    {
        { new MenuComponents(MENU_SEED,  KeyEvent.VK_S, KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK)), 
          new MenuComponents(MENU_EXIT,  KeyEvent.VK_X, KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK)) }, 
        { new MenuComponents(MENU_PREFERENCES, KeyEvent.VK_P, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, Event.ALT_MASK)) }, 
        { new MenuComponents(MENU_INFO,  KeyEvent.VK_I, null),
          new MenuComponents(MENU_ABOUT, KeyEvent.VK_A, null) 
        }
    };

    /**
     * ActionListener for displaying the help popup
     */
    protected Action keyclickAction = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            window.showInformation();
        }
    };

    /**
     * ActionListener for the main menu
     */
    protected ActionListener mal = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            if (MENU_SEED.equals( ((JMenuItem)e.getSource()).getText() ))
            {
                window.showSeedInput();
            }
            else if (MENU_EXIT.equals( ((JMenuItem)e.getSource()).getText() ))
            {
                System.exit(0);
            }
            else if (MENU_PREFERENCES.equals( ((JMenuItem)e.getSource()).getText() ))
            {
                window.showPreferences();
            }
            else if (MENU_INFO.equals( ((JMenuItem)e.getSource()).getText() ))
            {
                window.showInformation();
            }
            else if (MENU_ABOUT.equals( ((JMenuItem)e.getSource()).getText() ))
            {
                window.showAbout();
            }
        }
    };

    private KeyStroke helpKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false); // For showing the Reference popup

    public Menu(Window window)
    {
        this.window = window;

        // Add the F1 -> help mapping
        window.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(helpKeyStroke, "KEYCLICK");
        window.getRootPane().getActionMap().put("KEYCLICK", keyclickAction);

        final JMenu[] menus = new JMenu[MAIN_MENU_ITEMS.length];

        for (int i = 0; i < MAIN_MENU_ITEMS.length; i++)
        {
            menus[i] = new JMenu(MAIN_MENU_ITEMS[i]);
            menus[i].setMnemonic(MAIN_MNOMONICS[i]);
            
            for (int j = 0; j < MENU_OPTIONS[i].length; j++)
            {
                final JMenuItem jmi = new JMenuItem(MENU_OPTIONS[i][j].label);
                jmi.addActionListener(mal);
                jmi.setMnemonic(MENU_OPTIONS[i][j].mnemonic);
                
                if (MENU_OPTIONS[i][j].accelerator != null) 
                    jmi.setAccelerator(MENU_OPTIONS[i][j].accelerator);
                
                menus[i].add(jmi); // add the submenu
            }
            
            add(menus[i]); // add the menu
        }
    }

}
