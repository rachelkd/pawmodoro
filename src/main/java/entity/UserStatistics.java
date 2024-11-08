package entity;

import java.time.LocalDate;
import java.util.Map;

/**
 * Represents statistics tracking for a user's focus sessions and productivity.
 * Different implementations can store and calculate statistics differently
 * based on the specific data access requirements.
 */
public interface UserStatistics {
    /**
     * Adds focus time to the user's statistics.
     *
     * @param minutes the number of minutes to add
     */
    void addFocusTime(int minutes);

    /**
     * Increments the count of completed focus sessions.
     */
    void incrementCompletedSessions();

    /**
     * Resets the weekly statistics tracking.
     */
    void resetWeeklyStats();

    /**
     * Gets the total focus time across all sessions.
     *
     * @return total focus time in minutes
     */
    int getTotalFocusTime();

    /**
     * Gets the focus time for the current week.
     *
     * @return weekly focus time in minutes
     */
    int getWeeklyFocusTime();

    /**
     * Gets the total number of completed focus sessions.
     *
     * @return number of completed sessions
     */
    int getCompletedSessions();

    /**
     * Gets the daily focus time statistics.
     *
     * @return map of dates to focus time in minutes
     */
    Map<LocalDate, Integer> getDailyStats();
}
