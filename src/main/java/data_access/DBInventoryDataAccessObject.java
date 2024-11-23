package data_access;

import config.SupabaseConfig;
import entity.*;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.create_inventory.CreateInventoryInventoryDataAccessInterface;
import use_case.food_management.add_to_inventory.AddToInventoryDataAccessInterface;
import use_case.food_management.use_item_in_inventory.UseItemDataAccessInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Database implementation of the Inventory Data Access Object.
 */
public class DBInventoryDataAccessObject implements
        AddToInventoryDataAccessInterface, UseItemDataAccessInterface, CreateInventoryInventoryDataAccessInterface {
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
    private final FoodInventoryFactory foodInventoryFactory;
    private final FoodItemFactory foodItemFactory;


    /**
     * Creates new DBInventoryDAO.
     * @param foodInventoryFactory factory for creating inventories
     */
    public DBInventoryDataAccessObject(FoodInventoryFactory foodInventoryFactory, FoodItemFactory foodItemFactory) {
        this.foodInventoryFactory = foodInventoryFactory;
        this.foodItemFactory = foodItemFactory;
    }

    /**
     * Saves the inventory to the database.
     * 
     * @param inventory The inventory to save
     * @throws UnsupportedOperationException currently not implemented
     */
    @Override
    public void save(Inventory inventory) {
        boolean isSuccessful = false;
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
                final Response response = client.newCall(request).execute();
                isSuccessful = response.isSuccessful();
            }
            catch (final IOException exception) {
                // Keep isSuccessful as false
            }
        }
        // return isSuccessful;
    }

    JSONArray getJsonArray(Inventory inventory) {
        final JSONArray jsonArray = new JSONArray();
        Map<String, AbstractFood> inventoryMap = inventory.getItems();

        for (Map.Entry<String, AbstractFood> entry : inventoryMap.entrySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("food_name", entry.getKey());
            jsonObject.put("quantity", entry.getValue().getQuantity());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    Map<String, AbstractFood> getHashMap(JSONArray jsonArray) {
        Map<String, AbstractFood> hashMap = new HashMap<String, AbstractFood>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String key = jsonObject.getString("food_name");
            int quantity = jsonObject.getInt("quantity");

            AbstractFood foodObject = foodItemFactory.create(key);
            foodObject.setQuantity(quantity);
            hashMap.put(key, foodObject);
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
    public Map<String, AbstractFood> getInventoryItems(String ownerId) {
        Map<String, AbstractFood> result = null;

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
                JSONArray foodItems = inventoryJson.getJSONArray(FOOD_INVENTORY_COLUMN);
                for (int i = 0; i < foodItems.length(); i++) {
                    JSONObject food = jsonArray.getJSONObject(i);
                    if (food.getString("food_name").equals(foodId) && food.getInt("quantity") > 0) {
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
