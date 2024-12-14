package use_case.signup;

import entity.User;
import entity.exceptions.DatabaseAccessException;

/**
 * DAO for the Signup Use Case.
 */
public interface SignupUserDataAccessInterface {

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
}
