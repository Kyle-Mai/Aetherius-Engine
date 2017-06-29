package core.gui;

/*
Lolita's Revenge
June 29 2017

Extends the JPanel with new methods and constructors for more freedom of manipulation.
 */

import javax.swing.*;
import java.awt.*;

public class XPanel extends JPanel {

    private int posX, posY;

    /** Main Constructor **/

    public XPanel(Color backgroundColor) {
        this.setLayout(null);
        this.setBackground(backgroundColor);
        this.setFocusable(false);
        this.setOpaque(true);
        this.setBorder(null);

    }

    public XPanel() {
        this.setLayout(null);
        this.setFocusable(false);
        this.setOpaque(false);
        this.setBorder(null);

    }

    public int getPosX() { return this.posX; }
    public int getPosY() { return this.posY; }
    public void setPosValues(int x, int y) { this.posX = x; this.posY = y; }


    /** Base Methods **/
    //Methods used by the UI core and most UI elements.

    //paints components onto the main window
    @Override
    protected void paintComponent(Graphics gfx) {
        super.paintComponent(gfx);

    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }

}
