package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;

import entity.AbstractFood;
import interface_adapter.add_to_inventory.AddToInventoryController;
import interface_adapter.create_inventory.CreateInventoryController;
import interface_adapter.create_inventory.InventoryState;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.use_item_in_inventory.UseItemController;

/**
 * The View when user is viewing contents of their inventory.
 */
public class InventoryView extends JPanel implements ActionListener, PropertyChangeListener {
    private final InventoryViewModel inventoryViewModel;
    private CreateInventoryController createInventoryController;
    private AddToInventoryController addToInventoryController;
    private UseItemController useItemController;
    private Map<String, AbstractFood> userInventory;
    private JPanel inventoryPanel;
    private JLabel[] selectedLabel;

    /**
     * Creates the Inventory View.
     * @param inventoryViewModel the view model
     */
    public InventoryView(InventoryViewModel inventoryViewModel) {
        this.inventoryViewModel = inventoryViewModel;
        this.inventoryViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 200));
        final Border border = BorderFactory.createLineBorder(Color.black);
        this.setBorder(border);

        final JLabel title = new JLabel(InventoryViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        this.add(title, BorderLayout.NORTH);

        this.inventoryPanel = new JPanel();
        // Track the selected label in view
        this.selectedLabel = new JLabel[]{null};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // move to be in initialization or another function?
        if (evt.getPropertyName().equals("inventory_created")) {

            final InventoryState state = (InventoryState) evt.getNewValue();
            userInventory = state.getInventoryItems();

            buildInventory();
            this.add(inventoryPanel, BorderLayout.CENTER);

        } // add to inventory when complete study session so this use case doesn't need to be here
        else if (evt.getPropertyName().equals("inventory_add")) {
            final InventoryState state = (InventoryState) evt.getNewValue();
            final AbstractFood food = state.getNewFoodItem();
            // update the user inventory
            userInventory = state.getInventoryItems();

            addFoodLabel(food);
        }

        else if (evt.getPropertyName().equals("inventory_item_used")) {
            final InventoryState state = (InventoryState) evt.getNewValue();
            userInventory = state.getInventoryItems();
            refreshInventory();
        }
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

    void buildInventory() {
        if (!userInventory.isEmpty()) {
            // JPanel inventoryPanel = new JPanel();
            inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));

            for (AbstractFood food : userInventory.values()) {
                addFoodLabel(food);
            }

            final JButton selectItemButton = new JButton("Use Selected Item");
            this.add(selectItemButton, BorderLayout.SOUTH);
            selectItemButton.addActionListener(
                    event -> {
                        if (selectedLabel[0] != null) {
                            final String selectedText = selectedLabel[0].getText();
                            JOptionPane.showMessageDialog(null, "Selected Item: " + selectedText);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Please select an item first.");
                        }
                    });
        }
        else {
            // user does not have an existing inventory or their inventory is empty
            inventoryPanel.setLayout(new GridBagLayout());
            final JLabel items = new JLabel(inventoryViewModel.EMPTY_INVENTORY_LABEL);
            items.setAlignmentX(Component.CENTER_ALIGNMENT);
            inventoryPanel.add(items);
        }
    }

    void addFoodLabel(AbstractFood food) {
        // panel to put food and quantity labels side by side
        final JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Define a border to indicate label selection
        final Border selectedBorder = BorderFactory.createLineBorder(Color.BLUE, 2);

        final JLabel foodLabel = new JLabel(food.getName());
        final JLabel quantityLabel = new JLabel(": " + food.getQuantity());
        foodLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Deselect previous label if any
                if (selectedLabel[0] != null) {
                    selectedLabel[0].setBorder(BorderFactory.createEmptyBorder());
                }
                // Set new selected label and add border
                selectedLabel[0] = foodLabel;
                foodLabel.setBorder(selectedBorder);
            }
        });
        labelPanel.add(foodLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(quantityLabel);

        inventoryPanel.add(labelPanel);
    }

    void refreshInventory() {
        inventoryPanel.removeAll();
        buildInventory();
    }
}
