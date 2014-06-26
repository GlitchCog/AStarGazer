package astargazer.gui;

import javax.swing.tree.DefaultMutableTreeNode;

public class HelpNode extends DefaultMutableTreeNode
{
    private String helpText;

    public HelpNode(String userObject)
    {
        this(userObject, userObject);
    }

    public HelpNode(Object userObject, String helpText)
    {
        super(userObject);
        this.helpText = helpText;
    }

    public String getHelpText()
    {
        if (helpText == null)
        {
            return getUserObject().toString();
        }
        else
        {
            return helpText;
        }
    }
}