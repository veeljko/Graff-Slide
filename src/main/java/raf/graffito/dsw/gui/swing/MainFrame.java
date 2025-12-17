package raf.graffito.dsw.gui.swing;

import error_handler.ErrorMessage;
import error_handler.ErrorType;
import error_handler.observer.Subscriber;
import jtree.GraffTree;
import jtree.GraffTreeImplementation;
import jtree.nodechangeobserver.INodeChangePublisher;
import jtree.nodechangeobserver.INodeChangeSubscriber;
import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.core.ApplicationFramework;
import serijalizacija.SerializationImplementation;
import tabs.GraffTabbedPane;
import tabs.ucitaneslike.UcitaneSlikeView;

import javax.swing.*;
import java.awt.*;

@Getter

public class MainFrame extends JFrame implements Subscriber {

    // Buduća polja za sve komponente view-a na glavnom prozoru
    private static MainFrame instance = new MainFrame();
    private ActionManager actionManager;
    private GraffTree tree;
    private GraffTabbedPane tabbedPane;
    private JTree workspace;
    private SerializationImplementation serijalizator;
    private UcitaneSlikeView ucitaneSlike;
    private JScrollPane scrollPane;

    //dimensions
    private int windowHeight = (int)((double)Toolkit.getDefaultToolkit().getScreenSize().height * 0.85);
    private int windowWidth = (int)((double)Toolkit.getDefaultToolkit().getScreenSize().width * 0.85);
    private int scrollPaneHeight = 400;
    private int scrollPaneWidth = 150;

    private MainFrame() {
    }

    public static MainFrame getInstance(){
        return instance;
    }

    public void initialize() {
        tree = new GraffTreeImplementation();
        workspace = tree.generateTree(ApplicationFramework.getInstance().getGraffRepository().getWorkspace());

        JPanel desktop = new JPanel();
        JScrollPane scroll = new JScrollPane(workspace);
        scroll.setMinimumSize(new Dimension(200, 149));
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, desktop);
        getContentPane().add(split, BorderLayout.CENTER);
        split.setDividerLocation(200);
        split.setOneTouchExpandable(true);
        setResizable(false);
        desktop.setLayout(new BorderLayout());
        tabbedPane = new GraffTabbedPane();
        desktop.add(tabbedPane);
        ((INodeChangePublisher) tree).addSubscriber(tabbedPane);

        actionManager = new ActionManager();

        setSize(windowWidth, windowHeight);
        setLocationRelativeTo(null); // Centriranje prozora na ekranu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zatvaranje aplikacije pri zatvaranju prozora
        setTitle("Graffito"); // Naslov prozora

        serijalizator = new SerializationImplementation();

        ucitaneSlike = new UcitaneSlikeView();

        scrollPane = new JScrollPane(ucitaneSlike);
        scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.EAST);

        MyMenuBar menu = new MyMenuBar(); // Kreiranje menija
        setJMenuBar(menu); // Postavljanje menija na prozor

        MyToolBar toolBar = new MyToolBar(); // Kreiranje toolbar-a
        add(toolBar, BorderLayout.NORTH); // Postavljanje toolbar-a na vrh prozora
    }


    @Override
    public void update(ErrorMessage errorMessage) {
        if(errorMessage.getType() == ErrorType.ERROR){
            JOptionPane.showMessageDialog(this, errorMessage.getFormatedMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        else if(errorMessage.getType() == ErrorType.NOTIFY){
            JOptionPane.showMessageDialog(this, errorMessage.getFormatedMessage(), "Notification", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void updateSize(double factor){
        setSize((int) ((double)windowWidth*factor), (int) ((double)windowHeight*factor));
        scrollPane.setPreferredSize(new Dimension((int)((double)scrollPaneWidth * factor), (int)((double)scrollPaneHeight * factor)));
    }
}
