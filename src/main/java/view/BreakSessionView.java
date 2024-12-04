package view;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import app.service.DialogService;
import constants.Constants;
import interface_adapter.adoption.AdoptionState;
import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.break_session.BreakSessionController;
import interface_adapter.break_session.BreakSessionState;
import interface_adapter.break_session.BreakSessionViewModel;
import interface_adapter.logout.LogoutController;
import view.components.BreakSessionPanelManager;
import view.components.SessionUiFactory;

/**
 * The BreakSessionView class.
 */
public class BreakSessionView extends JPanel implements PropertyChangeListener {
    private final BreakSessionViewModel breakSessionViewModel;
    private final BreakSessionState breakSessionState;
    private LogoutController logoutController;
    private BreakSessionController breakSessionController;
    private Timer swingTimer;
    private long remainingTime;
    private JLabel timerLabel;
    private JButton startTimerButton;
    private JButton logOutSettings;
    private final CatContainerView catContainerView;
    private StudySessionView studySessionView;
    private final AdoptionViewModel adoptionViewModel;
    private final DialogService dialogService;
    private final DisplayCatImageView displayCatImageView;
    private JPanel catsPanel;

    public BreakSessionView(BreakSessionViewModel breakSessionViewModel,
            AdoptionViewModel adoptionViewModel,
            DialogService dialogService,
            DisplayCatImageView displayCatImageView,
            CatContainerView catContainerView) {
        this.breakSessionViewModel = breakSessionViewModel;
        this.breakSessionState = breakSessionViewModel.getState();
        this.catContainerView = catContainerView;
        this.displayCatImageView = displayCatImageView;
        this.adoptionViewModel = adoptionViewModel;
        this.dialogService = dialogService;
        
        breakSessionViewModel.addPropertyChangeListener(this);
        initializeComponents();
        this.catsPanel = BreakSessionPanelManager.setupLayout(this, createComponents());
    }

    private void initializeComponents() {
        initializeTimerComponents();
        initializeButtons();
    }

    private void initializeTimerComponents() {
        this.remainingTime = breakSessionState.getBreakInterval();
        timerLabel = SessionUiFactory.createTimerLabel(SessionUiFactory.formatTime(remainingTime));
        
        swingTimer = SessionUiFactory.createSessionTimer(remainingTime, new SessionUiFactory.TimerCallback() {
            @Override
            public void onTick(String formattedTime) {
                remainingTime -= Constants.SECONDS_TO_MILLIS;
                timerLabel.setText(formattedTime);
            }

            @Override
            public void onComplete() {
                handleTimerCompletion();
            }
        });
    }

    private void initializeButtons() {
        logOutSettings = SessionUiFactory.createStandardButton("Log Out");
        startTimerButton = SessionUiFactory.createStandardButton("Start Timer");

        logOutSettings.addActionListener(evt -> handleLogout());
        startTimerButton.addActionListener(evt -> handleStartTimer());
    }

    private BreakSessionPanelManager.ComponentBundle createComponents() {
        return new BreakSessionPanelManager.ComponentBundle(
            timerLabel, startTimerButton, logOutSettings, 
            catContainerView, displayCatImageView, 
            () -> handleAdoptionButton()
        );
    }

    private void handleStartTimer() {
        swingTimer.start();
        startTimerButton.setEnabled(false);
    }

    private void handleLogout() {
        if (logoutController != null) {
            studySessionView.showCatContainerView();
            logoutController.execute("");
        }
    }

    private void handleTimerCompletion() {
        breakSessionController.switchToStudySessionView();
        studySessionView.showCatContainerView();
        breakSessionState.resetToDefaultBreakInterval();
        remainingTime = breakSessionState.getBreakInterval();
        timerLabel.setText(SessionUiFactory.formatTime(remainingTime));
        startTimerButton.setEnabled(true);
    }

    private void handleAdoptionButton() {
        dialogService.createAdoptionDialog(adoptionViewModel);
        dialogService.showAdoptionDialog(adoptionViewModel);
        final AdoptionState adoptionState = adoptionViewModel.getState();
        adoptionViewModel.setState(adoptionState);
    }

    /**
     * Shows catContainerView.
     */
    public void showCatContainerView() {
        catsPanel.removeAll();
        catsPanel.add(catContainerView, BorderLayout.CENTER);
        SwingUtilities.invokeLater(() -> {
            catContainerView.setVisible(true);
            catsPanel.revalidate();
            catsPanel.repaint();
        });
    }

    public void setBreakSessionController(BreakSessionController breakSessionController) {
        this.breakSessionController = breakSessionController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setStudySessionView(StudySessionView studySessionView) {
        this.studySessionView = studySessionView;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final BreakSessionState newState = (BreakSessionState) evt.getNewValue();
            remainingTime = newState.getBreakInterval();
            timerLabel.setText(SessionUiFactory.formatTime(remainingTime));
            startTimerButton.setEnabled(true);
        }
    }

    public String getViewName() {
        return breakSessionViewModel.getViewName();
    }
}
