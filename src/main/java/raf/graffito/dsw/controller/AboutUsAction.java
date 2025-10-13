package raf.graffito.dsw.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutUsAction extends AbstractGraffAction{

    public AboutUsAction() {
        putValue(ACCELERATOR_KEY, null); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, super.loadIcon("/images/aboutus.png")); // Postavljanje ikonice
        putValue(NAME, "About Us"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "About Us"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
