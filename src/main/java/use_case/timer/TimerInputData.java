package use_case.timer;

/**
 * Input data for timer operations.
 */
public class TimerInputData {
    private final String action;

    /**
     * Creates timer input data.
     * 
     * @param action the timer action to perform ("start", "pause", "resume",
     *            "stop")
     */
    public TimerInputData(String action) {
        this.action = action;
    }

    String getAction() {
        return action;
    }
}
