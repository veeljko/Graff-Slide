package tabs.undoredo;

import jtree.GraffTreeImplementation;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import tabs.GraffPanel;
import tabs.state.slide.SlideController;
import tabs.undoredo.command_implementation.AddCommand;
import tabs.undoredo.command_implementation.DeleteCommand;

import java.util.Stack;

public class CommandManager {
    private Stack<Command> history = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
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
}