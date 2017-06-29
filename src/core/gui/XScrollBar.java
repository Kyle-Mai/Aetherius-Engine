package core.gui;

import javax.swing.*;
import java.awt.*;

/*
 Lolita's Revenge
 June 29 2017

 Extends the JScrollBar and allows for more freedom of manipulation.
 */

public class XScrollBar extends JScrollBar {

    //default constructor
    public XScrollBar(Color fore, Color back) {
        this.setBorder(null);
        this.setOpaque(true);
        this.setUI(new XScrollBarUI(fore, back));

    }

}
