package app.service;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import app.factory.DialogFactory;
import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.runawaycat.RunawayCatViewModel;
import view.DisplayCatStatsView;
import view.GetCatFactView;
import view.InventoryView;
import view.RunawayCatView;

/**
 * Service for showing dialogs.
 */
public class DialogService {

    private InventoryView inventoryDialog;
    private DisplayCatStatsView displayCatStatsDialog;
    private JDialog adoptionDialog;
    private JDialog runawayCatDialog;

    private final JPanel mainPanel;
    private final DialogFactory dialogFactory;
    private JFrame mainFrame;

    /**
     * Creates a new DialogService.
     * @param mainPanel the main panel of the application
     */
    public DialogService(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.dialogFactory = new DialogFactory(mainPanel, this);
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
     * @param displayCatStatsViewModel the view model for the dialog
     * @param inventoryViewModel the view model for the inventory dialog
     * @param getCatFactView the get cat fact view
     */
    public void showCatStatsDialog(DisplayCatStatsViewModel displayCatStatsViewModel,
            InventoryViewModel inventoryViewModel,
            GetCatFactView getCatFactView) {
        if (mainFrame == null) {
            mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        }

        // Create dialog if it doesn't exist
        if (displayCatStatsDialog == null) {
            displayCatStatsDialog = dialogFactory.createCatStatsDialog(
                    displayCatStatsViewModel, inventoryViewModel, getCatFactView);
        }

        // Update the cat fact before showing
        getCatFactView.fetchNewFact();

        // Show the dialog
        displayCatStatsDialog.setVisible(true);
    }

    /**
     * Create the inventory dialog.
     * @param viewModel the view model for the dialog
     */
    public void createInventoryDialog(InventoryViewModel viewModel) {
        if (mainFrame == null) {
            mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        }
        if (inventoryDialog == null) {
            inventoryDialog = dialogFactory.createInventoryDialog(viewModel);
        }
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
    public InventoryView getInventoryDialog() {
        return inventoryDialog;
    }

    /**
     * Creates the display cat stats dialog.
     * @param displayCatStatsViewModel the view model for the dialog
     * @param inventoryViewModel the view model for the inventory dialog
     * @param getCatFactView the get cat fact view
     */
    public void createDisplayCatStatsDialog(DisplayCatStatsViewModel displayCatStatsViewModel,
            InventoryViewModel inventoryViewModel,
            GetCatFactView getCatFactView) {
        if (mainFrame == null) {
            mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        }
        if (displayCatStatsDialog == null) {
            displayCatStatsDialog = dialogFactory.createCatStatsDialog(
                    displayCatStatsViewModel, inventoryViewModel, getCatFactView);
        }
    }

    /**
     * Shows the display cat stats dialog.
     */
    public void showDisplayCatStatsDialog() {
        if (displayCatStatsDialog != null) {
            displayCatStatsDialog.setVisible(true);
        }
    }

    /**
     * Gets the display cat stats dialog.
     * @return the display cat stats dialog
     */
    public DisplayCatStatsView getDisplayCatStatsDialog() {
        return displayCatStatsDialog;
    }

    /**
     * Creates the Adoption Dialog.
     * @param viewModel the view model for the adoption dialog
     */
    public void createAdoptionDialog(AdoptionViewModel viewModel) {
        if (mainFrame == null) {
            mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        }
        if (adoptionDialog == null) {
            adoptionDialog = dialogFactory.createAdoptionDialog(viewModel);
        }
    }

    /**
     * Shows the adoption dialog.
     * @param viewModel the view model for the adoption dialog
     */
    public void showAdoptionDialog(AdoptionViewModel viewModel) {
        if (mainFrame == null) {
            mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        }
        if (adoptionDialog == null) {
            adoptionDialog = dialogFactory.createAdoptionDialog(viewModel);
        }
        adoptionDialog.setVisible(true);
    }

    /**
     * Hides the adoption dialog.
     */
    public void hideAdoptionDialog() {
        if (adoptionDialog != null && adoptionDialog.isVisible()) {
            adoptionDialog.setVisible(false);
        }
    }

    /**
     * Gets the adoption dialog.
     * @return the adoption dialog
     */
    public JDialog getAdoptionDialog() {
        return adoptionDialog;

    }

    /**
     * Creates the Runaway Cat Dialog.
     * @param viewModel the view model for the runaway cat dialog
     */
    public void createRunawayCatDialog(RunawayCatViewModel viewModel) {
        if (mainFrame == null) {
            mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        }
        if (runawayCatDialog == null) {
            runawayCatDialog = dialogFactory.createRunawayCatDialog(viewModel);
        }
    }

    /**
    * Shows the Runaway Cat Dialog.
    * @param viewModel the view model for the runaway cat dialog
    */
    public void showRunawayCatDialog(RunawayCatViewModel viewModel) {
        if (mainFrame == null) {
            mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        }
        if (runawayCatDialog == null) {
            runawayCatDialog = dialogFactory.createRunawayCatDialog(viewModel);
        }
        runawayCatDialog.setVisible(true);
    }

    /**
    * Hides the runaway cat dialog.
    */
    public void hideRunawayCatDialog() {
        if (runawayCatDialog != null && runawayCatDialog.isVisible()) {
            runawayCatDialog.setVisible(false);
        }
    }

    /**
    * Gets the runaway cat dialog.
    * @return the runaway cat dialog
    */
    public JDialog getRunawayCatDialog() {
        return runawayCatDialog;
    }

}
