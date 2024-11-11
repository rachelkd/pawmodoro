package use_case.timer;

public class GetTimerOutputData {
    private final String status;
    private final String currentInterval;
    private final long elapsedTime;
    private final long intervalDuration;

    public GetTimerOutputData(String status, String currentInterval,
            long elapsedTime, long intervalDuration) {
        this.status = status;
        this.currentInterval = currentInterval;
        this.elapsedTime = elapsedTime;
        this.intervalDuration = intervalDuration;
    }

    public String getStatus() {
        return status;
    }

    public String getCurrentInterval() {
        return currentInterval;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public long getIntervalDuration() {
        return intervalDuration;
    }
}
