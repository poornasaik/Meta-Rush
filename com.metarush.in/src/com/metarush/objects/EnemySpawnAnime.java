package com.metarush.objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

import com.metarush.game.GameObject;
import com.metarush.game.Handler;
import com.metarush.game.ID;

public class EnemySpawnAnime extends GameObject{
	
	private float alpha =0.8f, life = 0.07f;
	private Handler handler;
	private Color color;
	int width, height;
	public EnemySpawnAnime(float x, float y, ID id, Color color, int width, int height,int degree, Handler handler) {
		super(x, y, id);

		this.width=4;
		this.height=4;
		degree = (degree/12)*30;
		this.x = (float) ((x)-((width+this.width+10)/2)*Math.cos(Math.toRadians(degree)))+width/2-this.width/2;
		this.y = (float) ((y)-((height+this.height+10)/2)*Math.sin(Math.toRadians(degree)))+height/2-this.height/2;
		this.id = id;
		this.color=color;
		this.handler=handler;
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
	@Override
	public float getDamage() {
		return 0;
	}

}
