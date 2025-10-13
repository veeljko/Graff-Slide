package raf.graffito.dsw.core;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.gui.swing.MainFrame;

public class ApplicationFramework {
    // Buduća polja za model celog projekta
    private static ApplicationFramework instance;

    private ApplicationFramework(){
        initialize();
    }

    public static ApplicationFramework getInstance()
    {
        if(instance == null){
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public void initialize(){
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }
}
