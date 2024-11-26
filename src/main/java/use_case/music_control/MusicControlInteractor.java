package use_case.music_control;

import entity.MusicPlayer;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicControlInteractor implements MusicControlInputBoundary{
    private MusicPlayer musicPlayer;
    private final MusicControlOutputBoundary musicPresenter;
    private Clip audioClip;
    private static final String MUSIC_DIRECTORY = "src/main/resources/sounds/study.wav";

    public MusicControlInteractor(MusicControlOutputBoundary musicPresenter) {
        this.musicPlayer = new MusicPlayer();
        this.musicPresenter = musicPresenter;
        initializeAudio();
    }

    private void initializeAudio() {
        try {
            File audioFile = new File(MUSIC_DIRECTORY);
            if (!audioFile.exists()) {
                musicPresenter.prepareFailView("Audio file not found at: " + MUSIC_DIRECTORY);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);

            // Initialize with stopped state
            audioClip.stop();
            musicPlayer.setPlaying(false);

        } catch (UnsupportedAudioFileException e) {
            musicPresenter.prepareFailView("Unsupported audio format: " + e.getMessage());
        } catch (IOException e) {
            musicPresenter.prepareFailView("Error reading audio file: " + e.getMessage());
        } catch (LineUnavailableException e) {
            musicPresenter.prepareFailView("Audio line unavailable: " + e.getMessage());
        }
    }

    @Override
    public void togglePlayback() {
        if (audioClip == null) {
            musicPresenter.prepareFailView("Audio system not properly initialized");
            return;
        }
        
        try {
            if (audioClip.isRunning()) {
                audioClip.stop();
                musicPlayer.setPlaying(false);
            } else {
                audioClip.start();
                musicPlayer.setPlaying(true);
            }
            musicPresenter.prepareSuccessView(musicPlayer.isPlaying());
        } catch (Exception e) {
            musicPresenter.prepareFailView("Error toggling playback: " + e.getMessage());
        }
    }

    @Override
    public void setVolume(int volume) {
        if(audioClip != null) {
            FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume / 100.0) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
            musicPlayer.setVolume(volume);
        }
    }
}

