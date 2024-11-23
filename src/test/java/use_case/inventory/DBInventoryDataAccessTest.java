package use_case.inventory;

import config.SupabaseConfig;
import data_access.DBInventoryDataAccessObject;
import entity.AbstractFood;
import entity.FoodInventoryFactory;
import entity.FoodItemFactory;
import entity.Inventory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.food_management.InventoryDataAccessInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DBInventoryDataAccessTest {
    private static FoodInventoryFactory inventoryFactory;
    private static FoodItemFactory foodItemFactory;
    private static final OkHttpClient CLIENT = new OkHttpClient().newBuilder().build();
    private InventoryDataAccessInterface inventoryRepository;

    @BeforeAll
    static void setUpFactory() {
        inventoryFactory = new FoodInventoryFactory();
        foodItemFactory = new FoodItemFactory();
    }

    @BeforeEach
    void setUp() {
        inventoryRepository = new DBInventoryDataAccessObject(inventoryFactory, foodItemFactory);
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
    void successGetInventoryItemsTest() {
        final Inventory inventory = inventoryFactory.create("testuser");
        Map<String, AbstractFood> items = new HashMap<>();
        items.put("milk", foodItemFactory.create("milk"));
        items.put("cheese", foodItemFactory.create("cheese"));
        inventory.setItems(items);
        inventoryRepository.save(inventory);

        Map<String, AbstractFood> databaseItems = inventoryRepository.getInventoryItems("testuser");
        assertNotNull(databaseItems);
        assertEquals(2, databaseItems.size());
        assertEquals(1, databaseItems.get("milk").getQuantity());
        assertEquals(1, databaseItems.get("cheese").getQuantity());
    }

    @Test
    void successUpdateInventoryTest() {
        final Inventory inventory = inventoryFactory.create("testuser");
        Map<String, AbstractFood> items = new HashMap<>();
        AbstractFood milk = foodItemFactory.create("milk");
        items.put("milk", milk);
        inventory.setItems(items);

        inventoryRepository.save(inventory);

        items.put("cheese", foodItemFactory.create("cheese"));
        inventory.setItems(items);
        milk.setQuantity(2);

        boolean updated = inventoryRepository.updateInventory(inventory);

        assertTrue(updated);
        Map<String, AbstractFood> databaseItems = inventoryRepository.getInventoryItems("testuser");
        assertNotNull(databaseItems);
        assertEquals(2, items.size());
        assertEquals(2, databaseItems.get("milk").getQuantity());
        assertEquals(1, databaseItems.get("cheese").getQuantity());
    }

    @Test
    void successCanUseItemTest() {
        final Inventory inventory = inventoryFactory.create("testuser");
        Map<String, AbstractFood> items = new HashMap<>();
        items.put("milk", foodItemFactory.create("milk"));
        inventory.setItems(items);

        inventoryRepository.save(inventory);

        assertTrue(inventoryRepository.canUseItem("testuser", "milk"));
        assertFalse(inventoryRepository.canUseItem("testuser", "tuna"));
    }

    @Test
    void successGetInventoryTest() {
        final Inventory inventory = inventoryFactory.create("testuser");
        Map<String, AbstractFood> items = new HashMap<>();
        items.put("milk", foodItemFactory.create("milk"));
        inventory.setItems(items);
        inventoryRepository.save(inventory);

        Inventory retrievedInventory = inventoryRepository.getInventory("testuser");
        assertNotNull(retrievedInventory);
        assertEquals("testuser", retrievedInventory.getOwnerId());
        assertEquals(1, retrievedInventory.getItems().get("milk").getQuantity());
    }


}
