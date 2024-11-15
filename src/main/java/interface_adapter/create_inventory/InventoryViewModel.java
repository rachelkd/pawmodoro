package interface_adapter.create_inventory;

import interface_adapter.ViewModel;

/**
 * View Model for the Inventory View.
 */
public class InventoryViewModel extends ViewModel<InventoryState> {

    public static final String TITLE_LABEL = "Inventory";
    public static final String EMPTY_INVENTORY_LABEL = "Inventory is currently empty...";

    public InventoryViewModel() {
        super("inventory");
        setState(new InventoryState());
    }
}
