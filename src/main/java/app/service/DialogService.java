package app.service;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import app.factory.DialogFactory;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import view.InventoryView;

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
     * @param mainPanel the main panel of the application
     */
    public DialogService(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.dialogFactory = new DialogFactory(mainPanel);
    }

    /**
     * Sets the main frame of the application.
     * This should be called after the main frame is created.
     * @param frame the main application frame
     */
    public void setMainFrame(JFrame frame) {
        this.mainFrame = frame;
        this.dialogFactory.setMainFrame(frame);
    }

    /**
     * Shows the cat stats dialog.
     * @param viewModel the view model for the dialog
     */
    public void showCatStatsDialog(DisplayCatStatsViewModel viewModel) {
        if (mainFrame == null) {
            mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        }
        dialogFactory.createCatStatsDialog(viewModel).setVisible(true);
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
     * @param viewModel the view model for the InventoryView dialog
     */
    public void showInventoryDialog(InventoryViewModel viewModel) {
        if (mainFrame == null) {
            mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        }
        if (inventoryDialog == null) {
            inventoryDialog = dialogFactory.createInventoryDialog(viewModel);
        }
        inventoryDialog.setVisible(true);
    }

    /**
     * Hide the Inventory Dialog.
     */
    public void hideInventoryDialog() {
        if (inventoryDialog != null && inventoryDialog.isVisible()) {
            inventoryDialog.setVisible(false);
        }
    }

    /**
     * Gets the inventory dialog.
     * @return the inventory dialog
     */
    public JDialog getInventoryDialog() {
        return inventoryDialog;
    }
}
