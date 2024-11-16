package entity.exceptions;

/**
 * Custom exception thrown when there is an error fetching cat images.
 */
public class CatImageFetchException extends Exception {

    /**
     * Constructs a new CatImageFetchException with the specified detail message.
     *
     * @param message The detail message explaining the error.
     */
    public CatImageFetchException(String message) {
        super(message);
    }

    /**
     * Constructs a new CatImageFetchException with the specified detail message and
     * cause.
     *
     * @param message The detail message explaining the error.
     * @param cause The cause of the error.
     */
    public CatImageFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
