package data_access;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import config.SupabaseConfig;
import entity.User;
import entity.UserFactory;
import entity.exceptions.DatabaseAccessException;
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
 * Data access object for user authentication and management using Supabase.
 */
public class DbUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        AdoptionDataAccessInterface {

    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String API_KEY_HEADER = "apikey";
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String EMPTY_ARRAY = "[]";
    private static final String ID_FIELD = "id";

    private static final String AUTH_SIGNUP_ENDPOINT = "/auth/v1/signup";
    private static final String AUTH_SIGNIN_ENDPOINT = "/auth/v1/token?grant_type=password";
    private static final String AUTH_SIGNOUT_ENDPOINT = "/auth/v1/logout";
    private static final String AUTH_PASSWORD_RESET = "/auth/v1/user/password";
    private static final String USER_PROFILES_ENDPOINT = "/rest/v1/user_profiles";
    private static final String AUTH_USERS_ENDPOINT = "/auth/v1/user";

    private final OkHttpClient client;
    private final String apiUrl;
    private final String apiKey;
    private final UserFactory userFactory;
    private String currentUsername;
    private String accessToken;

    /**
     * Creates a new DbUserDataAccessObject.
     * @param userFactory factory for creating User objects
     */
    public DbUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
        this.client = new OkHttpClient().newBuilder().build();
        this.apiUrl = SupabaseConfig.getUrl();
        this.apiKey = SupabaseConfig.getAnonKey();
    }

    @Override
    public void save(User user, String password) throws DatabaseAccessException {
        String userId = null;
        try {
            // First create the auth user
            final JSONObject authResponse = createAuthUser(user.getEmail(), password);
            userId = authResponse.getJSONObject("user").getString(ID_FIELD);
            // Get the access token from the response
            this.accessToken = authResponse.getString("access_token");

            // Then create the profile using the access token
            createUserProfile(userId, user.getName());
        }
        catch (DatabaseAccessException exception) {
            throw new DatabaseAccessException("Failed to create user: " + exception.getMessage());
        }
    }

    /**
     * Creates a new auth user in Supabase.
     * @param email the user's email
     * @param password the user's password
     * @return JSONObject containing the response from Supabase auth
     * @throws DatabaseAccessException if the user creation fails
     */
    private JSONObject createAuthUser(String email, String password) throws DatabaseAccessException {
        final JSONObject authBody = new JSONObject()
                .put("email", email)
                .put("password", password);

        final RequestBody body = RequestBody.create(
                authBody.toString(),
                MediaType.parse(CONTENT_TYPE_JSON));

        final Request request = new Request.Builder()
                .url(apiUrl + AUTH_SIGNUP_ENDPOINT)
                .post(body)
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON)
                .build();

        try (Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            if (!response.isSuccessful()) {
                throw new DatabaseAccessException(parseErrorMessage(responseBody));
            }
            return new JSONObject(responseBody);
        }
        catch (IOException exception) {
            throw new DatabaseAccessException("Failed to create auth user: " + exception.getMessage());
        }
    }

    /**
     * Parses the error message from a JSON response.
     * @param jsonResponse the JSON response
     * @return the error message
     */
    private String parseErrorMessage(String jsonResponse) {
        String result = "";
        try {
            final JSONObject error = new JSONObject(jsonResponse);
            result = error.getString("msg");
        }
        catch (JSONException exception) {
            result = jsonResponse;
        }
        return result;
    }

    /**
     * Creates a user profile in the public.profiles table.
     * @param userId the UUID of the auth user
     * @param username the username to save
     * @throws DatabaseAccessException if the profile creation fails
     */
    private void createUserProfile(String userId, String username) throws DatabaseAccessException {
        final JSONObject profileBody = new JSONObject()
                .put(ID_FIELD, userId)
                .put("username", username);

        final RequestBody body = RequestBody.create(
                profileBody.toString(),
                MediaType.parse(CONTENT_TYPE_JSON));

        final Request request = new Request.Builder()
                .url(apiUrl + USER_PROFILES_ENDPOINT)
                .post(body)
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + accessToken)
                .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON)
                .addHeader("Prefer", "return=minimal")
                .build();

        try (Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            if (!response.isSuccessful()) {
                throw new DatabaseAccessException("Failed to create user profile: " + responseBody);
            }
        }
        catch (IOException exception) {
            throw new DatabaseAccessException("Failed to create user profile: " + exception.getMessage());
        }
    }

    @Override
    public User get(String username) throws DatabaseAccessException {
        final Request request = new Request.Builder()
                .url(apiUrl + USER_PROFILES_ENDPOINT + "?username=eq." + username)
                .get()
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();

            if (!response.isSuccessful() || responseBody.equals(EMPTY_ARRAY)) {
                throw new UserNotFoundException("User not found: " + username);
            }

            // Get the first profile from the array
            final JSONObject userProfile = new JSONObject(responseBody).getJSONArray("data").getJSONObject(0);

            // Get the user's email from auth.users
            final String userId = userProfile.getString(ID_FIELD);
            final String email = getUserEmail(userId);

            return userFactory.create(
                    userProfile.getString("username"),
                    email);
        }
        catch (IOException exception) {
            throw new DatabaseAccessException("Failed to access database", exception);
        }
    }

    /**
     * Gets a user's email from the auth.users table.
     * @param userId the UUID of the user
     * @return the user's email address
     * @throws IOException if there is a network error
     * @throws DatabaseAccessException if the query fails
     * @throws UserNotFoundException if the user is not found
     */
    private String getUserEmail(String userId) throws IOException, DatabaseAccessException {
        final Request request = new Request.Builder()
                .url(apiUrl + AUTH_USERS_ENDPOINT + "?" + ID_FIELD + "=eq." + userId + "&select=email")
                .get()
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new DatabaseAccessException("Failed to get user email");
            }
            final String responseBody = response.body().string();
            if (responseBody.equals(EMPTY_ARRAY)) {
                throw new UserNotFoundException("User not found with ID: " + userId);
            }
            return new JSONObject(responseBody).getJSONArray("data").getJSONObject(0).getString("email");
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
    public boolean existsByName(String username) throws DatabaseAccessException {
        final Request request = new Request.Builder()
                .url(apiUrl + USER_PROFILES_ENDPOINT + "?username=eq." + username)
                .get()
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();
            return response.isSuccessful() && !responseBody.equals(EMPTY_ARRAY);
        }
        catch (IOException exception) {
            throw new DatabaseAccessException("Failed to check user existence", exception);
        }
    }

    @Override
    public void changePassword(User user, String newPassword) throws DatabaseAccessException {
        if (accessToken == null) {
            throw new DatabaseAccessException("User not authenticated");
        }

        final JSONObject jsonBody = new JSONObject().put("password", newPassword);
        final Request request = buildPasswordResetRequest(jsonBody);

        try {
            final Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new DatabaseAccessException("Failed to update password");
            }
        }
        catch (IOException exception) {
            throw new DatabaseAccessException("Failed to access database", exception);
        }
    }

    private Request buildPasswordResetRequest(JSONObject jsonBody) {
        final RequestBody body = RequestBody.create(
                jsonBody.toString(),
                MediaType.parse(CONTENT_TYPE_JSON));

        return new Request.Builder()
                .url(apiUrl + AUTH_PASSWORD_RESET)
                .put(body)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + accessToken)
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON)
                .build();
    }
}
