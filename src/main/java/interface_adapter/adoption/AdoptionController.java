package interface_adapter.adoption;

import entity.User;
import use_case.adoption.AdoptionInputBoundary;
import use_case.adoption.AdoptionInputData;

/**
 * Controller for handling adoption-related user actions.
 */
public class AdoptionController {

    private final AdoptionInputBoundary adoptionUseCaseInteractor;

    public AdoptionController(AdoptionInputBoundary adoptionUseCaseInteractor) {
        this.adoptionUseCaseInteractor = adoptionUseCaseInteractor;
    }

    /**
     * Executes the Adoption Use Case
     *
     * @param catName the name of the adopted cat
     */
    public void execute(String catName, User owner) {
        final AdoptionInputData adoptionInputData = new AdoptionInputData(catName, owner);
    }

    public void switchToSetupView() {
        adoptionUseCaseInteractor.switchToSetupView();
    }
}
