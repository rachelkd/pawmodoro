package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;

import config.SupabaseConfig;
import entity.Cat;
import entity.CatFactory;
import entity.exceptions.NoCatsFoundException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.cat.CatDataAccessInterface;

/**
 * Database implementation of CatDataAccessInterface using Supabase.
 */
public class DbCatDataAccessObject implements CatDataAccessInterface {
    private static final String API_KEY_HEADER = "apikey";
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String PREFER_HEADER = "Prefer";
    private static final String PREFER_RETURN_MINIMAL = "return=minimal";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String EMPTY_JSON_ARRAY = "[]";

    private static final String CATS_ENDPOINT = "/rest/v1/cats";
    private static final String NAME_QUERY = "?cat_name=eq.";
    private static final String OWNER_QUERY = "&owner_username=eq.";

    private static final String NAME_COLUMN = "cat_name";
    private static final String OWNER_USERNAME_COLUMN = "owner_username";
    private static final String HAPPINESS_LEVEL_COLUMN = "happiness_level";
    private static final String HUNGER_LEVEL_COLUMN = "hunger_level";
    private static final String IMAGE_FILE_NAME_COLUMN = "image_file_name";

    private final OkHttpClient client = new OkHttpClient().newBuilder().build();
    private final String apiUrl = SupabaseConfig.getUrl();
    private final String apiKey = SupabaseConfig.getAnonKey();
    private final CatFactory catFactory;

    /**
     * Creates a new DbCatDataAccessObject.
     * @param catFactory the factory to create Cat entities
     */
    public DbCatDataAccessObject(CatFactory catFactory) {
        this.catFactory = catFactory;
    }

    @Override
    public boolean saveCat(Cat cat) {
        boolean isSuccessful = false;
        if (!existsByNameAndOwner(cat.getName(), cat.getOwnerUsername())) {
            final JSONObject jsonBody = new JSONObject()
                    .put(NAME_COLUMN, cat.getName())
                    .put(OWNER_USERNAME_COLUMN, cat.getOwnerUsername())
                    .put(HAPPINESS_LEVEL_COLUMN, cat.getHappinessLevel())
                    .put(HUNGER_LEVEL_COLUMN, cat.getHungerLevel())
                    .put(IMAGE_FILE_NAME_COLUMN, cat.getImageFileName());

            final RequestBody body = RequestBody.create(
                    jsonBody.toString(),
                    MediaType.parse(CONTENT_TYPE_JSON));

            final Request request = new Request.Builder()
                    .url(apiUrl + CATS_ENDPOINT)
                    .post(body)
                    .addHeader(API_KEY_HEADER, apiKey)
                    .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                    .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON)
                    .addHeader(PREFER_HEADER, PREFER_RETURN_MINIMAL)
                    .build();

            try {
                final Response response = client.newCall(request).execute();
                isSuccessful = response.isSuccessful();
            }

            catch (final IOException exception) {
                // Keep isSuccessful as false
            }
        }
        return isSuccessful;
    }

    @Override
    public Cat getCatByNameAndOwner(String name, String ownerUsername) {
        Cat result = null;
        final Request request = new Request.Builder()
                .url(apiUrl + CATS_ENDPOINT + NAME_QUERY + name + OWNER_QUERY + ownerUsername)
                .get()
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();

            if (response.isSuccessful() && !responseBody.equals(EMPTY_JSON_ARRAY)) {
                final JSONArray jsonArray = new JSONArray(responseBody);
                final JSONObject catJson = jsonArray.getJSONObject(0);
                result = catFactory.create(
                        catJson.getString(NAME_COLUMN),
                        catJson.getString(OWNER_USERNAME_COLUMN),
                        catJson.getInt(HAPPINESS_LEVEL_COLUMN),
                        catJson.getInt(HUNGER_LEVEL_COLUMN),
                        catJson.getString(IMAGE_FILE_NAME_COLUMN));

            }
        }
        catch (final IOException exception) {
            // Keep result as null
        }
        return result;
    }

    @Override
    public Collection<Cat> getCatsByOwner(String ownerUsername) {
        final Collection<Cat> cats = new ArrayList<>();
        final Request request = new Request.Builder()
                .url(apiUrl + CATS_ENDPOINT + "?owner_username=eq." + ownerUsername)
                .get()
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();

            if (response.isSuccessful() && !responseBody.equals(EMPTY_JSON_ARRAY)) {
                final JSONArray jsonArray = new JSONArray(responseBody);
                for (int i = 0; i < jsonArray.length(); i++) {
                    final JSONObject catJson = jsonArray.getJSONObject(i);
                    final Cat cat = catFactory.create(
                            catJson.getString(NAME_COLUMN),
                            catJson.getString(OWNER_USERNAME_COLUMN),
                            catJson.getInt(HAPPINESS_LEVEL_COLUMN),
                            catJson.getInt(HUNGER_LEVEL_COLUMN),
                            catJson.getString(IMAGE_FILE_NAME_COLUMN));
                    cats.add(cat);

                }
            }
        }
        catch (final IOException exception) {
            // Keep cats as empty list
        }
        return cats;
    }

    @Override
    public boolean updateCat(Cat cat) {
        boolean isSuccessful = false;
        if (existsByNameAndOwner(cat.getName(), cat.getOwnerUsername())) {
            final JSONObject jsonBody = new JSONObject()
                    .put(HAPPINESS_LEVEL_COLUMN, cat.getHappinessLevel())
                    .put(HUNGER_LEVEL_COLUMN, cat.getHungerLevel());

            final RequestBody body = RequestBody.create(
                    jsonBody.toString(),
                    MediaType.parse(CONTENT_TYPE_JSON));

            final Request request = new Request.Builder()
                    .url(apiUrl + CATS_ENDPOINT + NAME_QUERY + cat.getName()
                            + OWNER_QUERY + cat.getOwnerUsername())
                    .patch(body)
                    .addHeader(API_KEY_HEADER, apiKey)
                    .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                    .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON)
                    .addHeader(PREFER_HEADER, PREFER_RETURN_MINIMAL)
                    .build();

            try {
                final Response response = client.newCall(request).execute();
                isSuccessful = response.isSuccessful();
            }
            catch (final IOException exception) {
                // Keep isSuccessful as false
            }
        }
        return isSuccessful;
    }

    @Override
    public boolean existsByNameAndOwner(String name, String ownerUsername) {
        boolean exists = false;
        final Request request = new Request.Builder()
                .url(apiUrl + CATS_ENDPOINT + NAME_QUERY + name + OWNER_QUERY + ownerUsername)
                .get()
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();
            exists = response.isSuccessful() && !responseBody.equals(EMPTY_JSON_ARRAY);
        }
        catch (final IOException exception) {
            // Keep exists as false
        }
        return exists;
    }

    @Override
    public int getNumberOfCatsByOwner(String ownerUsername) {
        int count = 0;
        final Request request = new Request.Builder()
                .url(apiUrl + CATS_ENDPOINT + "?owner_username=eq." + ownerUsername)
                .get()
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();

            if (response.isSuccessful() && !responseBody.equals(EMPTY_JSON_ARRAY)) {
                final JSONArray jsonArray = new JSONArray(responseBody);
                count = jsonArray.length();
            }
        }
        catch (final IOException exception) {
            // Keep count as 0
        }
        return count;
    }

    @Override
    public boolean removeCat(String name, String ownerUsername) {
        boolean isSuccessful = false;
        final Request request = new Request.Builder()
                .url(apiUrl + CATS_ENDPOINT + NAME_QUERY + name + OWNER_QUERY + ownerUsername)
                .delete()
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                .addHeader(PREFER_HEADER, PREFER_RETURN_MINIMAL)

                .build();

        if (existsByNameAndOwner(name, ownerUsername)) {
            try {
                final Response response = client.newCall(request).execute();
                isSuccessful = response.isSuccessful();
            }
            catch (final IOException exception) {
                // Keep isSuccessful false
            }
        }
        return isSuccessful;
    }

    @Override
    public int getHungerLevel(String name, String ownerUsername) {
        final Cat cat = getCatByNameAndOwner(name, ownerUsername);
        if (cat == null) {
            throw new NoCatsFoundException("Cat " + name + " not found for user: " + ownerUsername);
        }
        return cat.getHungerLevel();
    }

    @Override
    public int getHappinessLevel(String name, String ownerUsername) {
        final Cat cat = getCatByNameAndOwner(name, ownerUsername);
        if (cat == null) {
            throw new NoCatsFoundException("Cat " + name + " not found for user: " + ownerUsername);
        }
        return cat.getHappinessLevel();
    }
}
