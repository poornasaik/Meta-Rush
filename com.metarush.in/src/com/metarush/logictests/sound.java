package com.metarush.logictests;

public class sound {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		GameSoundTest.load();
		GameSoundTest.playMusic("Game", -1);
		Thread.sleep(5000);
		GameSoundTest.stopMusic();
	
		
		GameSoundTest.playMusic("Game", -1); 
		System.out.println("start again");
		Thread.sleep(5000);
		System.out.println("playing again"); GameSoundTest.playMusic("Game", -1);
		 
		Thread.sleep(50000);
		System.out.println("Done");
		

	}

}
