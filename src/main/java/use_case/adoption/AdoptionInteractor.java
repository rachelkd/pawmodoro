package use_case.adoption;

import entity.Cat;

/**
 * The Adoption Interactor
 */
public class AdoptionInteractor implements AdoptionInputBoundary {
    private AdoptionDataAccessInterface userDataAccessObject;
    private final AdoptionOutputBoundary adoptionPresenter;

    public AdoptionInteractor(AdoptionOutputBoundary adoptionPresenter, AdoptionDataAccessInterface userDataAccessInterface) {
        this.adoptionPresenter = adoptionPresenter;
        this.userDataAccessObject = userDataAccessInterface;
    }

    @Override
    public void execute(AdoptionInputData adoptionInputData) {
        final String catName = adoptionInputData.getCatName();
        final String ownerName = userDataAccessObject.getCurrentUsername();
        final AdoptionOutputData adoptionOutputData = new AdoptionOutputData(catName, false);
        adoptionPresenter.prepareSuccessView(adoptionOutputData);
    }

}
