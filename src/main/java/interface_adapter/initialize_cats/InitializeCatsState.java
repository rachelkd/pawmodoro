package interface_adapter.initialize_cats;

import java.util.Collection;

import entity.Cat;
import interface_adapter.cat.CatViewModel;

public class InitializeCatsState {
    private Collection<CatViewModel> catViewModels;
    private Collection<Cat> cats;

    public InitializeCatsState(InitializeCatsState copy) {

        this.cats = copy.cats;
        this.catViewModels = copy.catViewModels;
    }

    // default constructor
    public InitializeCatsState() {

    }

    public Collection<Cat> getCats() {
        return cats;
    }

    public void setCats(Collection<Cat> cats) {
        this.cats = cats;
    }

    public Collection<CatViewModel> getCatViewModels() {
        return catViewModels;
    }

    public void setCatViewModels(Collection<CatViewModel> catViewModels) {
        this.catViewModels = catViewModels;
    }
}
