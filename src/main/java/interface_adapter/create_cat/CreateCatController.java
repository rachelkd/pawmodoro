package interface_adapter.create_cat;

import use_case.cat_management.create_cat.CreateCatInputBoundary;
import use_case.cat_management.create_cat.CreateCatInputData;

public class CreateCatController {
    private final CreateCatInputBoundary createCatInteractor;

    public CreateCatController(CreateCatInputBoundary createCatInteractor) {
        this.createCatInteractor = createCatInteractor;
    }

    void execute(String catName, String ownerUsername) {
        final CreateCatInputData createCatInputData = new CreateCatInputData(catName, ownerUsername);
        createCatInteractor.execute(createCatInputData);
    }
}
