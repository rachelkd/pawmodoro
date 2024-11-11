package interface_adapter.timer;

/**
 * Represents the possible states of a timer in the presentation layer.
 */
public class TimerState {
    private static final String RUNNING = "RUNNING";
    private static final String PAUSED = "PAUSED";
    private static final String STOPPED = "STOPPED";

    private String state;

    public TimerState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns the running state string.
     * 
     * @return the running state string
     */
    public static String running() {
        return RUNNING;
    }

    /**
     * Returns the paused state string.
     * 
     * @return the paused state string
     */
    public static String paused() {
        return PAUSED;
    }

    /**
     * Returns the stopped state string.
     * 
     * @return the stopped state string
     */
    public static String stopped() {
        return STOPPED;
    }
}
