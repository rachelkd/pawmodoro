package data_access;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import constants.Constants;
import entity.Timer;
import use_case.timer.TimerSettingsDataAccessInterface;
import use_case.timer.TimerStatusDataAccessInterface;

/**
 * In-memory implementation of timer data access.
 */
public class InMemoryTimerDataAccessObject
        implements TimerSettingsDataAccessInterface, TimerStatusDataAccessInterface {

    // Default durations
    private static final long DEFAULT_WORK_DURATION = TimeUnit.MINUTES.toMillis(Constants.DEFAULT_WORK_MINUTES);
    private static final long DEFAULT_SHORT_BREAK_DURATION = TimeUnit.MINUTES.toMillis(Constants.DEFAULT_BREAK_MINUTES);
    private static final long DEFAULT_LONG_BREAK_DURATION = TimeUnit.MINUTES.toMillis(15);

    private final Map<String, Timer> timers = new HashMap<>();

    @Override
    public void save(String username, Timer timer) {
        timers.put(username, timer);
    }

    @Override
    public long getWorkDuration() {
        return DEFAULT_WORK_DURATION;
    }

    @Override
    public long getShortBreakDuration() {
        return DEFAULT_SHORT_BREAK_DURATION;
    }

    @Override
    public long getLongBreakDuration() {
        return DEFAULT_LONG_BREAK_DURATION;
    }

    @Override
    public String formatTime(long duration) {
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % Constants.MINUTES_TO_SECONDS;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
