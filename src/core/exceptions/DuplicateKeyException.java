package core.exceptions;

/**
 * Lolita
 * Nov 21 2017
 * Exception thrown when a KeybindList is given a duplicate key.
 */

public class DuplicateKeyException extends Exception {

    public DuplicateKeyException() { super(); }
    public DuplicateKeyException(String msg) { super(msg); }
    public DuplicateKeyException(String msg, Throwable cause) { super(msg, cause); }
    public DuplicateKeyException(Throwable cause) { super(cause); }

}
