package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import constants.Constants;
import interface_adapter.adoption.AdoptionController;
import interface_adapter.adoption.AdoptionState;
import interface_adapter.adoption.AdoptionViewModel;

/**
 * The view for the Adopting a cat use case.
 */
public class AdoptionView extends JPanel implements ActionListener, PropertyChangeListener {
    private final AdoptionViewModel adoptionViewModel;
    private final String viewName = "adoption";
    private final JLabel name = new JLabel(AdoptionViewModel.NAME_LABEL);
    private final JTextField nameField = new JTextField(15);
    private final JButton confirmButton = new JButton(AdoptionViewModel.CONFIRM_BUTTON_LABEL);
    private final JButton cancelButton = new JButton(AdoptionViewModel.CANCEL_BUTTON_LABEL);
    private AdoptionController adoptionController;

    /**
     * Creates a new AdoptionView.
     * 
     * @param adoptionViewModel the view model for the adoption use case
     */
    public AdoptionView(AdoptionViewModel adoptionViewModel) {
        this.adoptionViewModel = adoptionViewModel;
        adoptionViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(AdoptionViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel information = new JPanel();
        information.add(name);
        information.add(nameField);
        final JPanel finish = new JPanel();
        finish.add(confirmButton);
        finish.add(cancelButton);

        confirmButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(confirmButton)) {
                        final AdoptionState currentState = adoptionViewModel.getState();

                        adoptionController.execute(
                                currentState.getCatName(),
                                currentState.getOwner()
                        );
                    }
                }
            }
        );

        cancelButton.addActionListener (
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    adoptionController.switchToSetupView();
                }
            }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(information);
        this.add(finish);
        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING)));
    }

    private void addCatNameListener() {
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final AdoptionState currentState = adoptionViewModel.getState();
                currentState.setCatName(new String(nameField.getText()));
                adoptionViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {documentListenerHelper();}

            @Override
            public void removeUpdate(DocumentEvent e) {documentListenerHelper();}

            @Override
            public void changedUpdate(DocumentEvent e) {documentListenerHelper();}
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AdoptionState state = (AdoptionState) evt.getNewValue();
        if(state.getAdoptionError() != null) {
            JOptionPane.showMessageDialog(this, state.getAdoptionError());
        }
    }

    /**
     * Gets the name of this view.
     * 
     * @return the view name
     */
    public String getViewName() {
        return viewName;
    }

    public void setAdoptionController(AdoptionController adoptionController) {
        this.adoptionController = adoptionController;
    }
}
