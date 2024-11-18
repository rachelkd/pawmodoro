package app.components;

import data_access.*;

/**
 * Holds all data access components for the application.
 */
public class DataAccessComponents {
    private final DBUserDataAccessObject userDataAccess;
    private final InMemoryInventoryDataAccessObject inventoryDataAccess;
    private final InMemoryTimerDataAccessObject timerDataAccess;
    private final AdoptionDataAccessObject adoptionDataAccess;
    private final ApiDisplayCatImageDataAccessObject displayCatImageDataAccess;

    /**
     * Creates a new DataAccessComponents instance.
     *
     * @param userDataAccess the user data access object
     * @param inventoryDataAccess the inventory data access object
     * @param timerDataAccess the timer data access object
     * @param adoptionDataAccess the adoption data access object
     * @param displayCatImageDataAccess the display cat image data access object
     */
    public DataAccessComponents(DBUserDataAccessObject userDataAccess,
            InMemoryInventoryDataAccessObject inventoryDataAccess,
            InMemoryTimerDataAccessObject timerDataAccess,
            AdoptionDataAccessObject adoptionDataAccess,
            ApiDisplayCatImageDataAccessObject displayCatImageDataAccess) {
        this.userDataAccess = userDataAccess;
        this.inventoryDataAccess = inventoryDataAccess;
        this.timerDataAccess = timerDataAccess;
        this.adoptionDataAccess = adoptionDataAccess;
        this.displayCatImageDataAccess = displayCatImageDataAccess;
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
}
