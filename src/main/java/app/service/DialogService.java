package app.service;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import view.DisplayCatStatsView;
import view.InventoryView;

import app.factory.DialogFactory;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.timer.TimerViewModel;
import view.TimerView;



/**
 * Service for showing dialogs.
 */
public class DialogService {
    private JDialog inventoryDialog;
    private final JPanel mainPanel;
    private final DialogFactory dialogFactory;
    private JFrame mainFrame;

    /**
     * Creates a new DialogService.
     * 
     * @param mainPanel the main panel of the application
     */
    public DialogService(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.dialogFactory = new DialogFactory(mainPanel);
    }

    /**
     * Sets the main frame of the application.
     * This should be called after the main frame is created.
     * 
     * @param frame the main application frame
     */
    public void setMainFrame(JFrame frame) {
        this.mainFrame = frame;
        this.dialogFactory.setMainFrame(frame);
    }

    /**
     * Shows the cat stats dialog.
     * 
     * @param viewModel the view model for the dialog
     */
    public void showCatStatsDialog(DisplayCatStatsViewModel viewModel) {
        if (mainFrame == null) {
            mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        }
        dialogFactory.createCatStatsDialog(viewModel).setVisible(true);
    }

    /**
     * Shows the timer settings dialog.
     */
    public void showTimerSettingsDialog() {
        // TODO: implement timer settings dialog @yhj050224
        // Method should take in a TimerSettingsViewModel
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
