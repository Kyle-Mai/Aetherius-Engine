package core.renderer;

public class Vector3 {

    private double posx,posy,posz, posx2, posy2, posz2;
    private double[] points = {posx, posy, posz};

    public Vector3(double x, double y, double z) {
        posx = x;
        posy = y;
        posz = z;
        posx2 = x;
        posy2 = y;
        posz2 = z;
    }

    public double getPosX() { return posx; }
    public double getPosY() { return posy; }
    public double getPosZ() { return posz; }
    public double[] getPoints() { return points; }
    public void setPosX(double x) { posx = x; }
    public void setPosY(double y) { posy = y; }
    public void setPosZ(double z) { posz = z; }

    public void scale(Vector3 sc) {
        Vector3 temp = Vectors.scale(new Vector3(posx2, posy2, posz2), sc);
        setPosX(temp.getPosX());
        setPosY(temp.getPosY());
        setPosZ(temp.getPosZ());
    }

    public void translate(Vector3 tr) {
        Vector3 temp = Vectors.translate(this, tr);
        setPosX(temp.getPosX());
        setPosY(temp.getPosY());
        setPosZ(temp.getPosZ());
    }

}
