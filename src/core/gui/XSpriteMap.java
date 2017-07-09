package core.gui;

/*
Lolita's Revenge
June 30 2017

Builds a sprite map to be used on a SwingEX component.
*/

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;


public class XSpriteMap implements XSpriteConstants {

    /*------------------------------------------------------------------------------------------------------------------
     Variables.
     Defines the function of the sprite map.
     */

    private int currentSprite = 0; //Index that will be called when getCurrentSprite() is called - can be used for animation maps
    private BufferedImage source; //Source image used to create the sprites
    private ArrayList<BufferedImage> sprites = new ArrayList<>(); //ArrayList of the sprites created from the source image

    private String spriteName; //Identifiers used to identify the sprite map, unnecessary
    private int ID;

    /*------------------------------------------------------------------------------------------------------------------
     Constructors.
     Used to construct instances of the sprite map.
     */

    public XSpriteMap() {}
    public XSpriteMap(String name) { spriteName = name; }
    public XSpriteMap(int identifier) { ID = identifier; }
    public XSpriteMap(BufferedImage s) { source = s; }

    public XSpriteMap(String name, BufferedImage s) {
        spriteName = name;
        source = s;
    }

    public XSpriteMap(int identifier, BufferedImage s) {
        source = s;
        ID = identifier;
    }

    public XSpriteMap(String name, int identifier, BufferedImage s) {
        spriteName = name;
        source = s;
        ID = identifier;
    }

    /*------------------------------------------------------------------------------------------------------------------
     Accessible methods.
     Can be accessed to edit the sprite map's characteristics.
     */

    public BufferedImage getSource() { return source; }
    public String getSpriteName() { return spriteName; }
    public int getID() { return ID; }
    public int getCurrentIndex() { return currentSprite; } //gets the current index, used with getCurrentSprite and defined by 'currentSprite'
    public BufferedImage getCurrentSprite() { return sprites.get(currentSprite); } //gets the currently indexed sprite specified by the int 'currentSprite'
    public BufferedImage getSprite(int i) { return sprites.get(i); } //gets the sprite at the specified index
    public BufferedImage getSprite(BufferedImage b) { return sprites.get(sprites.indexOf(b)); } //returns the equivalent sprite from the sprite map
    public int getIndexOf(BufferedImage b) { return sprites.indexOf(b); } //gets the index of the specified sprite
    public int getSpriteCount() { return sprites.size(); } //returns the number of sprites in the sprite map

    public void setSource(BufferedImage s) { source = s; } //Sets the source image used to create sprites.
    public void setCurrentIndex(int i) { currentSprite = i; } //sets the current indexed sprite that will be called with getCurrentSprite()
    public void setID(int i) { ID = i; } //sets the sprite map's ID - purely used to identify the sprite map in arrays and whatnot
    public void setSpriteName(String s) { spriteName = s; } //sets the sprite map's name - purely used to identify the sprite map in arrays and whatnot

    public void addSprite(BufferedImage b) { sprites.add(b); } //adds a sprite from a buffered image
    public void addSprite(int i, BufferedImage b) { sprites.add(i, b); } //adds a sprite to the specified index
    public void addSprites(BufferedImage... b) { sprites.addAll(Arrays.asList(b)); } //adds sprites from a set of buffered images
    public void removeSprite(int i) { sprites.remove(i); } //removes the sprite at the specified index
    public void removeSprite(BufferedImage b) { sprites.remove(b); } //removes the sprite that corresponds with the input image
    public void resizeSprite(int i, int x, int y) { sprites.get(i).getScaledInstance(x, y, Image.SCALE_SMOOTH); } //resizes the sprite at the specified index
    public void clear() { sprites.clear(); } //Removes all of the sprites from the sprite map.

    public void createSprites(boolean custom, int i1, int i2) { //Builds sprites from the source image.
        int width, height; //Width and height of each sprite.
        int x = 0, y = 0; //The current position index.
        if (source == null) throw new NullPointerException(); //No source -> cannot create sprites.

        if (!custom) { //Preset sprite map, i1 defines the style of sprites (HORIZONTAL_MAP / VERTICAL_MAP), i2 defines the number of sprites on the map
            if (i1 == HORIZONTAL_MAP) { //Creates sprites from a left-right oriented sprite map.
                height = source.getHeight();
                width = source.getWidth() / i2;
                for (int i = 0; i < i2; i++) {
                    try {
                        sprites.add(source.getSubimage(x, y, width, height));
                    } catch (Exception e) { //If an error occurs while generating the sprite, replace it with a null.
                        System.out.println("Error when generating sprite image: " + e.getMessage());
                        sprites.add(null);
                    }
                    x += width;
                }
                System.out.println("Sprites created.");
            } else if (i1 == VERTICAL_MAP) { //Creates sprites from a top-down oriented sprite map.
                height = source.getHeight() / i2;
                width = source.getWidth();
                for (int i = 0; i < i2; i++) {
                    try {
                        sprites.add(source.getSubimage(x, y, width, height));
                    } catch (Exception e) { //If an error occurs while generating the sprite, replace it with a null.
                        System.out.println("Error when generating sprite image: " + e.getMessage());
                        sprites.add(null);
                    }
                    y += height;
                }
                System.out.println("Sprites created.");
            } else { //Unknown map.
                System.out.println("Unknown mapping style.");
            }

        } else { //Custom sprite map, such as one with multiple rows and columns. i1 defines the number of sprites in the x-coordinate, i2 defines the number of sprites in the y-coordinate.
            width = source.getWidth() / i1;
            height = source.getHeight() / i2;

            for (int i = 0; i < i2; i++) {
                x = 0;
                for (int j = 0; j < i1; j++) {
                    try {
                        sprites.add(source.getSubimage(x, y, width, height));
                    } catch (Exception e) { //If an error occurs while generating the sprite, replace it with a null.
                        System.out.println("Error when generating sprite image: " + e.getMessage());
                        sprites.add(null);
                    }
                    x += width;
                }
                y += height;
            }
            System.out.println("Sprites created.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

}
