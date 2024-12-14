package use_case.change_password;

import entity.User;
import entity.exceptions.DatabaseAccessException;

/**
 * The interface of the DAO for the Change Password Use Case.
 */
public interface ChangePasswordUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     * @param user the user whose password is to be updated
     * @throws DatabaseAccessException if there is an error accessing the database
     */
    void changePassword(User user) throws DatabaseAccessException;

    /**
     * Checks if a user with the given name exists in the system.
     * @param username the name to check
     * @return true if a user with the given name exists, false otherwise
     * @throws DatabaseAccessException if there is an error accessing the database
     */
    boolean existsByName(String username) throws DatabaseAccessException;
}
