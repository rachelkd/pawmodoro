package config;

/**
 * Configuration for Supabase database connection.
 * Note: Credentials are hardcoded for course project purposes only.
 */
public class SupabaseConfig {
    private static final String SUPABASE_URL = "https://xuqqtivutcipoavzpfme.supabase.co";
    private static final String SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJ"
            + "lZiI6Inh1cXF0aXZ1dGNp"
            + "b2F2enBmbWUiLCJyb2xlIjoiYW5vbiIsImlhVCI6MTczMTYzMzMxNSwiZXhwIjoyMDQ3MjA5MzE1fQ.iThDca"
            + "XziMhmhWVaqwdLiMywgL8uC5-LeSaaGqKOtiY";

    public static String getUrl() {
        return SUPABASE_URL;
    }

    public static String getAnonKey() {
        return SUPABASE_ANON_KEY;
    }
}
