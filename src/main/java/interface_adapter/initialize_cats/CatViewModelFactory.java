package interface_adapter.initialize_cats;

import entity.Cat;
import interface_adapter.cat.CatState;
import interface_adapter.cat.CatViewModel;

public class CatViewModelFactory {

    public CatViewModel create(Cat cat) {
        final CatViewModel catViewModel = new CatViewModel();
        final CatState catState = catViewModel.getState();

        catState.setCatName(cat.getName());
        catState.setOwnerUsername(cat.getOwnerUsername());
        catState.setHungerLevel(cat.getHungerLevel());
        catState.setHappinessLevel(cat.getHappinessLevel());
        catState.setImageFileName(cat.getImageFileName());

        catViewModel.setState(catState);

        return catViewModel;
    }
}
