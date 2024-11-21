/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Player;

import java.io.ByteArrayInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.FloatControl;
/**
 *
 * @author Makin Artavia
 */


public class SoundPlayer {

    private Clip clip;
    private final float volume = -10.0f;

    public SoundPlayer(String filePath) {
        try {

            InputStream audioSrc = getClass().getResourceAsStream(filePath);
            if (audioSrc == null) {
                throw new IOException("Archivo de sonido no encontrado: " + filePath);
            }
            
            byte[] audioData = audioSrc.readAllBytes(); 
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioData);


            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream)) {
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            }
            setVolume(volume);
        } catch (UnsupportedAudioFileException e) {
            System.err.println("El formato de archivo no es compatible: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("La línea de audio no está disponible: " + e.getMessage());
        }
    }

    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
        }
    }
    
    public void setVolume(float volume) {
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume); 
        }
    }
}
