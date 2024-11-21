package app.builder;

import app.components.DataAccessComponents;
import app.factory.DataAccessFactory;
import use_case.adoption.AdoptionDataAccessInterface;
import use_case.cat.CatDataAccessInterface;
import use_case.create_inventory.CreateInventoryInventoryDataAccessInterface;
import use_case.display_cat_image.DisplayCatImageDataAccessInterface;
import use_case.get_cat_fact.CatFactDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.timer.TimerDataAccessInterface;

/**
 * Builder for data access components.
 */
public class DataAccessBuilder {
    private final DataAccessFactory factory;
    private LoginUserDataAccessInterface userDataAccess;
    private CreateInventoryInventoryDataAccessInterface inventoryDataAccess;
    private TimerDataAccessInterface timerDataAccess;
    private AdoptionDataAccessInterface adoptionDataAccess;
    private DisplayCatImageDataAccessInterface displayCatImageDataAccess;
    private CatDataAccessInterface catDataAccess;
    private CatFactDataAccessInterface catFactDataAccess;

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
        userDataAccess = factory.createUserDataAccess();
        return this;
    }

    /**
     * Builds the inventory data access.
     *
     * @return this builder
     */
    private DataAccessBuilder buildInventoryDataAccess() {
        inventoryDataAccess = factory.createInventoryDataAccess();
        return this;
    }

    /**
     * Builds the timer data access.
     *
     * @return this builder
     */
    private DataAccessBuilder buildTimerDataAccess() {
        timerDataAccess = factory.createTimerDataAccess();
        return this;
    }

    /**
     * Builds the adoption data access.
     *
     * @return this builder
     */
    private DataAccessBuilder buildAdoptionDataAccess() {
        adoptionDataAccess = factory.createAdoptionDataAccess();
        return this;
    }

    /**
     * Builds the display cat image data access.
     *
     * @return this builder
     */
    private DataAccessBuilder buildDisplayCatImageDataAccess() {
        displayCatImageDataAccess = factory.createDisplayCatImageDataAccess();
        return this;
    }

    /**
     * Builds the cat data access.
     *
     * @return this builder
     */
    private DataAccessBuilder buildCatDataAccess() {
        catDataAccess = factory.createCatDataAccess();
        return this;
    }

    /**
     * Builds the cat fact data access.
     *
     * @return this builder
     */
    private DataAccessBuilder buildCatFactDataAccess() {
        catFactDataAccess = factory.createCatFactDataAccess();
        return this;
    }
}
