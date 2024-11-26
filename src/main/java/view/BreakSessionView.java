package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
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

    public BreakSessionView(BreakSessionViewModel breakSessionViewModel, BreakSessionState breakSessionState) {
        this.setLayout(new BorderLayout());
        this.breakSessionViewModel = breakSessionViewModel;
        this.breakSessionState = breakSessionState;

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

        logOutSettings.addActionListener(this);
        startTimerButton.addActionListener(this);

        // Add components to main panel
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createTimerPanel(), BorderLayout.CENTER);
        this.add(createLogOutPanel(), BorderLayout.SOUTH);

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
                        breakSessionController.switchToStudySessionView();
                        breakSessionState.resetToDefaultBreakInterval();
                        remainingTime = breakSessionState.getBreakInterval();
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

    private JPanel createLogOutPanel() {
        final JPanel logOutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logOutPanel.add(logOutSettings);
        return logOutPanel;
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
            logoutController.execute("");
        }
        else if (evt.getSource().equals(startTimerButton)) {
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
        return breakSessionViewModel.getViewName();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final BreakSessionState newState = (BreakSessionState) evt.getNewValue();
            remainingTime = newState.getBreakInterval();
            updateTimerLabel();
        }
    }
}

