package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.controller.AboutUsAction;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.controller.ExitAction;

import javax.swing.*;

public class MyToolBar extends JToolBar {

    private ActionManager ac;

    public MyToolBar(ActionManager ac) {
        super(HORIZONTAL);
        this.ac = ac;
        setFloatable(false);
        ExitAction exitAction = ac.getExitAction();
        AboutUsAction aboutUsAction = ac.getAboutUsAction();
        add(exitAction);
        add(aboutUsAction);
    }
}
