package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.cat.CatState;
import interface_adapter.display_cat_stats.DisplayCatStatsViewModel;

/**
 * Dialog for displaying cat statistics.
 */
public class DisplayCatStatsView extends JDialog implements ActionListener, PropertyChangeListener {
    private static final int PADDING = 10;
    private static final int FONT_SIZE = 16;
    private static final int IMAGE_SIZE = 200;

    private final DisplayCatStatsViewModel displayCatStatsViewModel;
    private final JLabel catImageLabel;
    private final JLabel catNameLabel;
    private final JLabel hungerLabel;
    private final JLabel happinessLabel;

    /**
     * Creates a new DisplayCatStatsView dialog.
     *
     * @param parent The parent frame
     * @param displayCatStatsViewModel The view model
     */
    public DisplayCatStatsView(JFrame parent, DisplayCatStatsViewModel displayCatStatsViewModel) {
        super(parent, DisplayCatStatsViewModel.TITLE_LABEL, true);
        this.displayCatStatsViewModel = displayCatStatsViewModel;
        this.displayCatStatsViewModel.addPropertyChangeListener(this);

        // Initialize components with default values
        catImageLabel = new JLabel("No Image Available");
        catNameLabel = new JLabel("Loading...");
        hungerLabel = new JLabel(DisplayCatStatsViewModel.HUNGER_LABEL + "0");
        happinessLabel = new JLabel(DisplayCatStatsViewModel.HAPPINESS_LABEL + "0");

        final JPanel mainPanel = createMainPanel();
        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(parent);

        // Only update fields if state is available
        final CatState initialState = displayCatStatsViewModel.getState();
        if (initialState != null) {
            updateFields(initialState);
        }
    }

    private JPanel createMainPanel() {
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        addImageSection(mainPanel);
        addNameSection(mainPanel);
        addStatsSection(mainPanel);
        addCloseButton(mainPanel);

        return mainPanel;
    }

    private void addImageSection(JPanel mainPanel) {
        catImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(catImageLabel);
        mainPanel.add(Box.createVerticalStrut(PADDING));
    }

    private void addNameSection(JPanel mainPanel) {
        catNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        catNameLabel.setFont(new Font(catNameLabel.getFont().getName(), Font.BOLD, FONT_SIZE));
        mainPanel.add(catNameLabel);
        mainPanel.add(Box.createVerticalStrut(PADDING));
    }

    private void addStatsSection(JPanel mainPanel) {
        final JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        hungerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        happinessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        statsPanel.add(hungerLabel);
        statsPanel.add(happinessLabel);

        mainPanel.add(statsPanel);
        mainPanel.add(Box.createVerticalStrut(PADDING));
    }

    private void addCloseButton(JPanel mainPanel) {
        final JButton closeButton = new JButton(DisplayCatStatsViewModel.CLOSE_BUTTON_LABEL);
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.addActionListener(this);
        mainPanel.add(closeButton);
    }

    private void updateFields(CatState state) {
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
            this.dispose();
        }
        else {
            // Update name and stats
            catNameLabel.setText(state.getCatName());
            hungerLabel.setText(String.format("%s%d", DisplayCatStatsViewModel.HUNGER_LABEL, state.getHungerLevel()));
            happinessLabel.setText(String.format("%s%d", DisplayCatStatsViewModel.HAPPINESS_LABEL,
                    state.getHappinessLevel()));

            // Try to load image if available
            if (state.getImageFileName() != null) {
                final ImageIcon imageIcon =
                        new ImageIcon(getClass().getResource("/images/" + state.getImageFileName()));
                if (imageIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    final Image image =
                            imageIcon.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH);
                    catImageLabel.setIcon(new ImageIcon(image));
                    catImageLabel.setText(null);
                }
                this.pack();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final CatState state = (CatState) evt.getNewValue();
            updateFields(state);
        }
    }

    /**
     * Gets the view name.
     *
     * @return the view name
     */
    public String getViewName() {
        return displayCatStatsViewModel.getViewName();
    }

    /**
     * Shows a new DisplayCatStatsView dialog.
     *
     * @param parent the parent frame
     * @param viewModel the view model
     */
    public static void show(JFrame parent, DisplayCatStatsViewModel viewModel) {
        final DisplayCatStatsView dialog = new DisplayCatStatsView(parent, viewModel);
        dialog.setVisible(true);
    }
}
