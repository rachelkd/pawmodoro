package interface_adapter.adoption;

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
     * Executes the Adoption Use Case.
     * @param catName the name of the adopted cat
     * @param owner the owner of the adopted cat
     */
    public void execute(String catName, String owner) {
        final AdoptionInputData adoptionInputData = new AdoptionInputData(catName, owner);

        adoptionUseCaseInteractor.execute(adoptionInputData);
    }

    /**
     * Executes the switch to Setup Session View use case.
     */
    public void switchToSetupView() {
        adoptionUseCaseInteractor.switchToSetupView();
    }
}
