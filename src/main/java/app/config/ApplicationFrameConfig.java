package app.config;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * ApplicationFrameConfig class.
 */
public class ApplicationFrameConfig {
    /**
     * Configures the Frame.
     * @param frame the frame
     * @param mainPanel the mainPanel
     */
    public void configureFrame(JFrame frame, JPanel mainPanel) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(mainPanel);

        // Pack the frame to calculate preferred size based on components
        frame.pack();

        // Calculate and set to 60% of screen dimensions
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) (screenSize.getWidth() * 0.6);
        final int height = (int) (screenSize.getHeight() * 0.6);
        frame.setMinimumSize(new Dimension(width, height));
        frame.setPreferredSize(new Dimension(width, height));
        frame.setSize(width, height);
        frame.pack();

        // Center the frame on screen
        frame.setLocationRelativeTo(null);
    }
}
