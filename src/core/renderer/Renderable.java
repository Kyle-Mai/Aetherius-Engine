package core.renderer;

/**
 * Designates the class as a renderable object.
 */
public interface Renderable {
    boolean isVisible();
    void scale(Vector3 e);
    void translate(Vector3 e);
    Point3 getOrigin();
}
