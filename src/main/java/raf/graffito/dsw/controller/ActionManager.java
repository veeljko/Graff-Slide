package raf.graffito.dsw.controller;

import lombok.Getter;

@Getter
public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewChildAction newChildAction;
    private RemoveChildAction removeChildAction;
    private EditNodeAction editNodeAction;
    private SaveAction saveAction;
    private SaveAsAction saveAsAction;
    private OpenAction openAction;
    private SaveTemplateAction saveTemplateAction;

    public ActionManager() {
     initialise();
    }

    public void initialise(){
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
        newChildAction = new NewChildAction();
        removeChildAction = new RemoveChildAction();
        editNodeAction = new EditNodeAction();
        saveAction = new SaveAction();
        saveAsAction = new SaveAsAction();
        openAction = new OpenAction();
        saveTemplateAction = new SaveTemplateAction();
    }

}
