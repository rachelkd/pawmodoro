package app.factory.viewmodel;

import interface_adapter.cat.CatViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.get_cat_fact.GetCatFactViewModel;
import interface_adapter.initialize_cats.InitializeCatsViewModel;
import interface_adapter.adoption.AdoptionViewModel;

/**
 * Factory for creating shared view models.
 */
public class SharedViewModelFactory {
    /**
     * Creates a new display cat stats view model.
     * @return the display cat stats view model
     */
    public DisplayCatStatsViewModel createDisplayCatStatsViewModel() {
        return new DisplayCatStatsViewModel();
    }

    /**
     * Creates a new inventory view model.
     * @return the inventory view model
     */
    public InventoryViewModel createInventoryViewModel() {
        return new InventoryViewModel();
    }

    /**
     * Creates a new cat view model.
     * @return the cat view model
     */
    public CatViewModel createCatViewModel() {
        return new CatViewModel();
    }

    /**
     * Creates a new get cat fact view model.
     * @return the get cat fact view model
     */
    public GetCatFactViewModel createGetCatFactViewModel() {
        return new GetCatFactViewModel();
    }

    public AdoptionViewModel createAdoptionViewModel() { return new AdoptionViewModel();}

    /**
     * Creates a new Initialize Cats view model.
     * @return the initialize cats view model
     */
    public InitializeCatsViewModel createInitializeCatsViewModel() {
        return new InitializeCatsViewModel();
    }
}
