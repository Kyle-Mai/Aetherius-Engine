package core.renderer;

public class Vector2 {

    private Double posx,posy;

    public Vector2(double x, double y) {
        posx = x;
        posy = y;
    }

    public Double getPosX() { return posx; }
    public Double getPosY() { return posy; }
    public void setPosX(double x) { posx = x; }
    public void setPosY(double y) { posy = y; }

}
