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

public class BossEnemy extends GameObject {
	Random r = new Random();
	long initTime = new java.util.Date().getTime();
	long initTime2 = new java.util.Date().getTime();
	private int timer = 20;
	private int timer2 = 30;
	private static int directionX = -1;
	int speed;
	private Handler handler;

	public BossEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);

		velx = 0;
		vely = 1;
		this.handler = handler;
	}

	public void tick() {
		x += velx;
		y += vely;
		if (timer < 0) {
			vely = 0;
			timer2--;
		} else
			timer--;
		if (timer2 < 0) {
			if (velx == 0)
				velx = 2 * directionX;
			directionX *= -1;
			if (velx > 0)
				velx += 0.01f;
			else if (velx < 0)
				velx -= 0.01f;

			velx = Game.clamp(velx, -3, 3);

			/* if(y<=0||y>=Game.HEIGHT-100) vely*=-1; */
			if (x <= 0 || x >= Game.WIDTH - 70)
				velx *= -1;
			int random;
			if (velx > 5.5f || velx < -5.5f)
				random = 12;
			else
				random = 16;
			int bulletSpawner = r.nextInt(random);
			long currentTime = new java.util.Date().getTime();
			if (((currentTime - initTime) / 200) > 1) {
				
				handler.addObject(new BossEnemyBullets(x + 22, y + 58, -2, 5, ID.BasicEnemy, handler));
				handler.addObject(new BossEnemyBullets(x + 22, y + 58, 0, 6, ID.BasicEnemy, handler));
				handler.addObject(new BossEnemyBullets(x + 22, y + 58, 2, 5, ID.BasicEnemy, handler));
				handler.addObject(new BossEnemyBullets(x + 22, y + 58, 4, 4, ID.BasicEnemy, handler));
				handler.addObject(new BossEnemyBullets(x + 22, y + 58, -4, 4, ID.BasicEnemy, handler));
				initTime = currentTime;
			}

			/*if ((int) ((currentTime - initTime2) / 1000) == 1) {
				handler.addObject(new BossEnemyBullets(x + 22, y + 58, -1, 5, ID.BasicEnemy, handler));
				handler.addObject(new BossEnemyBullets(x + 22, y + 58, 1, 5, ID.BasicEnemy, handler));
				initTime2=currentTime;
			}*/

		}
		// handler.addObject(new Trail(x, y, ID.Trail, Color.red, 50, 50, 0.06f,
		// handler));
		Toolkit.getDefaultToolkit().sync();

	}

	public void render(Graphics g) {
		g.setColor(new Color(255, 99, 71));
		g.fillRoundRect((int) x, (int) y, 50, 50, 10, 10);
		g.setColor(Color.RED);

		g.drawRect((int) x, (int) y, 50, 50);
		g.drawRect((int) x + 1, (int) y + 1, 48, 48);
		g.fillRect((int) x + 10, (int) y + 50, 30, 15);
		Toolkit.getDefaultToolkit().sync();
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 50, 50);

	}
	@Override
	public float getDamage() {
		return 30f;
	}

}
