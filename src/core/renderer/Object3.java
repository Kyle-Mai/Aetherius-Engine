package core.renderer;


import java.util.ArrayList;
import java.util.Arrays;


public class Object3 implements Renderable {

    private ArrayList<Face> faces = new ArrayList<>();
    private Point3 origin;
    private boolean isVisible = true;

    public Object3(Object3 copy) {
        faces.addAll(copy.getFaces());
        origin = copy.getOrigin();
        isVisible = copy.isVisible;
    }

    public Object3(Face...f) {
        faces.addAll(Arrays.asList(f));
    }

    public Object3(Point3 o, Face...f) {
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
        origin = Vectors.translate(new Vector3(origin.getPosX(), origin.getPosY(), origin.getPosZ()), tr).getPointB();
    }

    public ArrayList<Face> getFaces() { return faces; }
    public void setOrigin(Point3 o) { origin = o; }
    public Point3 getOrigin() { return origin; }
    public void setVisible(boolean b) { isVisible = b; }
    public boolean isVisible() { return isVisible; }

}
