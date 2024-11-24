package app.service;

import javax.swing.*;

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
     * Shows the inventory dialog.
     * @param parent the parent frame
     * @param viewModel the view model for the dialog
     */
    public void showInventoryDialog(JFrame parent, InventoryViewModel viewModel) {
        // avoid duplicating dialog
        if (inventoryDialog != null && inventoryDialog.isVisible()) {
            return;
        }

        inventoryDialog = new InventoryView(parent, viewModel);
        // viewModel.addPropertyChangeListener(event -> {});

        inventoryDialog.setVisible(true);
    }
}
