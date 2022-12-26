package com.metarush.game;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

	private static Map<String, File> musicMap = new HashMap<String, File>();
	private static Map<String, File> soundMap = new HashMap<String, File>();
	private static Clip play;
	private static Clip button;
	private static String SoundFolder = "Sounds/";

	public static void load() {
		musicMap.put("Game", new File(SoundFolder+"Game.wav"));
		musicMap.put("Menu", new File(SoundFolder+"Menu.wav"));
		soundMap.put("Mouse", new File(SoundFolder+"mouse.wav"));
		soundMap.put("Start", new File(SoundFolder+"Start.wav"));
		soundMap.put("Timer", new File(SoundFolder+"time.wav"));

	}

	private static AudioInputStream loadAudio(File file) {

		try {
			return AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException | IOException e) {
			// e.printStackTrace();
			System.out.println("Error");
			return null;
		}

	}

	public static void playMusic(String sound, int count)

	{
		try {
			play = AudioSystem.getClip();
			play.open(loadAudio(musicMap.get(sound)));

			if (count > -1) {
				play.loop(count);
			} else {
				play.loop(Clip.LOOP_CONTINUOUSLY);
			}

		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}

	}

	public static void playSound(String sound, int count)

	{
		try {
			button = AudioSystem.getClip();
			button.open(loadAudio(soundMap.get(sound)));

			if (count > -1) {
				button.loop(count);
			} else {
				button.loop(Clip.LOOP_CONTINUOUSLY);
			}

		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}

	}

	public static void stopMusic()

	{
		if (play != null) {
			play.close();
			play.flush();
		}

	}

	public static void stopSound()

	{
		if (button != null) {
			button.close();
			button.flush();
		}

	}

}