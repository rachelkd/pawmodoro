package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import app.service.DialogService;
import constants.Constants;
import interface_adapter.change_cat_happiness.ChangeCatHappinessController;
import interface_adapter.logout.LogoutController;
import interface_adapter.music_control.MusicControlController;
import interface_adapter.music_control.MusicControlViewModel;
import interface_adapter.study_session.StudySessionController;
import interface_adapter.study_session.StudySessionViewModel;


/**
 * Views for Study sessions.
 */
public class StudySessionView extends JPanel implements ActionListener, PropertyChangeListener {
    private final CatView catView;

    private final StudySessionViewModel studySessionViewModel;
    private final MusicControlViewModel musicControlViewModel;

    private LogoutController logoutController;
    private StudySessionController studySessionController;
    private ChangeCatHappinessController changeCatHappinessController;
    private MusicControlController musicControlController;

    private final JButton timerSettings;
    private final JButton logOutSettings;
    private final JButton startTimerButton;
    private final JButton stopTimerButton;
    private final JButton playPauseButton;

    private final JLabel timerLabel;
    private final Timer swingTimer;
    private long remainingTime = Constants.DEFAULT_WORK_DURATION_MS;

    private DialogService dialogService;

    public StudySessionView(StudySessionViewModel studySessionViewModel, DialogService dialogService, CatView catView,
                            MusicControlViewModel musicControlViewModel) {

        this.dialogService = dialogService;
        studySessionViewModel.addPropertyChangeListener(this);
        this.studySessionViewModel = studySessionViewModel;
        this.musicControlViewModel = musicControlViewModel;

        this.catView = catView;

        this.setLayout(new BorderLayout());

        // Timer label to display the timer countdown
        timerLabel = new JLabel(formatTime(remainingTime), SwingConstants.CENTER);
        timerLabel.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TIMER_FONT_SIZE));
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        timerSettings = createButton("Timer Settings");
        logOutSettings = createButton("Log Out");
        playPauseButton = createButton("Play");
        startTimerButton = createButton("Start Timer");
        stopTimerButton = createButton("Stop Timer");

        timerSettings.addActionListener(this);
        logOutSettings.addActionListener(this);
        playPauseButton.addActionListener(this);
        startTimerButton.addActionListener(this);
        stopTimerButton.addActionListener(this);

        // Add components to main panel (without the timer)
        this.add(createTopPanel(), BorderLayout.NORTH);
        this.add(createTimerPanel(), BorderLayout.CENTER);
        this.add(createCatPanel(), BorderLayout.SOUTH);

        swingTimer = new Timer(Constants.SECONDS_TO_MILLIS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainingTime > 0) {
                    remainingTime -= Constants.SECONDS_TO_MILLIS;
                    updateTimerLabel();
                }
                else {
                    swingTimer.stop();
                    System.out.println("Time is up! Switching to Break Session...");

                    // Notify the presenter or controller to switch the view
                    if (studySessionController != null) {
                        studySessionController.switchToBreakSessionView();
                    }
                    else {
                        System.err.println("StudySessionOutputBoundary is not initialized.");
                    }
                }
            }
        });

        // Make sure this panel is visible
        this.setVisible(true);
        this.setOpaque(true);

        // Force layout update
        this.revalidate();
        this.repaint();
    }

    private JButton createButton(String text) {
        return new JButton(text);
    }

    private JPanel createTopPanel() {
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(createButtonsPanel());
        topPanel.add(createTitlePanel());
        return topPanel;
    }

    private JPanel createButtonsPanel() {
        final JPanel buttonsPanel = new JPanel(new BorderLayout());

        final JPanel leftButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftButtons.add(timerSettings);

        final JPanel centerButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerButtons.add(playPauseButton);

        final JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtons.add(logOutSettings);

        buttonsPanel.add(leftButtons, BorderLayout.WEST);
        buttonsPanel.add(centerButtons, BorderLayout.CENTER);
        buttonsPanel.add(rightButtons, BorderLayout.EAST);

        return buttonsPanel;
    }

    private JPanel createTitlePanel() {
        final JLabel title = new JLabel("STUDY SESSION", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TITLE));

        final JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(title, BorderLayout.CENTER);
        return titlePanel;
    }

    private JPanel createTimerPanel() {
        // Create a panel to hold the timer and buttons
        final JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.Y_AXIS));

        // Add the timer label
        timerPanel.add(timerLabel);

        // Add the start and stop buttons below the timer label
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startTimerButton);
        buttonPanel.add(stopTimerButton);

        timerPanel.add(Box.createVerticalStrut(Constants.TIMER_VERTICAL_SPACING));
        timerPanel.add(buttonPanel);

        return timerPanel;
    }

    private JPanel createCatPanel() {
        final JPanel catPanel = new JPanel();
        catPanel.setLayout(new BorderLayout());
        catPanel.setVisible(true);
        catPanel.setOpaque(true);

        catView.setVisible(true);
        catPanel.add(catView, BorderLayout.CENTER);
        SwingUtilities.invokeLater(() -> {
            catView.setVisible(true);
            catView.revalidate();
            catView.repaint();
        });

        return catPanel;
    }

    public String getViewName() {
        return studySessionViewModel.getViewName();
    }

    public void setLogoutController(LogoutController controller) {
        this.logoutController = controller;
    }

    public void setStudySessionController(StudySessionController studySessionController) {
        this.studySessionController = studySessionController;
    }

    public void setChangeCatHappinessController(ChangeCatHappinessController controller) {
        this.changeCatHappinessController = controller;
    }

    public void setMusicControlController(MusicControlController musicControlController) {
        this.musicControlController = musicControlController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(timerSettings)) {
            studySessionController.switchToSetupSessionView();
        }
        else if (evt.getSource().equals(logOutSettings)) {
            // Execute the logout use case through the Controller
            this.logoutController.execute("");
        }
        else if (evt.getSource().equals(startTimerButton)) {
            // Start the timer
            swingTimer.start();
        }
        else if (evt.getSource().equals(stopTimerButton)) {
            // Stop the timer
            swingTimer.stop();
        }
        else if (evt.getSource().equals(playPauseButton)) {
            // Toggle playback
            musicControlController.togglePlayback();
            // Update button text based on the new state
            playPauseButton.setText(musicControlViewModel.getState().isPlaying() ? "Pause" : "Play");
        }
    }

    // Updated updateTimerLabel() to display the remaining time
    private void updateTimerLabel() {
        timerLabel.setText(formatTime(remainingTime));
    }

    private String formatTime(long timeInMil) {
        final long minutes = timeInMil / (Constants.SECONDS_TO_MILLIS * Constants.MINUTES_TO_SECONDS);
        final long seconds = (timeInMil / Constants.SECONDS_TO_MILLIS) % Constants.MINUTES_TO_SECONDS;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Nothing to do here
    }
}
