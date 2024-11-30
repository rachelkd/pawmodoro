package use_case.adoption;

import entity.Cat;

/**
 * The Adoption Interactor
 */
public class AdoptionInteractor implements AdoptionInputBoundary {
    private final AdoptionOutputBoundary adoptionPresenter;

    public AdoptionInteractor(AdoptionOutputBoundary adoptionPresenter) {
        this.adoptionPresenter = adoptionPresenter;
    }

    @Override
    public void execute(AdoptionInputData adoptionInputData) {
        final String catName = adoptionInputData.getCatName();
        final String ownerName = adoptionInputData.getOwnerName();
        final AdoptionOutputData adoptionOutputData = new AdoptionOutputData(catName, false, ownerName);
        adoptionPresenter.prepareSuccessView(adoptionOutputData);
    }

}
