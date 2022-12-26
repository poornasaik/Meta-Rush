package com.metarush.game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Trail extends GameObject{
	
	private float alpha =0.9f, life;
	private Handler handler;
	private Color color;
	int width, height;
	public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);
		this.color=color;
		this.width=width;
		this.height=height;
		this.handler=handler;
		this.life=life;
	}

	public void tick() {
		alpha-=(life-0.01f);
		if(alpha<life)
		handler.removeObject(this);
		Toolkit.getDefaultToolkit().sync();
		
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillOval((int)x, (int)y, width, height);
		g2d.setComposite(makeTransparent(1));
		Toolkit.getDefaultToolkit().sync();
		
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		
		int type =AlphaComposite.SRC_OVER;
		
		return AlphaComposite.getInstance(type, alpha);
		
	}
	public Rectangle getBounds() {
		
		return null;
	}

}
