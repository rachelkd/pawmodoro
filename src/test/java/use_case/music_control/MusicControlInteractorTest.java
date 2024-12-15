package use_case.music_control;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class MusicControlInteractorTest {
    
    @Test
    void successPlaybackToggleTest() {
        final MusicControlOutputBoundary successPresenter = new MusicControlOutputBoundary() {
            @Override
            public void prepareSuccessView(boolean isPlaying) {
                assertTrue(isPlaying);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        final MusicControlInteractor interactor = new MusicControlInteractor(successPresenter);
        interactor.togglePlayback();
    }

    @Test
    void successPauseToggleTest() {
        final MusicControlOutputBoundary successPresenter = new MusicControlOutputBoundary() {
            private boolean firstCall = true;

            @Override
            public void prepareSuccessView(boolean isPlaying) {
                if (firstCall) {
                    assertTrue(isPlaying);
                    firstCall = false;
                } else {
                    assertFalse(isPlaying);
                }
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        final MusicControlInteractor interactor = new MusicControlInteractor(successPresenter);
        // First toggle playback to play
        interactor.togglePlayback();
        // Second toggle playback to pause
        interactor.togglePlayback();
    }
}
