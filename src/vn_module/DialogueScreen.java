package vn_module;

import core.gui.XLabel;

/**
 * Lolita's Revenge
 * July 28 2017
 * Handles the typical VN-style dialogue / cutscene screen.
 */
public class DialogueScreen {

    private XLabel backdrop;
    private XLabel textbg;

    private boolean panningIn = false; //whether or not the characters will pan in during dialogue

    public boolean isPanningIn() { return panningIn; }
    public void setPanningIn(boolean b) { panningIn = b; }

}
