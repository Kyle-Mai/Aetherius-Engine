package core.renderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Face implements Renderable {

    private Color color;
    public ArrayList<Vertice> components = new ArrayList<>();
    private boolean isVisible = true;

    public Face(Color c, Vertice...t) {
        color = c;
        components.addAll(Arrays.asList(t));
    }

    public void scale(Vector3 sc) {
        for (Vertice v : components) {
            for (Vector3 vec : v.getPoints()) {
                vec.scale(sc);
            }
        }
    }

    public void translate(Vector3 tr) {
        for (Vertice v : components) {
            for (Vector3 vec : v.getPoints()) {
                vec.translate(tr);
            }
        }
    }

    public Color getColor() { return color; }
    public ArrayList<Vertice> getComponents() { return components; }
    public void setVisible(boolean t) { isVisible = t; }

    public boolean isVisible() {
        return isVisible;
    }

}
