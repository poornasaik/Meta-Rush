package com.gametraining.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

import com.gametraining.in.Game;
import com.gametraining.in.GameObject;
import com.gametraining.in.Handler;
import com.gametraining.in.ID;
import com.gametraining.in.Trail;

public class FastEnemy extends GameObject {
	int speed;
	private Handler handler;
	public FastEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velx=2;
		vely=9;
		this.handler = handler;
	}

	@Override
	public void tick() {
		x+=velx;
		y+=vely;
		if(y<=0||y>=Game.HEIGHT-54) vely*=-1;
		if(x<=0||x>=Game.WIDTH-30) velx*=-1;
		handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 20, 20, 0.05f, handler));
		Toolkit.getDefaultToolkit().sync();
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillOval((int)x, (int)y, 20, 20);
		Toolkit.getDefaultToolkit().sync();
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 20, 20);
	}

}
