package use_case.cat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data_access.InMemoryCatDataAccessObject;
import entity.Cat;
import entity.CatFactory;
import entity.exceptions.NoCatsFoundException;

class InMemoryCatDataAccessTest {
    private static CatFactory factory;
    private CatDataAccessInterface catRepository;

    @BeforeAll
    static void setUpFactory() {
        factory = new CatFactory();
    }

    @BeforeEach
    void setUp() {
        catRepository = new InMemoryCatDataAccessObject();
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
    void successGetHungerLevelTest() {
        final Cat cat = factory.create("Whiskers", "testuser");
        catRepository.saveCat(cat);

        assertEquals(100, catRepository.getHungerLevel("Whiskers", "testuser"));
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

        assertEquals(100, catRepository.getHappinessLevel("Whiskers", "testuser"));
    }

    @Test
    void failureGetHappinessLevelNonexistentCatTest() {
        assertThrows(NoCatsFoundException.class, () -> {
            catRepository.getHappinessLevel("NonexistentCat", "testuser");
        });
    }
}
