package interface_adapter.change_cat_happiness;

import entity.Cat;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInputBoundary;
import use_case.cat_management.change_cat_happiness.ChangeCatHappinessInputData;

/**
 * Controller for the Change Cat Happiness Use Case.
 */
public class ChangeCatHappinessController {
    private final ChangeCatHappinessInputBoundary changeCatHappinessUseCaseInteractor;

    public ChangeCatHappinessController(ChangeCatHappinessInputBoundary changeCatHappinessUseCaseInteractor) {
        this.changeCatHappinessUseCaseInteractor = changeCatHappinessUseCaseInteractor;
    }

    /**
     * Executes the Change Cat Happiness Use Case.
     * @param catName name of the cat
     * @param ownerUsername username of cat owner
     * @param completedStudySession boolean indicated if the study session was completed
     * @param studySessionLength the length of the study session
     */
    public void execute(String catName, String ownerUsername,
            boolean completedStudySession, int studySessionLength) {

        final ChangeCatHappinessInputData changeCatHappinessUseInputData =
                new ChangeCatHappinessInputData(catName, ownerUsername, completedStudySession, studySessionLength);
        changeCatHappinessUseCaseInteractor.execute(changeCatHappinessUseInputData);
    }
}
