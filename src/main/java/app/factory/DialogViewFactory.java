package app.factory;

import app.service.DialogService;
import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.runawaycat.RunawayCatViewModel;
import view.AdoptionView;
import view.DisplayCatStatsView;
import view.GetCatFactView;
import view.InventoryView;
import view.RunawayCatView;

/**
 * Factory for creating dialog-related views.
 */
public class DialogViewFactory {
    private final DialogService dialogService;

    public DialogViewFactory(DialogService dialogService) {
        this.dialogService = dialogService;
    }

    /**
     * Creates an Adoption View.
     * @param adoptionViewModel the adoption view model
     * @return AdoptionView
     */
    public AdoptionView createAdoptionView(AdoptionViewModel adoptionViewModel) {
        dialogService.createAdoptionDialog(adoptionViewModel);
        return (AdoptionView) dialogService.getAdoptionDialog();
    }

    /**
     * Creates a Runaway Cat View.
     * @param runawayCatViewModel the runaway cat view model
     * @return RunawayCatView
     */
    public RunawayCatView createRunawayCatView(RunawayCatViewModel runawayCatViewModel) {
        dialogService.createRunawayCatDialog(runawayCatViewModel);
        return (RunawayCatView) dialogService.getRunawayCatDialog();
    }

    /**
     * Creates an Inventory View.
     * @param inventoryViewModel the inventory view model
     * @return InventoryView
     */
    public InventoryView createInventoryView(InventoryViewModel inventoryViewModel) {
        dialogService.createInventoryDialog(inventoryViewModel);
        return (InventoryView) dialogService.getInventoryDialog();
    }

    /**
     * Creates a Display Cat Stats View.
     * @param displayCatStatsViewModel the display cat stats view model
     * @param inventoryViewModel the inventory view model
     * @param getCatFactView the get cat fact view
     * @return DisplayCatStatsView
     */
    public DisplayCatStatsView createDisplayCatStatsView(DisplayCatStatsViewModel displayCatStatsViewModel,
                                                        InventoryViewModel inventoryViewModel,
                                                        GetCatFactView getCatFactView) {
        dialogService.createDisplayCatStatsDialog(displayCatStatsViewModel, inventoryViewModel, getCatFactView);
        return (DisplayCatStatsView) dialogService.getDisplayCatStatsDialog();
    }
}
