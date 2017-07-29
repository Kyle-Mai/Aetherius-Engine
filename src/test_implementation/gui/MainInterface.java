package test_implementation.gui;

import core.gui.XButton;
import core.gui.XConstants;
import core.gui.XFrame;

import java.awt.*;

/**
 * Lolita's Revenge
 * 7/29/2017
 */

public class MainInterface {

    private XFrame frame;

    public MainInterface() {
        frame = new XFrame("Test Program");
        frame.setSize(800, 600);
        frame.centerOnScreen();
        frame.setBackground(XConstants.BLUE);
        frame.setVisible(true);

        XButton btnExit = new XButton();
        frame.add(btnExit);
        btnExit.setBounds(5, 5, 30, 30);
        btnExit.setText("X");
        btnExit.setFont(new Font("Arial", Font.ITALIC, 8));
        btnExit.setBackground(new Color(80, 80, 70, 255));

        frame.refresh();
    }

    public XFrame getFrame() { return frame; }

}
