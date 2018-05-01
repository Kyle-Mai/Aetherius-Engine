package core.renderer;


import java.util.ArrayList;
import java.util.Arrays;


public class Object3 implements Renderable {

    private ArrayList<Face> faces = new ArrayList<>();
    private Vector3 origin;
    private boolean isVisible = true;

    public Object3(Object3 copy) {
        faces.addAll(copy.getFaces());
        origin = copy.getOrigin();
        isVisible = copy.isVisible;
    }

    public Object3(Face...f) {
        faces.addAll(Arrays.asList(f));
    }

    public Object3(Vector3 o, Face...f) {
        faces.addAll(Arrays.asList(f));
        origin = o;
    }

    public void scale(Vector3 sc) {
        for (Face f : faces) {
            for (Vertice v : f.getComponentVectors()) {
                v.scale(sc);
            }
        }
    }

    public void translate(Vector3 tr) {
        for (Face f : faces) {
            for (Vertice v : f.getComponentVectors()) {
                v.translate(tr);
            }
        }
    }

    public ArrayList<Face> getFaces() { return faces; }
    public void setOrigin(Vector3 o) { origin = o; }
    public Vector3 getOrigin() { return origin; }
    public void setVisible(boolean b) { isVisible = b; }
    public boolean isVisible() { return isVisible; }

}
