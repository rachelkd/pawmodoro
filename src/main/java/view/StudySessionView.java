package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import constants.Constants;
import interface_adapter.study_session.StudySessionController;
import interface_adapter.study_session.StudySessionViewModel;
import interface_adapter.timer.TimerController;
import interface_adapter.timer.TimerViewModel;

/**
 * Views for Study sessions.
 */
public class StudySessionView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "study session";

    private final TimerViewModel timerViewModel;
    private final TimerView timerView;
    private final StudySessionViewModel studySessionViewModel;
    private TimerController timerController;
    private final JButton timerSettings;
    private final JButton logOutSettings;

    public StudySessionView(StudySessionViewModel studySessionViewModel, TimerViewModel timerViewModel) {
        this.timerViewModel = timerViewModel;
        timerViewModel.addPropertyChangeListener(this);
        this.studySessionViewModel = studySessionViewModel;
        studySessionViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());

        final JPanel buttonsPanel = new JPanel(new BorderLayout());

        final JPanel leftButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timerSettings = new JButton("Timer Settings");
        leftButtons.add(timerSettings);

        final JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logOutSettings = new JButton("Log Out");
        rightButtons.add(logOutSettings);

        buttonsPanel.add(leftButtons, BorderLayout.WEST);
        buttonsPanel.add(rightButtons, BorderLayout.EAST);

        final JLabel title = new JLabel("STUDY SESSION", SwingConstants.CENTER);
        title.setFont(new Font(Constants.FONT_FAMILY, Font.BOLD, Constants.TITLE));

        final JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(title, BorderLayout.CENTER);

        timerSettings.addActionListener(this);
        logOutSettings.addActionListener(this);

        this.timerView = new TimerView(timerViewModel);

        this.add(buttonsPanel, BorderLayout.NORTH);
        this.add(titlePanel, BorderLayout.CENTER);
        this.add(timerView, BorderLayout.SOUTH);
    }

    public String getViewName() {
        return viewName;
    }

    public void setTimerController(TimerController timerController) {
        this.timerController = timerController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("settings clicked");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("changed to study session view");
    }
}
