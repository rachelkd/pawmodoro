package interface_adapter.adoption;

import use_case.adoption.AdoptionDataAccessInterface;
import use_case.adoption.AdoptionInputBoundary;
import use_case.adoption.AdoptionInputData;

/**
 * Controller for handling adoption-related user actions.
 */
public class AdoptionController {

    private final AdoptionInputBoundary adoptionUseCaseInteractor;
    private final AdoptionDataAccessInterface userDataAccessObject;

    public AdoptionController(AdoptionInputBoundary adoptionUseCaseInteractor, AdoptionDataAccessInterface userDataAccessObject) {
        this.adoptionUseCaseInteractor = adoptionUseCaseInteractor;
        this.userDataAccessObject = userDataAccessObject;
    }

    /**
     * Executes the Adoption Use Case.
     * @param catName the name of the adopted cat
     */
    public void execute(String catName) {
        final AdoptionInputData adoptionInputData = new AdoptionInputData(catName, userDataAccessObject.getOwnerName());

        adoptionUseCaseInteractor.execute(adoptionInputData);
    }
}
