package raf.graffito.dsw.controller;

import lombok.Getter;

@Getter
public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewChildAction newChildAction;
    private RemoveChildAction removeChildAction;

    public ActionManager() {
     initialise();
    }

    public void initialise(){
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
        newChildAction = new NewChildAction();
        removeChildAction = new RemoveChildAction();
    }

}
