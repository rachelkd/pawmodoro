package interface_adapter.initialize_cats;

import app.service.DialogService;
import interface_adapter.cat.CatViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import view.CatView;
import view.GetCatFactView;

/**
 * Foctory for building cat views.
 */
public class CatViewFactory {

    /**
     * Create the Cat View.
     * @param catViewModel the cat view model
     * @param displayCatStatsViewModel the display cat stats view model
     * @param inventoryViewModel the inventory view model
     * @param dialogService the dialog service
     * @param getCatFactView the cat fact view
     * @return a Cat view
     */
    public CatView createCatView(CatViewModel catViewModel,
                                 DisplayCatStatsViewModel displayCatStatsViewModel,
                                 InventoryViewModel inventoryViewModel,
                                 DialogService dialogService,
                                 GetCatFactView getCatFactView) {
        // need to set controller
        final CatView catView =
                new CatView(catViewModel, displayCatStatsViewModel, inventoryViewModel, dialogService, getCatFactView);
        return catView;
    }
}
