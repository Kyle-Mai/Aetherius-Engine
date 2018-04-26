package core.renderer;

public class Vector3 {

    private double posx,posy,posz;

    public Vector3(double x, double y, double z) {
        posx = x;
        posy = y;
        posz = z;
    }

    public double getPosX() { return posx; }
    public double getPosY() { return posy; }
    public double getPosZ() { return posz; }
    public void setPosX(double x) { posx = x; }
    public void setPosY(double y) { posy = y; }
    public void setPosZ(double z) { posz = z; }

}
