package core.exceptions;

/**
 * Lolita
 * Nov 20 2017
 * Exception for invalid XML files.
 */

public class InvalidXMLException extends Exception {

    public InvalidXMLException() { super(); }
    public InvalidXMLException(String msg) { super(msg); }
    public InvalidXMLException(String msg, Throwable cause) { super(msg, cause); }
    public InvalidXMLException(Throwable cause) { super(cause); }

}
