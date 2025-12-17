package tabs.ucitaneslike.control;

import com.sun.tools.javac.Main;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import tabs.GraffPanel;
import tabs.ucitaneslike.proxy.ImageInterfejs;
import tabs.ucitaneslike.proxy.ProxyImage;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;

public class UcitajSlikuControl implements ActionListener {

    ImageInterfejs proxyImg;

    public UcitajSlikuControl(ImageInterfejs proxyImg) {
        this.proxyImg = proxyImg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffPanel panel = (GraffPanel) MainFrame.getInstance().getTabbedPane().getSelectedComponent();
        panel.getSlideController().addLocalImageAgain((ProxyImage) proxyImg);
    }
}
