package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import constants.Constants;
import interface_adapter.adoption.AdoptionController;
import interface_adapter.adoption.AdoptionState;
import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.create_cat.CreateCatController;

/**
 * The view for the Adopting a cat use case.
 */
public class AdoptionView extends JDialog implements ActionListener, PropertyChangeListener {
    private static final int INDENT = 10;
    private final AdoptionViewModel adoptionViewModel;
    private final String viewName = "adoption";
    private final JLabel name = new JLabel(AdoptionViewModel.NAME_LABEL);
    private final JTextField nameField = new JTextField(15);
    private final JButton confirmButton = new JButton(AdoptionViewModel.CONFIRM_BUTTON_LABEL);
    private final JButton cancelButton = new JButton(AdoptionViewModel.CANCEL_BUTTON_LABEL);
    private AdoptionController adoptionController;
    private CreateCatController createCatController;
    private final JPanel mainPanel;
    private final JPanel information;
    private final JPanel finish;
    private final JPanel adoptionPanel;

    /**
     * Creates a new AdoptionView.
     *
     * @param parent the application
     * @param adoptionViewModel the view model for the adoption use case
     */
    public AdoptionView(JFrame parent, AdoptionViewModel adoptionViewModel) {
        super(parent, AdoptionViewModel.TITLE_LABEL, true);
        this.adoptionViewModel = adoptionViewModel;
        this.adoptionViewModel.addPropertyChangeListener(this);

        this.mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(Constants.ADOPTION_VIEW_WIDTH, Constants.ADOPTION_VIEW_HEIGHT));
        mainPanel.setLayout(new BorderLayout());
        final Border border = BorderFactory.createLineBorder(Color.black);
        mainPanel.setBorder(border);

        this.information = new JPanel();
        this.finish = new JPanel();

        final JLabel title = new JLabel(AdoptionViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, INDENT, 0, 0));
        mainPanel.add(title, BorderLayout.NORTH);

        information.add(name);
        information.add(nameField);
        finish.add(confirmButton);
        finish.add(cancelButton);

        mainPanel.add(information, BorderLayout.CENTER);
        mainPanel.add(finish, BorderLayout.SOUTH);

        confirmButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(confirmButton)) {
                            final AdoptionState currentState = adoptionViewModel.getState();

                            AdoptionView.this.createCatController.execute(currentState.getCatName(),
                                    currentState.getOwner());

                            adoptionController.execute(
                                    currentState.getCatName(),
                                    currentState.getOwner());
                        }
                    }
                });
        cancelButton.addActionListener(event -> this.setVisible(false));

        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(parent);
        this.adoptionPanel = new JPanel();

        nameField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final AdoptionState currentState = adoptionViewModel.getState();
                currentState.setCatName(new String(nameField.getText()));
                adoptionViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });


        this.add(Box.createRigidArea(new Dimension(Constants.SPACING, Constants.SPACING)));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AdoptionState state = (AdoptionState) evt.getNewValue();
        if (state.getAdoptionError() != null) {
            JOptionPane.showMessageDialog(this, state.getAdoptionError());
        }
    }

    /**
     * Gets the name of this view.
     * @return the view name
     */
    public String getViewName() {
        return viewName;
    }

    public void setAdoptionController(AdoptionController adoptionController) {
        this.adoptionController = adoptionController;
    }

    public void setCreateCatController(CreateCatController createCatController) {
        this.createCatController = createCatController;
    }
}
