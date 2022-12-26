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
import com.metarush.game.Trail;

public class BasicEnemy extends GameObject {
	Random r = new Random();
	int speed;
	private Handler handler;
	public BasicEnemy(float x, float y, ID id, Handler handler) {
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
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.06f, handler));
		Toolkit.getDefaultToolkit().sync();
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval((int)x, (int)y, 16, 16);
		Toolkit.getDefaultToolkit().sync();
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 16, 16);
	}

}
