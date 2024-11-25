package app.builder;

import app.components.DataAccessComponents;
import app.factory.DataAccessFactory;
import data_access.AdoptionDataAccessObject;
import data_access.ApiCatFactDataAccessObject;
import data_access.ApiDisplayCatImageDataAccessObject;
import data_access.DBCatDataAccessObject;
import data_access.DBUserDataAccessObject;
import data_access.InMemoryTimerDataAccessObject;
import use_case.food_management.InventoryService;

/**
 * Builder for data access components.
 */
public class DataAccessBuilder {
    private final DataAccessFactory factory;
    private DBUserDataAccessObject userDataAccess;
    private InventoryService inventoryDataAccess;
    private InMemoryTimerDataAccessObject timerDataAccess;
    private AdoptionDataAccessObject adoptionDataAccess;
    private ApiDisplayCatImageDataAccessObject displayCatImageDataAccess;
    private DBCatDataAccessObject catDataAccess;
    private ApiCatFactDataAccessObject catFactDataAccess;

    public DataAccessBuilder() {
        this.factory = new DataAccessFactory();
    }

    /**
     * Builds and returns all data access components.
     *
     * @return the data access components
     */
    public DataAccessComponents build() {
        buildUserDataAccess()
                .buildInventoryDataAccess()
                .buildTimerDataAccess()
                .buildAdoptionDataAccess()
                .buildDisplayCatImageDataAccess()
                .buildCatDataAccess()
                .buildCatFactDataAccess();

        return new DataAccessComponents(
                userDataAccess,
                inventoryDataAccess,
                timerDataAccess,
                adoptionDataAccess,
                displayCatImageDataAccess,
                catDataAccess,
                catFactDataAccess);
    }

    /**
     * Builds the user data access.
     *
     * @return this builder
     */
    private DataAccessBuilder buildUserDataAccess() {
        this.userDataAccess = factory.createUserDataAccess();

        return this;
    }

    /**
     * Builds the inventory data access.
     *
     * @return this builder
     */
    private DataAccessBuilder buildInventoryDataAccess() {
        this.inventoryDataAccess = factory.createInventoryAccessFactory();
        return this;
    }

    /**
     * Builds the timer data access.
     *
     * @return this builder
     */
    private DataAccessBuilder buildTimerDataAccess() {
        this.timerDataAccess = factory.createTimerDataAccess();
        return this;
    }

    /**
     * Builds the adoption data access.
     *
     * @return this builder
     */
    private DataAccessBuilder buildAdoptionDataAccess() {
        this.adoptionDataAccess = factory.createAdoptionDataAccess();
        return this;
    }

    /**
     * Builds the display cat image data access.
     *
     * @return this builder
     */
    private DataAccessBuilder buildDisplayCatImageDataAccess() {
        this.displayCatImageDataAccess = factory.createDisplayCatImageDataAccess();
        return this;
    }

    /**
     * Builds the cat data access.
     *
     * @return this builder
     */
    private DataAccessBuilder buildCatDataAccess() {
        this.catDataAccess = factory.createCatDataAccess();
        return this;
    }

    /**
     * Builds the cat fact data access.
     * 
     * @return this builder
     */
    private DataAccessBuilder buildCatFactDataAccess() {
        this.catFactDataAccess = factory.createCatFactDataAccess();
        return this;
    }
}
