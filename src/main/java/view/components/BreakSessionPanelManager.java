package view.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;

/**
 * Manages panel creation and layout for break session view.
 */
public class BreakSessionPanelManager {

    /**
     * Creates a main panel with vertical box layout.
     * @param components Components to add to the panel
     * @return JPanel with the components arranged vertically
     */
    public static JPanel createMainPanel(Component... components) {
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        for (Component component : components) {
            mainPanel.add(component);
        }
        return mainPanel;
    }

    /**
     * Creates a title panel with the given text.
     * @param titleText Text to display as title
     * @return JPanel containing the title
     */
    public static JPanel createTitlePanel(String titleText) {
        final JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(SessionUiFactory.createTitleLabel(titleText), BorderLayout.CENTER);
        return titlePanel;
    }

    /**
     * Creates a timer panel with label and start button.
     * @param timerLabel Label displaying the timer
     * @param startTimerButton Button to start the timer
     * @return JPanel containing timer components
     */
    public static JPanel createTimerPanel(JLabel timerLabel, JButton startTimerButton) {
        final JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.Y_AXIS));
        timerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        timerPanel.add(timerLabel);
        timerPanel.add(Box.createVerticalStrut(Constants.TIMER_VERTICAL_SPACING));

        startTimerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerPanel.add(startTimerButton);

        return timerPanel;
    }

    /**
     * Creates a control panel with adoption and logout buttons.
     * @param adoptionButton Button for adoption actions
     * @param logOutSettings Button for logout
     * @return JPanel containing the control buttons
     */
    public static JPanel createControlPanel(JButton adoptionButton, JButton logOutSettings) {
        final JPanel panel = new JPanel(new BorderLayout());

        adoptionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(adoptionButton, BorderLayout.CENTER);

        final JPanel logOutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logOutPanel.add(logOutSettings);
        panel.add(logOutPanel, BorderLayout.EAST);

        return panel;
    }

    /**
     * Creates a panel for displaying cats.
     * @param catContainerView Component containing cat display
     * @return JPanel containing the cat display
     */
    public static JPanel createCatsPanel(Component catContainerView) {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setVisible(true);
        panel.setOpaque(true);
        panel.add(catContainerView, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Adds a cat image view to a panel.
     * @param panel Panel to add the image to
     * @param displayCatImageView Component containing cat image
     */
    public static void addDisplayCatImageView(JPanel panel, Component displayCatImageView) {
        ((JComponent) displayCatImageView).setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(displayCatImageView);
        panel.add(Box.createVerticalStrut(Constants.COMPONENT_VERTICAL_SPACING));
    }

    /**
     * Sets up the complete layout for the break session view.
     * @param mainView The main panel to set up
     * @param components Bundle of components to use in the layout
     * @return The cats panel for later reference
     */
    public static JPanel setupLayout(JPanel mainView, ComponentBundle components) {
        mainView.setLayout(new BoxLayout(mainView, BoxLayout.Y_AXIS));
        final JPanel bottomPanel = createMainPanel();

        mainView.add(createTitlePanel("BREAK SESSION"));
        mainView.add(createTimerPanel(components.getTimerLabel(), components.getStartTimerButton()));

        final JButton adoptionButton = SessionUiFactory.createStandardButton(Constants.ADOPTION_LABEL);
        adoptionButton.addActionListener(evt -> components.getAdoptionHandler().run());

        addDisplayCatImageView(bottomPanel, components.getDisplayCatImageView());

        final JPanel catsPanel = createCatsPanel(components.getCatContainerView());
        bottomPanel.add(catsPanel);
        bottomPanel.add(createControlPanel(adoptionButton, components.getLogOutSettings()));

        mainView.add(bottomPanel);
        return catsPanel;
    }

    /**
     * Bundle of components used in the break session view.
     * @write This class bundles together UI components needed for the break session view.
     */
    public static class ComponentBundle {
        private final JLabel timerLabel;
        private final JButton startTimerButton;
        private final JButton logOutSettings;
        private final Component catContainerView;
        private final Component displayCatImageView;
        private final Runnable adoptionHandler;

        public ComponentBundle(JLabel timerLabel, JButton startTimerButton,
                               JButton logOutSettings, Component catContainerView,
                               Component displayCatImageView, Runnable adoptionHandler) {
            this.timerLabel = timerLabel;
            this.startTimerButton = startTimerButton;
            this.logOutSettings = logOutSettings;
            this.catContainerView = catContainerView;
            this.displayCatImageView = displayCatImageView;
            this.adoptionHandler = adoptionHandler;
        }

        public JLabel getTimerLabel() {
            return timerLabel;
        }

        public JButton getStartTimerButton() {
            return startTimerButton;
        }

        public JButton getLogOutSettings() {
            return logOutSettings;
        }

        public Component getCatContainerView() {
            return catContainerView;
        }

        public Component getDisplayCatImageView() {
            return displayCatImageView;
        }

        public Runnable getAdoptionHandler() {
            return adoptionHandler;
        }
    }
} 