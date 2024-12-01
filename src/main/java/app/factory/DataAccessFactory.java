package app.factory;

import data_access.ApiCatFactDataAccessObject;
import data_access.ApiDisplayCatImageDataAccessObject;
import data_access.DBCatDataAccessObject;
import data_access.DBInventoryDataAccessObject;
import data_access.DBUserDataAccessObject;
import data_access.InMemoryInventoryDataAccessObject;
import data_access.InMemoryTimerDataAccessObject;
import entity.CatFactory;
import entity.CatImageFactory;
import entity.CommonUserFactory;
import entity.FoodInventoryFactory;

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

    /**
     * Creates a new cat fact data access object.
     * 
     * @return the cat fact data access object
     */
    public ApiCatFactDataAccessObject createCatFactDataAccess() {
        return new ApiCatFactDataAccessObject();
    }

    /**
     * Creates a inventory data access object.
     * @return the inventory data access object
     */
    public DBInventoryDataAccessObject createInventoryAccessFactory() {
        return new DBInventoryDataAccessObject(new FoodInventoryFactory());
    }
}
