package core.gui;

/*
Lolita's Revenge
June 29 2017

Extended tooltip with better control.
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class XToolTip extends JToolTip {

    public XToolTip(String text, Font font, Border border, Color background) {
        this.setTipText(text);
        this.setFont(font);
        this.setBorder(border);
        this.setBackground(background);

    }

}
