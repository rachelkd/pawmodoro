package config;

/**
 * Configuration for Supabase database connection.
 */
public final class SupabaseConfig {
    private static final String SUPABASE_URL = "https://xuqqtivutcipoavzpfme.supabase.co";
    private static final String SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
            + "eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inh1cXF0aXZ1dGNpcG9hdnpw"
            + "Zm1lIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2MzMzMTUsImV4c"
            + "CI6MjA0NzIwOTMxNX0.iThDcaXziMhmhWVaqwdLiMywgL8uC5-LeSaaGqKOtiY";

    private SupabaseConfig() {

    }

    public static String getUrl() {
        return SUPABASE_URL;
    }

    public static String getAnonKey() {
        return SUPABASE_ANON_KEY;
    }
}
