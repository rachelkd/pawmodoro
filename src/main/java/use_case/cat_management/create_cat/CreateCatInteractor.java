package use_case.cat_management.create_cat;

import constants.Constants;
import entity.Cat;
import entity.CatFactory;
import use_case.cat.CatDataAccessInterface;

/**
 * Create Cat Interactor.
 */
public class CreateCatInteractor implements CreateCatInputBoundary {
    private final CatDataAccessInterface catDataAccessObject;
    private final CreateCatOutputBoundary createCatPresenter;
    private final CatFactory catFactory;

    public CreateCatInteractor(CatDataAccessInterface catDataAccessObject,
                               CreateCatOutputBoundary createCatPresenter, CatFactory catFactory) {
        this.catDataAccessObject = catDataAccessObject;
        this.createCatPresenter = createCatPresenter;
        this.catFactory = catFactory;
    }

    @Override
    public void execute(CreateCatInputData createCatInputData) {
        boolean isSuccess = false;
        // call this use case to create all of user's cats when they sign in

        // if user has the maximum number of cats
        if (catDataAccessObject.getCatsByOwner(createCatInputData.getOwnerUsername()).size()
                >= Constants.MAX_AMOUNT_OF_CATS) {
            createCatPresenter.prepareFailView("Reached maximum amount of cats :(");
        }
        // if user has cat with that name
        else if (catDataAccessObject.existsByNameAndOwner(createCatInputData.getCatName(),
                createCatInputData.getOwnerUsername())
                && catDataAccessObject.getCatByNameAndOwner(createCatInputData.getCatName(),
                createCatInputData.getOwnerUsername()).isCatObjectCreated()) {

            createCatPresenter.prepareFailView("You already have cat with this name >:(");
        }
        else {
            final Cat cat = catFactory.create(createCatInputData.getCatName(), createCatInputData.getOwnerUsername());
            cat.setCatObjectCreated(true);
            catDataAccessObject.saveCat(cat);

            isSuccess = catDataAccessObject.existsByNameAndOwner(createCatInputData.getCatName(),
                    createCatInputData.getOwnerUsername());

            final CreateCatOutputData createCatOutputData =
                    new CreateCatOutputData(cat.getOwnerUsername(), cat.getName(),
                            catDataAccessObject.getCatByNameAndOwner(cat.getName(), cat.getOwnerUsername()), isSuccess);
            createCatPresenter.prepareSuccessView(createCatOutputData);
        }
    }
}
