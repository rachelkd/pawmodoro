package data_access;

import entity.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data using Supabase database.
 * Implements interfaces for signup, login, change password, and logout.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     *
     * @param user the user whose password is to be updated
     */
    @Override
    public void changePassword(User user) {

    }

    /**
     * Returns the user with the given username.
     *
     * @param username the username to look up
     * @return the user with the given username
     */
    @Override
    public User get(String username) {
        return null;
    }

    /**
     * Returns the username of the curren user of the application.
     *
     * @return the username of the current user; null indicates that no one is
     * logged into the application.
     */
    @Override
    public String getCurrentUsername() {
        return "";
    }

    /**
     * Sets the username indicating who is the current user of the application.
     *
     * @param username the new current username; null to indicate that no one is
     *                 currently logged into the application.
     */
    @Override
    public void setCurrentUsername(String username) {

    }

    /**
     * Checks if the given username exists.
     *
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    @Override
    public boolean existsByName(String username) {
        return false;
    }

    /**
     * Saves the user.
     *
     * @param user the user to save
     */
    @Override
    public void save(User user) {

    }
}
