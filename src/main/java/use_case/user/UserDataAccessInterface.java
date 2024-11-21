package use_case.user;

import entity.User;

/**
 * Unified interface for user data access operations.
 */
public interface UserDataAccessInterface {
    /**
     * Checks if the given username exists.
     * 
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves the user.
     * 
     * @param user the user to save
     */
    void save(User user);

    /**
     * Returns the user with the given username.
     * 
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);

    /**
     * Returns the username of the current user of the application.
     * 
     * @return the username of the current user; null indicates that no one is
     *         logged into the application.
     */
    String getCurrentUsername();

    /**
     * Sets the username indicating who is the current user of the application.
     * 
     * @param username the new current username; null to indicate that no one is
     *            currently logged into the application.
     */
    void setCurrentUsername(String username);

    /**
     * Updates the system to record this user's password.
     * 
     * @param user the user whose password is to be updated
     */
    void changePassword(User user);
}
