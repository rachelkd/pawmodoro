package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.Timer;
import entity.TimerFactory;
import use_case.timer.display_timer.DisplayTimerDataAccessInterface;

/**
 * In-memory implementation of timer data access.
 */
public class InMemoryTimerDataAccessObject implements DisplayTimerDataAccessInterface {
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
}
