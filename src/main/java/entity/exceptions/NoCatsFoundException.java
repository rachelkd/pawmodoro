package entity.exceptions;

/**
 * Exception thrown when a user has no cats.
 */
public class NoCatsFoundException extends RuntimeException {
    public NoCatsFoundException(String username) {
        super("No cats found for user: " + username);
    }
}
