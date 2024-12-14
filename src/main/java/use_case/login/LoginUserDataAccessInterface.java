package use_case.login;

import entity.User;
import entity.exceptions.DatabaseAccessException;

/**
 * DAO for the Login Use Case.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     * @throws DatabaseAccessException if there is an error accessing the database
     */
    boolean existsByName(String username) throws DatabaseAccessException;

    /**
     * Saves the user.
     * @param user the user to save
     * @throws DatabaseAccessException if there is an error accessing the database
     */
    void save(User user) throws DatabaseAccessException;

    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     * @throws DatabaseAccessException if there is an error accessing the database
     */
    User get(String username) throws DatabaseAccessException;

    /**
     * Returns the username of the curren user of the application.
     * @return the username of the current user; null indicates that no one is logged into the application.
     * @throws DatabaseAccessException if there is an error accessing the database
     */
    String getCurrentUsername() throws DatabaseAccessException;

    /**
     * Sets the username indicating who is the current user of the application.
     * @param username the new current username; null to indicate that no one is currently logged into the application.
     * @throws DatabaseAccessException if there is an error accessing the database
     */
    void setCurrentUsername(String username) throws DatabaseAccessException;
}
