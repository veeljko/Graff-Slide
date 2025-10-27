package raf.graffito.dsw.gui.swing;

import error_handler.ErrorMessage;
import error_handler.ErrorType;
import error_handler.observer.Subscriber;
import raf.graffito.dsw.controller.ActionManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Subscriber {

    // Buduća polja za sve komponente view-a na glavnom prozoru
    private static MainFrame instance;
    private ActionManager actionManager;

    private MainFrame() {
        initialize();
    }

    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
        }
        return instance;
    }

    private void initialize() {
        actionManager = new ActionManager();
        Toolkit kit = Toolkit.getDefaultToolkit(); // Toolkit omogućava interakciju sa platformom
        Dimension screenSize = kit.getScreenSize(); // Veličina ekrana
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null); // Centriranje prozora na ekranu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zatvaranje aplikacije pri zatvaranju prozora
        setTitle("Graffito"); // Naslov prozora

        MyMenuBar menu = new MyMenuBar(actionManager); // Kreiranje menija
        setJMenuBar(menu); // Postavljanje menija na prozor

        MyToolBar toolBar = new MyToolBar(actionManager); // Kreiranje toolbar-a
        add(toolBar, BorderLayout.NORTH); // Postavljanje toolbar-a na vrh prozora
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    @Override
    public void update(ErrorMessage errorMessage) {
        if(errorMessage.getType() == ErrorType.ERROR){
            JOptionPane.showMessageDialog(this, errorMessage.getFormatedMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        else if(errorMessage.getType() == ErrorType.NOTIFY){
            JOptionPane.showMessageDialog(this, errorMessage.getFormatedMessage(), "Notification", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
