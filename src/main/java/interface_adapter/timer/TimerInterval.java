package interface_adapter.timer;

/**
 * Represents the different interval types in the presentation layer.
 */
public class TimerInterval {
    private static final String WORK = "WORK";
    private static final String SHORT_BREAK = "SHORT_BREAK";
    private static final String LONG_BREAK = "LONG_BREAK";

    private String interval;

    public TimerInterval(String interval) {
        this.interval = interval;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    /**
     * Work interval constant.
     * 
     * @return the work interval string
     */
    public static String work() {
        return WORK;
    }

    /**
     * Short break interval constant.
     * 
     * @return the short break interval string
     */
    public static String shortBreak() {
        return SHORT_BREAK;
    }

    /**
     * Long break interval constant.
     * 
     * @return the long break interval string
     */
    public static String longBreak() {
        return LONG_BREAK;
    }
}
