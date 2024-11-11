package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import interface_adapter.cat_image.CatImageController;
import interface_adapter.cat_image.CatImageViewModel;

/**
 * View component for displaying cat images.
 */
public class CatImageView extends JPanel implements PropertyChangeListener {
    private final CatImageViewModel viewModel;
    private final CatImageController controller;
    private final JLabel imageLabel;

    /**
     * Creates a new CatImageView.
     * 
     * @param viewModel the view model
     * @param controller the controller
     */
    public CatImageView(CatImageViewModel viewModel, CatImageController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.viewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Set preferred size to ensure proper layout
        setPreferredSize(new Dimension(Constants.CAT_IMAGE_SIZE, Constants.CAT_IMAGE_SIZE));

        // Create a panel for the image to ensure proper centering
        final JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.CENTER);

        if (viewModel.isRefreshAllowed()) {
            final JButton refreshButton = new JButton("New Cat");
            refreshButton.addActionListener(event -> controller.fetchNewImage());
            add(refreshButton, BorderLayout.SOUTH);
        }

        // Fetch initial image
        controller.fetchNewImage();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (viewModel.getErrorMessage().isEmpty()) {
            try {
                final URL imageUrl = new URL(viewModel.getImageUrl());
                final ImageIcon originalIcon = new ImageIcon(imageUrl);
                final Image originalImage = originalIcon.getImage();

                // Calculate height to maintain aspect ratio
                final double aspectRatio = (double) originalImage.getHeight(null) / originalImage.getWidth(null);
                final int scaledHeight = (int) (Constants.CAT_IMAGE_SIZE * aspectRatio);

                final Image scaledImage = originalImage.getScaledInstance(
                        Constants.CAT_IMAGE_SIZE,
                        scaledHeight,
                        Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));

                // Update panel size to match image
                setPreferredSize(new Dimension(Constants.CAT_IMAGE_SIZE, scaledHeight));
            }
            catch (final MalformedURLException exception) {
                imageLabel.setText("Failed to load image: Invalid URL");
            }
        }
        else {
            imageLabel.setText(viewModel.getErrorMessage());
        }
        revalidate();
        repaint();
    }
}
