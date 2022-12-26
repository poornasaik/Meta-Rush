package com.gametraining.logictests;

import java.io.Serializable;

public class playerScore implements Serializable{

	private static final long serialVersionUID = -6560603916049122185L;
	private int highScore =0;
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
