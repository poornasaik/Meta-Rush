package com.metarush.game;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.LinkedList;

public class Handler {
	
	public LinkedList<GameObject> playerObject = new LinkedList<GameObject>();
	public LinkedList<GameObject> enemyObject = new LinkedList<GameObject>();
	public LinkedList<GameObject> trailObject = new LinkedList<GameObject>();
	public void tick() {
		for(int i=0;i<playerObject.size();i++) {
			playerObject.get(i).tick();
		}
		
		for(int i=0;i<enemyObject.size();i++) {
			enemyObject.get(i).tick();
		}
		for(int i=0;i<trailObject.size();i++) {
			trailObject.get(i).tick();
		}
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void render(Graphics g) {
		for(GameObject tempObject:playerObject) {
			tempObject.render(g);
		}
		for(GameObject tempObject:enemyObject) {
			tempObject.render(g);
		}
		for(GameObject tempObject:trailObject) {
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		ID id = object.getID();
		if(id==ID.Player)
		     this.playerObject.add(object);
		if(id==ID.BasicEnemy||id==ID.FastEnemy||id==ID.SmartEnemy||id==ID.BossEnemy||id==ID.MenuParticles)
		     this.enemyObject.add(object);	
		if(id==ID.Trail)
		     this.trailObject.add(object);	
	}
	
	public void removeObject(GameObject object) {
		ID id = object.getID();
		if(id==ID.Player)
		     this.playerObject.remove(object);
		if(id==ID.BasicEnemy||id==ID.FastEnemy|id==ID.SmartEnemy||id==ID.BossEnemy)
		     this.enemyObject.remove(object);	
		if(id==ID.Trail) {
		     this.trailObject.remove(object);
		}	
	}

}
