package com.metarush.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import com.metarush.game.AudioPlayer;
import com.metarush.game.Game;
import com.metarush.game.GameObject;
import com.metarush.game.HUD;
import com.metarush.game.Handler;
import com.metarush.game.ID;
import com.metarush.game.Trail;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	private float xmin = 0, xmax = Game.WIDTH - 48, ymin = 0, ymax = Game.HEIGHT - 71;
	private Color color;
	private Color normalColor = Color.white;
	private Color touchColor = new Color(255, 69, 69);

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		/*
		 * velx=r.nextInt(5)+1; vely=r.nextInt(5);
		 */
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public void tick() {

		x += velx;
		y += vely;

		for (int i = 0; i < handler.enemyObject.size(); i++) {
			GameObject to = handler.enemyObject.get(i);
			if (to.getID() == ID.BossEnemy) {
				ymin = to.getY() + 65;
				break;
			} else
				ymin = 0;

		}

		x = Game.clamp(x, xmin, xmax);

		y = Game.clamp(y, ymin, ymax);
		if (collision()) {
			color = touchColor;
		} else
		{
			color = normalColor;
		}
		handler.addObject(new Trail(x, y, ID.Trail, color, 32, 32, 0.1f, handler));
		Toolkit.getDefaultToolkit().sync();
		/*
		 * if(x<-2||x>Game.WIDTH-47) { x-=velx; }
		 * 
		 * if(y<-4||y>Game.HEIGHT-70) { y-=vely; }
		 */

	}

	private boolean collision() {
		for (int i = 0; i < handler.enemyObject.size(); i++) {
			GameObject tempObject = handler.enemyObject.get(i);
			if (getBounds().intersects(tempObject.getBounds())) {
				HUD.HEALTH -= 100;
				return true;
			}

		}
		return false;

	}

	public void render(Graphics g) {

		g.setColor(color);
		g.fillRect((int) x, (int) y, 32, 32);
		g.setColor(Color.gray);
		g.fillRoundRect((int) x + 2, (int) y + 2, 28, 28, 3, 3);
		g.setColor(Color.LIGHT_GRAY);
		// g.fillOval((int)x + 2,(int) y+2, 28, 28);
		Toolkit.getDefaultToolkit().sync();
	}

}
