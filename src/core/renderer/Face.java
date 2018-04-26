package core.renderer;

import java.awt.*;
import java.util.ArrayList;

public class Face implements Renderable {

    private Color color;
    public ArrayList<Vertice> components = new ArrayList<>();

    public Face(Color c, Vertice...t) {
        color = c;
        for (int i = 0; i < t.length; i++) {
            components.add(t[i]);
        }
    }

    public Color getColor() { return color; }
    public ArrayList<Vertice> getComponents() { return components; }

}
