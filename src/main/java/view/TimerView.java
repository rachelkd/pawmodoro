package view;

import java.awt.Component;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import interface_adapter.timer.TimerController;
import interface_adapter.timer.TimerViewModel;

/**
 * A component responsible for displaying the timer. This view shows the current
 * timer state, including remaining time in a large, readable format.
 */
public class TimerView extends JPanel implements PropertyChangeListener {
    private final TimerViewModel timerViewModel;
    private TimerController timerController;

    private final JLabel timerLabel;
    private final SimpleDateFormat timeFormat = new SimpleDateFormat(Constants.TIME_FORMAT_PATTERN);

    public TimerView(TimerViewModel timerViewModel) {
        this.timerViewModel = timerViewModel;
        this.timerViewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize timer label with the current state
        final long initialDuration = timerViewModel.getState().getIntervalDuration();
        timerLabel = new JLabel(formatTime(initialDuration));
        timerLabel.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TIMER_FONT_SIZE));
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        timerLabel.setFont(new Font(
                Constants.FONT_FAMILY,
                Font.BOLD,
                Constants.TIMER_FONT_SIZE));
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(Constants.TIMER_VERTICAL_SPACING));
        add(timerLabel);
        add(Box.createVerticalStrut(Constants.TIMER_VERTICAL_SPACING));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateTimerDisplay();
        }
    }

    private void updateTimerDisplay() {
        long remainingTime = timerViewModel.getState().getIntervalDuration()
                - timerViewModel.getState().getElapsedTime();

        // Ensure no negative time display
        remainingTime = Math.max(remainingTime, 0);

        timerLabel.setText(formatTime(remainingTime));
    }

    public void setTimerController(TimerController timerController) {
        this.timerController = timerController;
    }

    private String formatTime(long timeInMillis) {
        final long seconds = (timeInMillis / Constants.SECONDS_TO_MILLIS) % Constants.MINUTES_TO_SECONDS;
        final long minutes = (timeInMillis / (Constants.SECONDS_TO_MILLIS * Constants.MINUTES_TO_SECONDS))
                % Constants.MINUTES_TO_SECONDS;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public String getViewName() {
        return timerViewModel.getViewName();
    }
}
