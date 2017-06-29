package core.gui;

/*
Lolita's Revenge
June 29 2017

Extends the JFrame with new constructors and methods.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class XFrame extends JFrame {


    public XFrame(String text, BufferedImage icon) {
        this.setUndecorated(true);
        this.setResizable(false);
        this.setTitle(text); //sets the name of the program
        this.setIconImage(icon); //sets the program's icon image
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    //refreshes the GFX
    public void refresh() {
        this.revalidate();
        this.repaint();
    }

    public void minimize() { //minimizes the frame
        this.setState(Frame.ICONIFIED);
    }

    public void close() { //closes the program
        this.dispose(); //ensure the thread dies
        System.exit(0); //close the program
    }

}
