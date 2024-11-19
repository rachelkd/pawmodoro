package view;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import constants.Constants;
import interface_adapter.display_cat_stats.DisplayCatStatsController;
import interface_adapter.cat.CatState;
import interface_adapter.cat.CatViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;
import app.service.DialogService;

/**
 * A view component that displays a cat's image and handles click interactions.
 * When clicked, it should display a random cat fact and provide access to the
 * cat's statistics.
 */
public class CatView extends JPanel implements PropertyChangeListener {
    private final CatViewModel catViewModel;
    private final DisplayCatStatsViewModel displayCatStatsViewModel;
    private final DialogService dialogService;
    private final JLabel imageLabel;
    private DisplayCatStatsController displayCatStatsController;

    /**
     * Creates a new CatView.
     *
     * @param catViewModel the view model for this cat view
     * @param displayCatStatsViewModel the view model for displaying cat stats
     * @param dialogService the service for showing dialogs
     */

    public CatView(CatViewModel catViewModel, DisplayCatStatsViewModel displayCatStatsViewModel,
            DialogService dialogService) {
        this.catViewModel = catViewModel;
        this.displayCatStatsViewModel = displayCatStatsViewModel;
        this.dialogService = dialogService;
        this.catViewModel.addPropertyChangeListener(this);

        // Create and configure image label
        imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // When cat is clicked, display cat stats dialog
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (displayCatStatsController != null) {
                    final CatState state = catViewModel.getState();
                    displayCatStatsController.execute(state.getOwnerUsername(), state.getCatName());

                    final JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(CatView.this);
                    dialogService.showCatStatsDialog(parentFrame, displayCatStatsViewModel);
                }
            }
        });

        // Add the image label to the panel
        this.add(imageLabel);

        // Update the image initially
        updateCatImage();
    }

    public void setDisplayCatStatsController(DisplayCatStatsController controller) {
        this.displayCatStatsController = controller;
    }

    private void updateCatImage() {
        final CatState state = catViewModel.getState();
        if (state != null && state.getImageFileName() != null) {
            final String imagePath = "/images/" + state.getImageFileName();
            final ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));

            final Image image = imageIcon.getImage();
            final Image scaledImage = image.getScaledInstance(
                    Constants.CAT_SPRITE_DISPLAY_SIZE,
                    Constants.CAT_SPRITE_DISPLAY_SIZE,
                    Image.SCALE_SMOOTH);

            imageLabel.setIcon(new ImageIcon(scaledImage));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: Rachel: Not sure what to do here yet
        if ("state".equals(evt.getPropertyName())) {
            updateCatImage();
        }
    }

    /**
     * Gets the view name.
     *
     * @return the view name
     */
    public String getViewName() {
        return catViewModel.getViewName();
    }
}
