package core.renderer;

import java.awt.*;

public class Vertice implements Renderable, RenderConstants {

    private Vector3 pointA, pointB, pointC, normal;
    private Vector3[] points = new Vector3[3];
    private boolean isVisible = true;
    private Color color;

    public Vertice(Vector3 a, Vector3 b, Vector3 c) {
        pointA = a;
        pointB = b;
        pointC = c;
        color = Color.BLACK;
        assignNormals(NORMAL_R);
    }

    public Vertice(Vector3 a, Vector3 b, Vector3 c, Color clr) {
        pointA = a;
        pointB = b;
        pointC = c;
        color = clr;
        assignNormals(NORMAL_R);
    }

    public Vertice(Vector3 a, Vector3 b, Vector3 c, int norm) {
        pointA = a;
        pointB = b;
        pointC = c;
        color = Color.BLACK;
        assignNormals(norm);
    }

    public Vertice(Vector3 a, Vector3 b, Vector3 c, Color clr, int norm) {
        pointA = a;
        pointB = b;
        pointC = c;
        color = clr;
        assignNormals(norm);
    }

    private void assignNormals(int norm) {
        points[0] = pointA;
        points[1] = pointB;
        points[2] = pointC;
        switch (norm) {
            case NORMAL_L:
                //normal = new Vector3(((pointB.getPosY()-pointA.getPosY())*(pointC.getPosZ()-pointA.getPosZ())) - ((pointB.getPosZ()-pointA.getPosZ())*(pointC.getPosY()-pointA.getPosY())), ((pointB.getPosZ()-pointA.getPosZ())*(pointC.getPosX()-pointA.getPosX())) - ((pointB.getPosX()-pointA.getPosX())*(pointC.getPosZ()-pointA.getPosZ())), ((pointB.getPosX()-pointA.getPosX())*(pointC.getPosY()-pointA.getPosY())) - ((pointB.getPosY()-pointA.getPosY())*(pointC.getPosX()-pointA.getPosX())));
                normal = new Vector3(-1*((pointB.getPosY()*pointC.getPosZ()) - (pointB.getPosZ()*pointC.getPosY())), -1*((pointB.getPosZ()*pointC.getPosX()) - (pointB.getPosX()*pointC.getPosZ())), -1*((pointB.getPosX()*pointC.getPosY()) - (pointB.getPosY()*pointC.getPosX())));
                return;
            case NORMAL_R:
                normal = new Vector3(((pointB.getPosY()*pointC.getPosZ()) - (pointB.getPosZ()*pointC.getPosY())),  ((pointB.getPosZ()*pointC.getPosX()) - (pointB.getPosX()*pointC.getPosZ())), ((pointB.getPosX()*pointC.getPosY()) - (pointB.getPosY()*pointC.getPosX())));
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void scale(Vector3 sc) {
        for (Vector3 vec : getPoints()) {
            vec.scale(sc);
        }
    }

    public void translate(Vector3 tr) {
        for (Vector3 vec : getPoints()) {
            vec.translate(tr);
        }
    }

    public Vector3 getPointA() { return pointA; }
    public Vector3 getPointB() { return pointB; }
    public Vector3 getPointC() { return  pointC; }
    public Vector3[] getPoints() { return points; }
    public Vector3 getNormal() { return normal; }
    public double getNormalMagnitude() {
        return Math.sqrt((Math.exp(normal.getPosX() - pointA.getPosX())) + (Math.exp(normal.getPosY() - pointA.getPosY())) + (Math.exp(normal.getPosZ() - pointA.getPosZ())));
    }

    public void setVisible(boolean t) { isVisible = t; }
    public boolean isVisible() {
        return isVisible;
    }

}
