package app.factory;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import app.service.DialogService;
import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import view.AdoptionView;
import view.DisplayCatStatsView;
import view.GetCatFactView;
import view.InventoryView;

/**
 * Factory for creating dialog views.
 */
public class DialogFactory {
    private final JPanel mainPanel;
    private JFrame mainFrame;
    private DialogService dialogService;

    /**
     * Creates a new DialogFactory.
     * @param mainPanel the main panel of the application
     * @param dialogService the dialog service
     */
    public DialogFactory(JPanel mainPanel, DialogService dialogService) {
        this.mainPanel = mainPanel;
        this.dialogService = dialogService;
    }

    /**
     * Sets the main frame of the application.
     * @param frame the main application frame
     */
    public void setMainFrame(JFrame frame) {
        this.mainFrame = frame;
    }

    /**
     * Gets the parent frame of the application.
     * @return the parent frame
     */
    private JFrame getParentFrame() {
        final JFrame parentFrame;
        if (mainFrame != null) {
            parentFrame = mainFrame;
        }
        else {
            parentFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        }
        return parentFrame;
    }

    /**
     * Creates a new InventoryView dialog.
     * @param viewModel the view model for the dialog
     * @return the inventory view dialog
     */
    public InventoryView createInventoryDialog(InventoryViewModel viewModel) {
        return new InventoryView(getParentFrame(), viewModel);
    }

    /**
     * Creates a new DisplayCatStatsView dialog.
     * @param displayCatStatsViewModel the view model for the dialog
     * @param inventoryViewModel the view model for inventory dialog
     * @param getCatFactView the get cat fact view
     * @return the cat stats dialog
     */
    public DisplayCatStatsView createCatStatsDialog(DisplayCatStatsViewModel displayCatStatsViewModel,
            InventoryViewModel inventoryViewModel,
            GetCatFactView getCatFactView) {
        return new DisplayCatStatsView(getParentFrame(), displayCatStatsViewModel,
                inventoryViewModel, getCatFactView, dialogService);
    }

    /**
     * Creats a new AdoptionView dialog.
     * @param viewModel the view model for the dialog
     * @return the adoption view dialog
     */
    public AdoptionView createAdoptionDialog(AdoptionViewModel viewModel) {
        return new AdoptionView(getParentFrame(), viewModel);
    }
}
