package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import app.service.DialogService;
import constants.Constants;
import interface_adapter.logout.LogoutController;
import interface_adapter.study_session.StudySessionController;
import interface_adapter.study_session.StudySessionViewModel;
import interface_adapter.timer.TimerController;
import interface_adapter.timer.TimerViewModel;

/**
 * Views for Study sessions.
 */
public class StudySessionView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "study session";

    private final TimerView timerView;
    private final CatView catView;

    private final TimerViewModel timerViewModel;

    private LogoutController logoutController;
    private StudySessionController studySessionController;

    // TODO: We don't need timerController if all the input is handled by TimerController in TimerView @yhj050224
    // Inject TimerView into StudySessionView similar to CatView instead of creating a new instance
    // Similar to CatView
    // Remove TimerViewModel from this class and constructor
    private TimerController timerController;

    private final JButton timerSettings;
    private final JButton logOutSettings;

    private DialogService dialogService;

    public StudySessionView(StudySessionViewModel studySessionViewModel, TimerViewModel timerViewModel,
            DialogService dialogService, CatView catView) {

        studySessionViewModel.addPropertyChangeListener(this);

        this.dialogService = dialogService;
        this.timerViewModel = timerViewModel;

        this.timerView = new TimerView(timerViewModel);
        this.catView = catView;

        this.setLayout(new BorderLayout());

        timerSettings = createButton("Timer Settings");
        logOutSettings = createButton("Log Out");

        timerSettings.addActionListener(this);
        logOutSettings.addActionListener(this);

        // Add components to main panel
        this.add(createTopPanel(), BorderLayout.NORTH);
        this.add(createTimerPanel(), BorderLayout.CENTER);
        this.add(createCatPanel(), BorderLayout.SOUTH);

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

        final JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtons.add(logOutSettings);

        buttonsPanel.add(leftButtons, BorderLayout.WEST);
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

    private TimerView createTimerPanel() {
        timerView.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerView.setVisible(true);
        return timerView;
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
        return viewName;
    }

    public void setTimerController(TimerController timerController) {
        this.timerController = timerController;
    }

    public void setLogoutController(LogoutController controller) {
        this.logoutController = controller;
    }

    public void setStudySessionController(StudySessionController controller) {
        this.studySessionController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(timerSettings)) {
            // TODO: Switch to TimerSettingsView @yhj050224
            dialogService.showTimerSettingsDialog();
        }
        else if (evt.getSource().equals(logOutSettings)) {
            // Execute the logout use case through the Controller
            this.logoutController.execute("");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            // TODO: Implement this: What happens when StudySessionViewModel changes state?
        }
    }
}
