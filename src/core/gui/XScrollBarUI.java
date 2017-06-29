package core.gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/*
Lolita's Revenge
June 29 2017

Creates a custom scroll bar design.
 */

public class XScrollBarUI extends BasicScrollBarUI implements XConstants {

    Color foreground = WHITE;
    Color background = GREY;

    public XScrollBarUI(Color fore, Color back) { //sets up the UI colors
        this.foreground = fore;
        this.background = back;
    }

    public void setForegroundColor(Color c) { this.foreground = c; }
    public void setBackgroundColor(Color c) { this.background = c; }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(background);
        g2d.fill(trackBounds);
        g2d.draw(trackBounds);

    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(foreground);
        g2d.fill(thumbBounds);

    }

    @Override
    protected JButton createIncreaseButton(int i) { return createZeroButton(); }

    @Override
    protected JButton createDecreaseButton(int orientation) { return createZeroButton(); }

    //removes the buttons
    private JButton createZeroButton() {
        JButton button = new JButton("What button?");
        Dimension zeroDim = new Dimension(0,0);
        button.setPreferredSize(zeroDim);
        button.setMinimumSize(zeroDim);
        button.setMaximumSize(zeroDim);
        return button;

    }

}
