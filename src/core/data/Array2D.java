package core.data;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation designed to more efficiently manage 2D arrays, such that they require less overall code for each operation.
 * Data is stored in a y,x format.
 */

public class Array2D<E> {

    private List<List<E>> array = new ArrayList<>();

    public Array2D(List<List<E>> a) { array = a; }
    public Array2D(int c, int r) { //creates a 2D list with the specified number of rows and columns
        for (int i = 0; i < c; i++) {
            array.add(new ArrayList<>());
        }
        for (List<E> a : array) {
            for (int k = 0; k < r; k++) {
                a.add(null);
            }
        }
    }

    public void add(int c, int r, E e) { array.get(c).set(r, e); }
    public E get(int c, int r) { return array.get(c).get(r); }
    public void remove(int c, int r) { array.get(c).set(r, null); } //removes the object at the specified index, replacing it with null
    public List<E> getRowArray(int c) { return array.get(c); }
    public List<List<E>> getColumnArray() { return array; }
    public int getColumns() { return array.size(); } //returns the number of columns in the array
    public int getRows() { //returns the number of rows in the first column, this assumes that all columns have an equal number of rows
        if (array.size() > 0) {
            return array.get(0).size();
        } else {
            return -1;
        }
    }

    public List<E> getAll() { //returns a 1D list of all of the elements in the 2D array
        List<E> temp = new ArrayList<E>();
        for (List<E> a : array) {
            temp.addAll(a);
        }
        return temp;
    }

    public Array2D<E> getSubArray(int c1, int r1, int c2, int r2) { //returns a smaller 2D array from data within the array
        if (c1 < 0 || r1 < 0 || c2 > getColumns() || r2 > getRows()) throw new IndexOutOfBoundsException("Specified bounds exceed the scope of the parent 2D array.");
        List<List<E>> temp = array.subList(c1, c2);
        List<List<E>> temp2 = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            temp2.add(temp.get(i).subList(r1, r2));
        }
        return new Array2D<>(temp2);
    }

}
