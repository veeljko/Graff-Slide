package tabs.state.slide.states_selector;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.http.WebSocket;

public class SlideStatesView extends JPanel {

    private JButton btnSelect;
    private JButton btnMove;
    private JButton btnResize;
    private JButton btnDelete;
    private JButton btnRotateLeft;
    private JButton btnRotateRight;
    private JButton btnZoom;
    private JButton btnUndo;
    private JButton btnRedo;

    public SlideStatesView(ActionListener listener) {
        // Horizontalni raspored
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setAutoscrolls(true);

        btnSelect = new JButton("Select");
        btnMove = new JButton("Move");
        btnResize = new JButton("Resize");
        btnDelete = new JButton("Delete");
        btnRotateLeft = new JButton("Rotate Left");
        btnRotateRight = new JButton("Rotate Right");
        btnZoom = new JButton("Zoom");
        btnUndo = new JButton("Undo");
        btnRedo = new JButton("Redo");

        // Dodavanje listener-a
        btnSelect.addActionListener(listener);
        btnSelect.setActionCommand("select");
        btnMove.addActionListener(listener);
        btnMove.setActionCommand("move");
        btnResize.addActionListener(listener);
        btnResize.setActionCommand("resize");
        btnDelete.addActionListener(listener);
        btnDelete.setActionCommand("delete");
        btnRotateLeft.addActionListener(listener);
        btnRotateLeft.setActionCommand("rotateleft");
        btnRotateRight.addActionListener(listener);
        btnRotateRight.setActionCommand("rotateright");
        btnZoom.addActionListener(listener);
        btnZoom.setActionCommand("zoom");
        btnUndo.addActionListener(listener);
        btnUndo.setActionCommand("undo");
        btnRedo.addActionListener(listener);
        btnRedo.setActionCommand("redo");

        // Dodavanje dugmadi u panel
        add(btnSelect);
        add(btnMove);
        add(btnResize);
        add(btnDelete);
        add(btnRotateLeft);
        add(btnRotateRight);
        add(btnZoom);
        add(btnUndo);
        add(btnRedo);
    }

}
