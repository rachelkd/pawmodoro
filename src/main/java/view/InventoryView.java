package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import interface_adapter.add_to_inventory.AddToInventoryController;
import interface_adapter.change_cat_hunger.ChangeCatHungerController;
import interface_adapter.create_inventory.CreateInventoryController;
import interface_adapter.create_inventory.InventoryState;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.use_item_in_inventory.UseItemController;
import view.components.InventoryUIFactory;

/**
 * The View when user is viewing contents of their inventory.
 */
public class InventoryView extends JDialog implements ActionListener, PropertyChangeListener {
    private static final int INVENTORY_WIDTH = 500;
    private static final int INVENTORY_HEIGHT = 200;
    private static final int INDENT = 10;

    private final InventoryViewModel inventoryViewModel;
    private CreateInventoryController createInventoryController;
    private AddToInventoryController addToInventoryController;
    private UseItemController useItemController;
    private ChangeCatHungerController changeCatHungerController;
    private Map<String, Integer> userInventory;
    private final JPanel mainPanel;
    private final JPanel inventoryPanel;
    private final JPanel buttonPanel;
    private final JLabel[] selectedLabel = new JLabel[]{null};

    /**
     * Creates the Inventory View.
     *
     * @param parent the application
     * @param inventoryViewModel the view model
     */
    public InventoryView(JFrame parent, InventoryViewModel inventoryViewModel) {

        super(parent, InventoryViewModel.TITLE_LABEL, false);
        this.inventoryViewModel = inventoryViewModel;

        this.mainPanel = InventoryUIFactory.createMainPanel();
        this.buttonPanel = new JPanel();
        this.inventoryPanel = InventoryUIFactory.createInventoryPanel(true);

        setupInitialLayout(parent);
        inventoryViewModel.addPropertyChangeListener(this);
    }

    private void setupInitialLayout(JFrame parent) {
        final JLabel title = new JLabel(InventoryViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, INDENT, 0, 0));

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(parent);
        this.setSize(new Dimension(INVENTORY_WIDTH, INVENTORY_HEIGHT));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals("inventory_created")) {
            SwingUtilities.invokeLater(() -> {
                final InventoryState state = (InventoryState) evt.getNewValue();
                userInventory = state.getInventoryItems();

                refreshInventory(state.getOwnerId());
                mainPanel.add(inventoryPanel, BorderLayout.CENTER);

                inventoryPanel.revalidate();
                inventoryPanel.repaint();
            });

        }
        else if (evt.getPropertyName().equals("inventory_add")) {
            SwingUtilities.invokeLater(() -> {
                final InventoryState state = (InventoryState) evt.getNewValue();
                clearEmptyMessage(state.getOwnerId());
                addToInventory(state);
                // update the user inventory
                userInventory = state.getInventoryItems();
                inventoryPanel.revalidate();
                inventoryPanel.repaint();
            });
        }

        else if (evt.getPropertyName().equals("inventory_item_used")) {
            SwingUtilities.invokeLater(() -> {
                final InventoryState state = (InventoryState) evt.getNewValue();
                userInventory = state.getInventoryItems();
                refreshInventory(state.getOwnerId());

                inventoryPanel.revalidate();
                inventoryPanel.repaint();
            });
        }
    }

    void buildInventory(String ownerId) {
        inventoryPanel.removeAll();
        if (!userInventory.isEmpty()) {
            inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
            for (Map.Entry<String, Integer> entry : userInventory.entrySet()) {
                addFoodLabel(entry.getKey(), entry.getValue());
            }
            addCloseButton();
            addSelectItemButton(ownerId);
        }
        else {
            inventoryPanel.setLayout(new GridBagLayout());
            final JLabel items = new JLabel(InventoryViewModel.EMPTY_INVENTORY_LABEL);
            items.setAlignmentX(Component.CENTER_ALIGNMENT);
            inventoryPanel.add(items);
            addCloseButton();
        }
    }

    void addSelectItemButton(String ownerId) {
        final JButton selectItemButton = new JButton("Use Selected Item");
        buttonPanel.add(selectItemButton, BorderLayout.SOUTH);
        selectItemButton.addActionListener(event -> {
            clickedSelectItemButton(ownerId);
        });
    }

    void clickedSelectItemButton(String ownerId) {
        if (selectedLabel[0] != null) {
            final String selectedText = selectedLabel[0].getText();
            useItemController.execute(ownerId, selectedText);
            changeCatHungerController.execute(inventoryViewModel.getState().getCurrentCatName(), ownerId, selectedText);
        }
        else {
            JOptionPane.showMessageDialog(null, "Please select an item first.");
        }
    }

    void addCloseButton() {
        final JButton closeButton = new JButton("Close Inventory");
        closeButton.addActionListener(event -> this.setVisible(false));
        buttonPanel.add(closeButton);
    }

    void clearEmptyMessage(String ownerId) {
        if (inventoryPanel.getComponent(0) instanceof JLabel) {
            final JLabel firstLabel = (JLabel) inventoryPanel.getComponent(0);
            if (InventoryViewModel.EMPTY_INVENTORY_LABEL.equals(firstLabel.getText())) {

                inventoryPanel.remove(firstLabel);
                addSelectItemButton(ownerId);

                inventoryPanel.revalidate();
                inventoryPanel.repaint();
            }
        }
    }

    void addToInventory(InventoryState state) {
        final String foodName = state.getCurrentFoodName();
        final Integer quantity = state.getInventoryItems().get(foodName);
        if (!userInventory.containsKey(foodName)) {
            addFoodLabel(foodName, quantity);
        }
        else {
            final Component[] foodLabels = inventoryPanel.getComponents();
            // update label
            for (int i = 0; i < foodLabels.length; i++) {
                final JPanel labelPanel = (JPanel) foodLabels[i];

                final JLabel foodLabel = (JLabel) labelPanel.getComponent(0);

                if (foodLabel.getText().equalsIgnoreCase(foodName)) {
                    final JLabel quantitylabel = (JLabel) labelPanel.getComponent(2);
                    quantitylabel.setText(": " + quantity);
                    foodLabel.revalidate();
                    foodLabel.repaint();
                    break;
                }
            }
        }
    }

    void addFoodLabel(String foodName, Integer quantity) {
        final JPanel labelPanel = InventoryUIFactory.createFoodLabelPanel(foodName, quantity, selectedLabel);
        inventoryPanel.add(labelPanel);
    }

    void refreshInventory(String ownerId) {
        inventoryPanel.removeAll();
        buttonPanel.removeAll();

        buildInventory(ownerId);
    }

    public String getViewName() {
        return inventoryViewModel.getViewName();
    }

    public void setCreateInventoryController(CreateInventoryController createInventoryController) {
        this.createInventoryController = createInventoryController;
    }

    public void setAddToInventoryController(AddToInventoryController addToInventoryController) {
        this.addToInventoryController = addToInventoryController;
    }

    public void setUseItemController(UseItemController useItemController) {
        this.useItemController = useItemController;
    }

    public void setChangeCatHungerController(ChangeCatHungerController changeCatHungerController) {
        this.changeCatHungerController = changeCatHungerController;
    }
}
