package com.metarush.objects;

public class Utilities {

	public synchronized static boolean isInTime(long initTime,float waitTime) {
		return (System.currentTimeMillis()-initTime)/(double)1000>waitTime;
	}
	
	public synchronized static double getSeconds(long initTime) {
		return (System.currentTimeMillis()-initTime)/(double)1000;
	}
}
