package com.gametraining.in;

import java.util.Random;

import com.gametraining.objects.BasicEnemy;
import com.gametraining.objects.BossEnemy;
import com.gametraining.objects.FastEnemy;
import com.gametraining.objects.SmartEnemy;

public class Spawner {

	private Handler handler;
	private HUD hud;


	static int scoreKeep = 0;
	private Random r = new Random();

	public Spawner(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;

	}

	public void tick() {
		scoreKeep++;
		if (scoreKeep > 500&&HUD.HEALTH>0) {
			scoreKeep = 0;
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
