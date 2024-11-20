package view;

import interface_adapter.maxcatserror.MaxCatsErrorController;
import interface_adapter.maxcatserror.MaxCatsErrorViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

/**
 * The window that is displayed when the user has already adopted 4 cats (and cannot adopt more).
 **/
public class MaxCatsErrorView extends JPanel implements ActionListener {
    private final String viewName = "max cats error view";
    private final MaxCatsErrorViewModel maxCatsErrorViewModel;
    private MaxCatsErrorController maxCatsErrorController;

    public MaxCatsErrorView(MaxCatsErrorViewModel maxCatsErrorViewModel) {
        this.maxCatsErrorViewModel = maxCatsErrorViewModel;

        final JLabel title = new JLabel(MaxCatsErrorViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JPanel errormessage = new JPanel();
        final JLabel message = new JLabel(MaxCatsErrorViewModel.ERROR_MESSAGE);
        final JButton returnButton = new JButton(MaxCatsErrorViewModel.RETURN_BUTTON_LABEL);
        final JPanel button = new JPanel();
        errormessage.add(message);
        button.add(returnButton);

        returnButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        maxCatsErrorController.switchToBreakView();
                    }
                });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(errormessage);
        this.add(button);

    }

    /**
     * React to a button click that results in e.
     *
     * @param e ActionEvent to react to
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    public String getViewName() {
        return viewName;
    }

    public void setMaxCatsController(MaxCatsErrorController maxCatsErrorController) {
        this.maxCatsErrorController = maxCatsErrorController;
    }
}
