package use_case.adoption;

import entity.Cat;

/**
 * The Adoption Interactor
 */
public class AdoptionInteractor implements AdoptionInputBoundary {

    //private final AdoptionDataAccessInterface adoptionDataAccessObject;
    private final AdoptionOutputBoundary adoptionPresenter;

    public AdoptionInteractor(//AdoptionDataAccessInterface adoptionDataAccessObject,
                              AdoptionOutputBoundary adoptionPresenter) {
//        this.adoptionDataAccessObject = adoptionDataAccessObject;
        this.adoptionPresenter = adoptionPresenter;
    }
    @Override
    public void execute(AdoptionInputData adoptionInputData) {
        final String catName = adoptionInputData.getCatName();
//        if (adoptionDataAccessObject.nameAlreadyExists(catName)) {
//            adoptionPresenter.prepareFailView("You already have a cat with this name!");
//        }
//        else {
            final AdoptionOutputData adoptionOutputData = new AdoptionOutputData(catName, false);
            adoptionPresenter.prepareSuccessView(adoptionOutputData);
        //}
    }

    @Override
    public void switchToSetupView() {
        adoptionPresenter.switchToSetupView();
    }
}
