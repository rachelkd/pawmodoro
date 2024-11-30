package app.components;

import data_access.ApiCatFactDataAccessObject;
import data_access.ApiDisplayCatImageDataAccessObject;
import data_access.DBCatDataAccessObject;
import data_access.DBUserDataAccessObject;
import use_case.food_management.InventoryService;

/**
 * Holds all data access components for the application.
 */
public class DataAccessComponents {
    private final DBUserDataAccessObject userDataAccess;
    private final InventoryService inventoryDataAccess;
    private final ApiDisplayCatImageDataAccessObject displayCatImageDataAccess;
    private final DBCatDataAccessObject catDataAccess;
    private final ApiCatFactDataAccessObject catFactDataAccess;

    /**
     * Creates a new DataAccessComponents instance.
     * @param userDataAccess the user data access object
     * @param inventoryDataAccess the inventory data access object
     * @param displayCatImageDataAccess the display cat image data access object
     * @param catDataAccess the cat data access object
     * @param catFactDataAccess the cat fact data access object
     */
    public DataAccessComponents(DBUserDataAccessObject userDataAccess,
            InventoryService inventoryDataAccess,
            ApiDisplayCatImageDataAccessObject displayCatImageDataAccess,
            DBCatDataAccessObject catDataAccess,
            ApiCatFactDataAccessObject catFactDataAccess) {
        this.userDataAccess = userDataAccess;
        this.inventoryDataAccess = inventoryDataAccess;
        this.displayCatImageDataAccess = displayCatImageDataAccess;
        this.catDataAccess = catDataAccess;
        this.catFactDataAccess = catFactDataAccess;
    }

    public DBUserDataAccessObject getUserDataAccess() {
        return userDataAccess;
    }

    public InventoryService getInventoryDataAccess() {
        return inventoryDataAccess;
    }

    public ApiDisplayCatImageDataAccessObject getDisplayCatImageDataAccess() {
        return displayCatImageDataAccess;
    }

    public DBCatDataAccessObject getCatDataAccess() {
        return catDataAccess;
    }

    public ApiCatFactDataAccessObject getCatFactDataAccess() {
        return catFactDataAccess;
    }
}
