package core.locale;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/*
Lolita's Revenge
July 07 2017

Handles lists of strings that can be pulled from to generate something like a name.
*/

public class NameList {

    /*------------------------------------------------------------------------------------------------------------------
     Variables.
     Used to determine the function of the class.
     */

    private boolean useDuplicates = false; //Whether or not the NameList will use the same strings multiple times.
    private boolean storeUsed = true; //Whether or not the NameList will store the used strings in the 'used' ArrayList.
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> used = new ArrayList<>();

    /*------------------------------------------------------------------------------------------------------------------
     Constructors.
     Used to construct instances of the NameList.
     */

    public NameList() {}

    public NameList(boolean store, boolean dupl) {
        storeUsed = store;
        useDuplicates = dupl;
    }

    /*------------------------------------------------------------------------------------------------------------------
     Accessible methods.
     Methods that can be accessed from outside the name list class
     */

    public boolean isUsingDuplicates() { return useDuplicates; }
    public void setDuplicate(boolean b) { useDuplicates = b; }

    public boolean isStoringUsed() { return storeUsed; }
    public void setStoreUsed(boolean b) { storeUsed = b; }

    public void add(String s) { list.add(s); }
    public void add(String... s) {
        for (int i = 0; i < s.length; i++) { list.add(s[i]); } //NO IT CAN'T
    }

    public void clearUsed() { used.clear(); }
    public void clearList() { list.clear(); }
    public void clearAll() {
        list.clear();
        used.clear();
    }

    public void reset() { //Resets the list to the default state.
        if (used.size() > 0) {
            list.addAll(used);
            used.clear();
        }
    }

    public String getAt(int i) { return list.get(i); }
    public String getUsed(int i) { return used.get(i); }
    public void remove(int i) { list.remove(i); }
    public int size() { return list.size(); }

    public String generate() throws NoSuchElementException { //Gets a name from the name list.
        if (list.size() <= 0) throw new NoSuchElementException();
        Random r = new Random();
        int i = r.nextInt(list.size());
        if (storeUsed) { used.add(list.get(i)); } //Stores a list of the used strings if applicable.
        if (!useDuplicates) { list.remove(i); } //If we aren't reusing names, remove this one from the primary list.
        return used.get(used.size() - 1);
    }

    //------------------------------------------------------------------------------------------------------------------

}
