package use_case.change_password;

import entity.User;

/**
 * The interface of the DAO for the Change Password Use Case.
 */
public interface ChangePasswordUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     * 
     * @param user the user whose password is to be updated
     */
    void changePassword(User user);

    /**
     * Checks if a user with the given name exists in the system.
     * 
     * @param username the name to check
     * @return true if a user with the given name exists, false otherwise
     */
    boolean existsByName(String username);
}
