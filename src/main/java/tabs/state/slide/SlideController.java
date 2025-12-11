package tabs.state.slide;

import jtree.GraffTreeImplementation;
import lombok.Setter;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.ImageElement;
import lombok.Getter;
import tabs.elements.element_implementation.LogoElement;
import tabs.state.StateManager;
import tabs.undoredo.Command;
import tabs.undoredo.CommandManager;
import tabs.undoredo.command_implementation.AddCommand;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

@Getter
public class SlideController implements MouseListener, MouseMotionListener, ActionListener, MouseWheelListener {
    private GraffNode slide; //konkretan slide za koji je vezan (model)
    private SlideView slideView; //view
    private StateManager stateManager;
    private CommandManager commandManager;
    @Setter
    private double scaleFactor;

    public SlideController(GraffNode slide, SlideView slideView, StateManager stateManager, CommandManager commandManager) {
        this.slide = slide;
        this.slideView = slideView;
        this.stateManager = stateManager;
        this.commandManager = commandManager;

        slideView.setViewComponents(
                new ArrayList<>(((GraffNodeComposite) slide).getChildren())
        );

        slideView.addMouseListener(this);
        slideView.addMouseMotionListener(this);
        slideView.addMouseWheelListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        stateManager.getCurrentState().mousePressed(e, this);
        updateView();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        stateManager.getCurrentState().mouseReleased(e, this);
        updateView();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        stateManager.getCurrentState().mouseDragged(e, this);
        updateView();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        stateManager.getCurrentState().mouseWheelMoved(e, this);
        updateView();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "img1":
                addImage("sundjerbob.png");
                break;
            case "img2":
                addImage("exit.png");
                break;
            case "img3":
                addImage("patrik.png");
                break;
            case "logo":
                addLogo();
                break;
            case "text":
                stateManager.setTextState();
                break;
        }

        slideView.repaint();
    }

    private void addImage(String fileName) {
        try {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/" + fileName)));
            GraffNode el = new ImageElement(slide, new Point(50, 50), new Dimension(100, 100), img);
            AddCommand addCommand = new AddCommand((GraffNodeComposite) slide, el);
            commandManager.executeCommand(addCommand);
            updateView();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addLogo() {
        try {
            GraffNode logoElement = new LogoElement(slide, new Point(100, 100), new Dimension(100, 100));
            AddCommand addCommand = new AddCommand((GraffNodeComposite) slide, logoElement);
            commandManager.executeCommand(addCommand);
            updateView();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateView(){
        slideView.setViewComponents(
                new ArrayList<>(((GraffNodeComposite) slide).getChildren())
        );

        slideView.validate();
        slideView.repaint();
    }
}
