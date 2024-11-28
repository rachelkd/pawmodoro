package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import constants.Constants;
import interface_adapter.break_session.BreakSessionController;
import interface_adapter.break_session.BreakSessionState;
import interface_adapter.break_session.BreakSessionViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.adoption.AdoptionState;
import app.service.DialogService;

/**
 * Views for Break Session.
 */
public class BreakSessionView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final Logger LOGGER = Logger.getLogger(BreakSessionView.class.getName());

    private final BreakSessionViewModel breakSessionViewModel;
    private final BreakSessionState breakSessionState;

    private LogoutController logoutController;
    private BreakSessionController breakSessionController;

    private final JButton logOutSettings;
    private final JButton startTimerButton;

    private final JLabel timerLabel;
    private Timer swingTimer;
    private long remainingTime;

    private final AdoptionViewModel adoptionViewModel;
    private final DialogService dialogService;

    public BreakSessionView(BreakSessionViewModel breakSessionViewModel, BreakSessionState breakSessionState, AdoptionViewModel adoptionViewModel, DialogService dialogService) {
        this.setLayout(new BorderLayout());
        this.breakSessionViewModel = breakSessionViewModel;
        this.breakSessionState = breakSessionState;

        this.adoptionViewModel = adoptionViewModel;
        this.dialogService = dialogService;

        // Initialize remaining time with the break interval from the state
        breakSessionViewModel.addPropertyChangeListener(this);
        this.remainingTime = breakSessionState.getBreakInterval();

        // Timer label to display the countdown timer
        timerLabel = new JLabel(formatTime(remainingTime), SwingConstants.CENTER);
        timerLabel.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TIMER_FONT_SIZE));
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create buttons
        logOutSettings = createButton("Log Out");
        startTimerButton = createButton("Start Timer");

        // Set preferred size to match buttons
        Dimension buttonSize = new Dimension(150, 40);
        startTimerButton.setPreferredSize(buttonSize);

        logOutSettings.addActionListener(this);
        startTimerButton.addActionListener(this);

        // Add components to main panel
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createTimerPanel(), BorderLayout.CENTER);
        this.add(createAdoptionAndLogOutPanel(buttonSize), BorderLayout.SOUTH);

        // Initialize the timer to decrement remaining time
        swingTimer = new Timer(Constants.SECONDS_TO_MILLIS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainingTime > 0) {
                    remainingTime -= Constants.SECONDS_TO_MILLIS;
                    updateTimerLabel();
                } else {
                    swingTimer.stop();
                    LOGGER.info("Break time is over, switching to study session.");

                    if (breakSessionController != null) {
                        breakSessionController.switchToStudySessionView();
                        breakSessionState.resetToDefaultBreakInterval();
                        remainingTime = breakSessionState.getBreakInterval();
                        updateTimerLabel();
                    } else {
                        LOGGER.severe("BreakSessionController is not initialized.");
                    }

                    // Reset buttons
                    startTimerButton.setEnabled(true);
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

    private JPanel createTitlePanel() {
        final JLabel title = new JLabel("BREAK SESSION", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TITLE));

        final JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(title, BorderLayout.CENTER);
        return titlePanel;
    }

    private JPanel createTimerPanel() {
        // Create a panel to hold the timer and start button
        final JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.Y_AXIS));
        timerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the timer label
        timerPanel.add(timerLabel);
        timerPanel.add(Box.createVerticalStrut(Constants.TIMER_VERTICAL_SPACING));

        // Add the start button below the timer label
        startTimerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerPanel.add(startTimerButton);

        return timerPanel;
    }

    private JPanel createAdoptionAndLogOutPanel(Dimension buttonSize) {
        final JPanel adoptionAndLogOutPanel = new JPanel(new BorderLayout());

        // Create and add the adoption button in the center
        final JButton adoptionButton = createButton(BreakSessionViewModel.ADOPTION_LABEL);
        adoptionButton.setPreferredSize(buttonSize);  // Set size to match startTimerButton
        adoptionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        adoptionButton.addActionListener(event -> {
            dialogService.createAdoptionDialog(adoptionViewModel);
            dialogService.showAdoptionDialog(adoptionViewModel);

            final AdoptionState adoptionState = adoptionViewModel.getState();
            adoptionViewModel.setState(adoptionState);
        });
        adoptionAndLogOutPanel.add(adoptionButton, BorderLayout.CENTER);

        // Create and add the log out button in the right corner
        final JPanel logOutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logOutPanel.add(logOutSettings);
        adoptionAndLogOutPanel.add(logOutPanel, BorderLayout.EAST);

        return adoptionAndLogOutPanel;
    }

    public void setBreakSessionController(BreakSessionController breakSessionController) {
        this.breakSessionController = breakSessionController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logOutSettings)) {
            if (logoutController != null) {
                logoutController.execute("");
            } else {
                LOGGER.severe("LogoutController is not initialized.");
            }
        } else if (evt.getSource().equals(startTimerButton)) {
            swingTimer.start();
            startTimerButton.setEnabled(false);  // Disable the start button while the timer is running
        }
    }

    private void updateTimerLabel() {
        timerLabel.setText(formatTime(remainingTime));
    }

    private String formatTime(long timeInMillis) {
        final long minutes = timeInMillis / (Constants.SECONDS_TO_MILLIS * Constants.MINUTES_TO_SECONDS);
        final long seconds = (timeInMillis / Constants.SECONDS_TO_MILLIS) % Constants.MINUTES_TO_SECONDS;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public String getViewName() {
        return breakSessionViewModel.getViewName();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final BreakSessionState newState = (BreakSessionState) evt.getNewValue();
            remainingTime = newState.getBreakInterval();
            updateTimerLabel();
            startTimerButton.setEnabled(true); // Enable the start button when the state changes
        }
    }
}


