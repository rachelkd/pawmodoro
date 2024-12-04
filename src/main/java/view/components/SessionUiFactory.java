package view.components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import constants.Constants;

/**
 * Factory for creating common UI components used in session views.
 */
public class SessionUiFactory {

    /**
     * Creates a standard button with default size.
     * @param text The button text
     * @return A configured JButton
     */
    public static JButton createStandardButton(String text) {
        final JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(Constants.DEFAULT_BUTTON_SIZE_W, Constants.DEFAULT_BUTTON_SIZE_H));
        return button;
    }

    /**
     * Creates a timer label with specified initial text.
     * @param initialText The initial text to display
     * @return A configured JLabel
     */
    public static JLabel createTimerLabel(String initialText) {
        final JLabel label = new JLabel(initialText, SwingConstants.CENTER);
        label.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TIMER_FONT_SIZE));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    /**
     * Creates a title label with specified text.
     * @param text The title text
     * @return A configured JLabel
     */
    public static JLabel createTitleLabel(String text) {
        final JLabel title = new JLabel(text, SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TITLE));
        return title;
    }

    /**
     * Creates a session timer with callback functionality.
     * @param initialTime Initial time in milliseconds
     * @param callback Callback interface for timer events
     * @return A configured Timer
     */
    public static Timer createSessionTimer(long initialTime, TimerCallback callback) {
        return new Timer(Constants.SECONDS_TO_MILLIS, evt -> {
            if (initialTime > 0) {
                final long remaining = initialTime - Constants.SECONDS_TO_MILLIS;
                callback.onTick(formatTime(remaining));
            }
            else {
                ((Timer) evt.getSource()).stop();
                callback.onComplete();
            }
        });
    }

    /**
     * Formats time in milliseconds to MM:SS format.
     * @param timeInMillis Time in milliseconds
     * @return Formatted time string
     */
    public static String formatTime(long timeInMillis) {
        final long minutes = timeInMillis / (Constants.SECONDS_TO_MILLIS * Constants.MINUTES_TO_SECONDS);
        final long seconds = (timeInMillis / Constants.SECONDS_TO_MILLIS) % Constants.MINUTES_TO_SECONDS;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Callback interface for timer events.
     * @write This interface defines callbacks for timer tick and completion events.
     */
    public interface TimerCallback {
        /**
         * Called on each timer tick with formatted time.
         * @param formattedTime The current time formatted as string
         */
        void onTick(String formattedTime);

        /**
         * Called when the timer completes.
         */
        void onComplete();
    }
}