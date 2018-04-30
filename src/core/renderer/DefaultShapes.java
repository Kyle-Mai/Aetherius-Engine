package core.renderer;

import java.awt.*;

public interface DefaultShapes {

    Object3 CUBE = new Object3(
            new Vector3(0, 0, 0),
            new Face(Color.RED, new Vertice(new Vector3(1, 0, 0), new Vector3(1, 1, 0), new Vector3(1, 1, 1), Color.RED), new Vertice(new Vector3(1, 0, 0), new Vector3(1, 0, 1), new Vector3(1, 1, 1), Color.red)),
            new Face(Color.ORANGE, new Vertice(new Vector3(0, 0, 1), new Vector3(1, 0, 1), new Vector3(1, 1, 1), Color.ORANGE), new Vertice(new Vector3(0, 0, 1), new Vector3(0, 1, 1), new Vector3(1, 1, 1), Color.ORANGE)),
            new Face(Color.black, new Vertice(new Vector3(0, 0, 0), new Vector3(0, 1, 0), new Vector3(0, 1, 1), Color.black), new Vertice(new Vector3(0, 0, 0), new Vector3(0, 0, 1), new Vector3(0, 1, 1), Color.black)),
            new Face(Color.green, new Vertice(new Vector3(0, 0, 0), new Vector3(1, 0, 0), new Vector3(1, 1, 0), Color.green), new Vertice(new Vector3(0, 0, 0), new Vector3(0, 1, 0), new Vector3(1, 1, 0), Color.green)),
            new Face(Color.darkGray, new Vertice(new Vector3(0, 1, 0), new Vector3(0, 1, 1), new Vector3(1, 1, 1), Color.darkGray), new Vertice(new Vector3(0, 1, 0), new Vector3(1, 1, 0), new Vector3(1, 1, 1), Color.darkGray)),
            new Face(Color.CYAN, new Vertice(new Vector3(0, 0, 1), new Vector3(1, 0, 1), new Vector3(0, 0, 0), Color.cyan), new Vertice(new Vector3(0, 0, 0), new Vector3(1, 0, 0), new Vector3(1, 0, 1), Color.cyan))
    );


}
