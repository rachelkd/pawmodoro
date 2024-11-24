package use_case.music_control;

import entity.MusicPlayer;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicControlInteractor implements MusicControlInputBoundary{
    private MusicPlayer musicPlayer;
    private final MusicControlOutputBoundary musicPresenter;
    private Clip audioClip;
    private static final String MUSIC_DIRECTORY = "src/main/resources/music/study.wav";

    public MusicControlInteractor(MusicControlOutputBoundary musicPresenter) {
        this.musicPlayer = new MusicPlayer();
        this.musicPresenter = musicPresenter;
        initializeAudio();
    }

    private void initializeAudio() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(MUSIC_DIRECTORY));
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            musicPresenter.prepareFailView("Error loading audio file: " + e.getMessage());
        }
    }

    @Override
    public void togglePlayback() {
        if (audioClip.isRunning()) {
            if (musicPlayer.isPlaying()) {
                audioClip.stop();
                musicPlayer.setPlaying(false);
            } else {
                audioClip.start();
                musicPlayer.setPlaying(true);
            }
            musicPresenter.prepareSuccessView(musicPlayer.isPlaying());
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

