package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Development-only configuration for direct database access.
 * This should only be used for development and testing purposes.
 */
public final class SupabaseDevConfig {
    private static Properties properties;
    private static final String SUPABASE_DB_PASSWORD_STRING = "SUPABASE_DB_PASSWORD";
    private static final String SUPABASE_DB_USER_STRING = "SUPABASE_DB_USER";

    private SupabaseDevConfig() {

    }

    private static void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileInputStream(".env"));
            }
            catch (IOException exception) {
                final String dbUser = System.getenv(SUPABASE_DB_USER_STRING);
                final String dbPassword = System.getenv(SUPABASE_DB_PASSWORD_STRING);
                if (dbUser != null && dbPassword != null) {
                    properties.setProperty(SUPABASE_DB_USER_STRING, dbUser);
                    properties.setProperty(SUPABASE_DB_PASSWORD_STRING, dbPassword);
                }
                else {
                    throw new RuntimeException(
                            "Database credentials not found. This configuration is only needed for development.");
                }
            }
        }
    }

    /**
     * Get the database password.
     * 
     * @return the database password
     */
    public static String getDbPassword() {
        loadProperties();
        return properties.getProperty(SUPABASE_DB_PASSWORD_STRING);
    }

    /**
     * Get the database user.
     * 
     * @return the database user
     */
    public static String getDbUser() {
        loadProperties();
        return properties.getProperty(SUPABASE_DB_USER_STRING);
    }
}
