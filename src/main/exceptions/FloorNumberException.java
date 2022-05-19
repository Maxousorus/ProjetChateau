package main.exceptions;

/**
 * Exception thrown when a floor number is not valid.
 */
public class FloorNumberException extends Exception {
    /**
     * Instantiates a new Floor number exception.
     *
     * @param message the message
     */
    public FloorNumberException(String message) {
        super(message);
    }
}
