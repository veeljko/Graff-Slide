package raf.graffito.dsw.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractGraffAction{

    public AboutUsAction() {
        putValue(ACCELERATOR_KEY, null); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, super.loadIcon("/images/aboutus.png")); // Postavljanje ikonice
        putValue(NAME, "About Us"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "About Us"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // String s = "Projekat rade studenti: \n" + "1. Milenko Milosavljević RN 17/24 \n" + "2. Veljko Mladenović RN 15/24\n";
        //JOptionPane.showMessageDialog(null, s, "ABOUT US", JOptionPane.INFORMATION_MESSAGE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));

        JTextArea textArea = new JTextArea(
                "Projekat rade studenti:\n" +
                        "1. Milenko Milosavljević RN 17/24\n" +
                        "2. Veljko Mladenović RN 15/24"
        );
        textArea.setEditable(false);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JLabel leftImage = new JLabel(super.loadIcon("/images/sundjerbob.png"));
        JLabel rightImage = new JLabel(super.loadIcon("/images/patrik.png"));

        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        imagePanel.add(leftImage);
        imagePanel.add(rightImage);

        panel.add(textArea, BorderLayout.NORTH);
        panel.add(imagePanel, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(null, panel, "ABOUT US", JOptionPane.INFORMATION_MESSAGE);
    }
}
