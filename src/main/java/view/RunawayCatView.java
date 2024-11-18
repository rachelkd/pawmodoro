package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.runawaycat.RunawayCatController;
import interface_adapter.runawaycat.RunawayCatState;
import interface_adapter.runawaycat.RunawayCatViewModel;

/**
 * A view component that displays a message when a cat runs away.
 * This panel shows the cat's name and a reminder message about cat care,
 * along with a confirmation button.
 */
public class RunawayCatView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "runaway cat";
    private final String badNews = "Oh no, %s has ran away!";
    private final JButton confirm = new JButton(RunawayCatViewModel.CONFIRM_LABEL);
    private final JLabel news = new JLabel();
    private final JLabel reminders = new JLabel(RunawayCatViewModel.REMINDER_LABEL);

    private final RunawayCatViewModel runawayCatViewModel;
    private RunawayCatController runawayCatController;

    public RunawayCatView(RunawayCatViewModel runawayCatViewModel) {
        this.runawayCatViewModel = runawayCatViewModel;

        final JPanel messages = new JPanel();
        messages.add(news);
        messages.add(reminders);

        final JPanel buttons = new JPanel();
        buttons.add(confirm);

        confirm.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(messages);
        this.add(buttons);

        runawayCatViewModel.addPropertyChangeListener(this);
        updateMessage();
    }

    private void updateMessage() {
        final RunawayCatState state = runawayCatViewModel.getState();
        news.setText(String.format(badNews, state.getCatName()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirm) {
            runawayCatController.switchToBreakView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateMessage();
    }

    public String getViewName() {
        return viewName;
    }

    public void setRunawayCatController(RunawayCatController runawayCatController) {
        this.runawayCatController = runawayCatController;
    }

}
