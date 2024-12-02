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
import data_access.DbInventoryDataAccessObject;
import entity.FoodInventoryFactory;
import entity.Inventory;
import entity.InventoryFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import use_case.food_management.InventoryDataAccessInterface;

class DbInventoryDataAccessTest {
    private static InventoryFactory inventoryFactory;
    private static final OkHttpClient CLIENT = new OkHttpClient().newBuilder().build();
    private InventoryDataAccessInterface inventoryRepository;

    @BeforeAll
    static void setUpFactory() {
        inventoryFactory = new FoodInventoryFactory();
    }

    @BeforeEach
    void setUp() {
        inventoryRepository = new DbInventoryDataAccessObject(inventoryFactory);
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
        inventoryRepository.save(inventory);

        assertTrue(inventoryRepository.existsByOwnerId("testuser"));
    }

    @Test
    void successExistsByOwnerIdTest() {

        final Inventory inventory = inventoryFactory.create("testuser");
        inventoryRepository.save(inventory);

        assertTrue(inventoryRepository.existsByOwnerId("testuser"));
        assertFalse(inventoryRepository.existsByOwnerId("testuser2"));
    }

    @Test
    void successGetInventoryItemsTest() {
        final Inventory inventory = inventoryFactory.create("testuser");
        final Map<String, Integer> items = new HashMap<>();
        items.put("milk", 1);
        items.put("cheese", 1);
        inventory.setItems(items);
        inventoryRepository.save(inventory);

        final Map<String, Integer> databaseItems = inventoryRepository.getInventoryItems("testuser");
        assertNotNull(databaseItems);
        assertEquals(2, databaseItems.size());
        assertEquals(1, databaseItems.get("milk"));
        assertEquals(1, databaseItems.get("cheese"));
    }

    @Test
    void successUpdateInventoryTest() {
        final Inventory inventory = inventoryFactory.create("testuser");
        final Map<String, Integer> items = new HashMap<>();
        items.put("milk", 1);
        inventory.setItems(items);

        inventoryRepository.save(inventory);

        items.put("cheese", 1);
        items.put("milk", 2);
        inventory.setItems(items);

        final boolean updated = inventoryRepository.updateInventory(inventory);

        assertTrue(updated);
        final Map<String, Integer> databaseItems = inventoryRepository.getInventoryItems("testuser");
        assertNotNull(databaseItems);
        assertEquals(2, items.size());
        assertEquals(2, databaseItems.get("milk"));
        assertEquals(1, databaseItems.get("cheese"));
    }

    @Test
    void successCanUseItemTest() {
        final Inventory inventory = inventoryFactory.create("testuser");
        final Map<String, Integer> items = new HashMap<>();
        items.put("milk", 1);
        inventory.setItems(items);

        inventoryRepository.save(inventory);

        assertTrue(inventoryRepository.canUseItem("testuser", "Milk"));
        assertFalse(inventoryRepository.canUseItem("testuser", "tuna"));
    }

    @Test
    void successGetInventoryTest() {
        final Inventory inventory = inventoryFactory.create("testuser");
        final Map<String, Integer> items = new HashMap<>();
        items.put("milk", 1);
        inventory.setItems(items);
        inventoryRepository.save(inventory);

        final Inventory retrievedInventory = inventoryRepository.getInventory("testuser");
        assertNotNull(retrievedInventory);
        assertEquals("testuser", retrievedInventory.getOwnerId());
        assertEquals(1, retrievedInventory.getItems().get("milk"));
    }

    @Test
    void failureNonExistentInventoryTest() {
        assertFalse(inventoryRepository.existsByOwnerId("nonexistentuser"));
        assertNull(inventoryRepository.getInventoryItems("nonexistentuser"));
        assertNull(inventoryRepository.getInventory("nonexistentuser"));
        assertFalse(inventoryRepository.canUseItem("nonexistentuser", "milk"));
    }

}
