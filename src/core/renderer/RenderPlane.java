package core.renderer;

import core.data.Array2D;
import java.util.List;

/**
 * Defines the default 3D plane coordinate frame used to orient objects in the rasterizer.
 */

public class RenderPlane {

    private int maxCells; //the maximum range of cells in the renderer
    private int renderRange; //the range of cells that will be sent to the renderer
    private int cellSize; //the x/z size of the cells

    private Array2D<RenderCell> cells;

    public RenderPlane() {
        renderRange = 1;
        cellSize = 10;
        maxCells = 10;
        cells = new Array2D<>(maxCells, maxCells);
    }

    public RenderPlane(int max) {
        renderRange = 1;
        cellSize = 10;
        maxCells = max;
        cells = new Array2D<>(maxCells, maxCells);
    }

    public RenderPlane(int max, int range) {
        renderRange = range;
        cellSize = 10;
        maxCells = max;
        cells = new Array2D<>(maxCells, maxCells);
    }

    public RenderPlane(int max, int range, int csize) {
        renderRange = range;
        cellSize = csize;
        maxCells = max;
        cells = new Array2D<>(maxCells, maxCells);
    }

    public void addCell(RenderCell c, int y, int x) {
        if (y > cells.getColumns() && x > cells.getRows()) throw new IndexOutOfBoundsException("Attempted to place the render cell beyond the scope of the range.");
        cells.add(y, x, c);
    }

    public List<RenderCell> getRenderedCells(Point3 position) { //returns all of the cells within the render range
        int posx = (int)Math.round(((maxCells)/2) + (position.getPosX()/cellSize));
        int posy = (int)Math.round(((maxCells)/2) + (position.getPosZ()/cellSize));
        //System.out.println(position.getPosX());
        //System.out.println(posy);
        return cells.getSubArray(Math.max(0, posy-renderRange), Math.max(0, posx-renderRange), Math.min(maxCells-1, posy+renderRange), Math.min(maxCells-1, posx+renderRange)).getAll();
    }

    public void addToCells(Object3 r) {
        int posx = (int)Math.round(((maxCells)/2) + (r.getOrigin().getPosX()/cellSize));
        int posy = (int)Math.round(((maxCells)/2) + (r.getOrigin().getPosZ()/cellSize));
        System.out.println(posx + " " + posy + " || " + r.getOrigin().getPosX() + " " + r.getOrigin().getPosZ());
        if (cells.get(posy, posx) != null) { //if the render cell doesn't exist, create it
            cells.get(posy, posx).addObject(r);
        } else {
            cells.add(posy, posx, new RenderCell());
            cells.get(posy, posx).addObject(r);
        }
    }

    public void setRenderRange(int i) { renderRange = i; }
    public void setCellSize(int s) { cellSize = s; }
    public int getRenderRange() { return renderRange; }
    public int getCellSize() { return cellSize; }

}
