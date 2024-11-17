package data_access;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import entity.Cat;
import use_case.cat.CatDataAccessInterface;

/**
 * In-memory implementation of CatDataAccessInterface for testing and development.
 */
public class InMemoryCatDataAccessObject implements CatDataAccessInterface {
    private final Map<String, Map<String, Cat>> catsByOwner = new HashMap<>();

    @Override
    public boolean saveCat(Cat cat) {
        boolean isSuccessful = false;
        if (!existsByNameAndOwner(cat.getName(), cat.getOwnerUsername())) {
            catsByOwner
                    .computeIfAbsent(cat.getOwnerUsername(), username -> new HashMap<>())
                    .put(cat.getName(), cat);
            isSuccessful = true;
        }
        return isSuccessful;
    }

    @Override
    public Cat getCatByNameAndOwner(String name, String ownerUsername) {
        return catsByOwner
                .getOrDefault(ownerUsername, new HashMap<>())
                .get(name);
    }

    @Override
    public Collection<Cat> getCatsByOwner(String ownerUsername) {
        return catsByOwner
                .getOrDefault(ownerUsername, new HashMap<>())
                .values();
    }

    @Override
    public boolean updateCat(Cat cat) {
        boolean isSuccessful = false;
        final Map<String, Cat> ownerCats = catsByOwner.get(cat.getOwnerUsername());
        if (ownerCats != null && ownerCats.containsKey(cat.getName())) {
            ownerCats.put(cat.getName(), cat);
            isSuccessful = true;
        }
        return isSuccessful;
    }

    @Override
    public boolean existsByNameAndOwner(String name, String ownerUsername) {
        return catsByOwner
                .getOrDefault(ownerUsername, new HashMap<>())
                .containsKey(name);
    }

    @Override
    public int getNumberOfCatsByOwner(String ownerUsername) {
        return catsByOwner
                .getOrDefault(ownerUsername, new HashMap<>())
                .size();
    }

    @Override
    public boolean removeCat(String name, String ownerUsername) {
        // TODO: Implement this method for cat running away use case
        return false;
    }
}
