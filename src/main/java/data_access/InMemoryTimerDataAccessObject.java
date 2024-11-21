package data_access;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import entity.Timer;
import entity.TimerFactory;
import use_case.timer.TimerDataAccessInterface;

/**
 * In-memory implementation of timer data access.
 */
public class InMemoryTimerDataAccessObject implements TimerDataAccessInterface {

    // Default durations
    private static final long DEFAULT_WORK_DURATION = TimeUnit.MINUTES.toMillis(25);
    private static final long DEFAULT_SHORT_BREAK_DURATION = TimeUnit.MINUTES.toMillis(5);
    private static final long DEFAULT_LONG_BREAK_DURATION = TimeUnit.MINUTES.toMillis(15);

    private final Map<String, Timer> timers = new HashMap<>();
    private final TimerFactory timerFactory = new TimerFactory();

    @Override
    public Timer getTimer(String username) {
        return timers.computeIfAbsent(username,
                user -> timerFactory.create());
    }

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
    public String formatTimeDisplay(long duration) {
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
