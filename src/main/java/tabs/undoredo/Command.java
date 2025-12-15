package tabs.undoredo;

public interface Command {
    void execute(); // Izvršavanje akcije
    void undo();    // Poništavanje akcije
}
