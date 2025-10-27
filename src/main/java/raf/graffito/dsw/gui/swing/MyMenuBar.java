package raf.graffito.dsw.gui.swing;



import raf.graffito.dsw.controller.AboutUsAction;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.controller.ExitAction;

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
        fileMenu.add(exitAction);
        fileMenu.add(aboutUsAction);
        add(fileMenu);
    }

}
