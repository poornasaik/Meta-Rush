package com.metarush.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.metarush.game.Game.STATE;
import com.metarush.objects.BasicEnemy;
import com.metarush.objects.Coins;
import com.metarush.objects.MenuParticles;
import com.metarush.objects.Player;

public class Menu extends MouseAdapter {
	Game game;
	Handler handler;
	long initTime;
	private int counter = 4;
	HUD hud;
	Random r = new Random();
	int MenuParticles = 10;

	public Menu(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		// For Play button click
		switch (Game.getGameState()) {
		case Menu: {
			// start Game
			if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 110, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				Game.setGameState(STATE.Select);
				return;

			}
			// Navigate to help screen
			else if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 30, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				Game.setGameState(STATE.Help);
				return;
			}
			// Quit the Game
			else if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 50, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				System.exit(0);
			}
			break;
		}
		case Select: {
			// Normal Game
			if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 110, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				initTime = new java.util.Date().getTime();
				Game.setGameState(STATE.Timer);
				handler.init();
				return;

			}
			// Hard Game
			else if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 30, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				initTime = new java.util.Date().getTime();
				Game.setGameState(STATE.Timer);
				return;
			}
			// Difficult Game
			else if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 50, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				initTime = new java.util.Date().getTime();
				Game.setGameState(STATE.Timer);
				return;
			}
			break;
		}
		case Help: {
			if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 80, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				Game.setGameState(STATE.Menu);
				return;
			}
			break;
		}
		case End: {
			if (buttonPressed(mx, my, 210, Game.HEIGHT / 2 + 80, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				counter = 4;
				initTime = new java.util.Date().getTime();
				Game.setGameState(STATE.Timer);
				return;
			}

			else if (buttonPressed(mx, my, 12, Game.HEIGHT / 2 + 80, 180, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				counter = 4;
				Game.setGameState(STATE.Menu);
				return;
			} else if (buttonPressed(mx, my, 430, Game.HEIGHT / 2 + 80, 180, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				counter = 4;
				Game.setGameState(STATE.Menu);
				return;
			}
			break;
		}
		default:
			break;

		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public boolean buttonPressed(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;

		} else
			return false;

	}

	public void tick() {
		if (Game.getGameState() == STATE.Timer) {
			long currentTime = new java.util.Date().getTime();
			if ((currentTime - initTime) / 500 > 1 && counter > 0) {
				counter--;
				initTime = currentTime;
			}
		}

	}

	public void render(Graphics g) {
		switch (Game.getGameState()) {

		case Menu: {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setColor(Color.WHITE);
			g.setFont(fnt);
			g.drawString("META RUSH", 170, 60);

			g.setFont(fnt2);
			g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 110, 200, 60);
			g.drawRect(Game.WIDTH / 2 - 99, Game.HEIGHT / 2 - 109, 198, 58);
			g.drawRect(Game.WIDTH / 2 - 98, Game.HEIGHT / 2 - 108, 196, 56);

			g.drawString("Play", Game.WIDTH / 2 - 30, Game.HEIGHT / 2 - 70);
			g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 30, 200, 60);
			g.drawRect(Game.WIDTH / 2 - 99, Game.HEIGHT / 2 - 29, 198, 58);
			g.drawRect(Game.WIDTH / 2 - 98, Game.HEIGHT / 2 - 28, 196, 56);
			g.drawString("Help", Game.WIDTH / 2 - 30, Game.HEIGHT / 2 + 10);
			g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 50, 200, 60);
			g.drawRect(Game.WIDTH / 2 - 99, Game.HEIGHT / 2 + 51, 198, 58);
			g.drawRect(Game.WIDTH / 2 - 98, Game.HEIGHT / 2 + 52, 196, 56);
			g.drawString("Quit", Game.WIDTH / 2 - 30, Game.HEIGHT / 2 + 90);
			while (MenuParticles > 0) {
				handler.addObject(new MenuParticles(r.nextInt(Game.WIDTH - 55), r.nextInt(Game.HEIGHT - 55),
						ID.MenuParticles, handler));
				MenuParticles--;
			}
			break;
		}
		case Select: {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setColor(Color.WHITE);
			g.setFont(fnt);
			g.drawString("META RUSH", 170, 60);

			g.setFont(fnt2);
			g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 110, 200, 60);
			g.drawRect(Game.WIDTH / 2 - 99, Game.HEIGHT / 2 - 109, 198, 58);
			g.drawRect(Game.WIDTH / 2 - 98, Game.HEIGHT / 2 - 108, 196, 56);

			g.drawString("Normal", Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 70);
			g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 30, 200, 60);
			g.drawRect(Game.WIDTH / 2 - 99, Game.HEIGHT / 2 - 29, 198, 58);
			g.drawRect(Game.WIDTH / 2 - 98, Game.HEIGHT / 2 - 28, 196, 56);
			g.drawString("Hard", Game.WIDTH / 2 - 35, Game.HEIGHT / 2 + 10);
			g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 50, 200, 60);
			g.drawRect(Game.WIDTH / 2 - 99, Game.HEIGHT / 2 + 51, 198, 58);
			g.drawRect(Game.WIDTH / 2 - 98, Game.HEIGHT / 2 + 52, 196, 56);
			g.drawString("Difficult", Game.WIDTH / 2 - 55, Game.HEIGHT / 2 + 90);
			while (MenuParticles > 0) {
				handler.addObject(new MenuParticles(r.nextInt(Game.WIDTH - 55), r.nextInt(Game.HEIGHT - 55),
						ID.MenuParticles, handler));
				MenuParticles--;
			}
			break;

		}
		case Timer: {
			if (counter == 0) {
				AudioPlayer.stopMusic();
				AudioPlayer.playMusic("Game", -1);
				Game.setGameState(STATE.Game);
				hud.init();
				Spawner.levelAdder = 1;
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 54), ID.BasicEnemy,
						handler));
				handler.addObject(new Coins(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 54), ID.Money,handler,hud));

			} else {
				Font fnt = new Font("arial", 1, 50);
				handler.enemyObject.clear();
				MenuParticles = 10;
				g.setColor(Color.WHITE);
				g.setFont(fnt);
				if (counter != 1) {
					g.drawString("" + (counter - 1), Game.WIDTH / 2 - 20, Game.HEIGHT / 2);
				} else {
					g.drawString("Start", Game.WIDTH / 2 - 55, Game.HEIGHT / 2);
				}

			}
			break;
		}
		case Help: {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 20);
			g.setColor(Color.WHITE);
			g.setFont(fnt);
			g.drawString("Help", Game.WIDTH / 2 - 65, 60);

			g.setFont(fnt2);
			g.setColor(Color.GRAY);
			// g.fillRect(2, 75, Game.WIDTH - 20, 200);
			g.setColor(Color.black);
			// g.drawRect(1, 74, Game.WIDTH - 19, 201);
			// g.drawRect(2, 73, Game.WIDTH - 19, 201);
			g.setColor(Color.WHITE);
			g.drawString("Use Arrow Keys to move the player", Game.WIDTH / 2 - 150, 100);
			g.drawString("Do Not Touch Anyone", Game.WIDTH / 2 - 150, 125);
			g.setColor(Color.black);
			g.drawString("Use " + '\u2191' + " to move Upwards", Game.WIDTH / 2 - 120, 170);
			g.drawString("Use " + '\u2193' + " to move Downwards", Game.WIDTH / 2 - 120, 200);
			g.drawString("Use " + '\u2192' + " to move to the Left", Game.WIDTH / 2 - 120, 230);
			g.drawString("Use " + '\u2190' + " to move to the Right", Game.WIDTH / 2 - 120, 260);
			g.drawString("Use " + "'P' to Pause the Game", Game.WIDTH / 2 - 120, 290);
			g.setColor(Color.WHITE);
			g.drawRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 80, 200, 60);
			g.drawRect(Game.WIDTH / 2 - 99, Game.HEIGHT / 2 + 81, 198, 58);
			g.drawString("Back", Game.WIDTH / 2 - 25, Game.HEIGHT / 2 + 115);
			break;
		}
		case End: {
			Font myFont = new Font("Courier", Font.BOLD, 20);
			g.setFont(myFont);
			g.setColor(Color.black);
			g.drawString("Game Over", Game.WIDTH / 2 - 65, Game.HEIGHT / 2 - 50);
			g.drawString("Your Score:" + hud.getScore(), Game.WIDTH / 2 - 90, Game.HEIGHT / 2);
			g.drawString("High Score:" + hud.getHighScore(), Game.WIDTH / 2 - 90, Game.HEIGHT / 2 + 30);
			g.setColor(Color.white);
			// Try Again
			g.drawRoundRect(210, Game.HEIGHT / 2 + 80, 200, 60, 20, 20);
			g.drawRoundRect(211, Game.HEIGHT / 2 + 81, 198, 58, 20, 20);

			// Menu
			g.drawRoundRect(12, Game.HEIGHT / 2 + 80, 180, 60, 20, 20);
			g.drawRoundRect(13, Game.HEIGHT / 2 + 81, 178, 58, 20, 20);
			// Shop
			g.drawRoundRect(430, Game.HEIGHT / 2 + 80, 180, 60, 20, 20);
			g.drawRoundRect(431, Game.HEIGHT / 2 + 81, 178, 58, 20, 20);
			g.drawString("Try Again", 255, Game.HEIGHT / 2 + 115);
			g.drawString("Menu", 75, Game.HEIGHT / 2 + 115);
			g.drawString("Shop", 498, Game.HEIGHT / 2 + 115);
			while (MenuParticles > 0) {
				handler.addObject(new MenuParticles(r.nextInt(Game.WIDTH - 55), r.nextInt(Game.HEIGHT - 55),
						ID.MenuParticles, handler));
				MenuParticles--;
			}
			break;
		}
		default:
			break;

		}
	}
}
