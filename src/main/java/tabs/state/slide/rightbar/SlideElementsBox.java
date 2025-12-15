package tabs.state.slide.rightbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class SlideElementsBox extends JPanel {
    private ArrayList<JButton> buttons = new ArrayList<>();

    public SlideElementsBox() {
        setLayout(new GridLayout(6, 1, 0, 10));
        setPreferredSize(new Dimension(80, 400));

        JButton addImage = new JButton("Add Image");
        addImage.setActionCommand("addLocalImage");
        add(addImage);

        createButton("/images/sundjerbob.png", "img1");
        createButton("/images/exit.png", "img2");
        createButton("/images/patrik.png", "img3");
        createButton("/images/slide.png", "logo");
        createButton("/images/text.png", "text");

        setBorder(BorderFactory.createLineBorder(Color.black));
        setOpaque(true);
    }

    private void createButton(String iconPath, String command) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(iconPath)));
        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        icon.setImage(scaledImage);

        JButton btn = new JButton(icon);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);

        btn.setActionCommand(command);

        buttons.add(btn);
        add(btn);
    }

    public void addController(ActionListener controller) {
        removeController();
        for (JButton b : buttons)
            b.addActionListener(controller);
    }

    public void removeController() {
        for (JButton b : buttons) {
            for (int i=0;i<b.getActionListeners().length;i++) {
                b.removeActionListener(b.getActionListeners()[i]);
            }
        }
    }
}
