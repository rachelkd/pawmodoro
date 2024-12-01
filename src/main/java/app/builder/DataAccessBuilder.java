package app.builder;

import app.components.DataAccessComponents;
import app.factory.DataAccessFactory;
import data_access.ApiCatFactDataAccessObject;
import data_access.ApiDisplayCatImageDataAccessObject;
import data_access.DbCatDataAccessObject;
import data_access.DbInventoryDataAccessObject;
import data_access.DbUserDataAccessObject;

/**
 * Builder for data access components.
 */
public class DataAccessBuilder {
    private final DataAccessFactory factory;
    private DbUserDataAccessObject userDataAccess;
    private DbInventoryDataAccessObject inventoryDataAccess;
    private ApiDisplayCatImageDataAccessObject displayCatImageDataAccess;
    private DbCatDataAccessObject catDataAccess;
    private ApiCatFactDataAccessObject catFactDataAccess;

    public DataAccessBuilder() {
        this.factory = new DataAccessFactory();
    }

    /**
     * Builds and returns all data access components.
     * @return the data access components
     */
    public DataAccessComponents build() {
        buildUserDataAccess()
                .buildInventoryDataAccess()
                .buildDisplayCatImageDataAccess()
                .buildCatDataAccess()
                .buildCatFactDataAccess();

        return new DataAccessComponents(
                userDataAccess,
                inventoryDataAccess,
                displayCatImageDataAccess,
                catDataAccess,
                catFactDataAccess);
    }

    /**
     * Builds the user data access.
     * @return this builder
     */
    private DataAccessBuilder buildUserDataAccess() {
        this.userDataAccess = factory.createUserDataAccess();

        return this;
    }

    /**
     * Builds the inventory data access.
     * @return this builder
     */
    private DataAccessBuilder buildInventoryDataAccess() {
        this.inventoryDataAccess = factory.createInventoryAccessFactory();
        return this;
    }

    /**
     * Builds the display cat image data access.
     * @return this builder
     */
    private DataAccessBuilder buildDisplayCatImageDataAccess() {
        this.displayCatImageDataAccess = factory.createDisplayCatImageDataAccess();
        return this;
    }

    /**
     * Builds the cat data access.
     * @return this builder
     */
    private DataAccessBuilder buildCatDataAccess() {
        this.catDataAccess = factory.createCatDataAccess();
        return this;
    }

    /**
     * Builds the cat fact data access.
     * @return this builder
     */
    private DataAccessBuilder buildCatFactDataAccess() {
        this.catFactDataAccess = factory.createCatFactDataAccess();
        return this;
    }
}
