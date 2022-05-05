package main.exceptions;

/**
 * Exception thrown when a floor number is not valid.
 */
public class FloorNumberException extends Exception {
    public FloorNumberException(String message) {
        super(message);
    }
}
