package tabs.state;

import lombok.Getter;
import tabs.state.state_implementation.*;

public class StateManager {
    @Getter
    private ToolState currentState;
    private ToolState delete;
    private ToolState move;
    private ToolState resize;
    private ToolState select;
    private ToolState zoom;
    private ToolState text;

    public StateManager() {
        init();
    }

    private void init(){
        delete = new DeleteState();
        move = new MoveState();
        resize = new ResizeState();
        select = new SelectState();
        zoom = new ZoomState();
        text = new TextState();
        currentState = select;
    }

    public void setDeleteState(){
        currentState = delete;
    }
    public void setMoveState(){
        currentState = move;
    }
    public void setResizeState(){
        currentState = resize;
    }
    public void setSelectState(){
        currentState = select;
    }
    public void setZoomState(){
        currentState = zoom;
    }

    public void setTextState(){
        currentState = text;
    }
}
