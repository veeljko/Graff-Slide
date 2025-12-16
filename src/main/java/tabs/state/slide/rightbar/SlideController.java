package tabs.state.slide.rightbar;

import lombok.Setter;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import strategy.EmptySpaceCalculator;
import strategy.concretes.FirstEmptySpaceCalculateStrategy;
import tabs.GraffPanel;
import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.ImageElement;
import lombok.Getter;
import tabs.elements.element_implementation.LogoElement;
import tabs.state.StateManager;
import tabs.state.slide.SlideView;
import tabs.undoredo.CommandManager;
import tabs.undoredo.command_implementation.AddCommand;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Getter
public class SlideController implements MouseListener, MouseMotionListener, ActionListener, MouseWheelListener {
    private GraffNode slide; //konkretan slide za koji je vezan (model)
    private SlideView slideView; //view
    private StateManager stateManager;
    private CommandManager commandManager;

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
            case "addLocalImage":
                try {
                    addLocalImage();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
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
            String imagePath = "/images/" + fileName;
            ImageElement el = new ImageElement(slide, new Point(50, 50), new Dimension(100, 100), img);
            el.setImagePath(imagePath);
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

    private void addLocalImage() throws IOException {
        System.out.println("pokusavam da otvorim sliku");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Otvori JSON projekat");

        int userSelection = fileChooser.showOpenDialog(MainFrame.getInstance());
        fileChooser.setDialogTitle("Izaberite sliku");

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            BufferedImage img = ImageIO.read(fileToLoad);
            ImageElement el = new ImageElement(slide, new Point(50, 50), new Dimension(100, 100), img);
            el.setImagePath(fileToLoad.getAbsolutePath());
            AddCommand addCommand = new AddCommand((GraffNodeComposite) slide, el);
            commandManager.executeCommand(addCommand);
            updateView();
            MainFrame.getInstance().getUcitaneSlike().addImage(el.getImage());
        }
    }

    public void updateView(){
        slideView.setViewComponents(
                new ArrayList<>(((GraffNodeComposite) slide).getChildren())
        );

        slideView.validate();
        slideView.repaint();

        GraffPanel active = (GraffPanel)MainFrame.getInstance().getTabbedPane().getSelectedComponent();
        System.out.println("Empty Space:");
        int emptyPixels = active.getEmptySpaceCalculator().calculateEmptySpace((ArrayList<GraffNode>) ((GraffNodeComposite) slide).getChildren(), slideView.getWindowWidth(), slideView.getWindowHeight());
        System.out.println("Empty pixels : " + emptyPixels);
        double procenti = ((double)emptyPixels / (650.0 * 450.0)) * 100.0;
        System.out.println("Procenti: " + (int)procenti + "%  (Slobodna povrsina)");
    }

    public void setScaleFactor(double scaleFactor) {
        slideView.setScaleFactorWindow(scaleFactor);
    }
}
