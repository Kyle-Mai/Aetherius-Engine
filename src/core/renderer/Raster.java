package core.renderer;

import core.gui.XFrame;
import core.gui.XPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Test class.
 */

public class Raster {

    Graphics g;
    int a = 0;
    Point3 camera = new Point3(-3, -2, -7);
    Orientation3 camrot = new Orientation3(Math.toRadians(0), Math.toRadians(25), Math.toRadians(0));

    private ArrayList<Vertice> verts = new ArrayList<>();
    int fovlevel = 500;
    Point3 fov = new Point3(0, 0, 700);
    //Vector3 fovsc = new Vector3(25, 25, 500);
    double viewangle = 2*Math.atan(1/fov.getPosZ());

    double aspRatio = 2000 / 1200;
    double viewH = 100;
    double viewV = Math.atan(Math.tan(viewH / 2) * (1200 / 2000));

    private double dx, dy, dz;
    private double x, y, z;
    private Double bx, by;

    private ArrayList<Vector2> rendered = new ArrayList<>();
    private ArrayList<line> lines = new ArrayList<>();
    //private ArrayList<Face> faces = new ArrayList<>();
    private ArrayList<Object3> objects = new ArrayList<>();
    //private ArrayList<Polygon3> polys = new ArrayList<>();
    private PriorityQueue<Polygon3> polys = new PriorityQueue<>();

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

        Object3 cube = new Object3(
                new Vector3(0, 0, 0),
                new Face(Color.RED, new Vertice(new Point3(1, 0, 0), new Point3(1, 1, 0), new Point3(1, 1, 1), 1), new Vertice(new Point3(1, 0, 0), new Point3(1, 0, 1), new Point3(1, 1, 1), -1)),
                new Face(Color.ORANGE, new Vertice(new Point3(0, 0, 1), new Point3(1, 0, 1), new Point3(1, 1, 1), 1), new Vertice(new Point3(0, 0, 1), new Point3(0, 1, 1), new Point3(1, 1, 1), -1)),
                new Face(Color.black, new Vertice(new Point3(0, 0, 0), new Point3(0, 1, 0), new Point3(0, 1, 1), -1), new Vertice(new Point3(0, 0, 0), new Point3(0, 0, 1), new Point3(0, 1, 1), 1)),
                new Face(Color.green, new Vertice(new Point3(0, 0, 0), new Point3(1, 0, 0), new Point3(1, 1, 0), -1), new Vertice(new Point3(0, 0, 0), new Point3(0, 1, 0), new Point3(1, 1, 0), 1)),
                new Face(Color.darkGray, new Vertice(new Point3(0, 1, 0), new Point3(0, 1, 1), new Point3(1, 1, 0), 1), new Vertice(new Point3(1, 1, 1), new Point3(1, 1, 0), new Point3(0, 1, 1), 1)),
                new Face(Color.CYAN, new Vertice(new Point3(0, 0, 0), new Point3(1, 0, 0), new Point3(0, 0, 1), 1), new Vertice(new Point3(1, 0, 1), new Point3(0, 0, 1), new Point3(1, 0, 0), 1))
        );
        cube.scale(new Vector3(2, 2, 2));
        objects.add(cube);

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
        maths();
        x = v.getPointB().getPosX() - camera.getPosX();
        y = v.getPointB().getPosY() - camera.getPosY();
        z = v.getPointB().getPosZ() - camera.getPosZ();
        maths();
        x = v.getPointC().getPosX() - camera.getPosX();
        y = v.getPointC().getPosY() - camera.getPosY();
        z = v.getPointC().getPosZ() - camera.getPosZ();
        maths();


        lines.add(new line(rendered.get(0).getPosX().intValue() + xx, rendered.get(0).getPosY().intValue() + yy, rendered.get(1).getPosX().intValue() + xx, rendered.get(1).getPosY().intValue() + yy));
        lines.add(new line(rendered.get(1).getPosX().intValue() + xx, rendered.get(1).getPosY().intValue() + yy, rendered.get(2).getPosX().intValue() + xx, rendered.get(2).getPosY().intValue() + yy));
        lines.add(new line(rendered.get(2).getPosX().intValue() + xx, rendered.get(2).getPosY().intValue() + yy, rendered.get(0).getPosX().intValue() + xx, rendered.get(0).getPosY().intValue() + yy));
    }

    public void render(Face f) {
        int xx = frame.getWidth() / 2;
        int yy = frame.getHeight() / 2;
        int zmax = 0, zmin = 0;

        for (int i = 0; i < f.getComponentVectors().size(); i++) {
            Vertice v = f.getComponentVectors().get(i);
            Polygon3 poly = new Polygon3();
            rendered.clear();
            x = v.getPointA().getPosX() - camera.getPosX();
            y = v.getPointA().getPosY() - camera.getPosY();
            z = v.getPointA().getPosZ() - camera.getPosZ();
            //System.out.println(x + " " +  y + " " + z);
            maths();
            if (!poly.contains(bx + xx, by + yy) && dz > 0) {
                poly.addPoint(bx.intValue() + xx, by.intValue() + yy);
                zmax = Math.max(zmax, (int)dz);
                zmin = Math.min(zmin, (int)dz);
            }
            x = v.getPointB().getPosX() - camera.getPosX();
            y = v.getPointB().getPosY() - camera.getPosY();
            z = v.getPointB().getPosZ() - camera.getPosZ();
            maths();
            if (!poly.contains(bx + xx, by + yy) && dz > 0) {
                poly.addPoint(bx.intValue() + xx, by.intValue() + yy);
                zmax = Math.max(zmax, (int)dz);
                zmin = Math.min(zmin, (int)dz);
            }
            x = v.getPointC().getPosX() - camera.getPosX();
            y = v.getPointC().getPosY() - camera.getPosY();
            z = v.getPointC().getPosZ() - camera.getPosZ();
            maths();
            if (!poly.contains(bx + xx, by + yy) && dz > 0) {
                poly.addPoint(bx.intValue() + xx, by.intValue() + yy);
                zmax = Math.max(zmax, (int)dz);
                zmin = Math.min(zmin, (int)dz);
            }
            poly.setColor(f.getColor());
            //Vector3 temp = new Vector3(v.getOrigin().getPosX()-camera.getPosX(), v.getOrigin().getPosY()-camera.getPosY(), v.getOrigin().getPosZ()-camera.getPosZ());
            //System.out.println(Vectors.scale(new Vector3(v.getOrigin().getPosX()-camera.getPosX(), v.getOrigin().getPosY()-camera.getPosY(), v.getOrigin().getPosZ()-camera.getPosZ()), v.getNormal()).getPointB().getPosY());
            //System.out.println(Vectors.scale(new Vector3(v.getOrigin().getPosX()-camera.getPosX(), v.getOrigin().getPosY()-camera.getPosY(), v.getOrigin().getPosZ()-camera.getPosZ()), v.getNormal()).getMagnitude());
            if (Vectors.dotProduct(new Vector3(v.getOrigin().getPosX()-camera.getPosX(), v.getOrigin().getPosY()-camera.getPosY(), v.getOrigin().getPosZ()-camera.getPosZ()), v.getNormal()) >= 0) {
                poly.setVisible(false);
            }

            //poly.setVisible(f.isVisible());
            poly.setZ((zmax + zmin) / 2);
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
            camera = new Point3(6 * Math.cos(Math.toRadians(a)) + 0, 3*Math.cos(Math.toRadians(a)) + 1,-6 * Math.sin(Math.toRadians(a)) - 0);
            camrot = new Orientation3(Math.toRadians(0), Math.toRadians(180) + Math.atan2(camera.getPosX() - 1, camera.getPosZ() - 1), 0); // Y X Z
            //objects.get(0).scale(new Vector3(40 * Math.cos(Math.toRadians(a)) + 50, 25 * Math.sin(Math.toRadians(a)) + 50,25 * Math.sin(Math.toRadians(a)) + 50));

            for (Object3 obj : objects) {
                for (Face f : obj.getFaces()) {
                    render(f);
                }
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

    private void maths() {
        dx = (Math.cos(camrot.getRotY()) * ((Math.sin(camrot.getRotZ())*y) + (Math.cos(camrot.getRotZ())*x))) - (Math.sin(camrot.getRotY())*z);
        dy = (Math.sin(camrot.getRotX()) * (Math.cos(camrot.getRotY())*z + (Math.sin(camrot.getRotY())*((Math.sin(camrot.getRotZ())*y) + (Math.cos(camrot.getRotZ())*x))))) + (Math.cos(camrot.getRotX())*((Math.cos(camrot.getRotZ())*y) - (Math.sin(camrot.getRotZ())*x)));
        dz = (Math.cos(camrot.getRotX()) * (Math.cos(camrot.getRotY())*z + (Math.sin(camrot.getRotY())*((Math.sin(camrot.getRotZ())*y) + (Math.cos(camrot.getRotZ())*x))))) - (Math.sin(camrot.getRotX())*((Math.cos(camrot.getRotZ())*y) - (Math.sin(camrot.getRotZ())*x)));
        bx = (fov.getPosZ() / dz)*dx - fov.getPosX();
        by = (fov.getPosZ() / dz)*dy - fov.getPosY();
        //bx = (dx*2000)/(dz*2000)*-fovlevel;
        //by = (dy*1200)/(dz*1200)*-fovlevel;
        //System.out.println(dx + " " + dy + " " + dz + " // " + bx + " " + by);
        if (dz > 0) {
            rendered.add(new Vector2(bx, by));
        }
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
            System.out.println("START RENDER CYCLE");
            while (!polys.isEmpty()) {
                //System.out.println(polys.peek().getBounds().height + " " + polys.peek().getBounds().width);
                //System.out.println(Math.atan2(polys.get(i).getBounds().height, polys.get(i).getBounds().width));
                try {
                    g.setColor(polys.peek().getColor());
                } catch (IllegalArgumentException e) {
                    g.setColor(Color.black);
                }
                //System.out.println(polys.peek().getColor());
                if (polys.peek().isVisible()) {
                    g.fillPolygon(polys.poll());
                } else {
                    polys.poll();
                }
            }
            for (int i =0; i < lines.size(); i++) {
                g.drawLine(lines.get(i).getX1(), lines.get(i).getY1(), lines.get(i).getX2(), lines.get(i).getY2());
            }
            //System.out.println("Painted");
            clearstuff();
        }

    }

}
