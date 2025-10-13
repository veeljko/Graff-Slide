package raf.graffito.dsw.controller;

public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;

    public ActionManager() {
     initialise();
    }

    public void initialise(){
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
    }

    public ExitAction getExitAction(){
        return exitAction;
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }
}
