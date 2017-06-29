package core.gui;

/*
Lolita's Revenge
June 29 2017

Extends the JSlider with new constructors and variables.
 */

import javax.swing.*;
import java.util.Dictionary;

public class XSlider extends JSlider {

    public XSlider(int var1, int var2, int var3, int var4) {
        super(var1, var2, var3, var4);
        this.setOpaque(false);
        this.setFocusable(false);
    }

    public void setTicks(int major, int minor) { //sets both the ticks at once
        this.setMajorTickSpacing(major);
        this.setMinorTickSpacing(minor);
        this.setPaintTicks(true);
    }

    public void setLabelTable(Dictionary resource) {
        super.setLabelTable(resource);
        this.setPaintLabels(true);
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }

}
