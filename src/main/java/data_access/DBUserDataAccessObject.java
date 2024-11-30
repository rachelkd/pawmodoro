package data_access;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import config.SupabaseConfig;
import entity.User;
import entity.UserFactory;
import entity.exceptions.UserNotFoundException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.adoption.AdoptionDataAccessInterface;
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
        LogoutUserDataAccessInterface,
        AdoptionDataAccessInterface {

    private static final String API_KEY_HEADER = "apikey";
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String PREFER_HEADER = "Prefer";
    private static final String PREFER_RETURN_MINIMAL = "return=minimal";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String EMPTY_JSON_ARRAY = "[]";

    private static final String USERS_ENDPOINT = "/rest/v1/users";
    private static final String USERNAME_QUERY = "?username=eq.";

    private static final String USERNAME_COLUMN = "username";
    private static final String PASSWORD_COLUMN = "password";

    private final OkHttpClient client = new OkHttpClient().newBuilder().build();
    private final String apiUrl = SupabaseConfig.getUrl();
    private final String apiKey = SupabaseConfig.getAnonKey();
    private final UserFactory userFactory;
    private String currentUsername;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Override
    public void changePassword(User user) {
        final JSONObject jsonBody = new JSONObject();
        jsonBody.put(USERNAME_COLUMN, user.getName());
        jsonBody.put(PASSWORD_COLUMN, user.getPassword());

        final RequestBody body = RequestBody.create(
                jsonBody.toString(),
                MediaType.parse(CONTENT_TYPE_JSON));

        final Request request = new Request.Builder()
                .url(apiUrl + USERS_ENDPOINT + USERNAME_QUERY + user.getName())
                .patch(body)
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON)
                .addHeader(PREFER_HEADER, PREFER_RETURN_MINIMAL)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new UserNotFoundException("Failed to update password");
            }
        }
        catch (IOException exception) {
            throw new DatabaseAccessException("Failed to access database", exception);
        }
    }

    @Override
    public User get(String username) {
        final Request request = new Request.Builder()
                .url(apiUrl + USERS_ENDPOINT + USERNAME_QUERY + username)
                .get()
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();

            if (!response.isSuccessful() || responseBody.equals(EMPTY_JSON_ARRAY)) {
                throw new UserNotFoundException("User not found: " + username);
            }

            final JSONArray jsonArray = new JSONArray(responseBody);
            final JSONObject userJson = jsonArray.getJSONObject(0);
            return userFactory.create(
                    userJson.getString(USERNAME_COLUMN),
                    userJson.getString(PASSWORD_COLUMN));
        }
        catch (IOException exception) {
            throw new DatabaseAccessException("Failed to access database", exception);
        }
    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    @Override
    public boolean existsByName(String username) {
        final Request request = new Request.Builder()
                .url(apiUrl + USERS_ENDPOINT + USERNAME_QUERY + username)
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();
            return response.isSuccessful() && !responseBody.equals(EMPTY_JSON_ARRAY);
        }
        catch (IOException exception) {
            throw new UserNotFoundException("Network error while checking user existence: " + exception.getMessage());
        }
    }

    @Override
    public void save(User user) {
        final JSONObject jsonBody = new JSONObject();
        jsonBody.put(USERNAME_COLUMN, user.getName());
        jsonBody.put(PASSWORD_COLUMN, user.getPassword());

        // POST METHOD
        final RequestBody body = RequestBody.create(
                jsonBody.toString(),
                MediaType.parse(CONTENT_TYPE_JSON));

        final Request request = new Request.Builder()
                .url(apiUrl + USERS_ENDPOINT)
                .post(body)
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON)
                .addHeader(PREFER_HEADER, PREFER_RETURN_MINIMAL)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new UserNotFoundException("Failed to save user");
            }
        }
        catch (IOException exception) {
            throw new UserNotFoundException("Network error while saving user: " + exception.getMessage());
        }
    }

    /**
     * Exception for database/network issues.
     */
    public static class DatabaseAccessException extends RuntimeException {
        public DatabaseAccessException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
