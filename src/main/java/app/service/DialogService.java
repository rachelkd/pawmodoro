package app.service;

import javax.swing.JDialog;
import javax.swing.JFrame;

import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import view.DisplayCatStatsView;
import view.InventoryView;

/**
 * Service for showing dialogs in the application.
 */
public class DialogService {
    private JDialog inventoryDialog;

    /**
     * Shows the cat stats dialog.
     *
     * @param parent the parent frame
     * @param viewModel the view model for the dialog
     */
    public void showCatStatsDialog(JFrame parent, DisplayCatStatsViewModel viewModel) {
        DisplayCatStatsView.show(parent, viewModel);
    }

    /**
     * Return the inventory dialog.
     * @param parent the parent frame
     * @param viewModel the view model for the dialog
     * @return the Inventory View
     */
    public InventoryView createInventoryDialog(JFrame parent, InventoryViewModel viewModel) {
        // avoid duplicating dialog
        if (inventoryDialog != null) {
            return null;
        }
        this.inventoryDialog = new InventoryView(parent, viewModel);
        return (InventoryView) inventoryDialog;
    }

    /**
     * Show the Inventory Dialog.
     */
    public void showInventoryDialog() {
        if (inventoryDialog != null && !inventoryDialog.isVisible()) {
            inventoryDialog.setVisible(true);
        }
    }

    /**
     * Hide the Inventory Dialog.
     */
    public void hideInventoryDialog() {
        if (inventoryDialog != null && inventoryDialog.isVisible()) {
            inventoryDialog.setVisible(false);
        }
    }
}
