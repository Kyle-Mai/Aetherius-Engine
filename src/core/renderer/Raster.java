package core.renderer;

import core.gui.XFrame;
import core.gui.XPanel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Test class.
 */

public class Raster {

    Graphics g;
    int a = 0;
    Vector3 camera = new Vector3(100, 10, 100);
    Orientation camrot = new Orientation(Math.toRadians(0), Math.toRadians(0), Math.toRadians(0));

    private ArrayList<Vertice> verts = new ArrayList<>();

    Vector3 fov = new Vector3(0, 0, 500);
    double viewangle = 2*Math.atan(1/fov.getPosZ());

    double aspRatio = 2000 / 1200;
    double viewH = 100;
    double viewV = Math.atan(Math.tan(viewH / 2) * (1200 / 2000));

    private double dx, dy, dz;
    private double x, y, z;
    private Double bx, by;

    private ArrayList<Vector2> rendered = new ArrayList<>();
    private ArrayList<line> lines = new ArrayList<>();
    private ArrayList<Face> faces = new ArrayList<>();
    private ArrayList<Polygon> polys = new ArrayList<>();

    private XFrame frame;
    private test label;

    public void setup() {
        frame = new XFrame("Test Program");
        label = new test();
        frame.setContentPane(label);
        frame.pack();
        frame.setSize(2000, 1200);
        label.setSize(frame.getSize());
        frame.centerOnScreen();
        label.setLocation(0, 0);
        frame.setVisible(true);
        label.setVisible(true);
        //verts.add(new Vertice(new Vector3(0, 0, 50), new Vector3(50, 0, 50), new Vector3(0, 0, 0)));
        //verts.add(new Vertice(new Vector3(0, 0, 0), new Vector3(50, 0, 0), new Vector3(50, 0, 50)));
        //verts.add(new Vertice(new Vector3(50, 0, 0), new Vector3(50, 50, 0), new Vector3(50, 50, 50)));
        //verts.add(new Vertice(new Vector3(50, 0, 0), new Vector3(50, 0, 50), new Vector3(50, 50, 50)));
        //verts.add(new Vertice(new Vector3(0, 0, 50), new Vector3(50, 0, 50), new Vector3(50, 50, 50)));
        //verts.add(new Vertice(new Vector3(0, 0, 50), new Vector3(0, 50, 50), new Vector3(50, 50, 50)));
        //verts.add(new Vertice(new Vector3(0, 0, 0), new Vector3(0, 50, 0), new Vector3(0, 50, 50)));
        //verts.add(new Vertice(new Vector3(0, 0, 0), new Vector3(0, 0, 50), new Vector3(0, 50, 50)));
        //verts.add(new Vertice(new Vector3(0, 0, 50), new Vector3(50, 0, 50), new Vector3(50, 50, 50)));
        //verts.add(new Vertice(new Vector3(0, 0, 50), new Vector3(0, 50, 50), new Vector3(50, 50, 50)));
        //verts.add(new Vertice(new Vector3(0, 50, 0), new Vector3(0, 50, 50), new Vector3(50, 50, 50)));
        //verts.add(new Vertice(new Vector3(0, 50, 0), new Vector3(50, 50, 0), new Vector3(50, 50, 50)));
        faces.add(new Face(Color.CYAN, new Vertice(new Vector3(0, 0, 50), new Vector3(50, 0, 50), new Vector3(0, 0, 0)), new Vertice(new Vector3(0, 0, 0), new Vector3(50, 0, 0), new Vector3(50, 0, 50))));
        faces.add(new Face(Color.RED, new Vertice(new Vector3(50, 0, 0), new Vector3(50, 50, 0), new Vector3(50, 50, 50)), new Vertice(new Vector3(50, 0, 0), new Vector3(50, 0, 50), new Vector3(50, 50, 50))));
        faces.add(new Face(Color.ORANGE, new Vertice(new Vector3(0, 0, 50), new Vector3(50, 0, 50), new Vector3(50, 50, 50)), new Vertice(new Vector3(0, 0, 50), new Vector3(0, 50, 50), new Vector3(50, 50, 50))));
        faces.add(new Face(Color.black, new Vertice(new Vector3(0, 0, 0), new Vector3(0, 50, 0), new Vector3(0, 50, 50)), new Vertice(new Vector3(0, 0, 0), new Vector3(0, 0, 50), new Vector3(0, 50, 50))));
        faces.add(new Face(Color.green, new Vertice(new Vector3(0, 0, 50), new Vector3(50, 0, 50), new Vector3(50, 50, 50)), new Vertice(new Vector3(0, 0, 50), new Vector3(0, 50, 50), new Vector3(50, 50, 50))));
        faces.add(new Face(Color.darkGray, new Vertice(new Vector3(0, 50, 0), new Vector3(0, 50, 50), new Vector3(50, 50, 50)), new Vertice(new Vector3(0, 50, 0), new Vector3(50, 50, 0), new Vector3(50, 50, 50))));
        System.out.println("Completed setup");
        draw();
        anim();
    }

    public void render(Vertice v) {
        int xx = frame.getWidth() / 2;
        int yy = frame.getHeight() / 2;
        rendered.clear();
        x = v.getPointA().getPosX() - camera.getPosX();
        y = v.getPointA().getPosY() - camera.getPosY();
        z = v.getPointA().getPosZ() - camera.getPosZ();
        //System.out.println(x + " " +  y + " " + z);
        rendered.add(maths());
        x = v.getPointB().getPosX() - camera.getPosX();
        y = v.getPointB().getPosY() - camera.getPosY();
        z = v.getPointB().getPosZ() - camera.getPosZ();
        rendered.add(maths());
        x = v.getPointC().getPosX() - camera.getPosX();
        y = v.getPointC().getPosY() - camera.getPosY();
        z = v.getPointC().getPosZ() - camera.getPosZ();
        rendered.add(maths());


        lines.add(new line(rendered.get(0).getPosX().intValue() + xx, rendered.get(0).getPosY().intValue() + yy, rendered.get(1).getPosX().intValue() + xx, rendered.get(1).getPosY().intValue() + yy));
        lines.add(new line(rendered.get(1).getPosX().intValue() + xx, rendered.get(1).getPosY().intValue() + yy, rendered.get(2).getPosX().intValue() + xx, rendered.get(2).getPosY().intValue() + yy));
        lines.add(new line(rendered.get(2).getPosX().intValue() + xx, rendered.get(2).getPosY().intValue() + yy, rendered.get(0).getPosX().intValue() + xx, rendered.get(0).getPosY().intValue() + yy));
    }

    public void render(Face f) {
        int xx = frame.getWidth() / 2;
        int yy = frame.getHeight() / 2;

        for (int i = 0; i < f.getComponents().size(); i++) {
            Vertice v = f.getComponents().get(i);
            rendered.clear();
            x = v.getPointA().getPosX() - camera.getPosX();
            y = v.getPointA().getPosY() - camera.getPosY();
            z = v.getPointA().getPosZ() - camera.getPosZ();
            //System.out.println(x + " " +  y + " " + z);
            rendered.add(maths());
            x = v.getPointB().getPosX() - camera.getPosX();
            y = v.getPointB().getPosY() - camera.getPosY();
            z = v.getPointB().getPosZ() - camera.getPosZ();
            rendered.add(maths());
            x = v.getPointC().getPosX() - camera.getPosX();
            y = v.getPointC().getPosY() - camera.getPosY();
            z = v.getPointC().getPosZ() - camera.getPosZ();
            rendered.add(maths());
            Polygon poly = new Polygon();
            if (!poly.contains(rendered.get(0).getPosX().intValue() + xx, rendered.get(0).getPosY().intValue() + yy)) {
                poly.addPoint(rendered.get(0).getPosX().intValue() + xx, rendered.get(0).getPosY().intValue() + yy);
            }
            if (!poly.contains(rendered.get(1).getPosX().intValue() + xx, rendered.get(1).getPosY().intValue() + yy)) {
                poly.addPoint(rendered.get(1).getPosX().intValue() + xx, rendered.get(1).getPosY().intValue() + yy);
            }
            if (!poly.contains(rendered.get(2).getPosX().intValue() + xx, rendered.get(2).getPosY().intValue() + yy)) {
                poly.addPoint(rendered.get(2).getPosX().intValue() + xx, rendered.get(2).getPosY().intValue() + yy);
            }
            polys.add(poly);
        }
    }

    public void anim() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a += 1;
            camera = new Vector3(25 * Math.cos(Math.toRadians(a)) + 0, 50 * Math.sin(Math.toRadians(a)),25 * Math.sin(Math.toRadians(a)) - 80);
            //camrot = new Orientation(0, 0, Math.toRadians(a));
            for (int i = 0; i < faces.size(); i++) {
                render(faces.get(i));
            }
            draw();
            //System.out.println(lines.size());
        }
    }

    public void clearstuff() {

        lines.clear();
        polys.clear();
    }

    private void draw() {
        label.repaint();
        label.refresh();
    }

    private Vector2 maths() {
        dx = (Math.cos(camrot.getRotY()) * ((Math.sin(camrot.getRotY())*y) + (Math.cos(camrot.getRotZ())*x))) - (Math.sin(camrot.getRotY())*z);
        dy = (Math.sin(camrot.getRotX()) * (Math.cos(camrot.getRotY())*z + (Math.sin(camrot.getRotY())*((Math.sin(camrot.getRotZ())*y) + (Math.cos(camrot.getRotZ())*x))))) + (Math.cos(camrot.getRotX())*((Math.cos(camrot.getRotZ())*y) - (Math.sin(camrot.getRotZ())*x)));
        dz = (Math.cos(camrot.getRotX()) * (Math.cos(camrot.getRotY())*z + (Math.sin(camrot.getRotY())*((Math.sin(camrot.getRotZ())*y) + (Math.cos(camrot.getRotZ())*x))))) - (Math.sin(camrot.getRotX())*((Math.cos(camrot.getRotZ())*y) - (Math.sin(camrot.getRotZ())*x)));
        bx = (fov.getPosZ() / dz)*dx - fov.getPosX();
        by = (fov.getPosZ() / dz)*dy - fov.getPosY();

        //bx = (dx * Math.sin(camrot.getRotX())) / (dz * )
        //System.out.println(dx + " " + dy + " " + dz + " // " + bx + " " + by);
        return new Vector2(bx, by);
    }

    class line {
        int x1, x2, y1, y2;
        public line(Integer a, Integer b, Integer x, Integer y) {
            x1 = a;
            x2 = x;
            y1 = b;
            y2 = y;
        }

        int getX1() { return x1; }
        int getX2() { return x2; }
        int getY1() { return y1; }
        int getY2() { return y2; }
    }

    class test extends XPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            for (int i = 0; i < polys.size(); i++) {
                System.out.println(polys.get(i).getBounds().height + " " + polys.get(i).getBounds().width);
                g.setColor(new Color(255 - (polys.get(i).getBounds().height / 3), 255 - (polys.get(i).getBounds().height / 3), 255 - (polys.get(i).getBounds().width / 3)));
                g.fillPolygon(polys.get(i));
            }
            for (int i =0; i < lines.size(); i++) {
                g.drawLine(lines.get(i).getX1(), lines.get(i).getY1(), lines.get(i).getX2(), lines.get(i).getY2());
            }
            //System.out.println("Painted");
            clearstuff();
        }

    }

}
