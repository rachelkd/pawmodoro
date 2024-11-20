package app.builder;

import app.components.DataAccessComponents;
import data_access.AdoptionDataAccessObject;
import data_access.ApiDisplayCatImageDataAccessObject;
import data_access.DBCatDataAccessObject;
import data_access.DBUserDataAccessObject;
import data_access.InMemoryInventoryDataAccessObject;
import data_access.InMemoryTimerDataAccessObject;
import entity.CatFactory;
import entity.CatImageFactory;
import entity.CommonUserFactory;

/**
 * Builder for data access components.
 */
public class DataAccessBuilder {
    private DBUserDataAccessObject userDataAccess;
    private InMemoryInventoryDataAccessObject inventoryDataAccess;
    private InMemoryTimerDataAccessObject timerDataAccess;
    private AdoptionDataAccessObject adoptionDataAccess;
    private ApiDisplayCatImageDataAccessObject displayCatImageDataAccess;
    private DBCatDataAccessObject catDataAccess;

    /**
     * Builds the user data access component.
     *
     * @return this builder
     */
    public DataAccessBuilder buildUserDataAccess() {
        this.userDataAccess = new DBUserDataAccessObject(new CommonUserFactory());
        return this;
    }

    /**
     * Builds the inventory data access component.
     *
     * @return this builder
     */
    public DataAccessBuilder buildInventoryDataAccess() {
        this.inventoryDataAccess = new InMemoryInventoryDataAccessObject();
        return this;
    }

    /**
     * Builds the timer data access component.
     *
     * @return this builder
     */
    public DataAccessBuilder buildTimerDataAccess() {
        this.timerDataAccess = new InMemoryTimerDataAccessObject();
        return this;
    }

    /**
     * Builds the adoption data access component.
     *
     * @return this builder
     */
    public DataAccessBuilder buildAdoptionDataAccess() {
        this.adoptionDataAccess = new AdoptionDataAccessObject();
        return this;
    }

    /**
     * Builds the display cat image data access component.
     *
     * @return this builder
     */
    public DataAccessBuilder buildDisplayCatImageDataAccess() {
        this.displayCatImageDataAccess = new ApiDisplayCatImageDataAccessObject(new CatImageFactory());
        return this;
    }

    /**
     * Builds the cat data access component.
     *
     * @return this builder
     */
    public DataAccessBuilder buildCatDataAccess() {
        this.catDataAccess = new DBCatDataAccessObject(new CatFactory());
        return this;
    }

    /**
     * Builds and returns the data access components.
     *
     * @return the data access components
     */
    public DataAccessComponents build() {
        return new DataAccessComponents(
                userDataAccess,
                inventoryDataAccess,
                timerDataAccess,
                adoptionDataAccess,
                displayCatImageDataAccess,
                catDataAccess);
    }
}
