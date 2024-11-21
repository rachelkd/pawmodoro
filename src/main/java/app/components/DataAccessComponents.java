package app.components;

import use_case.adoption.AdoptionDataAccessInterface;
import use_case.cat.CatDataAccessInterface;
import use_case.create_inventory.CreateInventoryInventoryDataAccessInterface;
import use_case.display_cat_image.DisplayCatImageDataAccessInterface;
import use_case.get_cat_fact.CatFactDataAccessInterface;
import use_case.timer.TimerDataAccessInterface;
import use_case.user.UserDataAccessInterface;

/**
 * Components for data access.
 */
public class DataAccessComponents {
    private final UserDataAccessInterface userDataAccess;
    private final CreateInventoryInventoryDataAccessInterface inventoryDataAccess;
    private final TimerDataAccessInterface timerDataAccess;
    private final AdoptionDataAccessInterface adoptionDataAccess;
    private final DisplayCatImageDataAccessInterface displayCatImageDataAccess;
    private final CatDataAccessInterface catDataAccess;
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
    public DataAccessComponents(
            UserDataAccessInterface userDataAccess,
            CreateInventoryInventoryDataAccessInterface inventoryDataAccess,
            TimerDataAccessInterface timerDataAccess,
            AdoptionDataAccessInterface adoptionDataAccess,
            DisplayCatImageDataAccessInterface displayCatImageDataAccess,
            CatDataAccessInterface catDataAccess,
            CatFactDataAccessInterface catFactDataAccess) {
        this.userDataAccess = userDataAccess;
        this.inventoryDataAccess = inventoryDataAccess;
        this.timerDataAccess = timerDataAccess;
        this.adoptionDataAccess = adoptionDataAccess;
        this.displayCatImageDataAccess = displayCatImageDataAccess;
        this.catDataAccess = catDataAccess;
        this.catFactDataAccess = catFactDataAccess;
    }

    public UserDataAccessInterface getUserDataAccess() {
        return userDataAccess;
    }

    public CreateInventoryInventoryDataAccessInterface getInventoryDataAccess() {
        return inventoryDataAccess;
    }

    public TimerDataAccessInterface getTimerDataAccess() {
        return timerDataAccess;
    }

    public AdoptionDataAccessInterface getAdoptionDataAccess() {
        return adoptionDataAccess;
    }

    public DisplayCatImageDataAccessInterface getDisplayCatImageDataAccess() {
        return displayCatImageDataAccess;
    }

    public CatDataAccessInterface getCatDataAccess() {
        return catDataAccess;
    }

    public CatFactDataAccessInterface getCatFactDataAccess() {
        return catFactDataAccess;
    }
}
