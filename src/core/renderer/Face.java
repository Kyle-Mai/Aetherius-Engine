package core.renderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Face {

    private Color color = Color.MAGENTA; //defaults to pink for objects without an overriding color/texture
    private ArrayList<Vertice> componentVectors = new ArrayList<>();

    public Face(Vertice...t) {
        componentVectors.addAll(Arrays.asList(t));
    }

    public Face(Color c, Vertice...t) {
        color = c;
        componentVectors.addAll(Arrays.asList(t));
    }

    public Color getColor() { return color; }
    public ArrayList<Vertice> getComponentVectors() { return componentVectors; }
    public void setComponentVectors(ArrayList<Vertice> v) { componentVectors = v; }

}
