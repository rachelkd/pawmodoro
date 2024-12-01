package app.factory;

import app.service.DialogService;
import interface_adapter.cat.CatViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_image.DisplayCatImageViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModelFactory;
import interface_adapter.get_cat_fact.GetCatFactViewModel;
import interface_adapter.initialize_cats.CatViewFactory;
import interface_adapter.initialize_cats.InitializeCatsViewModel;
import view.CatContainerView;
import view.CatView;
import view.DisplayCatImageView;
import view.GetCatFactView;

/**
 * Factory for creating cat-related views.
 */
public class CatRelatedViewFactory {
    private final CatViewFactory catViewFactory;
    private final DisplayCatStatsViewModelFactory displayCatStatsViewModelFactory;

    public CatRelatedViewFactory(CatViewFactory catViewFactory,
                                DisplayCatStatsViewModelFactory displayCatStatsViewModelFactory) {
        this.catViewFactory = catViewFactory;
        this.displayCatStatsViewModelFactory = displayCatStatsViewModelFactory;
    }

    /**
     * Creates a Cat View.
     * @param catViewModel the cat view model
     * @param displayCatStatsViewModel the display cat stats view model
     * @param inventoryViewModel the inventory view model
     * @param dialogService the dialog service
     * @param getCatFactView get cat fact view
     * @return CatView
     */
    public CatView createCatView(CatViewModel catViewModel,
                                DisplayCatStatsViewModel displayCatStatsViewModel,
                                InventoryViewModel inventoryViewModel,
                                DialogService dialogService,
                                GetCatFactView getCatFactView) {
        return catViewFactory.createCatView(catViewModel, displayCatStatsViewModel,
                inventoryViewModel, dialogService, getCatFactView);
    }

    /**
     * Creates a Cat Container View.
     * @param initializeCatsViewModel the initialize cats view model
     * @param inventoryViewModel the inventory view model
     * @param dialogService the dialog service
     * @param getCatFactView get cat fact view
     * @return CatContainerView
     */
    public CatContainerView createCatContainerView(InitializeCatsViewModel initializeCatsViewModel,
                                               InventoryViewModel inventoryViewModel,
                                               DialogService dialogService,
                                               GetCatFactView getCatFactView) {
        return new CatContainerView(initializeCatsViewModel, inventoryViewModel, dialogService,
                getCatFactView, catViewFactory, displayCatStatsViewModelFactory);
    }

    /**
     * Creates a Get Cat Fact View.
     * @param getCatFactViewModel the get cat fact view model
     * @return GetCatFactView
     */
    public GetCatFactView createGetCatFactView(GetCatFactViewModel getCatFactViewModel) {
        return new GetCatFactView(getCatFactViewModel);
    }

    /**
     * Creates a Display Cat Image View.
     * @param displayCatImageViewModel the display cat image view model
     * @return DisplayCatImageView
     */
    public DisplayCatImageView createDisplayCatImageView(DisplayCatImageViewModel displayCatImageViewModel) {
        return new DisplayCatImageView(displayCatImageViewModel);
    }
}
