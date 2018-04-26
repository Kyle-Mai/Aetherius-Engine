package core.renderer;

public class Orientation {

    private double rotx, roty, rotz;

    public Orientation(double X, double Y, double Z) {
        rotx = X;
        roty = Y;
        rotz = Z;

    }

    public double getRotX() { return rotx; }
    public double getRotY() { return roty; }
    public double getRotZ() { return rotz; }

}
