package core.renderer;

import java.util.ArrayList;

/**
 * Used by the RenderPlane to define cells.
 */

public class RenderCell {

    private ArrayList<Object3> items = new ArrayList<>();

    public RenderCell() {}

    public void addObject(Object3 obj) { items.add(obj); }
    public void removeObject(Object3 obj) { items.remove(obj); }
    public void removeAtIndex(int i) { items.remove(i); }
    public boolean containsObject(Object3 obj) { return items.contains(obj); }
    public void clear() { items.clear(); }
    public int getItemCount() { return items.size(); }
    public ArrayList<Object3> getItems() { return items; }


}
