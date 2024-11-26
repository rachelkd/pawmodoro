package entity.exceptions;

/**
 * Exception thrown when there is an error retrieving a cat fact from the external API.
 * This could be due to network issues, API unavailability, or invalid responses.
 */
public class CatFactRetrievalException extends Exception {
    public CatFactRetrievalException(String message) {
        super(message);
    }

    public CatFactRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
