package tabs.state.slide;

import lombok.Setter;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.elements.element_implementation.ImageElement;
import lombok.Getter;
import tabs.state.StateManager;
import tabs.state.state_implementation.SelectState;

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
    @Setter
    private double scaleFactor;

    public SlideController(GraffNode slide, SlideView slideView, StateManager stateManager) {
        this.slide = slide;
        this.slideView = slideView;
        this.stateManager = stateManager;

        slideView.setComponents(
                new ArrayList<>(((GraffNodeComposite) slide).getChildren())
        );

        slideView.addMouseListener(this);
        slideView.addMouseMotionListener(this);
        slideView.addMouseWheelListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        stateManager.getCurrentState().mousePressed(e, this);
        slideView.setComponents(
                new ArrayList<>(((GraffNodeComposite) slide).getChildren())
        );
        slideView.repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        stateManager.getCurrentState().mouseReleased(e, this);
        slideView.repaint();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        stateManager.getCurrentState().mouseDragged(e, this);
        slideView.repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        stateManager.getCurrentState().mouseWheelMoved(e, this);
        slideView.repaint();
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

                break;
            case "text":

                break;
        }

        slideView.repaint();
    }

    private void addImage(String fileName) {
        try {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/" + fileName)));
            ImageElement el = new ImageElement("img", slide, new Point(20, 20), new Dimension(100, 100), img);
            ((GraffNodeComposite) slide).addChild(el);
            slideView.setComponents(
                    new ArrayList<>(((GraffNodeComposite) slide).getChildren())
            );

            slideView.validate();
            slideView.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
