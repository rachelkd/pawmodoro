package interface_adapter.change_cat_happiness;

import entity.Cat;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInputBoundary;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInputData;

public class ChangeCatHappinessController {
    private final ChangeCatHappinessInputBoundary changeCatHappinessUseCaseInteractor;

    public ChangeCatHappinessController(ChangeCatHappinessInputBoundary changeCatHappinessUseCaseInteractor) {
        this.changeCatHappinessUseCaseInteractor = changeCatHappinessUseCaseInteractor;
    }

    public void execute(String catName, String ownerUsername, Cat cat,
                        boolean completedStudySession, int studySessionLength) {

        final ChangeCatHappinessInputData changeCatHappinessUseInputData =
                new ChangeCatHappinessInputData(catName, ownerUsername, cat, completedStudySession, studySessionLength);
        changeCatHappinessUseCaseInteractor.execute(changeCatHappinessUseInputData);
    }


}
