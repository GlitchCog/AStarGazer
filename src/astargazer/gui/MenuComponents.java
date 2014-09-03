package astargazer.gui;

import javax.swing.KeyStroke;

/**
 * Simple grouping of a label, a mnemonic key to press, and a shortcut key
 * combination accelerator, together representing an entry on the main menu
 */
public class MenuComponents
{
    /**
     * The String to be displayed on the menu
     */
    public String label;
    /**
     * The letter to underline and the key to press to make a selection when
     * using the keyboard to navigate the menu
     */
    public int mnemonic;
    /**
     * The keyboard shortcut combination for the menu item
     */
    public KeyStroke accelerator;

    /**
     * A constructor to simply set all the values
     * 
     * @param label
     *            The String to be displayed on the menu
     * @param mnemonic
     *            The letter to underline and the key to press to make a
     *            selection when using the keyboard to navigate the menu
     * @param accelerator
     *            The keyboard shortcut combination for the menu item
     */
    public MenuComponents(String label, int mnemonic, KeyStroke accelerator)
    {
        this.label = label;
        this.mnemonic = mnemonic;
        this.accelerator = accelerator;  
    }
}