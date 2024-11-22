package use_case.cat_management.change_cat_happiness;

import entity.Cat;

/**
 * Input Data for Change Cat Happiness Usecase.
 */
public class ChangeCatHappinessInputData {
    private final String catName;
    private final String ownerUsername;
    private final boolean completedStudySession;
    private final int studySessionLength;

    public ChangeCatHappinessInputData(String catName, String ownerUsername,
                                       boolean completedStudySession, int studySessionLength) {
        this.catName = catName;
        this.ownerUsername = ownerUsername;
        this.completedStudySession = completedStudySession;
        this.studySessionLength = studySessionLength;
    }

    public String getOwnerUsername() {

        return ownerUsername;
    }

    public String getCatName() {

        return catName;
    }

    public boolean isCompletedStudySession() {

        return completedStudySession;
    }

    public int getStudySessionLength() {

        return studySessionLength;
    }
}
