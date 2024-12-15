package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.User;
import entity.exceptions.DatabaseAccessException;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This
 * implementation does NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, String> passwords = new HashMap<>();
    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user, String password) {
        users.put(user.getName(), user);
        passwords.put(user.getName(), password);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public User authenticate(String email, String password) throws DatabaseAccessException {
        // For in-memory implementation, we'll authenticate using the stored password
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                final String storedPassword = passwords.get(user.getName());
                if (password.equals(storedPassword)) {
                    return user;
                }
                throw new DatabaseAccessException("Incorrect password.");
            }
        }
        throw new DatabaseAccessException("User not found with email: " + email);
    }

    @Override
    public void changePassword(User user, String newPassword) {
        passwords.put(user.getName(), newPassword);
    }

    @Override
    public boolean logout() {
        boolean success = false;
        if (getCurrentUsername() != null) {
            setCurrentUsername(null);
            success = true;
        }
        return success;
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }
}
