package com.metarush.game;

import java.util.Random;

import com.metarush.objects.BasicEnemy;
import com.metarush.objects.BossEnemy;
import com.metarush.objects.FastEnemy;
import com.metarush.objects.SmartEnemy;
import com.metarush.objects.Utilities;

public class Spawner {

	private Handler handler;
	private HUD hud;
	static int levelAdder=1;
	private Random r = new Random();

	public Spawner(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;

	}

	public void tick() {
		if (HUD.HEALTH>0&&Utilities.getSeconds(hud.getInitTime())/30>levelAdder) {
			levelAdder++;
			if (hud.getLevel() <= 15) {
				hud.setLevel(hud.getLevel() + 1);
			}

			if (hud.getLevel() > 1 && hud.getLevel() < 4) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 54), ID.BasicEnemy,
						handler));
			}

			if (hud.getLevel() == 4) {

				handler.addObject(
						new FastEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 54), ID.FastEnemy, handler));
			}
			if (hud.getLevel() == 5) {

				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 54), ID.SmartEnemy,
						handler));
			}

			if (hud.getLevel() == 6) {

				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 54), ID.BasicEnemy,
						handler));
			}

			if (hud.getLevel() == 7) {

				handler.addObject(
						new FastEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 54), ID.FastEnemy, handler));
			}
			if (hud.getLevel() == 10) {
				handler.enemyObject.clear();
				handler.addObject(new BossEnemy(Game.WIDTH / 2 - 25, 0, ID.BossEnemy, handler));
			}

			if (hud.getLevel() == 13) {
				handler.enemyObject.clear();
				handler.addObject(new BossEnemy(Game.WIDTH / 2 - 75, 0, ID.BossEnemy, handler));
				handler.addObject(new BossEnemy(Game.WIDTH / 2 + 25, 0, ID.BossEnemy, handler));
			}

			if (hud.getLevel() == 15) {
				handler.enemyObject.clear();

				handler.addObject(new BossEnemy(Game.WIDTH / 2 - 75, 0, ID.BossEnemy, handler));
				handler.addObject(new BossEnemy(Game.WIDTH / 2 + 25, 0, ID.BossEnemy, handler));
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 54), ID.SmartEnemy,
						handler));
			}
		}

	}

}
