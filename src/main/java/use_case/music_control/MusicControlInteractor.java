package use_case.music_control;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import entity.MusicPlayer;

/**
 * The Interactor for the music_control usecase.
 */
public class MusicControlInteractor implements MusicControlInputBoundary {
    private static final String MUSIC_DIRECTORY = "src/main/resources/sounds/study.wav";
    private final MusicPlayer musicPlayer;
    private final MusicControlOutputBoundary musicPresenter;
    private Clip audioClip;

    public MusicControlInteractor(MusicControlOutputBoundary musicPresenter) {
        this.musicPlayer = new MusicPlayer();
        this.musicPresenter = musicPresenter;
        initializeAudio();
    }

    private void initializeAudio() {
        try {
            final File audioFile = new File(MUSIC_DIRECTORY);
            if (!audioFile.exists()) {
                musicPresenter.prepareFailView("Audio file not found at: " + MUSIC_DIRECTORY);
            }

            // Keep reference to audioStream
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

            // Initialize with stopped state. Don't start looping until play is pressed
            audioClip.stop();
            musicPlayer.setPlaying(false);

        }
        catch (UnsupportedAudioFileException evt) {
            musicPresenter.prepareFailView("Unsupported audio format: " + evt.getMessage());
        }
        catch (IOException evt) {
            musicPresenter.prepareFailView("Error reading audio file: " + evt.getMessage());
        }
        catch (LineUnavailableException evt) {
            musicPresenter.prepareFailView("Audio line unavailable: " + evt.getMessage());
        }
    }

    @Override
    public void togglePlayback() {
        if (audioClip == null) {
            musicPresenter.prepareFailView("Audio system not properly initialized");
        }
        
        try {
            if (audioClip.isRunning()) {
                audioClip.stop();
                musicPlayer.setPlaying(false);
            }
            else {
                audioClip.setFramePosition(0);
                audioClip.loop(Clip.LOOP_CONTINUOUSLY);
                audioClip.start();
                musicPlayer.setPlaying(true);
                audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            musicPresenter.prepareSuccessView(musicPlayer.isPlaying());
        }
        catch (NumberFormatException evt) {
            musicPresenter.prepareFailView("Error toggling playback: " + evt.getMessage());

            // Try to reinitialize audio if there's an error
            initializeAudio();
        }
    }
}

