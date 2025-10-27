package raf.graffito.dsw.core;
import error_handler.ErrorMessage;
import error_handler.ErrorType;
import error_handler.observer.MessageGenerator;
import raf.graffito.dsw.gui.swing.MainFrame;

import java.time.LocalDateTime;

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

    //TESTIRAO SAM MESSAGEGENERATOR
    public void initialize(){
        MessageGenerator msgGen = new MessageGenerator();
        MainFrame mainFrame = MainFrame.getInstance();
        msgGen.addSubsriber(MainFrame.getInstance());
        mainFrame.setVisible(true);

        ErrorMessage er = new ErrorMessage("Ovo je test", ErrorType.ERROR, LocalDateTime.now());
        msgGen.sendNotification(er);
    }
}
