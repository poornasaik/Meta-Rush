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

public class MenuParticles extends GameObject {
	Random r = new Random();
	int speed;
	private Handler handler;
	private Color color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
	public MenuParticles(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		/*while(true) {
		speed = r.nextInt(6);
		if(speed>2) break;
		
		}*/
		velx=5;
		vely=5;
		this.handler = handler;
	}

	@Override
	public void tick() {
		x+=velx;
		y+=vely;
		if(y<=0||y>=Game.HEIGHT-54) vely*=-1;
		if(x<=0||x>=Game.WIDTH-30) velx*=-1;
		handler.addObject(new Trail(x, y, ID.Trail, color, 14, 14, 0.08f, handler));
		Toolkit.getDefaultToolkit().sync();
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval((int)x, (int)y, 14, 14);
		Toolkit.getDefaultToolkit().sync();
	}

	public Rectangle getBounds() {
		return null;
	}

	@Override
	public float getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
