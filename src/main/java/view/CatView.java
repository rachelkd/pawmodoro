package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import app.service.DialogService;
import constants.Constants;
import interface_adapter.cat.CatState;
import interface_adapter.cat.CatViewModel;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.display_cat_stats.DisplayCatStatsController;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;

/**
 * A view component that displays a cat's image and handles click interactions.
 * When clicked, it displays the cat's statistics.
 */
public class CatView extends JPanel implements ActionListener, PropertyChangeListener {
    private final CatViewModel catViewModel;
    private final InventoryViewModel inventoryViewModel;
    private final JLabel imageLabel;
    private final DialogService dialogService;
    private DisplayCatStatsController displayCatStatsController;
    private final GetCatFactView getCatFactView;
    private final DisplayCatStatsViewModel displayCatStatsViewModel;

    /**
     * Creates a new CatView.
     * @param catViewModel the view model for this cat view
     * @param displayCatStatsViewModel the view model for displaying cat stats
     * @param inventoryViewModel the view model for the inventory
     * @param dialogService the service for showing dialogs
     * @param getCatFactView the view for displaying cat facts
     */
    public CatView(CatViewModel catViewModel, DisplayCatStatsViewModel displayCatStatsViewModel,
            InventoryViewModel inventoryViewModel, DialogService dialogService, GetCatFactView getCatFactView) {
        this.catViewModel = catViewModel;
        this.catViewModel.addPropertyChangeListener(this);
        this.inventoryViewModel = inventoryViewModel;
        this.dialogService = dialogService;
        this.getCatFactView = getCatFactView;
        this.displayCatStatsViewModel = displayCatStatsViewModel;

        // Set layout to BorderLayout for centering at bottom
        this.setLayout(new BorderLayout());

        // Create and configure image label
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.BOTTOM);

        // Set initial sizes for the image label only
        final Dimension size = new Dimension(Constants.CAT_SPRITE_DISPLAY_SIZE, Constants.CAT_SPRITE_DISPLAY_SIZE);
        imageLabel.setPreferredSize(size);

        // Make sure components are visible
        this.setOpaque(true);
        this.setVisible(true);
        imageLabel.setOpaque(true);
        imageLabel.setVisible(true);

        // Set up mouse listener
        this.setupMouseListener();

        // Add the image label to the bottom center of the panel
        this.add(imageLabel, BorderLayout.PAGE_END);

        // Update the image initially
        final CatState state = catViewModel.getState();
        updateCatImage(state);

        // Make sure we're visible after everything is set up
        SwingUtilities.invokeLater(() -> {
            this.setVisible(true);
            this.revalidate();
            this.repaint();
        });
    }

    /**
     * Sets up the mouse listener for the cat image.
     * When clicked, displays the cat's statistics.
     */
    private void setupMouseListener() {
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (displayCatStatsController != null) {
                    final CatState state = catViewModel.getState();
                    displayCatStatsController.execute(state.getOwnerUsername(), state.getCatName());
                    dialogService.showCatStatsDialog(displayCatStatsViewModel, inventoryViewModel, getCatFactView);
                }
            }
        });
    }

    public void setDisplayCatStatsController(DisplayCatStatsController controller) {
        this.displayCatStatsController = controller;
    }

    private void updateCatImage(CatState state) {
        if (state != null && state.getImageFileName() != null) {
            final String imagePath = "/images/" + state.getImageFileName();
            final URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl != null) {
                final ImageIcon imageIcon = new ImageIcon(imageUrl);

                // Scale the image
                final Image scaledImage = imageIcon.getImage().getScaledInstance(
                        Constants.CAT_SPRITE_DISPLAY_SIZE,
                        Constants.CAT_SPRITE_DISPLAY_SIZE,
                        Image.SCALE_SMOOTH);

                // Create a new ImageIcon with the scaled image
                final ImageIcon scaledIcon = new ImageIcon(scaledImage);
                imageLabel.setIcon(scaledIcon);

                // Force revalidate and repaint
                this.revalidate();
                this.repaint();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Mouse click is handled by MouseAdapter
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final CatState state = (CatState) evt.getNewValue();
            updateCatImage(state);
        }
    }

    /**
     * Gets the view name.
     * @return the view name
     */
    public String getViewName() {
        return catViewModel.getViewName();
    }
}
