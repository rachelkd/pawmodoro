/**
 * TODO: Implement Supabase DAO.
 */

package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.SupabaseConfig;
import entity.User;
import entity.UserFactory;
import entity.exceptions.UserNotFoundException;
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

    // Database connection constants
    private static final String JDBC_PROTOCOL = "jdbc:postgresql://";
    private static final String DB_HOST = "aws-0-us-east-1.pooler.supabase.com";
    private static final String DB_NAME = "postgres";
    private static final String DB_PROJECT = "xuqqtivutcipoavzpfme";
    private static final String PORT = "6543";
    private static final String CONNECTION_URL = String.format("%s%s:%s/postgres?user=%s.%s&password=%s",
            JDBC_PROTOCOL,
            DB_HOST,
            PORT,
            DB_NAME,
            DB_PROJECT,
            SupabaseConfig.getDbPassword());

    // SQL query constants
    private static final String SELECT_USER = "SELECT username, password FROM users WHERE username = ?";
    private static final String COUNT_USERNAME = "SELECT COUNT(*) FROM users WHERE username = ?";
    private static final String INSERT_USER = "INSERT INTO users (username, password) VALUES (?, ?)";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE username = ?";

    private final UserFactory userFactory;
    private final Connection connection;
    private String currentUsername;

    public DBUserDataAccessObject(UserFactory userFactory) throws SQLException {
        this.userFactory = userFactory;
        this.connection = DriverManager.getConnection(CONNECTION_URL, SupabaseConfig.getDbUser(),
                SupabaseConfig.getDbPassword());
    }

    @Override
    public User get(String username) {
        try {
            final PreparedStatement statement = connection.prepareStatement(SELECT_USER);
            statement.setString(1, username);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return userFactory.create(
                        resultSet.getString("username"),
                        resultSet.getString("password"));
            }
            throw new UserNotFoundException("User not found");
        }
        catch (SQLException ex) {
            throw new UserNotFoundException(ex.getMessage());
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public boolean existsByName(String username) {
        try {
            final PreparedStatement statement = connection.prepareStatement(COUNT_USERNAME);
            statement.setString(1, username);
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) > 0;
        }
        catch (SQLException ex) {
            throw new UserNotFoundException(ex.getMessage());
        }
    }

    @Override
    public void save(User user) {
        try {
            final PreparedStatement statement = connection.prepareStatement(INSERT_USER);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            final int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new UserNotFoundException("Failed to save user");
            }
        }
        catch (SQLException ex) {
            throw new UserNotFoundException(ex.getMessage());
        }
    }

    @Override
    public void changePassword(User user) {
        try {
            final PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getName());
            final int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new UserNotFoundException("Failed to change password");
            }
        }
        catch (SQLException ex) {
            throw new UserNotFoundException(ex.getMessage());
        }
    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }
}
