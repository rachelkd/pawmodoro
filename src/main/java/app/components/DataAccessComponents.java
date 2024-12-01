package app.components;

import data_access.ApiCatFactDataAccessObject;
import data_access.ApiDisplayCatImageDataAccessObject;
import data_access.DbCatDataAccessObject;
import data_access.DbInventoryDataAccessObject;
import data_access.DbUserDataAccessObject;

/**
 * Holds all data access components for the application.
 */
public class DataAccessComponents {
    private final DbUserDataAccessObject userDataAccess;
    private final DbInventoryDataAccessObject inventoryDataAccess;
    private final ApiDisplayCatImageDataAccessObject displayCatImageDataAccess;
    private final DbCatDataAccessObject catDataAccess;
    private final ApiCatFactDataAccessObject catFactDataAccess;

    /**
     * Creates a new DataAccessComponents instance.
     * @param userDataAccess the user data access object
     * @param inventoryDataAccess the inventory data access object
     * @param displayCatImageDataAccess the display cat image data access object
     * @param catDataAccess the cat data access object
     * @param catFactDataAccess the cat fact data access object
     */
    public DataAccessComponents(DbUserDataAccessObject userDataAccess,
            DbInventoryDataAccessObject inventoryDataAccess,
            ApiDisplayCatImageDataAccessObject displayCatImageDataAccess,
            DbCatDataAccessObject catDataAccess,
            ApiCatFactDataAccessObject catFactDataAccess) {
        this.userDataAccess = userDataAccess;
        this.inventoryDataAccess = inventoryDataAccess;
        this.displayCatImageDataAccess = displayCatImageDataAccess;
        this.catDataAccess = catDataAccess;
        this.catFactDataAccess = catFactDataAccess;
    }

    public DbUserDataAccessObject getUserDataAccess() {
        return userDataAccess;
    }

    public DbInventoryDataAccessObject getInventoryDataAccess() {
        return inventoryDataAccess;
    }

    public ApiDisplayCatImageDataAccessObject getDisplayCatImageDataAccess() {
        return displayCatImageDataAccess;
    }

    public DbCatDataAccessObject getCatDataAccess() {
        return catDataAccess;
    }

    public ApiCatFactDataAccessObject getCatFactDataAccess() {
        return catFactDataAccess;
    }
}
