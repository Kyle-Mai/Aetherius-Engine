package core.renderer;

import java.awt.*;

public interface DefaultShapes {

    Object3 CUBE = new Object3(
            new Point3(0, 0, 0),
            new Face(Color.RED, new Vertice(new Point3(1, 0, 0), new Point3(1, 1, 0), new Point3(1, 1, 1)), new Vertice(new Point3(1, 0, 0), new Point3(1, 0, 1), new Point3(1, 1, 1))),
            new Face(Color.ORANGE, new Vertice(new Point3(0, 0, 1), new Point3(1, 0, 1), new Point3(1, 1, 1)), new Vertice(new Point3(0, 0, 1), new Point3(0, 1, 1), new Point3(1, 1, 1))),
            new Face(Color.black, new Vertice(new Point3(0, 0, 0), new Point3(0, 1, 0), new Point3(0, 1, 1)), new Vertice(new Point3(0, 0, 0), new Point3(0, 0, 1), new Point3(0, 1, 1))),
            new Face(Color.green, new Vertice(new Point3(0, 0, 0), new Point3(1, 0, 0), new Point3(1, 1, 0)), new Vertice(new Point3(0, 0, 0), new Point3(0, 1, 0), new Point3(1, 1, 0))),
            new Face(Color.darkGray, new Vertice(new Point3(0, 1, 0), new Point3(0, 1, 1), new Point3(1, 1, 1)), new Vertice(new Point3(0, 1, 0), new Point3(1, 1, 0), new Point3(1, 1, 1))),
            new Face(Color.CYAN, new Vertice(new Point3(0, 0, 1), new Point3(1, 0, 1), new Point3(0, 0, 0)), new Vertice(new Point3(1, 0, 0), new Point3(0, 0, 0), new Point3(1, 0, 1)))
    );


}
