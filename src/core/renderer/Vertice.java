package core.renderer;

public class Vertice implements VectorConstants, RenderConstants, Renderable {

    private int normalOrientation = NORMAL_R;
    private Vector3[] vectors = new Vector3[3];
    private boolean isVisible = true;

    public Vertice(Point3 o, Point3 p2, Point3 p3) {
        vectors[0] = new Vector3(p2, p3);
        vectors[1] = new Vector3(o, p2);
        vectors[2] = new Vector3(o, p3);
    }

    public Vertice(Point3 o, Point3 p2, Point3 p3, int normalOrient) {
        vectors[0] = new Vector3(p2, p3);
        vectors[1] = new Vector3(o, p2);
        vectors[2] = new Vector3(o, p3);
        normalOrientation = normalOrient;
    }

    public Vertice(Point3 o, Vector3 v) {
        vectors[0] = v;
        vectors[1] = new Vector3(o, v.getPointA());
        vectors[2] = new Vector3(o, v.getPointB());
    }

    public Vertice(Point3 o, Vector3 v, int normalOrient) {
        vectors[0] = v;
        vectors[1] = new Vector3(o, v.getPointA());
        vectors[2] = new Vector3(o, v.getPointB());
        normalOrientation = normalOrient;
    }

    public Vector3 getNormal() {
        Vector3 temp = getUnitNormal();
        return new Vector3(getOrigin(), new Point3(temp.getPointB().getPosX()+getOrigin().getPosX(), temp.getPointB().getPosY()+getOrigin().getPosY(), temp.getPointB().getPosZ()+getOrigin().getPosZ()));
    }

    public Vector3 getUnitNormal() { //returns a unit vector measure of the vertice's normal
        Vector3 vec = vectors[0];
        Point3 origin = getOrigin();
        Vector3 ntemp = new Vector3(new Point3(((vec.getPointA().getPosY()-origin.getPosY())*(vec.getPointB().getPosZ()-origin.getPosZ())) - ((vec.getPointA().getPosZ()-origin.getPosZ())*(vec.getPointB().getPosY()-origin.getPosY())), ((vec.getPointA().getPosZ()-origin.getPosZ())*(vec.getPointB().getPosX()-origin.getPosX())) - ((vec.getPointA().getPosX()-origin.getPosX())*(vec.getPointB().getPosZ()-origin.getPosZ())), ((vec.getPointA().getPosX()-origin.getPosX())*(vec.getPointB().getPosY()-origin.getPosY())) - ((vec.getPointA().getPosY()-origin.getPosY())*(vec.getPointB().getPosX()-origin.getPosX()))));
        switch (normalOrientation) {
            case NORMAL_L:
                return new Vector3(-1*(ntemp.getPointB().getPosX()/ntemp.getMagnitude()), -1*(ntemp.getPointB().getPosY()/ntemp.getMagnitude()), -1*(ntemp.getPointB().getPosZ()/ntemp.getMagnitude()));
           case NORMAL_R:
                return new Vector3(ntemp.getPointB().getPosX()/ntemp.getMagnitude(), ntemp.getPointB().getPosY()/ntemp.getMagnitude(), ntemp.getPointB().getPosZ()/ntemp.getMagnitude());
            default:
                throw new IllegalArgumentException("Normal orientation data invalid.");
        }
    }

    public void scale(Vector3 v) {
        vectors[0] = Vectors.scale(vectors[0], v);
        vectors[1] = Vectors.scale(vectors[1], v);
        vectors[2] = Vectors.scale(vectors[2], v);
    }

    public void translate(Vector3 tr) {
        vectors[0] = Vectors.translate(vectors[0], tr);
        vectors[1] = Vectors.translate(vectors[1], tr);
        vectors[2] = Vectors.translate(vectors[2], tr);
    }

    public int getNormalOrientation() { return normalOrientation; }
    public void setNormalOrientation(int n) { normalOrientation = n; }

    public Point3 getOrigin() {
        return vectors[1].getPointA();
    }
    public boolean isVisible() { return isVisible; }
    public Vector3[] getVectors() { return vectors; }

    public Point3 getPointA() { return vectors[0].getPointA(); }
    public Point3 getPointB() { return vectors[0].getPointB(); }
    public Point3 getPointC() { return getOrigin(); }
    public Point3[] getPoints() {
        Point3[] temp = new Point3[3];
        temp[0] = getOrigin();
        temp[1] = vectors[0].getPointA();
        temp[2] = vectors[0].getPointB();
        return temp;
    }

}
