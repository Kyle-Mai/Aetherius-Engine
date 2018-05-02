package core.renderer;

import java.awt.*;

public class Polygon3 extends Polygon implements Comparable<Polygon3> {

    private int z;
    private boolean isVisible = true;
    private Color color;

    public int getZ() { return z; }
    public void setZ(int z) { this.z = z; }

    public int compareTo(Polygon3 o) {
        if (o.getZ() - z < 0) {
            return -1;
        } else if (o.getZ() - z > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean isVisible() { return isVisible; }
    public void setVisible(boolean b) { isVisible = b; }
    public Color getColor() { return color; }
    public void setColor(Color c) { color = c; }

}
