package app.factory.viewmodel;

import interface_adapter.create_inventory.InventoryViewModel;
import interface_adapter.get_cat_fact.GetCatFactViewModel;
import interface_adapter.setupsession.SetupSessionViewModel;
import interface_adapter.study_session.StudySessionViewModel;
import interface_adapter.timer.TimerViewModel;
import interface_adapter.music_control.MusicControlViewModel;

/**
 * Factory for creating session-related view models.
 */
public class SessionViewModelFactory {
    /**
     * Creates a new timer view model.
     *
     * @return the timer view model
     */
    public TimerViewModel createTimerViewModel() {
        return new TimerViewModel();
    }

    /**
     * Creates a new setup session view model.
     *
     * @return the setup session view model
     */
    public SetupSessionViewModel createSetupSessionViewModel() {
        return new SetupSessionViewModel();
    }

    /**
     * Creates a new inventory view model.
     *
     * @return the inventory view model
     */
    public InventoryViewModel createInventoryViewModel() {
        return new InventoryViewModel();
    }

    /**
     * Creates a new study session view model.
     *
     * @return the study session view model
     */
    public StudySessionViewModel createStudySessionViewModel() {
        return new StudySessionViewModel();
    }

    /**
     * Creates a new get cat fact view model.
     *
     * @return the get cat fact view model
     */
    public GetCatFactViewModel createGetCatFactViewModel() {
        return new GetCatFactViewModel();
    }

    /**
     * Creates a new music control view model.
     *
     * @return the music control view model
     */
    public MusicControlViewModel createMusicControlViewModel() {
        return new MusicControlViewModel();
    }
}
