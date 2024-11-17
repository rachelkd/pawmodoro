package data_access;

import entity.Cat;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessDataAccessInterface;
import use_case.cat_management.change_cat_hunger.ChangeCatHungerDataAccessInterface;
import use_case.cat_management.create_cat.CreateCatDataAccessInterface;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In memory implementation of cat-related usecase interfaces. For testing.
 */
public class InMemoryCatDataAccessObject implements CreateCatDataAccessInterface,
        ChangeCatHungerDataAccessInterface, ChangeCatHappinessDataAccessInterface {
    private final Map<String, Collection<Cat>> catsRepository = new HashMap<>();

    @Override
    public void updateHappinessLevel(String ownerId, String catName, int newHappinessLevel) {

    }

    @Override
    public Cat getCatByNameAndOwnerId(String catName, String ownerId) {
        return null;
    }

    @Override
    public void updateHungerLevels(String ownerId, String catName, int newHungerLevel) {

    }

    @Override
    public void save(Cat cat) {

    }

    @Override
    public Collection<Cat> getCatsByOwnerId(String ownerId) {
        return catsRepository.get(ownerId);
    }

    @Override
    public boolean existsByNameAndOwnerId(String name, String ownerId) {
        return false;
    }
}
