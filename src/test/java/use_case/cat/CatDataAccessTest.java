package use_case.cat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data_access.InMemoryCatDataAccessObject;
import entity.Cat;
import entity.CatFactory;

class CatDataAccessTest {
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
        final Cat cat = factory.create("Whiskers", "rachel");

        assertTrue(catRepository.saveCat(cat));
        assertTrue(catRepository.existsByNameAndOwner("Whiskers", "rachel"));
    }

    @Test
    void failureSaveDuplicateCatTest() {
        final Cat cat = factory.create("Whiskers", "rachel");

        assertTrue(catRepository.saveCat(cat));
        assertFalse(catRepository.saveCat(cat));
    }

    @Test
    void successGetCatTest() {
        final Cat cat = factory.create("Whiskers", "rachel");

        catRepository.saveCat(cat);
        final Cat retrievedCat = catRepository.getCatByNameAndOwner("Whiskers", "rachel");

        assertNotNull(retrievedCat);
        assertEquals("Whiskers", retrievedCat.getName());
        assertEquals("rachel", retrievedCat.getOwnerUsername());
    }

    @Test
    void successGetAllCatsTest() {
        catRepository.saveCat(factory.create("Whiskers", "rachel"));
        catRepository.saveCat(factory.create("Mittens", "rachel"));

        final Collection<Cat> paulsCats = catRepository.getCatsByOwner("rachel");
        assertEquals(2, paulsCats.size());
    }

    @Test
    void successUpdateCatTest() {
        final Cat cat = factory.create("Whiskers", "rachel");

        catRepository.saveCat(cat);

        assertTrue(catRepository.updateCat(cat));
        assertEquals(100, catRepository.getCatByNameAndOwner("Whiskers", "rachel").getHungerLevel());
    }

    @Test
    void failureUpdateNonexistentCatTest() {
        final Cat cat = factory.create("Whiskers", "rachel");

        assertFalse(catRepository.updateCat(cat));
    }

    @Test
    void successRemoveCatTest() {
        final Cat cat = factory.create("Whiskers", "rachel");
        catRepository.saveCat(cat);

        assertTrue(catRepository.removeCat("Whiskers", "rachel"));
        assertFalse(catRepository.existsByNameAndOwner("Whiskers", "rachel"));
    }

    @Test
    void failureRemoveNonexistentCatTest() {
        assertFalse(catRepository.removeCat("Whiskers", "rachel"));
    }
}
