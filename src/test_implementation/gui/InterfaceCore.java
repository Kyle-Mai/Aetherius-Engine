package test_implementation.gui;

import core.gui.*;
import javax.swing.*;

/**
 * KM
 * August 12 2017
 * Connects all of the different interface elements together.
 */

public class InterfaceCore {

    private XFrame main = new XFrame("");
    private JLayeredPane layers = new JLayeredPane();

    public InterfaceCore() {
        main.setContentPane(layers);
        main.pack();
        main.setSize(screenScale.W_1KHD.size());
        layers.setSize(main.getSize());
    }

    public XFrame getMain() { return main; }
    public JLayeredPane getLayers() { return layers; }

}
