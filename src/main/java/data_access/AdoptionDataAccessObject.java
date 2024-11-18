package data_access;

import entity.Cat;
import use_case.adoption.AdoptionDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class AdoptionDataAccessObject implements AdoptionDataAccessInterface {
    private final Map<Cat, String> catToUser = new HashMap<>();

    public AdoptionDataAccessObject(Map<Cat, String> catToUser) {
        this.catToUser.putAll(catToUser);
    }
    @Override
    public boolean nameAlreadyExists(String catName) {
        return false;
    }


    @Override
    public String getCatName(Cat cat) {
        return cat.getName();
    }
}
