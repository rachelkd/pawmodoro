package use_case.timer;

/**
 * Output data for timer operations.
 */
public class TimerOutputData {
    private final String state;
    private final String interval;
    private final long elapsedTime;
    private final boolean useCaseFailed;

    /**
     * Creates timer output data.
     * 
     * @param state the current timer state
     * @param interval the current interval type
     * @param elapsedTime the elapsed time in milliseconds
     * @param useCaseFailed whether the use case failed
     */
    public TimerOutputData(String state, String interval, long elapsedTime, boolean useCaseFailed) {
        this.state = state;
        this.interval = interval;
        this.elapsedTime = elapsedTime;
        this.useCaseFailed = useCaseFailed;
    }

    public String getState() {
        return state;
    }

    public String getInterval() {
        return interval;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
} 