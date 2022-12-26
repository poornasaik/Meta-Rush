package com.gametraining.in;

import java.io.Serializable;

public class ProgressSave implements Serializable{
	private static final long serialVersionUID = 2291912011801329607L;	
	private int highScore=0;
	private int coins =0;
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
}
