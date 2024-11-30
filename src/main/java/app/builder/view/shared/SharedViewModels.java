package app.builder.view.shared;

import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.cat.CatViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.get_cat_fact.GetCatFactViewModel;
import interface_adapter.initialize_cats.InitializeCatsViewModel;

/**
 * Container for view models that are shared across multiple views.
 */
public class SharedViewModels {
    private final DisplayCatStatsViewModel displayCatStatsViewModel;
    private final InventoryViewModel inventoryViewModel;
    private final CatViewModel catViewModel;
    private final GetCatFactViewModel getCatFactViewModel;
    private final InitializeCatsViewModel initializeCatsViewModel;
    private final AdoptionViewModel adoptionViewModel;

    /**
     * Creates a new SharedViewModels container.
     * @param displayCatStatsViewModel the display cat stats view model
     * @param inventoryViewModel the inventory view model
     * @param catViewModel the cat view model
     * @param getCatFactViewModel the get cat fact view model
     * @param adoptionViewModel the adoption view model
     * @param initializeCatsViewModel the initialize cat view model
     */
    public SharedViewModels(
            DisplayCatStatsViewModel displayCatStatsViewModel,
            InventoryViewModel inventoryViewModel,
            CatViewModel catViewModel,
            GetCatFactViewModel getCatFactViewModel,
            AdoptionViewModel adoptionViewModel,
            InitializeCatsViewModel initializeCatsViewModel) {
        this.displayCatStatsViewModel = displayCatStatsViewModel;
        this.inventoryViewModel = inventoryViewModel;
        this.catViewModel = catViewModel;
        this.getCatFactViewModel = getCatFactViewModel;
        this.adoptionViewModel = adoptionViewModel;
        this.initializeCatsViewModel = initializeCatsViewModel;
    }

    public DisplayCatStatsViewModel getDisplayCatStatsViewModel() {
        return displayCatStatsViewModel;
    }

    public InventoryViewModel getInventoryViewModel() {
        return inventoryViewModel;
    }

    public CatViewModel getCatViewModel() {
        return catViewModel;
    }

    public GetCatFactViewModel getGetCatFactViewModel() {
        return getCatFactViewModel;
    }

    public AdoptionViewModel getAdoptionViewModel() {
        return adoptionViewModel;
    }

    public InitializeCatsViewModel getInitializeCatsViewModel() {
        return initializeCatsViewModel;
    }
}
