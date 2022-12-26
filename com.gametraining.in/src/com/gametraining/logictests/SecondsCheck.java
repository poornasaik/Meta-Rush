package com.gametraining.logictests;

import java.util.Date;

public class SecondsCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long initTime = new java.util.Date().getTime();
		System.out.println(initTime);
		int counter=0;
		while (true) {
			long currentTime =new java.util.Date().getTime();
			//System.out.println(currentTime-initTime);
			//System.out.println((currentTime-initTime));
			if((int)((currentTime-initTime)/200)==1) {
			System.out.println("It worked");
			
			counter++;
			initTime=currentTime;
			}
			if(counter==20) break;
		}
	}

}
