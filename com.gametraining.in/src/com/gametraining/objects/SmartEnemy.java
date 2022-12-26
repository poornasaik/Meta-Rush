package com.gametraining.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import com.gametraining.in.GameObject;
import com.gametraining.in.Handler;
import com.gametraining.in.ID;
import com.gametraining.in.Trail;

public class SmartEnemy extends GameObject {
	Random r = new Random();
	int speed;
	private Handler handler;
	private GameObject huntPlayer;
	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		for(int i=0; i<handler.playerObject.size();i++) {
			if (handler.playerObject.get(i).getID()==ID.Player) {
				huntPlayer=handler.playerObject.get(i);
			}
		}

	}

	@Override
	public void tick() {
		x+=velx;
		y+=vely;
		float diffX= x- huntPlayer.getX();
		float diffY= y- huntPlayer.getY();
		float distance = (float) Math.sqrt((diffX*diffX+diffY*diffY));
		velx= (((-1/distance)*diffX)/10)*11;
		vely= (((-1/distance)*diffY)/10)*11;
		
		/*if(y<=0||y>=Game.HEIGHT-54) vely*=-1;
		if(x<=0||x>=Game.WIDTH-30) velx*=-1;*/
		handler.addObject(new Trail(x, y, ID.Trail, Color.GREEN, 16, 16, 0.04f, handler));
		Toolkit.getDefaultToolkit().sync();
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval((int)x, (int)y, 16, 16);
		Toolkit.getDefaultToolkit().sync();
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
