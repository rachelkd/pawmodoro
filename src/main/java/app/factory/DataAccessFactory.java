package app.factory;

import data_access.AdoptionDataAccessObject;
import data_access.ApiCatFactDataAccessObject;
import data_access.ApiDisplayCatImageDataAccessObject;
import data_access.DBCatDataAccessObject;
import data_access.DBUserDataAccessObject;
import data_access.InMemoryInventoryDataAccessObject;
import data_access.InMemoryTimerDataAccessObject;
import entity.CatFactory;
import entity.CatImageFactory;
import entity.CommonUserFactory;
import use_case.adoption.AdoptionDataAccessInterface;
import use_case.cat.CatDataAccessInterface;
import use_case.create_inventory.CreateInventoryInventoryDataAccessInterface;
import use_case.display_cat_image.DisplayCatImageDataAccessInterface;
import use_case.get_cat_fact.CatFactDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.timer.TimerDataAccessInterface;

/**
 * Factory for creating data access objects.
 */
public class DataAccessFactory {
    /**
     * Creates a new user data access object.
     *
     * @return the user data access interface
     */
    public LoginUserDataAccessInterface createUserDataAccess() {
        return new DBUserDataAccessObject(new CommonUserFactory());
    }

    /**
     * Creates a new inventory data access object.
     *
     * @return the inventory data access interface
     */
    public CreateInventoryInventoryDataAccessInterface createInventoryDataAccess() {
        return new InMemoryInventoryDataAccessObject();
    }

    /**
     * Creates a new timer data access object.
     *
     * @return the timer data access interface
     */
    public TimerDataAccessInterface createTimerDataAccess() {
        return new InMemoryTimerDataAccessObject();
    }

    /**
     * Creates a new adoption data access object.
     *
     * @return the adoption data access interface
     */
    public AdoptionDataAccessInterface createAdoptionDataAccess() {
        return new AdoptionDataAccessObject();
    }

    /**
     * Creates a new display cat image data access object.
     *
     * @return the display cat image data access interface
     */
    public DisplayCatImageDataAccessInterface createDisplayCatImageDataAccess() {
        return new ApiDisplayCatImageDataAccessObject(new CatImageFactory());
    }

    /**
     * Creates a new cat data access object.
     *
     * @return the cat data access interface
     */
    public CatDataAccessInterface createCatDataAccess() {
        return new DBCatDataAccessObject(new CatFactory());
    }

    /**
     * Creates a new cat fact data access object.
     *
     * @return the cat fact data access interface
     */
    public CatFactDataAccessInterface createCatFactDataAccess() {
        return new ApiCatFactDataAccessObject();
    }
}
