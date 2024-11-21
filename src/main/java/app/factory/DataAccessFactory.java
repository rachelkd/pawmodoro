package app.factory;

import data_access.AdoptionDataAccessObject;
import data_access.ApiDisplayCatImageDataAccessObject;
import data_access.DBCatDataAccessObject;
import data_access.InMemoryInventoryDataAccessObject;
import data_access.InMemoryTimerDataAccessObject;
import data_access.DBUserDataAccessObject;
import entity.CatFactory;
import entity.CatImageFactory;
import entity.CommonUserFactory;

/**
 * Factory for creating data access objects.
 */
public class DataAccessFactory {
    /**
     * Creates a new user data access object.
     *
     * @return the user data access object
     */
    public DBUserDataAccessObject createUserDataAccess() {
        return new DBUserDataAccessObject(new CommonUserFactory());
    }

    /**
     * Creates a new inventory data access object.
     *
     * @return the inventory data access object
     */
    public InMemoryInventoryDataAccessObject createInventoryDataAccess() {
        return new InMemoryInventoryDataAccessObject();
    }

    /**
     * Creates a new timer data access object.
     *
     * @return the timer data access object
     */
    public InMemoryTimerDataAccessObject createTimerDataAccess() {
        return new InMemoryTimerDataAccessObject();
    }

    /**
     * Creates a new adoption data access object.
     *
     * @return the adoption data access object
     */
    public AdoptionDataAccessObject createAdoptionDataAccess() {
        return new AdoptionDataAccessObject();
    }

    /**
     * Creates a new display cat image data access object.
     *
     * @return the display cat image data access object
     */
    public ApiDisplayCatImageDataAccessObject createDisplayCatImageDataAccess() {
        return new ApiDisplayCatImageDataAccessObject(new CatImageFactory());
    }

    /**
     * Creates a new cat data access object.
     *
     * @return the cat data access object
     */
    public DBCatDataAccessObject createCatDataAccess() {
        return new DBCatDataAccessObject(new CatFactory());
    }
}
