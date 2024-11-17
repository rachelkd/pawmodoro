package view;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import interface_adapter.cat.CatState;
import interface_adapter.cat.CatViewModel;

/**
 * A view component that displays a cat's image and handles click interactions.
 * When clicked, it should display a random cat fact and provide access to the
 * cat's statistics.
 */
public class CatView extends JPanel implements PropertyChangeListener {
    private final CatViewModel catViewModel;
    private final JLabel imageLabel;

    /**
     * Creates a new CatView.
     *
     * @param catViewModel the view model for this cat view
     */
    public CatView(CatViewModel catViewModel) {
        this.catViewModel = catViewModel;
        this.catViewModel.addPropertyChangeListener(this);

        // Create and configure image label
        imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add mouse listener for click interactions
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                // TODO: Implement in CatFactUseCase
                // 1. Controller should call use case to:
                // - Get random cat fact
                // - Create dialog with fact and "See {cat's name}'s statistics" button
                // 2. When statistics button is clicked, switch to CatStatisticsView
            }
        });

        this.add(imageLabel);
        this.updateCatImage();
    }

    private void updateCatImage() {
        final CatState state = catViewModel.getState();
        if (state != null && state.getImageFileName() != null) {
            try {
                final String imagePath = "/images/" + state.getImageFileName();
                final ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));

                final Image image = imageIcon.getImage();
                final Image scaledImage = image.getScaledInstance(
                        Constants.CAT_IMAGE_MAX_WIDTH,
                        Constants.CAT_IMAGE_MAX_HEIGHT,
                        Image.SCALE_SMOOTH);

                imageLabel.setIcon(new ImageIcon(scaledImage));
            }
            catch (Exception exception) {
                imageLabel.setIcon(null);
                imageLabel.setText("Image not found");
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final CatState state = (CatState) evt.getNewValue();
        // TODO: Rachel: Not sure what to do here yet
    }
}
