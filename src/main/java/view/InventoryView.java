package view;

import entity.AbstractFood;
import interface_adapter.add_to_inventory.AddToInventoryController;
import interface_adapter.create_inventory.CreateInventoryController;
import interface_adapter.create_inventory.InventoryState;
import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.use_item_in_inventory.UseItemController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

/**
 * The View when user is viewing contents of their inventory.
 */
public class InventoryView extends JPanel implements ActionListener, PropertyChangeListener {
    private final InventoryViewModel inventoryViewModel;
    private CreateInventoryController createInventoryController;
    private AddToInventoryController addToInventoryController;
    private UseItemController useItemController;
    private Map<String, AbstractFood> userInventory;

    /**
     * Creates the Inventory View.
     * @param inventoryViewModel the view model
     */
    public InventoryView(InventoryViewModel inventoryViewModel) {
        this.inventoryViewModel = inventoryViewModel;
        this.inventoryViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 200));
        Border border = BorderFactory.createLineBorder(Color.black);
        this.setBorder(border);

        final JLabel title = new JLabel(InventoryViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        this.add(title, BorderLayout.NORTH);

        // inventoryViewModel.firePropertyChanged("inventory_created");
    }

    // when add to inventory use case, update view

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "HELPPPPP");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals("inventory_created")) {

            final InventoryState state = (InventoryState) evt.getNewValue();
            userInventory = state.getInventoryItems();

            if (!userInventory.isEmpty()) {
                JPanel inventoryPanel = new JPanel();
                inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));

                // Define a border to indicate label selection
                Border selectedBorder = BorderFactory.createLineBorder(Color.BLUE, 2);
                // Track the selected label
                JLabel[] selectedLabel = {null};

                for (AbstractFood food : userInventory.values()) {
                    // panel to put food and quantity labels side by side
                    JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

                    JLabel foodLabel = new JLabel(food.getName());
                    JLabel quantityLabel = new JLabel(": " + food.getQuantity());

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
                this.add(inventoryPanel, BorderLayout.CENTER);

                JButton selectItemButton = new JButton("Use Selected Item");
                this.add(selectItemButton, BorderLayout.SOUTH);
                selectItemButton.addActionListener(
                        e -> {

                });

            }
            else {
                // user does not have an existing inventory or their inventory is empty
                JPanel inventoryPanel = new JPanel(new GridBagLayout());
                final JLabel items = new JLabel(inventoryViewModel.EMPTY_INVENTORY_LABEL);
                items.setAlignmentX(Component.CENTER_ALIGNMENT);
                inventoryPanel.add(items);
                this.add(inventoryPanel, BorderLayout.CENTER);
            }

        }
        else if (evt.getPropertyName().equals("inventory_add")) {
            final InventoryState state = (InventoryState) evt.getNewValue();
            // JOptionPane.showMessageDialog(null, "item added to inventory for " + state.getUsername());
        }
        else if (evt.getPropertyName().equals("inventory_item_used")) {
            final InventoryState state = (InventoryState) evt.getNewValue();
            // JOptionPane.showMessageDialog(null, "item used for " + state.getUsername());
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
}
