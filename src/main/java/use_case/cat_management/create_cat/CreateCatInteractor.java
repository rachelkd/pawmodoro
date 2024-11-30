package use_case.cat_management.create_cat;

import java.util.Random;

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
    private final Random random = new Random();
    private String[] catArray = {"cat-1.png", "cat-2.png", "cat-3.png", "cat-4.png", "cat-5.png"};

    public CreateCatInteractor(CatDataAccessInterface catDataAccessObject,
                               CreateCatOutputBoundary createCatPresenter) {
        this.catDataAccessObject = catDataAccessObject;
        this.createCatPresenter = createCatPresenter;
    }

    @Override
    public void execute(CreateCatInputData createCatInputData) {
        boolean isSuccess = false;

        // if user has the maximum number of cats
        if (catDataAccessObject.getNumberOfCatsByOwner(createCatInputData.getOwnerUsername())
                >= Constants.MAX_AMOUNT_OF_CATS) {
            createCatPresenter.prepareFailView("You cannot adopt right now! You have reached maximum " +
                    "amount of cats :(");
        }
        // if user has cat with that name
        else if (catDataAccessObject.existsByNameAndOwner(createCatInputData.getCatName(),
                createCatInputData.getOwnerUsername())) {
            createCatPresenter.prepareFailView("You already have cat with this name >:(");
        }
        else {
            final int randomInt = random.nextInt(catArray.length);

            final CatFactory catFactory = new CatFactory();
            final Cat cat = catFactory.create(createCatInputData.getCatName(), createCatInputData.getOwnerUsername(),
                    100, 100, catArray[randomInt]);

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
