package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.controller.*;

import javax.swing.*;

public class MyToolBar extends JToolBar {

    private ActionManager ac;

    public MyToolBar() {
        super(HORIZONTAL);
        ac = MainFrame.getInstance().getActionManager();
        setFloatable(false);
        ExitAction exitAction = ac.getExitAction();
        AboutUsAction aboutUsAction = ac.getAboutUsAction();
        NewChildAction newChildAction = ac.getNewChildAction();
        RemoveChildAction removeChildAction = ac.getRemoveChildAction();
        EditNodeAction editNodeAction = ac.getEditNodeAction();
        add(exitAction);
        add(aboutUsAction);
        add(newChildAction);
        add(removeChildAction);
        add(editNodeAction);
    }
}
