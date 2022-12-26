package com.metarush.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import com.metarush.game.Game;
import com.metarush.game.GameObject;
import com.metarush.game.Handler;
import com.metarush.game.ID;

public class BasicEnemy extends GameObject {
	Random r = new Random();
	int speed;
	private Handler handler;
	int diameter = 16;
	Color color = Color.red;

	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);

		/*
		 * while(true) { speed = r.nextInt(6); if(speed>2) break;
		 * 
		 * }
		 */
		velx = 5;
		vely = 5;
		this.handler = handler;
	}
	public BasicEnemy(float x, float y, ID id,int size,Color color, Handler handler) {
		super(x, y, id);
		this.diameter = size;
		this.color = color;
		/*
		 * while(true) { speed = r.nextInt(6); if(speed>2) break;
		 * 
		 * }
		 */
		velx = 5;
		vely = 5;
		this.handler = handler;
	}

	@Override
	public void tick() {
		if (animation == true && (System.currentTimeMillis() - initTime) / (double) 1000 < animationTime) {	
			animationCounter++;
			handler.addObject(new EnemySpawnAnime(x, y, ID.Trail, color, diameter, diameter,animationCounter, handler));
		} else {
			animation = false;
			x += velx;
			y += vely;
			if (y <= 0 || y >= Game.HEIGHT - 54)
				vely *= -1;
			if (x <= 0 || x >= Game.WIDTH - 30)
				velx *= -1;
			handler.addObject(new Trail(x, y, ID.Trail, color, diameter, diameter, 0.06f, handler));
		}
		Toolkit.getDefaultToolkit().sync();

	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, diameter, diameter);
		Toolkit.getDefaultToolkit().sync();
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, diameter, diameter);
	}
	@Override
	public float getDamage() {
		return 7.5f;
	}

}
