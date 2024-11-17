package use_case.cat_management.change_cat_happiness;

import entity.Cat;

/**
 * Input Data for Change Cat Happiness Usecase.
 */
public class ChangeCatHappinessInputData {
    private final String ownerId;
    private final String catName;
    private final Cat cat;
    private final boolean completedStudySession;
    private final int studySessionLength;

    public ChangeCatHappinessInputData(String ownerId, String catName, Cat cat,
                                       boolean completedStudySession, int studySessionLength) {
        this.ownerId = ownerId;
        this.catName = catName;
        this.cat = cat;
        this.completedStudySession = completedStudySession;
        this.studySessionLength = studySessionLength;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getCatName() {
        return catName;
    }

    public Cat getCat() { return cat; }

    public boolean isCompletedStudySession() {
        return completedStudySession;
    }

    public int getStudySessionLength() {
        return studySessionLength;
    }
}
