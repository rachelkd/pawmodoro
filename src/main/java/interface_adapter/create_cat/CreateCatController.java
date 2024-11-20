package interface_adapter.create_cat;

import use_case.cat_management.create_cat.CreateCatInputBoundary;
import use_case.cat_management.create_cat.CreateCatInputData;

/**
 * Controller for the Create Cat Use Case.
 */
public class CreateCatController {
    private final CreateCatInputBoundary createCatInteractor;

    public CreateCatController(CreateCatInputBoundary createCatInteractor) {
        this.createCatInteractor = createCatInteractor;
    }

    /**
     * Executes the Create Cat Use Case.
     * @param catName name of cat
     * @param ownerUsername username of cat owner
     */
    void execute(String catName, String ownerUsername) {
        final CreateCatInputData createCatInputData = new CreateCatInputData(catName, ownerUsername);
        createCatInteractor.execute(createCatInputData);
    }
}
