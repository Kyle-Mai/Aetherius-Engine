package core.xml;

/**
 * Lolita
 * Sept 10 2017
 * Handles the generic operations of XML files.
 */

//TODO: Fill out.

public class XMLBuilder {

    private boolean allowOverwrite = true; //whether or not it will overwrite existing files when writing

    public XMLBuilder() {}

    public void setAllowOverwrite(boolean b) { allowOverwrite = b; }
    public boolean isAllowOverwrite() { return allowOverwrite; }


}
