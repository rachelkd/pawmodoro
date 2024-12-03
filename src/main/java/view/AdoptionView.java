package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.adoption.AdoptionController;
import interface_adapter.adoption.AdoptionState;
import interface_adapter.adoption.AdoptionViewModel;
import interface_adapter.create_cat.CreateCatController;

/**
 * AdoptionView class.
 */
public class AdoptionView extends JDialog implements ActionListener, PropertyChangeListener {
    private final AdoptionViewModel adoptionViewModel;
    private final String viewName = "adoption";
    private AdoptionController adoptionController;
    private CreateCatController createCatController;
    private final AdoptionPanel adoptionPanel;

    public AdoptionView(JFrame parent, AdoptionViewModel adoptionViewModel) {
        super(parent, AdoptionViewModel.TITLE_LABEL, true);
        this.adoptionViewModel = adoptionViewModel;
        this.adoptionViewModel.addPropertyChangeListener(this);
        
        this.adoptionPanel = new AdoptionPanel(adoptionViewModel);
        setupListeners();
        
        this.add(adoptionPanel);
        this.pack();
        this.setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void setupListeners() {
        adoptionPanel.getNameField().getDocument().addDocumentListener(new NameFieldDocumentListener());
        adoptionPanel.getConfirmButton().addActionListener(new ConfirmButtonListener());
        adoptionPanel.getReturnButton().addActionListener(new ReturnButtonListener());
        adoptionPanel.getCancelButton().addActionListener(evt -> this.setVisible(false));
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

    public String getViewName() {
        return viewName;
    }

    public void setAdoptionController(AdoptionController adoptionController) {
        this.adoptionController = adoptionController;
    }

    public void setCreateCatController(CreateCatController createCatController) {
        this.createCatController = createCatController;
    }

    /**
     * NameFieldDocumentListener method.
     */
    private final class NameFieldDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateState();
        }
        
        private void updateState() {
            final AdoptionState currentState = adoptionViewModel.getState();
            currentState.setCatName(adoptionPanel.getNameField().getText());
            adoptionViewModel.setState(currentState);
        }
    }

    /**
     * ConfirmButtonListener method.
     */
    private final class ConfirmButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource().equals(adoptionPanel.getConfirmButton())) {
                final AdoptionState currentState = adoptionViewModel.getState();
                createCatController.execute(currentState.getCatName(),
                        currentState.getOwner());
                adoptionController.execute(currentState.getCatName());
                
                if (currentState.getIsSuccess()) {
                    updatePanelForSuccess();
                }
                else {
                    dispose();
                }
            }
        }
        
        private void updatePanelForSuccess() {
            final JPanel mainPanel = adoptionPanel.getMainPanel();
            mainPanel.remove(adoptionPanel.getInformation());
            mainPanel.remove(adoptionPanel.getFinish());
            mainPanel.add(adoptionPanel.getAdoptionCompletePanel(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    /**
     * ReturnButtonListener method.
     */
    private final class ReturnButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource().equals(adoptionPanel.getReturnButton())) {
                final JPanel mainPanel = adoptionPanel.getMainPanel();
                mainPanel.add(adoptionPanel.getInformation(), BorderLayout.CENTER);
                mainPanel.add(adoptionPanel.getFinish(), BorderLayout.SOUTH);
                mainPanel.remove(adoptionPanel.getAdoptionCompletePanel());
                adoptionPanel.getNameField().setText("");
                dispose();
            }
        }
    }
}

