package raf.graffito.dsw.gui.swing;



import raf.graffito.dsw.controller.*;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    private ActionManager ac;

    public MyMenuBar() {
        ac = MainFrame.getInstance().getActionManager();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        ExitAction exitAction = ac.getExitAction();
        AboutUsAction aboutUsAction = ac.getAboutUsAction();
        NewChildAction newChildAction = ac.getNewChildAction();
        RemoveChildAction removeChildAction = ac.getRemoveChildAction();
        EditNodeAction editNodeAction = ac.getEditNodeAction();
        fileMenu.add(exitAction);
        fileMenu.add(aboutUsAction);
        fileMenu.add(newChildAction);
        fileMenu.add(removeChildAction);
        fileMenu.add(editNodeAction);
        add(fileMenu);
    }

}
