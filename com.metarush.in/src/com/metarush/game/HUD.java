package com.metarush.game;

import java.awt.Color;
import java.awt.Graphics;

import com.metarush.game.Game.STATE;

public class HUD {
	
	public static float HEALTH =100;
	
	private int greenValue;
	private int redValue;
	
	private int score;
	private int level;
	private int HighScore, coins;
	private Handler handler;
	private long initTime,ScoreTime;
	public long getInitTime() {
		return initTime;
	}
	public HUD(Handler handler) {
		init();
		this.handler=handler;
	}

	public void tick() {
		//HEALTH--;
		HEALTH = Game.clamp(HEALTH, 0, 100);
		greenValue=(int) Game.clamp(greenValue, 0, 255);
		greenValue = (int) (HEALTH*2.55);
		if(greenValue<127) {
		redValue=255-greenValue;}
		if(ScoreTime==0&&(System.currentTimeMillis()-initTime)/(double)1000>3)
			ScoreTime = initTime;
		if(HEALTH<=0) {
			Game.setGameState(STATE.End);
			handler.init();
			AudioPlayer.stopMusic();
			AudioPlayer.playMusic("Menu",-1);
		}
		else{
			if(ScoreTime!=0&&((System.currentTimeMillis()-ScoreTime)/(double)1000)>0.75) {
				score+=1;
				ScoreTime = System.currentTimeMillis();
			}			
		}
		if(HighScore<score) {
			HighScore=score;
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(15, 10, 200, 12);
		if(greenValue>127)
		g.setColor(new Color(redValue, greenValue,0));
		else 
			g.setColor(new Color(redValue, 0,0));
		g.fillRect(15, 10, (int)HEALTH*2, 12);
		g.setColor(Color.WHITE); 
		g.drawRect(15, 10, 200, 12);
		g.setColor(Color.black); 
		g.drawString(HEALTH+" %", 100, 21);
		g.drawString("Score: "+score, 15, 50);
		g.drawString("High Score: "+HighScore, 15, 65);
		g.drawString("Level: "+level, 15, 35);
		g.drawString("coins collected: "+coins, 15, 80);
	}
	
	public void setScore(int score) {
		this.score=score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setLevel(int level) {
		this.level=level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getHighScore() {
		return HighScore;
	}
	public void setHighScore(int highScore) {
		HighScore = highScore;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public void init() {
		HEALTH =100;
		greenValue=255;
		redValue=0;	
		score =0;
		level =1;
		initTime = System.currentTimeMillis();
		ScoreTime = 0;
	}
}
