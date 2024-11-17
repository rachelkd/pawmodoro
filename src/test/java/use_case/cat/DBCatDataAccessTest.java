package use_case.cat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import config.SupabaseConfig;
import data_access.DBCatDataAccessObject;
import entity.Cat;
import entity.CatFactory;
import entity.exceptions.NoCatsFoundException;
import okhttp3.OkHttpClient;
import okhttp3.Request;

class DBCatDataAccessTest {
    private static CatFactory factory;
    private static final OkHttpClient CLIENT = new OkHttpClient().newBuilder().build();
    private CatDataAccessInterface catRepository;

    @BeforeAll
    static void setUpFactory() {
        factory = new CatFactory();
    }

    @BeforeEach
    void setUp() {
        catRepository = new DBCatDataAccessObject(factory);
        cleanupTestData();
    }

    private void cleanupTestData() {
        // Delete all test cats from the database
        final Request request = new Request.Builder()
                .url(SupabaseConfig.getUrl() + "/rest/v1/cats?owner_username=eq.testuser")
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
    void successSaveCatTest() {
        final Cat cat = factory.create("Whiskers", "testuser");

        assertTrue(catRepository.saveCat(cat));
        assertTrue(catRepository.existsByNameAndOwner("Whiskers", "testuser"));
    }

    @Test
    void failureSaveDuplicateCatTest() {
        final Cat cat = factory.create("Whiskers", "testuser");

        assertTrue(catRepository.saveCat(cat));
        assertFalse(catRepository.saveCat(cat));
    }

    @Test
    void successGetCatTest() {
        final Cat cat = factory.create("Whiskers", "testuser");

        catRepository.saveCat(cat);
        final Cat retrievedCat = catRepository.getCatByNameAndOwner("Whiskers", "testuser");

        assertNotNull(retrievedCat);
        assertEquals("Whiskers", retrievedCat.getName());
        assertEquals("testuser", retrievedCat.getOwnerUsername());
    }

    @Test
    void successGetAllCatsTest() {
        catRepository.saveCat(factory.create("Whiskers", "testuser"));
        catRepository.saveCat(factory.create("Mittens", "testuser"));

        final Collection<Cat> userCats = catRepository.getCatsByOwner("testuser");
        assertEquals(2, userCats.size());
    }

    @Test
    void successUpdateCatTest() {
        final Cat cat = factory.create("Whiskers", "testuser");
        catRepository.saveCat(cat);

        // Create updated cat with same name and owner but different stats
        final Cat updatedCat = factory.create("Whiskers", "testuser");
        updatedCat.updateHungerLevel(-50);
        updatedCat.updateHappinessLevel(-25);

        assertTrue(catRepository.updateCat(updatedCat));

        final Cat retrievedCat = catRepository.getCatByNameAndOwner("Whiskers", "testuser");
        assertEquals(50, retrievedCat.getHungerLevel());
        assertEquals(75, retrievedCat.getHappinessLevel());
    }

    @Test
    void failureUpdateNonexistentCatTest() {
        final Cat cat = factory.create("Whiskers", "testuser");

        assertFalse(catRepository.updateCat(cat));
    }

    @Test
    void successRemoveCatTest() {
        final Cat cat = factory.create("Whiskers", "testuser");
        catRepository.saveCat(cat);

        assertTrue(catRepository.removeCat("Whiskers", "testuser"));
        assertFalse(catRepository.existsByNameAndOwner("Whiskers", "testuser"));
    }

    @Test
    void failureRemoveNonexistentCatTest() {
        assertFalse(catRepository.removeCat("Whiskers", "testuser"));
    }

    @Test
    void successGetHungerLevelTest() {
        final Cat cat = factory.create("Whiskers", "testuser");
        catRepository.saveCat(cat);

        final int hungerLevel = catRepository.getHungerLevel("Whiskers", "testuser");
        assertEquals(100, hungerLevel); // Assuming initial hunger level is 100
    }

    @Test
    void failureGetHungerLevelNonexistentCatTest() {
        assertThrows(NoCatsFoundException.class, () -> {
            catRepository.getHungerLevel("NonexistentCat", "testuser");
        });
    }

    @Test
    void successGetHappinessLevelTest() {
        final Cat cat = factory.create("Whiskers", "testuser");
        catRepository.saveCat(cat);

        final int happinessLevel = catRepository.getHappinessLevel("Whiskers", "testuser");
        assertEquals(100, happinessLevel);
    }

    @Test
    void failureGetHappinessLevelNonexistentCatTest() {
        assertThrows(NoCatsFoundException.class, () -> {
            catRepository.getHappinessLevel("NonexistentCat", "testuser");
        });
    }
}
