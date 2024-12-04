package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;
/**
 * Factory for creating inventory UI components.
 */
public class InventoryUIFactory {
    
    /**
     * Creates the main panel for the inventory.
     * @return Configured main panel
     */
    public static JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        return mainPanel;
    }

    /**
     * Creates a food label panel with selection capability.
     * @param foodName Name of the food item
     * @param quantity Quantity of the food item
     * @param selectedLabel Reference to currently selected label
     * @return Panel containing food labels
     */
    public static JPanel createFoodLabelPanel(String foodName, Integer quantity, JLabel[] selectedLabel) {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel foodLabel = new JLabel(foodName);
        JLabel quantityLabel = new JLabel(": " + quantity);
        
        Border selectedBorder = BorderFactory.createLineBorder(Color.BLUE, 2);
        foodLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectedLabel[0] != null) {
                    selectedLabel[0].setBorder(BorderFactory.createEmptyBorder());
                }
                selectedLabel[0] = foodLabel;
                foodLabel.setBorder(selectedBorder);
            }
        });
        
        labelPanel.add(foodLabel);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(quantityLabel);
        
        return labelPanel;
    }

    /**
     * Creates an inventory panel with specified layout.
     * @param isEmpty Whether the inventory is empty
     * @return Configured inventory panel
     */
    public static JPanel createInventoryPanel(boolean isEmpty) {
        JPanel panel = new JPanel();
        if (isEmpty) {
            panel.setLayout(new GridBagLayout());
        } else {
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        }
        return panel;
    }
}
