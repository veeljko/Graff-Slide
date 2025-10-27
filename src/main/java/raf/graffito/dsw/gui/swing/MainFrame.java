package raf.graffito.dsw.gui.swing;

import error_handler.ErrorMessage;
import error_handler.ErrorType;
import error_handler.observer.Subscriber;
import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.controller.ActionManager;

import javax.swing.*;
import java.awt.*;

@Getter @Setter
public class MainFrame extends JFrame implements Subscriber {

    // Buduća polja za sve komponente view-a na glavnom prozoru
    private static MainFrame instance = new MainFrame();
    private ActionManager actionManager;

    private MainFrame() {
    }

    public static MainFrame getInstance(){
        return instance;
    }

    public void initialize() {
        actionManager = new ActionManager();
        Toolkit kit = Toolkit.getDefaultToolkit(); // Toolkit omogućava interakciju sa platformom
        Dimension screenSize = kit.getScreenSize(); // Veličina ekrana
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null); // Centriranje prozora na ekranu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zatvaranje aplikacije pri zatvaranju prozora
        setTitle("Graffito"); // Naslov prozora

        MyMenuBar menu = new MyMenuBar(); // Kreiranje menija
        setJMenuBar(menu); // Postavljanje menija na prozor

        MyToolBar toolBar = new MyToolBar(); // Kreiranje toolbar-a
        add(toolBar, BorderLayout.NORTH); // Postavljanje toolbar-a na vrh prozora
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
