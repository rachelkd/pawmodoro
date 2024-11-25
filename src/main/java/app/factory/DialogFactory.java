package app.factory;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import view.DisplayCatStatsView;
import view.InventoryView;

/**
 * Factory for creating dialog views.
 */
public class DialogFactory {
    private final JPanel mainPanel;
    private JFrame mainFrame;

    /**
     * Creates a new DialogFactory.
     * @param mainPanel the main panel of the application
     */
    public DialogFactory(JPanel mainPanel) {
        this.mainPanel = mainPanel;
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
     * Creates a new DisplayCatStatsView dialog.
     * @param viewModel the view model for the dialog
     * @return the created dialog
     */
    public DisplayCatStatsView createCatStatsDialog(DisplayCatStatsViewModel viewModel) {
        return new DisplayCatStatsView(getParentFrame(), viewModel);
    }

    /**
     * Creates a new InventoryView dialog.
     * @param viewModel the view model for the dialog
     * @return the created dialog
     */
    public InventoryView createInventoryDialog(InventoryViewModel viewModel) {
        return new InventoryView(getParentFrame(), viewModel);
    }
}
