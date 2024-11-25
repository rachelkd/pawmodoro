package app.service;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import app.factory.DialogFactory;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import view.GetCatFactView;

/**
 * Service for showing dialogs.
 */
public class DialogService {
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
     * @param getCatFactView the get cat fact view
     */
    public void showCatStatsDialog(DisplayCatStatsViewModel viewModel, GetCatFactView getCatFactView) {
        if (mainFrame == null) {
            mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        }
        dialogFactory.createCatStatsDialog(viewModel, getCatFactView).setVisible(true);
    }

    /**
     * Shows the timer settings dialog.
     */
    public void showTimerSettingsDialog() {
        // TODO: implement timer settings dialog @yhj050224
        // Method should take in a TimerSettingsViewModel
    }
}
