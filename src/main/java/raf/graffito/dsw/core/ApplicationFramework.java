package raf.graffito.dsw.core;
import error_handler.ErrorMessage;
import error_handler.ErrorType;
import error_handler.observer.MessageGenerator;
import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.GraffRepositoryImplementation;

import java.time.LocalDateTime;
@Getter
@Setter
public class ApplicationFramework {
    // Buduća polja za model celog projekta
    private static ApplicationFramework instance;
    private GraffRepository graffRepository;
    private MessageGenerator msgGen;

    private ApplicationFramework(){
       // initialize();
    }

    public static ApplicationFramework getInstance()
    {
        if(instance == null){
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public void initialize(GraffRepository graffRepository){
        this.graffRepository = graffRepository;
        msgGen = new MessageGenerator();
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.initialize();
        msgGen.addSubscriber(MainFrame.getInstance());
        mainFrame.setVisible(true);
    }

}
