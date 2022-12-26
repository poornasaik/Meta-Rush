package com.gametraining.in;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gametraining.in.Game.STATE;

public class HUD {
	
	public static  int HEALTH =100;
	
	private int greenValue;
	private int redValue;
	
	private int score;
	private int level;
	private int HighScore;
	private Handler handler;
	
	public HUD(Handler handler) {
		init();
		this.handler=handler;
	}

	public void tick() {
		//HEALTH--;
		HEALTH = (int) Game.clamp(HEALTH, 0, 100);
		greenValue=(int) Game.clamp(greenValue, 0, 255);
		greenValue = (int) (HEALTH*2.55);
		if(greenValue<127) {
		redValue=255-greenValue;}
		if(HEALTH>0) {
		score++;}
		else{
			Game.gameState=STATE.End;
			handler.enemyObject.clear();
			handler.playerObject.clear();
			handler.trailObject.clear();
			AudioPlayer.stopMusic();
			AudioPlayer.playMusic("Menu",-1);
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
		g.fillRect(15, 10, HEALTH*2, 12);
		g.setColor(Color.WHITE); 
		g.drawRect(15, 10, 200, 12);
		g.setColor(Color.black); 
		g.drawString(HEALTH+" %", 100, 21);
		g.drawString("Score: "+score, 15, 50);
		g.drawString("High Score: "+HighScore, 15, 65);
		g.drawString("Level: "+level, 15, 35);
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
	
	public void init() {
		HEALTH =100;
		greenValue=255;
		redValue=0;	
		score =1;
		level =1;
	}

}
