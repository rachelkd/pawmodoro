package interface_adapter.initialize_cats;

import app.service.DialogService;
import interface_adapter.cat.CatViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import view.CatView;
import view.GetCatFactView;

public class CatViewFactory {

    public CatView createCatView(CatViewModel catViewModel,
                                 DisplayCatStatsViewModel displayCatStatsViewModel,
                                 InventoryViewModel inventoryViewModel,
                                 DialogService dialogService,
                                 GetCatFactView getCatFactView) {
        // need to set controller
        CatView catView =
                new CatView(catViewModel, displayCatStatsViewModel, inventoryViewModel, dialogService, getCatFactView);
        return catView;
    }
}
