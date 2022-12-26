package com.gametraining.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import com.gametraining.in.Game;
import com.gametraining.in.GameObject;
import com.gametraining.in.Handler;
import com.gametraining.in.ID;
import com.gametraining.in.Trail;

public class BossEnemyBullets extends GameObject {
	Random r = new Random();
	int speed;
	private Handler handler;
	private Color color;
	public BossEnemyBullets(float x, float y, float velx, float vely, ID id, Handler handler) {
		super(x, y, id);
		
		/*while(true) {
		speed = r.nextInt(6);
		if(speed>2) break;
		
		}*/
		this.velx=velx;
		this.vely=vely;
		this.handler = handler;

		color = new Color(255,99,71);
	}

	public void tick() {
		x+=velx;
		y+=vely;
		if(y>=Game.HEIGHT||y<=0||x>=Game.WIDTH) handler.removeObject(this);
		//handler.addObject(new Trail(x, y, ID.Trail, color, 11, 11, 0.1f, handler));
		//Toolkit.getDefaultToolkit().sync();
		
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval((int)x, (int)y, 16, 16);
		//Toolkit.getDefaultToolkit().sync();
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 16, 16);
	}

}
