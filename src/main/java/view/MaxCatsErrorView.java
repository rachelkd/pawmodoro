package view;


import interface_adapter.maxcatserror.MaxCatsErrorViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

/* The window that is displayed when the user has already adopted 4 cats ( and cannot adopt more)
 */
public class MaxCatsErrorView extends JPanel implements PropertyChangeListener, ActionListener {
    private final String viewName = "max cats error view";
    private final MaxCatsErrorViewModel maxCatsErrorViewModel;

    public MaxCatsErrorView(MaxCatsErrorViewModel maxCatsErrorViewModel) {
        this.maxCatsErrorViewModel = maxCatsErrorViewModel;
        maxCatsErrorViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Maximum Cats Reached Error Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JPanel errormessage = new JPanel();
        final JLabel message = new JLabel("Oh no! You have reached the maximum number of cats! You cannot adopt at the moment");
        final JButton returnButton = new JButton("Return");
        final JPanel button = new JPanel();
        errormessage.add(message);
        button.add(returnButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(errormessage);
        this.add(button);

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public String getViewName() {
        return viewName;
    }
}
