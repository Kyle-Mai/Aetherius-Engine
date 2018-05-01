package core.renderer;

/**
 * A point in 3D space
 */

public class Point3 {

    private double px, py, pz;

    public Point3(double x, double y, double z) {
        px = x;
        py = y;
        pz = z;
    }

    public void setPosX(double x) { px = x; }
    public void setPosY(double y) { py = y; }
    public void setPosZ(double z) { pz = z; }

    public double getPosX() { return px; }
    public double getPosY() { return py; }
    public double getPosZ() { return pz; }
    public double[] getPoints() { //returns the point's full coordinate frame in x,y,z format
        double[] temp = new double[3];
        temp[0] = px;
        temp[1] = py;
        temp[2] = pz;
        return temp;
    }

}
