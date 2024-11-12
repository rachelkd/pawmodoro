package constants;

import java.awt.Font;

/**
 * Constants used throughout the program.
 */
public final class Constants {
    // Global constants
    public static final String FONT_FAMILY = Font.SANS_SERIF;
    // Timer constants
    public static final int DEFAULT_WORK_MINUTES = 25;
    public static final int MINUTES_TO_SECONDS = 60;
    public static final int SECONDS_TO_MILLIS = 1000;
    public static final int DEFAULT_WORK_DURATION_MS = DEFAULT_WORK_MINUTES * MINUTES_TO_SECONDS * SECONDS_TO_MILLIS;

    public static final String STATUS_STOPPED = "STOPPED";
    public static final String INTERVAL_WORK = "WORK";

    public static final int TIMER_FONT_SIZE = 48;
    public static final int TIMER_VERTICAL_SPACING = 20;
    public static final String TIME_FORMAT_PATTERN = "mm:ss";

    // Cat Image constants
    public static final int CAT_IMAGE_MAX_WIDTH = 300;
    public static final int CAT_IMAGE_MAX_HEIGHT = 200;
    public static final int CAT_IMAGE_SIZE = 300;

    // Signup, Login, Loggedin view model constants
    public static final int SPACING = 40;
    public static final int TITLE = 25;

    private Constants() {
        // Prevent instantiation
    }
}
