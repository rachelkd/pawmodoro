package entity;

import java.util.HashMap;
import java.util.Map;

public class FoodInventory implements Inventory {
    private final String ownerId;
    private final Map<String,AbstractFoodItem> items;

    public FoodInventory(String ownerId) {
        this.ownerId = ownerId;
        this.items = new HashMap<>();
    }

    public Map<String, AbstractFoodItem> getItems() {
        return new HashMap<>(items);
    }

    public String getOwnerId() {
        return ownerId;
    }

    @Override
    public boolean canUseFood(String foodId) {

        return items.containsKey(foodId);
    }

    @Override
    public void addItem(AbstractFoodItem item) {

        if (items.containsKey(item.getFoodId())) {
            item.setQuantity(item.getQuantity() + 1);
        }
        else {
            items.put(item.getFoodId(), item);
            item.setQuantity(1);
        }

    }

    @Override
    public boolean useFood(String foodId) {

        if (canUseFood(foodId)) {
            AbstractFoodItem item = items.get(foodId);
            item.setQuantity(item.getQuantity() - 1);
            return true;
        }
        return false;
    }


}
