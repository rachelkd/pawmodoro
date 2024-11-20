package app.service;

import javax.swing.JFrame;

import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import view.DisplayCatStatsView;

/**
 * Service for showing dialogs in the application.
 */
public class DialogService {
    /**
     * Shows the cat stats dialog.
     *
     * @param parent the parent frame
     * @param viewModel the view model for the dialog
     */
    public void showCatStatsDialog(JFrame parent, DisplayCatStatsViewModel viewModel) {
        DisplayCatStatsView.show(parent, viewModel);
    }
}
