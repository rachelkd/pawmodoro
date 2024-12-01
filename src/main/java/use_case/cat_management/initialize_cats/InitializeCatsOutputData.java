package use_case.cat_management.initialize_cats;

import java.util.Collection;

import entity.Cat;

/**
 * Output Data for the initialize cat use case.
 */
public class InitializeCatsOutputData {
    private final Collection<Cat> cats;

    public InitializeCatsOutputData(Collection<Cat> cats) {
        this.cats = cats;
    }

    public Collection<Cat> getCats() {
        return cats;
    }
}
