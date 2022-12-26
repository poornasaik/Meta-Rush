package com.metarush.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Arc2D;
import java.util.Random;

import com.metarush.game.Game;
import com.metarush.game.GameObject;
import com.metarush.game.HUD;
import com.metarush.game.Handler;
import com.metarush.game.ID;

public class Coins extends GameObject {
	Random r = new Random();
	int speed;
	private Handler handler;
	int diameter = 25, decrement = 0;
	private boolean reverse = true;
	Color color = Color.yellow;
	private HUD hud;

	public Coins(float x, float y, ID id, Handler handler ,HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud= hud;
	}

	public Coins(float x, float y, ID id, int size, Color color, Handler handler,HUD hud) {
		super(x, y, id);
		this.diameter = size;
		this.color = color;
		this.handler = handler;
		this.hud = hud;
	}

	@Override
	public void tick() {
		animationCounter++;
		if(decrement>diameter) {
			reverse = false;
		}else if (decrement<= diameter/2-3) {
			reverse = true;
		}
		if (animationCounter > 5 ) {
			if(reverse)
				decrement++;
			else {
				decrement--;
			}
			animationCounter =0;
		}
		
		if(collision()) {
			handler.removeObject(this);
			hud.setCoins(hud.getCoins()+1);
		}		

	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, diameter-decrement, diameter);
		g.setColor(Color.black);
		g.drawArc((int) x, (int) y, diameter-decrement, diameter, 0, 359);
		g.drawArc((int) x+2, (int) y+2, diameter-decrement-4, diameter-4, 0, 359);
		/*
		 * g.setColor(Color.black); g.fillOval((int) x+3, (int) y+3, diameter-6,
		 * diameter-6); g.setColor(color); g.fillOval((int) x+6, (int) y+6, diameter-12,
		 * diameter-12);
		 */
		Toolkit.getDefaultToolkit().sync();
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, diameter, diameter - decrement);
	}
	
	private boolean collision() {
		for (int i = 0; i < handler.playerObject.size(); i++) {
			GameObject tempObject = handler.playerObject.get(i);
			boolean intersect = getBounds().intersects(tempObject.getBounds());
			if (intersect) {
				return true;
			}

		}
		return false;

	}

	@Override
	public float getDamage() {
		return 7.5f;
	}

}
