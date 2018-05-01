package core.renderer;

/**
 * Vector3 with a scale.
 */

public class ScaledVector3 extends Vector3 {

    private Vector3 original; //the original vector before any scaling was applied

    public ScaledVector3(Vector3 vc, Vector3 sc) {
        super(Vectors.scale(vc, sc));
        original = vc;
    }

    public ScaledVector3(ScaledVector3 vc, Vector3 sc) {
        super(Vectors.scale(vc.getOriginal(), sc));
        original = vc.getOriginal();
    }

    public Vector3 getOriginal() { return original; }

}
