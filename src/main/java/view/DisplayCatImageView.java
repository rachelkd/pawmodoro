package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import constants.Constants;
import interface_adapter.display_cat_image.DisplayCatImageController;
import interface_adapter.display_cat_image.DisplayCatImageViewModel;

/**
 * View component for displaying cat images.
 */
public class DisplayCatImageView extends JPanel implements PropertyChangeListener {
    private final DisplayCatImageViewModel viewModel;
    private DisplayCatImageController displayCatImageController;
    private final JLabel imageLabel;

    /**
     * Creates a new DisplayCatImageView.
     * 
     * @param viewModel the view model
     */
    public DisplayCatImageView(DisplayCatImageViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Set preferred size to ensure proper layout
        setPreferredSize(new Dimension(Constants.CAT_IMAGE_SIZE, Constants.CAT_IMAGE_SIZE));

        // Create a panel for the image to ensure proper centering
        final JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.CENTER);

        if (viewModel.isRefreshAllowed()) {
            final JButton refreshButton = new JButton("New Cat");

            // Create a panel to hold the button and center it
            final JPanel buttonPanel = new JPanel();
            refreshButton.setPreferredSize(
                    new Dimension(Constants.DISPLAY_CAT_REFRESH_BUTTON_WIDTH,
                            Constants.DISPLAY_CAT_REFRESH_BUTTON_HEIGHT));
            buttonPanel.add(refreshButton);

            refreshButton.addActionListener(event -> displayCatImageController.fetchNewImage());
            add(buttonPanel, BorderLayout.SOUTH);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (viewModel.getErrorMessage().isEmpty()) {
            try {
                final URI imageUri = URI.create(viewModel.getImageUrl());
                final ImageIcon originalIcon = new ImageIcon(imageUri.toURL());
                final Image originalImage = originalIcon.getImage();

                final int originalWidth = originalImage.getWidth(null);
                final int originalHeight = originalImage.getHeight(null);

                // Calculate scaling factor to fit within max dimensions
                final double widthScale = (double) Constants.CAT_IMAGE_MAX_WIDTH / originalWidth;
                final double heightScale = (double) Constants.CAT_IMAGE_MAX_HEIGHT / originalHeight;
                final double scale = Math.min(widthScale, heightScale);

                // Calculate new dimensions
                final int scaledWidth = (int) (originalWidth * scale);
                final int scaledHeight = (int) (originalHeight * scale);

                final Image scaledImage = originalImage.getScaledInstance(
                        scaledWidth,
                        scaledHeight,
                        Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
                imageLabel.setText(viewModel.getErrorMessage());

                // Update panel size to match scaled image
                setPreferredSize(new Dimension(scaledWidth, scaledHeight));
            }
            catch (IllegalArgumentException | MalformedURLException exception) {
                imageLabel.setText("Failed to load image: Invalid URL" + exception.getMessage());
            }
        }
        else {
            imageLabel.setText(viewModel.getErrorMessage());
        }
        revalidate();
        repaint();
    }

    /**
     * Sets the controller for the display cat image view.
     * Fetches a new cat image when the controller is set.
     * 
     * @param displayCatImageController the controller
     */
    public void setDisplayCatImageController(DisplayCatImageController displayCatImageController) {
        this.displayCatImageController = displayCatImageController;
        this.fetchNewImage();
    }

    /**
     * Fetches a new cat image.
     */
    public void fetchNewImage() {
        displayCatImageController.fetchNewImage();
    }
}
