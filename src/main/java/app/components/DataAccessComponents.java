package app.components;

import data_access.AdoptionDataAccessObject;
import data_access.ApiDisplayCatImageDataAccessObject;
import data_access.DBCatDataAccessObject;
import data_access.DBUserDataAccessObject;
import data_access.InMemoryInventoryDataAccessObject;
import data_access.InMemoryTimerDataAccessObject;
import use_case.get_cat_fact.CatFactDataAccessInterface;

/**
 * Holds all data access components for the application.
 */
public class DataAccessComponents {
    private final DBUserDataAccessObject userDataAccess;
    private final InMemoryInventoryDataAccessObject inventoryDataAccess;
    private final InMemoryTimerDataAccessObject timerDataAccess;
    private final AdoptionDataAccessObject adoptionDataAccess;
    private final ApiDisplayCatImageDataAccessObject displayCatImageDataAccess;
    private final DBCatDataAccessObject catDataAccess;
    private final CatFactDataAccessInterface catFactDataAccess;

    /**
     * Creates a new DataAccessComponents instance.
     *
     * @param userDataAccess the user data access object
     * @param inventoryDataAccess the inventory data access object
     * @param timerDataAccess the timer data access object
     * @param adoptionDataAccess the adoption data access object
     * @param displayCatImageDataAccess the display cat image data access object
     * @param catDataAccess the cat data access object
     * @param catFactDataAccess the cat fact data access object
     */
    public DataAccessComponents(DBUserDataAccessObject userDataAccess,
            InMemoryInventoryDataAccessObject inventoryDataAccess,
            InMemoryTimerDataAccessObject timerDataAccess,
            AdoptionDataAccessObject adoptionDataAccess,
            ApiDisplayCatImageDataAccessObject displayCatImageDataAccess,
            DBCatDataAccessObject catDataAccess,
            CatFactDataAccessInterface catFactDataAccess) {
        this.userDataAccess = userDataAccess;
        this.inventoryDataAccess = inventoryDataAccess;
        this.timerDataAccess = timerDataAccess;
        this.adoptionDataAccess = adoptionDataAccess;
        this.displayCatImageDataAccess = displayCatImageDataAccess;
        this.catDataAccess = catDataAccess;
        this.catFactDataAccess = catFactDataAccess;
    }

    public DBUserDataAccessObject getUserDataAccess() {
        return userDataAccess;
    }

    public InMemoryInventoryDataAccessObject getInventoryDataAccess() {
        return inventoryDataAccess;
    }

    public InMemoryTimerDataAccessObject getTimerDataAccess() {
        return timerDataAccess;
    }

    public AdoptionDataAccessObject getAdoptionDataAccess() {
        return adoptionDataAccess;
    }

    public ApiDisplayCatImageDataAccessObject getDisplayCatImageDataAccess() {
        return displayCatImageDataAccess;
    }

    public DBCatDataAccessObject getCatDataAccess() {
        return catDataAccess;
    }

    public CatFactDataAccessInterface getCatFactDataAccess() {
        return catFactDataAccess;
    }
}
