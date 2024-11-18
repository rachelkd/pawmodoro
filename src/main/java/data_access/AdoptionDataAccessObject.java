package data_access;

import entity.Cat;
import use_case.adoption.AdoptionDataAccessInterface;

public class AdoptionDataAccessObject implements AdoptionDataAccessInterface {
    @Override
    public boolean nameAlreadyExists(String catName) {
        return false;
    }


    @Override
    public String getCatName(Cat cat) {
        return cat.getName();
    }
}
