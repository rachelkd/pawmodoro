package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import app.service.DialogService;
import constants.Constants;
import interface_adapter.add_to_inventory.AddToInventoryController;
import interface_adapter.change_cat_happiness.ChangeCatHappinessController;
import interface_adapter.change_cat_hunger.ChangeCatHungerController;
import interface_adapter.logout.LogoutController;
import interface_adapter.study_session.StudySessionController;
import interface_adapter.study_session.StudySessionState;
import interface_adapter.study_session.StudySessionViewModel;

/**
 * Views for Study sessions.
 */
public class StudySessionView extends JPanel implements ActionListener, PropertyChangeListener {
    private final CatContainerView catContainerView;
    private BreakSessionView breakSessionView;

    private final StudySessionViewModel studySessionViewModel;

    private LogoutController logoutController;
    private StudySessionController studySessionController;
    private ChangeCatHappinessController changeCatHappinessController;
    private ChangeCatHungerController changeCatHungerController;
    private AddToInventoryController addToInventoryController;

    private final JButton timerSettings;
    private final JButton logOutSettings;
    private final JButton startTimerButton;
    private final JButton stopTimerButton;

    private final JLabel timerLabel;
    private final Timer swingTimer;
    private long remainingTime = Constants.DEFAULT_WORK_DURATION_MS;

    private DialogService dialogService;
    private JPanel catsPanel;

    public StudySessionView(StudySessionViewModel studySessionViewModel,
                            DialogService dialogService,
                            CatContainerView catContainerView) {

        this.dialogService = dialogService;
        studySessionViewModel.addPropertyChangeListener(this);
        this.studySessionViewModel = studySessionViewModel;
        this.catContainerView = catContainerView;

        this.catsPanel = new JPanel();

        this.setLayout(new BorderLayout());

        // Timer label to display the timer countdown
        timerLabel = new JLabel(formatTime(remainingTime), SwingConstants.CENTER);
        timerLabel.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TIMER_FONT_SIZE));
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        timerSettings = createButton("Timer Settings");
        logOutSettings = createButton("Log Out");
        startTimerButton = createButton("Start Timer");
        stopTimerButton = createButton("Stop Timer");

        timerSettings.addActionListener(this);
        logOutSettings.addActionListener(this);
        startTimerButton.addActionListener(this);
        stopTimerButton.addActionListener(this);

        // Add components to main panel (without the timer)
        this.add(createTopPanel(), BorderLayout.NORTH);
        this.add(createTimerPanel(), BorderLayout.CENTER);
        this.add(createCatPanel(), BorderLayout.SOUTH);

        swingTimer = new Timer(Constants.SECONDS_TO_MILLIS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainingTime > 0) {
                    remainingTime -= Constants.SECONDS_TO_MILLIS;
                    updateTimerLabel();
                }
                else {
                    final StudySessionState state = studySessionViewModel.getState();
                    swingTimer.stop();

                    final int workInterval = (int) state.getWorkInterval()
                            / Constants.SECONDS_TO_MILLIS
                            / Constants.MINUTES_TO_SECONDS;
                    addToInventoryController.execute(state.getUsername(), workInterval);

                    // Notify controller to switch the view
                    studySessionController.switchToBreakSessionView();
                    breakSessionView.showCatContainerView();
                    state.resetToDefaultWorkInterval();
                    remainingTime = studySessionViewModel.getState().getWorkInterval();
                    updateTimerLabel();
                }
            }
        });

        // Make sure this panel is visible
        this.setVisible(true);
        this.setOpaque(true);

        // Force layout update
        this.revalidate();
        this.repaint();
    }

    private JButton createButton(String text) {
        return new JButton(text);
    }

    private JPanel createTopPanel() {
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(createButtonsPanel());
        topPanel.add(createTitlePanel());
        return topPanel;
    }

    private JPanel createButtonsPanel() {
        final JPanel buttonsPanel = new JPanel(new BorderLayout());

        final JPanel leftButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftButtons.add(timerSettings);

        final JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtons.add(logOutSettings);

        buttonsPanel.add(leftButtons, BorderLayout.WEST);
        buttonsPanel.add(rightButtons, BorderLayout.EAST);

        return buttonsPanel;
    }

    private JPanel createTitlePanel() {
        final JLabel title = new JLabel("STUDY SESSION", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TITLE));

        final JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(title, BorderLayout.CENTER);
        return titlePanel;
    }

    private JPanel createTimerPanel() {
        // Create a panel to hold the timer and buttons
        final JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.Y_AXIS));

        // Add the timer label
        timerPanel.add(timerLabel);

        // Add the start and stop buttons below the timer label
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startTimerButton);
        buttonPanel.add(stopTimerButton);

        timerPanel.add(Box.createVerticalStrut(Constants.TIMER_VERTICAL_SPACING));
        timerPanel.add(buttonPanel);

        return timerPanel;
    }

    private JPanel createCatPanel() {
        catsPanel.setLayout(new BorderLayout());
        catsPanel.setVisible(true);
        catsPanel.setOpaque(true);

        catContainerView.setVisible(true);
        catContainerView.setOpaque(true);

        catsPanel.add(catContainerView, BorderLayout.CENTER);

        SwingUtilities.invokeLater(() -> {
            catContainerView.setVisible(true);
            catContainerView.revalidate();
            catContainerView.repaint();
        });

        catsPanel.revalidate();
        catsPanel.repaint();

        return catsPanel;
    }

    public void showCatContainerView() {
        catsPanel.removeAll();
        catsPanel.add(catContainerView, BorderLayout.CENTER);
        catsPanel.revalidate();
        catsPanel.repaint();
    }

    public String getViewName() {
        return studySessionViewModel.getViewName();
    }

    public void setLogoutController(LogoutController controller) {
        this.logoutController = controller;
    }

    public void setStudySessionController(StudySessionController studySessionController) {
        this.studySessionController = studySessionController;
    }

    public void setChangeCatHappinessController(ChangeCatHappinessController controller) {
        this.changeCatHappinessController = controller;
    }

    public void setChangeCatHungerController(ChangeCatHungerController controller) {
        this.changeCatHungerController = controller;
    }

    public void setAddToInventoryController(AddToInventoryController controller) {
        this.addToInventoryController = controller;
    }

    public void setBreakSessionView(BreakSessionView breakSessionView) {
        this.breakSessionView = breakSessionView;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(timerSettings)) {
            studySessionController.switchToSetupSessionView();
        }
        else if (evt.getSource().equals(logOutSettings)) {
            // Execute the logout use case through the Controller
            this.logoutController.execute("");
        }
        else if (evt.getSource().equals(startTimerButton)) {
            // Start the timer
            swingTimer.start();
        }
        else if (evt.getSource().equals(stopTimerButton)) {
            // Stop the timer
            swingTimer.stop();
            studySessionController.stopStudyTimer();

        }
    }

    // Updated updateTimerLabel() to display the remaining time
    private void updateTimerLabel() {
        timerLabel.setText(formatTime(remainingTime));
    }

    private String formatTime(long timeInMil) {
        final long minutes = timeInMil / (Constants.SECONDS_TO_MILLIS * Constants.MINUTES_TO_SECONDS);
        final long seconds = (timeInMil / Constants.SECONDS_TO_MILLIS) % Constants.MINUTES_TO_SECONDS;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final StudySessionState newState = (StudySessionState) evt.getNewValue();
            remainingTime = newState.getWorkInterval();
            updateTimerLabel();
        }
    }
}
