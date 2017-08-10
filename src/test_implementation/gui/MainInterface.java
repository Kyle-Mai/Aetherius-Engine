package test_implementation.gui;

import core.gui.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Lolita's Revenge
 * 7/29/2017
 */

public class MainInterface {

    private final static File imageFolder = new File(System.getProperty("user.dir") + "/src/test_implementation/gui/resources/");

    private XFrame frame;
    private JLayeredPane main;
    private XSpriteMap monster;
    private XLabel monstersprite;
    private XGreyscale gsc;
    private XLabel backgroundimage;
    private XLabel header;

    public MainInterface() {
        frame = new XFrame("Test Program");
        main = new JLayeredPane();
        frame.setContentPane(main);
        frame.pack();
        frame.setSize(1800, 1000);
        main.setSize(frame.getSize());
        frame.centerOnScreen();
        main.setLocation(0, 0);
        frame.setVisible(true);
        main.setVisible(true);

        XButton btnExit = new XButton();
        main.add(btnExit, 5, 1);
        btnExit.setBounds(5, 5, 30, 30);
        btnExit.setText("X");
        btnExit.setFont(new Font("Arial", Font.ITALIC, 8));
        btnExit.setBackground(new Color(80, 80, 70, 255));

        btnExit.addMouseListener(new XMouseListener() {
            XButton source;

            @Override
            public void mouseClicked(MouseEvent e) {
                source = (XButton)e.getSource();
                frame.close();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        try {
            backgroundimage = new XLabel();
            main.add(backgroundimage, 1, 1);
            backgroundimage.setBounds(0, 0, main.getWidth(), main.getHeight());
            gsc = new XGreyscale(ImageIO.read(new File(imageFolder + "/background.jpg")));
            gsc.convertImage();
            backgroundimage.scaleImage(gsc.getSource());
            backgroundimage.setOpaque(true);
            backgroundimage.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        header = new XLabel("// TESTING //", new Font("Arial", Font.BOLD, 80), XConstants.GREEN);
        main.add(header, 10, 1);
        header.setBounds(0, 200, main.getWidth(), 100);
        header.setAlignments(SwingConstants.CENTER);
        header.setVisible(true);

        frame.refresh();
    }

    public void loadMonsterSprite() {
        BufferedImage monstermap;

        try {
            monstermap = ImageIO.read(new File(imageFolder + "/monster.png"));
            monster = new XSpriteMap("MonsterMap", 1, monstermap);
            monster.createSprites(false, XSpriteConstants.HORIZONTAL_MAP, 10);
            monster.flipAll();
            monstersprite = new XLabel(monster.get(monster.getCurrentIndex()));
            main.add(monstersprite, 2, 1);
            monstersprite.setBounds(0, 0, 1000, 1000);
            monstersprite.setBackground(XConstants.NONE);
            monstersprite.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public XFrame getFrame() { return frame; }
    public JLayeredPane getMain() { return main; }
    public XSpriteMap getMonster() { return monster; }
    public XLabel getMonstersprite() { return monstersprite; }
    public XLabel getBackgroundimage() { return backgroundimage; }
    public XGreyscale getGsc() { return gsc; }
    public XLabel getHeader() { return header; }

}
