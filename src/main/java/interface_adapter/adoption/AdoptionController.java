package interface_adapter.adoption;

import use_case.adoption.AdoptionDataAccessInterface;
import use_case.adoption.AdoptionInputBoundary;
import use_case.adoption.AdoptionInputData;

/**
 * Controller for handling adoption-related user actions.
 */
public class AdoptionController {

    private final AdoptionInputBoundary adoptionUseCaseInteractor;
    private final AdoptionDataAccessInterface userDataAccessInterface;

    public AdoptionController(AdoptionInputBoundary adoptionUseCaseInteractor, AdoptionDataAccessInterface userDataAccessInterface) {
        this.adoptionUseCaseInteractor = adoptionUseCaseInteractor;
        this.userDataAccessInterface = userDataAccessInterface;
    }

    /**
     * Executes the Adoption Use Case.
     * @param catName the name of the adopted cat
     */
    public void execute(String catName) {
        final AdoptionInputData adoptionInputData = new AdoptionInputData(catName, userDataAccessInterface.getOwnerName());

        adoptionUseCaseInteractor.execute(adoptionInputData);
    }
}
