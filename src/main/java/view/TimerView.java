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
 * timer
 * state including remaining time in a large, readable format.
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
        timerLabel = new JLabel(timeFormat.format(new Date(initialDuration)));

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
        final long remainingTime = timerViewModel.getState().getIntervalDuration()
                - timerViewModel.getState().getElapsedTime();
        timerLabel.setText(timeFormat.format(new Date(remainingTime)));
    }

    public void setTimerController(TimerController timerController) {
        this.timerController = timerController;
    }

    public String getViewName() {
        return timerViewModel.getViewName();
    }
}
