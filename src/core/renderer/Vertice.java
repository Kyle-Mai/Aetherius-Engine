package core.renderer;

public class Vertice implements Renderable {

    private Vector3 pointA, pointB, pointC;

    public Vertice(Vector3 a, Vector3 b, Vector3 c) {
        pointA = a;
        pointB = b;
        pointC = c;
    }

    public Vector3 getPointA() { return pointA; }
    public Vector3 getPointB() { return pointB; }
    public Vector3 getPointC() { return  pointC; }

}
