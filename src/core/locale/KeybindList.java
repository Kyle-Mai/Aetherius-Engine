package core.locale;

import core.exceptions.DuplicateKeyException;

import java.util.Hashtable;

/**
 * Lolita
 * Nov 21 2017
 * Handles key binding.
 */

public class KeybindList {

    private Hashtable<Character, Runnable> keybinds;

    public KeybindList() {}

    public void input(char c, Runnable event) throws DuplicateKeyException { //input a runnable into a key
        if (keybinds.containsKey(c)) {
            throw new DuplicateKeyException("Key is already in use by another binding.");
        } else {
            keybinds.put(c, event);
        }
    }

    public void remove(char c) { //removes the runnable tied to the specified key
        if (keybinds.containsKey(c)) {
            keybinds.remove(c);
        }
    }

    public void clear() { keybinds.clear(); } //clears all runnables/keys from the keybindlist
    public Runnable get(char c) { return keybinds.get(c); } //gets a runnable from a key
    public void inputOrReplace(char c, Runnable event) { keybinds.put(c, event); } //less safe input method that doesn't check for duplicates
    public boolean isInitialized() { return !keybinds.isEmpty(); } //whether or not the keybindList has any values assigned to it

}
