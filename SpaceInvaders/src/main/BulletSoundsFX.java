package main;
import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BulletSoundsFX{
	Clip clip;
	AudioInputStream stream;
	URL soundURL = getClass().getResource("/res/laser.wav");
	 public BulletSoundsFX(){
		 try {
			 stream = AudioSystem.getAudioInputStream(soundURL);
			 clip = AudioSystem.getClip();
			 clip.open(stream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("clip error");
		}
	 }
	 
	 public void startPlaying(int numLoops){
		 clip.setLoopPoints(0,23715);
		 clip.loop(1);
	 }
	 
	 public void stopPlaying(){
		 clip.stop();
	 }
}
