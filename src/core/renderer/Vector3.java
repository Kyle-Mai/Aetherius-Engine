package core.renderer;

/**
 * Vector in 3-space defined by either two points (POSITION_VECTOR), or one point (UNIT_VECTOR).
 */

public class Vector3 implements VectorConstants {

    private Point3 p1, p2;
    private int type;

    public Vector3(double x, double y, double z) {
        type = UNIT_VECTOR;
        p1 = new Point3(0, 0, 0);
        p2 = new Point3(x, y, z);
    }

    public Vector3(Vector3 c) {
        type = c.getType();
        p1 = c.getPointA();
        p2 = c.getPointB();
    }

    public Vector3(Point3 a, Point3 b) {
        type = POSITION_VECTOR;
        p1 = a;
        p2 = b;
    }

    public Vector3(Point3 a) {
        type = UNIT_VECTOR;
        p1 = new Point3(0, 0, 0);
        p2 = a;
    }

    public void setPointA(Point3 p) { p1 = p; }
    public void setPointB(Point3 p) { p2 = p; }

    public int getType() { return type; }
    public Point3 getPointA() { return p1; }
    public Point3 getPointB() { return p2; }
    public Point3[] getPoints() {
        Point3[] temp = new Point3[2];
        temp[0] = p1;
        temp[1] = p2;
        return temp;
    }
    public Vector3 getUnitVector() {
        return new Vector3(p2.getPosX()-p1.getPosX(), p2.getPosY()-p1.getPosY(), p2.getPosZ()-p1.getPosZ());
    }
    public double getMagnitude() {
        //return (p2.getPosX()*p1.getPosX()) + (p2.getPosY()*p1.getPosY()) + (p2.getPosZ()*p1.getPosZ());
        return Math.sqrt(Math.exp(getUnitVector().getPointB().getPosX()) + Math.exp(getUnitVector().getPointB().getPosY()) + Math.exp(getUnitVector().getPointB().getPosZ()));
    }

}
