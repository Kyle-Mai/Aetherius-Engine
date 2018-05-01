package core.renderer;

/**
 * A point in 2D space.
 */

public class Point2 {

    private double px, py;

    public Point2(double x, double y) {
        px = x;
        py = y;
    }

    public void setPosX(double x) { px = x; }
    public void setPosY(double y) { py = y; }

    public double getPosX() { return px; }
    public double getPosY() { return py; }
    public double[] getPoints() { //returns the point's full coordinate frame in x,y,z format
        double[] temp = new double[2];
        temp[0] = px;
        temp[1] = py;
        return temp;
    }


}
