package tabs.undoredo;

import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeType;
import repository.graff_implementation.Project;
import tabs.graffpanel.GraffPanelController;
import tabs.graffpanel.GraffPanelView;

import java.util.Stack;

public class CommandManager {
    private Stack<Command> history = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        setModified();
        history.push(command);
        redoStack.clear();
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
            redoStack.push(command);
        } else {
            System.out.println("Nema akcija za Undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            history.push(command);
        } else {
            System.out.println("Nema akcija za Redo.");
        }
    }

    private void setModified(){
        GraffNode project = ((GraffPanelView)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getGraffPanelController().getNode().getParent();
        if (project != null && project.getType() == GraffNodeType.PROJECT){
            ((Project)project).setModified(true);
        }
    }
}