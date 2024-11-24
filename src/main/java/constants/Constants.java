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

    // Cat Constants
    public static final int MAX_AMOUNT_OF_CATS = 4;

    // Happiness points for study session lengths
    public static final int POINTS_FOR_LESS_EQUAL_20 = 10;
    public static final int POINTS_FOR_BETWEEN_21_AND_40 = 20;
    public static final int POINTS_FOR_BETWEEN_41_AND_59 = 35;
    public static final int POINTS_FOR_60 = 40;

    // Hunger decreases for study session lengths
    public static final int HUNGER_FOR_LESS_EQUAL_20 = 10;
    public static final int HUNGER_FOR_BETWEEN_21_AND_40 = 20;
    public static final int HUNGER_FOR_BETWEEN_41_AND_59 = 25;
    public static final int HUNGER_FOR_60 = 30;

    // Study intervals for points
    public static final int MINUTES_20 = 20;
    public static final int MINUTES_40 = 40;
    public static final int MINUTES_60 = 60;

    // Cat Image constants
    public static final int CAT_IMAGE_MAX_WIDTH = 300;
    public static final int CAT_IMAGE_MAX_HEIGHT = 150;
    public static final int CAT_IMAGE_SIZE = 300;

    // Signup, Login, Loggedin view model constants
    public static final int SPACING = 40;
    public static final int TITLE = 25;

    public static final int DISPLAY_CAT_REFRESH_BUTTON_WIDTH = 100;
    public static final int DISPLAY_CAT_REFRESH_BUTTON_HEIGHT = 30;

    // Cat sprite constants
    public static final int CAT_SPRITE_BASE_SIZE = 32;
    public static final int CAT_SPRITE_SCALE = 2;
    public static final int CAT_SPRITE_DISPLAY_SIZE = CAT_SPRITE_BASE_SIZE * CAT_SPRITE_SCALE;

    private Constants() {
        // Prevent instantiation
    }
}
