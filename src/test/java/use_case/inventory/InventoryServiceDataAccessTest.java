package use_case.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import config.SupabaseConfig;
import data_access.DBInventoryDataAccessObject;
import entity.AbstractFood;
import entity.FoodInventoryFactory;
import entity.FoodItemFactory;
import entity.Inventory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import use_case.food_management.InventoryService;

public class InventoryServiceDataAccessTest {
    private static FoodInventoryFactory inventoryFactory;
    private static FoodItemFactory foodItemFactory;
    private static final OkHttpClient CLIENT = new OkHttpClient().newBuilder().build();
    private DBInventoryDataAccessObject inventorRepo;
    private InventoryService inventoryService;

    @BeforeAll
    static void setUpFactory() {
        inventoryFactory = new FoodInventoryFactory();
        foodItemFactory = new FoodItemFactory();
    }

    @BeforeEach
    void setUp() {
        inventorRepo = new DBInventoryDataAccessObject(inventoryFactory, foodItemFactory);
        inventoryService = InventoryService.getInstance(inventorRepo);
        cleanupTestData();
    }

    private void cleanupTestData() {
        // Delete all test cats from the database
        final Request request = new Request.Builder()
                .url(SupabaseConfig.getUrl() + "/rest/v1/inventory?username=eq.testuser")
                .delete()
                .addHeader("apikey", SupabaseConfig.getAnonKey())
                .addHeader("Authorization", "Bearer " + SupabaseConfig.getAnonKey())
                .addHeader("Prefer", "return=minimal")
                .build();

        try {
            CLIENT.newCall(request).execute();
        }
        catch (final IOException exception) {
            // Ignore cleanup errors
        }
    }

    @Test
    void successCreateInventoryTest() {

        final Inventory inventory = inventoryFactory.create("testuser");
        inventoryService.save(inventory);

        assertTrue(inventoryService.existsByOwnerId("testuser"));
    }

    @Test
    void successExistsByOwnerIdTest() {

        final Inventory inventory = inventoryFactory.create("testuser");
        inventoryService.save(inventory);

        assertTrue(inventoryService.existsByOwnerId("testuser"));
        assertFalse(inventoryService.existsByOwnerId("testuser2"));
    }

    @Test
    void successGetInventoryItemsTest() {
        final Inventory inventory = inventoryFactory.create("testuser");
        final Map<String, AbstractFood> items = new HashMap<>();
        items.put("milk", foodItemFactory.create("milk"));
        items.put("cheese", foodItemFactory.create("cheese"));
        inventory.setItems(items);
        inventoryService.save(inventory);

        final Map<String, AbstractFood> databaseItems = inventoryService.getInventoryItems("testuser");
        assertNotNull(databaseItems);
        assertEquals(2, databaseItems.size());
        assertEquals(1, databaseItems.get("milk").getQuantity());
        assertEquals(1, databaseItems.get("cheese").getQuantity());
    }

    @Test
    void successUpdateInventoryTest() {
        final Inventory inventory = inventoryFactory.create("testuser");
        final Map<String, AbstractFood> items = new HashMap<>();
        final AbstractFood milk = foodItemFactory.create("milk");
        items.put("milk", milk);
        inventory.setItems(items);

        inventoryService.save(inventory);

        items.put("cheese", foodItemFactory.create("cheese"));
        inventory.setItems(items);
        milk.setQuantity(2);

        final boolean updated = inventoryService.updateInventory(inventory);

        assertTrue(updated);
        final Map<String, AbstractFood> databaseItems = inventoryService.getInventoryItems("testuser");
        assertNotNull(databaseItems);
        assertEquals(2, items.size());
        assertEquals(2, databaseItems.get("milk").getQuantity());
        assertEquals(1, databaseItems.get("cheese").getQuantity());
    }

    @Test
    void successCanUseItemTest() {
        final Inventory inventory = inventoryFactory.create("testuser");
        final Map<String, AbstractFood> items = new HashMap<>();
        items.put("milk", foodItemFactory.create("milk"));
        inventory.setItems(items);

        inventoryService.save(inventory);

        assertTrue(inventoryService.canUseItem("testuser", "Milk"));
        assertFalse(inventoryService.canUseItem("testuser", "tuna"));
    }

    @Test
    void successGetInventoryTest() {
        final Inventory inventory = inventoryFactory.create("testuser");
        final Map<String, AbstractFood> items = new HashMap<>();
        items.put("milk", foodItemFactory.create("milk"));
        inventory.setItems(items);
        inventoryService.save(inventory);

        final Inventory retrievedInventory = inventoryService.getInventory("testuser");
        assertNotNull(retrievedInventory);
        assertEquals("testuser", retrievedInventory.getOwnerId());
        assertEquals(1, retrievedInventory.getItems().get("milk").getQuantity());
    }

    @Test
    void failureNonExistentInventoryTest() {
        assertFalse(inventoryService.existsByOwnerId("nonexistentuser"));
        assertNull(inventoryService.getInventoryItems("nonexistentuser1"));
        assertNull(inventoryService.getInventory("nonexistentuser"));
        assertFalse(inventoryService.canUseItem("nonexistentuser", "milk"));
    }
}
