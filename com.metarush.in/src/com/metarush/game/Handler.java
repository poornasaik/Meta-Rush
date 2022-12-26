package com.metarush.game;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Handler {
	
	public ArrayList<GameObject> playerObject = new ArrayList<GameObject>(5);
	public ArrayList<GameObject> enemyObject = new ArrayList<GameObject>(25);
	public ArrayList<GameObject> trailObject = new ArrayList<GameObject>(30);
	public ArrayList<GameObject> coinObject = new ArrayList<GameObject>();
	
	public void init() {
		playerObject = new ArrayList<GameObject>(5);
		enemyObject = new ArrayList<GameObject>(25);
		trailObject = new ArrayList<GameObject>(30);
		coinObject = new ArrayList<GameObject>();
		
	}
	public void tick() {
		for(int i=0;i<enemyObject.size();i++) {
			enemyObject.get(i).tick();
		}
		for(int i=0;i<playerObject.size();i++) {
			playerObject.get(i).tick();
		}	
		for(int i=0;i<trailObject.size();i++) {
			trailObject.get(i).tick();
		}
		for(int i=0;i<coinObject.size();i++) {
			coinObject.get(i).tick();
		}
		
	}
	
	public void render(Graphics g) {
		
		for(GameObject tempObject:enemyObject) {
			tempObject.render(g);
		}
		for(GameObject tempObject:playerObject) {
			tempObject.render(g);
		}
		for(GameObject tempObject:trailObject) {
			tempObject.render(g);
		}
		for(GameObject tempObject:coinObject) {
			tempObject.render(g);
		}
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void addObject(GameObject object) {
		switch(object.getID()) {
		case Player:{
			  this.playerObject.add(object);
			  break;			
		}
		case Trail:{
			this.trailObject.add(object);
			break;
		}
		case Money:{
			this.coinObject.add(object);
			break;
		}
		default:{
			this.enemyObject.add(object);
			break;
		}
		}
			
	}
	
	public void removeObject(GameObject object) {
		switch(object.getID()) {
		case Player:{
			  this.playerObject.remove(object);
			  break;	
		}
		case Trail:{
			this.trailObject.remove(object);
			break;
		}
		case Money:{
			this.coinObject.remove(object);
			break;
		}
		default:{
			this.enemyObject.remove(object);
			break;
		}
		}
	}

}
