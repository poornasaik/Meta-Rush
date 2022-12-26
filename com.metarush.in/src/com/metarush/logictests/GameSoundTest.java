package com.metarush.logictests;

import java.io.File;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;


import javax.sound.sampled.LineUnavailableException;

import javax.sound.sampled.UnsupportedAudioFileException;

public class GameSoundTest {

	private static Map<String, String> musicMap = new HashMap<String, String>();
	private static Map<String, String> soundMap = new HashMap<String, String>();
	private static Clip play;
	private static Clip button;

	public static void load() {
		musicMap.put("Game","Game.wav");
		musicMap.put("Menu","Menu.wav");
		soundMap.put("Mouse","mouse.wav");		
		
	}
	
	private static AudioInputStream loadAudio(String filelocation) {	
		
			try {
				
				return AudioSystem.getAudioInputStream(new File(filelocation));
			} catch (UnsupportedAudioFileException | IOException e) {
				//e.printStackTrace();
				System.out.println("Error");
				return null;
			}
				
	}
	
	
	
	
	
	public static void playMusic(String sound, int count)

	{
		try {
			play = AudioSystem.getClip();
			play.open(loadAudio(musicMap.get(sound)));
			
			if(count>-1) {
			play.loop(count);
			}
			else {
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
			button.open(loadAudio(musicMap.get(sound)));
			
			if(count>-1) {
				button.loop(count);
			}
			else {
				button.loop(Clip.LOOP_CONTINUOUSLY);
			}
			
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void stopMusic()

	{
		play.flush();
		play.close();
		

	}
	public static void stopSound()
	
	{
		button.flush();
		button.close();
		
		
	}

}