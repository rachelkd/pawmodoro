package use_case.cat_management.create_cat;

import constants.Constants;
import entity.Cat;
import entity.CatFactory;

/**
 * Create Cat Interactor
 */
public class CreateCatInteractor implements CreateCatInputBoundary{
    private final CreateCatDataAccessInterface createCatDataAccessObject;
    private final CreateCatOutputBoundary createCatPresenter;
    private final CatFactory catFactory;

    public CreateCatInteractor(CreateCatDataAccessInterface createCatDataAccessObject, CreateCatOutputBoundary createCatPresenter, CatFactory catFactory) {
        this.createCatDataAccessObject = createCatDataAccessObject;
        this.createCatPresenter = createCatPresenter;
        this.catFactory = catFactory;
    }

    @Override
    public void execute(CreateCatInputData createCatInputData) {

        // if cat name is the same as existing cat
        if (createCatDataAccessObject.existsByNameAndOwnerId(createCatInputData.getCatName(),
                createCatInputData.getOwnerId())) {
            createCatPresenter.prepareFailView("You already have cat with this name >:(");
        }
        // if user has the maximum number of cats
        if (createCatDataAccessObject.getCatsByOwnerId(createCatInputData.getOwnerId()).size() >=
                Constants.MAX_AMOUNT_OF_CATS) {
            createCatPresenter.prepareFailView("Reached maximum amount of cats :(");
        }

        final Cat cat = catFactory.create(createCatInputData.getOwnerId(), createCatInputData.getCatName());
        createCatDataAccessObject.save(cat);

        final CreateCatOutputData createCatOutputData = new CreateCatOutputData(cat.getOwnerId(), cat.getName());
        createCatPresenter.prepareSuccessView(createCatOutputData);

    }
}
