package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import app.service.DialogService;
import constants.Constants;
import interface_adapter.logout.LogoutController;
import interface_adapter.study_session.StudySessionController;
import interface_adapter.study_session.StudySessionViewModel;

/**
 * Views for Study sessions.
 */
public class StudySessionView extends JPanel implements ActionListener {
    private final String viewName = "study session";

    private final CatView catView;

    private LogoutController logoutController;
    private StudySessionController studySessionController;

    private final JButton timerSettings;
    private final JButton logOutSettings;
    private final JButton startTimerButton;
    private final JButton stopTimerButton;

    private final JLabel timerLabel;
    private Timer swingTimer;
    private long remainingTime = Constants.DEFAULT_WORK_DURATION_MS;

    private DialogService dialogService;

    public StudySessionView(StudySessionViewModel studySessionViewModel, DialogService dialogService, CatView catView,
                            StudySessionController studySessionController) {

        this.studySessionController = studySessionController;
        this.dialogService = dialogService;
        this.catView = catView;

        this.setLayout(new BorderLayout());

        // Timer label to display the timer countdown
        timerLabel = new JLabel(formatTime(remainingTime), SwingConstants.CENTER);
        timerLabel.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TIMER_FONT_SIZE));
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        timerSettings = createButton("Timer Settings");
        logOutSettings = createButton("Log Out");
        // Start and Stop Timer buttons
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
                    swingTimer.stop();
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

        timerPanel.add(Box.createVerticalStrut(10));
        timerPanel.add(buttonPanel);

        return timerPanel;
    }

    private JPanel createCatPanel() {
        final JPanel catPanel = new JPanel();
        catPanel.setLayout(new BorderLayout());
        catPanel.setVisible(true);
        catPanel.setOpaque(true);

        catView.setVisible(true);
        catPanel.add(catView, BorderLayout.CENTER);
        SwingUtilities.invokeLater(() -> {
            catView.setVisible(true);
            catView.revalidate();
            catView.repaint();
        });

        return catPanel;
    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController controller) {
        this.logoutController = controller;
    }

    public void setStudySessionController(StudySessionController controller) {
        this.studySessionController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(timerSettings)) {
            System.out.println("Timer Settings button clicked.");
            dialogService.showTimerSettingsDialog(null);
            // Ensure studySessionController is not null before using it
            if (studySessionController != null) {
                studySessionController.switchToSetupSessionView();
                System.out.println("hello");
            }
            else {
                System.err.println("StudySessionController is not initialized.");
            }
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
}