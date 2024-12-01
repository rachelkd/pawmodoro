package data_access;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import config.SupabaseConfig;
import entity.Inventory;
import entity.InventoryFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.food_management.InventoryDataAccessInterface;

/**
 * Database implementation of the Inventory Data Access Object.
 */
public class DbInventoryDataAccessObject implements InventoryDataAccessInterface {
    private static final String FOOD_KEY = "food_name";
    private static final String QUANTITY_KEY = "quantity";

    private static final String API_KEY_HEADER = "apikey";
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String PREFER_HEADER = "Prefer";
    private static final String PREFER_RETURN_MINIMAL = "return=minimal";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String EMPTY_JSON_ARRAY = "[]";

    private static final String INVENTORIES_ENDPOINT = "/rest/v1/inventory";
    private static final String OWNER_QUERY = "?username=eq.";

    private static final String USERNAME_COLUMN = "username";
    private static final String FOOD_INVENTORY_COLUMN = "food_inventory";

    private final OkHttpClient client = new OkHttpClient().newBuilder().build();
    private final String apiUrl = SupabaseConfig.getUrl();
    private final String apiKey = SupabaseConfig.getAnonKey();
    private final InventoryFactory foodInventoryFactory;

    /**
     * Creates new DBInventoryDAO.
     * @param foodInventoryFactory factory for creating inventories
     */
    public DbInventoryDataAccessObject(InventoryFactory foodInventoryFactory) {
        this.foodInventoryFactory = foodInventoryFactory;
    }

    /**
     * Saves the inventory to the database.
     * 
     * @param inventory The inventory to save
     * @throws UnsupportedOperationException currently not implemented
     */
    @Override
    public void save(Inventory inventory) {

        if (!existsByOwnerId(inventory.getOwnerId())) {
            final JSONObject jsonBody = new JSONObject()
                    .put(USERNAME_COLUMN, inventory.getOwnerId())
                    .put(FOOD_INVENTORY_COLUMN, getJsonArray(inventory));

            final RequestBody body = RequestBody.create(
                    jsonBody.toString(),
                    MediaType.parse(CONTENT_TYPE_JSON));

            final Request request = new Request.Builder()
                    .url(apiUrl + INVENTORIES_ENDPOINT)
                    .post(body)
                    .addHeader(API_KEY_HEADER, apiKey)
                    .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                    .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON)
                    .addHeader(PREFER_HEADER, PREFER_RETURN_MINIMAL)
                    .build();

            try {
                client.newCall(request).execute();
            }
            catch (final IOException exception) {
                // Keep isSuccessful as false
            }
        }

    }

    JSONArray getJsonArray(Inventory inventory) {
        final JSONArray jsonArray = new JSONArray();
        final Map<String, Integer> inventoryMap = inventory.getItems();

        for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put(FOOD_KEY, entry.getKey());
            jsonObject.put(QUANTITY_KEY, entry.getValue());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    Map<String, Integer> getHashMap(JSONArray jsonArray) {
        final Map<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            final String key = jsonObject.getString(FOOD_KEY);
            final Integer quantity = jsonObject.getInt(QUANTITY_KEY);

            hashMap.put(key, quantity);
        }
        return hashMap;
    }

    @Override
    public boolean existsByOwnerId(String ownerId) {

        boolean exists = false;
        final Request request = new Request.Builder()
                .url(apiUrl + INVENTORIES_ENDPOINT + OWNER_QUERY + ownerId)
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
    public Map<String, Integer> getInventoryItems(String ownerId) {
        Map<String, Integer> result = null;

        final Request request = new Request.Builder()
                .url(apiUrl + INVENTORIES_ENDPOINT + OWNER_QUERY + ownerId)
                .get()
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();

            if (response.isSuccessful() && !responseBody.equals(EMPTY_JSON_ARRAY)) {
                final JSONArray jsonArray = new JSONArray(responseBody);
                final JSONObject inventoryJson = jsonArray.getJSONObject(0);
                result = getHashMap(inventoryJson.getJSONArray(FOOD_INVENTORY_COLUMN));
            }
        }
        catch (final IOException exception) {
            // Keep result as null
        }
        return result;

    }

    /**
     * Retrieves the inventory from the database.
     * 
     * @return The retrieved inventory
     * @throws UnsupportedOperationException currently not implemented
     */
    @Override
    public Inventory getInventory(String ownerId) {
        Inventory result = null;
        final Request request = new Request.Builder()
                .url(apiUrl + INVENTORIES_ENDPOINT + OWNER_QUERY + ownerId)
                .get()
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();

            if (response.isSuccessful() && !responseBody.equals(EMPTY_JSON_ARRAY)) {
                final JSONArray jsonArray = new JSONArray(responseBody);
                final JSONObject inventoryJson = jsonArray.getJSONObject(0);
                result = foodInventoryFactory.create(ownerId);
                result.setItems(getHashMap(inventoryJson.getJSONArray(FOOD_INVENTORY_COLUMN)));
            }
        }
        catch (final IOException exception) {
            // Keep result as null
        }
        return result;
    }

    @Override
    public boolean canUseItem(String ownerId, String foodId) {
        boolean exists = false;

        final Request request = new Request.Builder()
                .url(apiUrl + INVENTORIES_ENDPOINT + OWNER_QUERY + ownerId)
                .get()
                .addHeader(API_KEY_HEADER, apiKey)
                .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final String responseBody = response.body().string();

            if (response.isSuccessful() && !responseBody.equals(EMPTY_JSON_ARRAY)) {
                final JSONArray jsonArray = new JSONArray(responseBody);
                final JSONObject inventoryJson = jsonArray.getJSONObject(0);
                final JSONArray foodItems = inventoryJson.getJSONArray(FOOD_INVENTORY_COLUMN);

                for (int i = 0; i < foodItems.length(); i++) {
                    final JSONObject food = foodItems.getJSONObject(i);
                    if (food.getString(FOOD_KEY).equalsIgnoreCase(foodId) && food.getInt(QUANTITY_KEY) > 0) {
                        exists = true;
                    }
                }
            }
        }
        catch (final IOException exception) {
            // Keep result as null
        }
        return exists;
    }

    @Override
    public boolean updateInventory(Inventory inventory) {
        boolean isSuccess = false;
        if (existsByOwnerId(inventory.getOwnerId())) {
            final JSONObject jsonBody = new JSONObject().put(FOOD_INVENTORY_COLUMN, getJsonArray(inventory));

            final RequestBody body = RequestBody.create(
                    jsonBody.toString(),
                    MediaType.parse(CONTENT_TYPE_JSON));

            final Request request = new Request.Builder()
                    .url(apiUrl + INVENTORIES_ENDPOINT + OWNER_QUERY + inventory.getOwnerId())
                    .patch(body)
                    .addHeader(API_KEY_HEADER, apiKey)
                    .addHeader(AUTH_HEADER, BEARER_PREFIX + apiKey)
                    .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON)
                    .addHeader(PREFER_HEADER, PREFER_RETURN_MINIMAL)
                    .build();

            try {
                final Response response = client.newCall(request).execute();
                isSuccess = response.isSuccessful();
            }
            catch (final IOException exception) {
                // Keep isSuccessful as false
            }
        }
        return isSuccess;
    }
}
