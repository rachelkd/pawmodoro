package app.service;

import app.factory.DialogFactory;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import interface_adapter.timer.TimerViewModel;
import view.TimerView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
     */
    public void showCatStatsDialog(DisplayCatStatsViewModel viewModel) {
        if (mainFrame == null) {
            mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        }
        dialogFactory.createCatStatsDialog(viewModel).setVisible(true);
    }

    public void showTimerSettingsDialog(TimerViewModel viewModel) {
        // TODO: implement timer settings dialog @yhj050224
    }
}
