package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import constants.Constants;
import interface_adapter.logout.LogoutController;

/**
 * Views for Break Session.
 */
public class BreakSessionView extends JPanel implements ActionListener {
    private final String viewName = "break session";

    private LogoutController logoutController;

    private final JButton logOutSettings;
    private final JButton startTimerButton;
    private final JButton stopTimerButton;

    private final JLabel timerLabel;
    private Timer swingTimer;
    private long remainingTime = Constants.DEFAULT_BREAK_DURATION_MS;

    public BreakSessionView() {
        this.setLayout(new BorderLayout());

        // Timer label to display the countdown timer
        timerLabel = new JLabel(formatTime(remainingTime), SwingConstants.CENTER);
        timerLabel.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TIMER_FONT_SIZE));
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create buttons
        logOutSettings = createButton("Log Out");
        startTimerButton = createButton("Start Timer");

        logOutSettings.addActionListener(this);
        startTimerButton.addActionListener(this);

        // Add components to main panel
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createTimerPanel(), BorderLayout.CENTER);

        // Initialize the timer to decrement remaining time
        swingTimer = new Timer(Constants.SECONDS_TO_MILLIS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainingTime > 0) {
                    remainingTime -= Constants.SECONDS_TO_MILLIS;
                    updateTimerLabel();
                }
                else {
                    swingTimer.stop();
                    System.out.println("Break session over!");
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
        buttonPanel.add(logOutSettings);

        timerPanel.add(Box.createVerticalStrut(10));
        timerPanel.add(buttonPanel);

        return timerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logOutSettings)) {
            // Execute the logout use case through the Controller
            if (logoutController != null) {
                this.logoutController.execute("");
            }
            else {
                System.err.println("LogoutController is not initialized.");
            }
        }
        else if (evt.getSource().equals(startTimerButton)) {
            // Start the timer
            swingTimer.start();
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
        return viewName;
    }

    public void setLogoutController(LogoutController controller) {
        this.logoutController = controller;
    }
}

