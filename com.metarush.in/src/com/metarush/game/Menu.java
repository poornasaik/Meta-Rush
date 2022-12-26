package com.metarush.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.metarush.game.Game.STATE;
import com.metarush.objects.BasicEnemy;
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
		if (Game.gameState == STATE.Menu) {
			// start Game
			
			if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 110, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				Game.gameState = STATE.Select;
				
			}
			// Navigate to help screen
			else if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 30, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				Game.gameState = STATE.Help;
			}
			// Quit the Game
			else if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 50, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				System.exit(0);
			}
		} else if (Game.gameState == STATE.Select) {
			// Normal Game
			if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 110, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				initTime = new java.util.Date().getTime();
				Game.gameState = STATE.Timer;
				
			}
			//  Hard Game
			else if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 30, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				initTime = new java.util.Date().getTime();
				Game.gameState = STATE.Timer;
			}
			// Difficult Game
			else if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 50, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				initTime = new java.util.Date().getTime();
				Game.gameState = STATE.Timer;
			}
		} 
		
		else if (Game.gameState == STATE.Help) {
			if (buttonPressed(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 80, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				Game.gameState = STATE.Menu;
			}
		}

		else if (Game.gameState == STATE.End) {
			if (buttonPressed(mx, my, 210, Game.HEIGHT / 2 + 80, 200, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				counter = 4;
				initTime = new java.util.Date().getTime();
				Game.gameState = STATE.Timer;

			}
			
			else if (buttonPressed(mx, my, 12, Game.HEIGHT / 2 + 80, 180, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				counter = 4;
				Game.gameState = STATE.Menu;
			}
			else if (buttonPressed(mx, my, 430, Game.HEIGHT / 2 + 80, 180, 60)) {
				AudioPlayer.stopSound();
				AudioPlayer.playSound("Mouse", 0);
				counter = 4;
				Game.gameState = STATE.Menu;
			}
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
		if (Game.gameState == STATE.Timer) {
			long currentTime = new java.util.Date().getTime();
			if ((currentTime - initTime) / 500 > 1 && counter > 0) {
				counter--;
				initTime = currentTime;
			}
		}

	}

	public void render(Graphics g) {
		if (Game.gameState == STATE.Menu) {
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

		} 
		else if (Game.gameState == STATE.Select) {
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

		}
		
		else if (Game.gameState == STATE.Timer) {
			if (counter == 0) {
				Game.gameState = STATE.Game;
				hud.init();
				Spawner.scoreKeep = 0;
				AudioPlayer.stopMusic();
				AudioPlayer.playMusic("Game",-1);
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.addObject(new BasicEnemy(50, 50, ID.BasicEnemy, handler));

			} else {
				Font fnt = new Font("arial", 1, 50);
				handler.enemyObject.clear();
				MenuParticles=10;
				g.setColor(Color.WHITE);
				g.setFont(fnt);
				if (counter != 1) {
					g.drawString("" + (counter - 1), Game.WIDTH / 2 - 20, Game.HEIGHT / 2);}
				else {
					g.drawString("Start", Game.WIDTH / 2 - 55, Game.HEIGHT / 2);
				}

			}

		} else if (Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 20);
			g.setColor(Color.WHITE);
			g.setFont(fnt);
			g.drawString("Help", Game.WIDTH / 2 - 65, 60);

			g.setFont(fnt2);
			g.setColor(Color.GRAY);
			//g.fillRect(2, 75, Game.WIDTH - 20, 200);
			g.setColor(Color.black);
			//g.drawRect(1, 74, Game.WIDTH - 19, 201);
			//g.drawRect(2, 73, Game.WIDTH - 19, 201);
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

		} else if (Game.gameState == STATE.End) {			
			Font myFont = new Font("Courier", Font.BOLD, 20);
			g.setFont(myFont);
			g.setColor(Color.black);
			g.drawString("Game Over", Game.WIDTH / 2 - 65, Game.HEIGHT / 2 - 50);
			g.drawString("Your Score:" + hud.getScore(), Game.WIDTH / 2 - 90, Game.HEIGHT / 2);
			g.drawString("High Score:" + hud.getHighScore(), Game.WIDTH / 2 - 90, Game.HEIGHT / 2 + 30);
			g.setColor(Color.white);
			//Try Again
			g.drawRoundRect(210, Game.HEIGHT / 2 + 80, 200, 60,20,20);
			g.drawRoundRect(211, Game.HEIGHT / 2 + 81, 198, 58,20,20);
			
			//Menu
			g.drawRoundRect(12, Game.HEIGHT / 2 + 80, 180, 60,20,20);
			g.drawRoundRect(13, Game.HEIGHT / 2 + 81, 178, 58,20,20);
			//Shop
			g.drawRoundRect(430, Game.HEIGHT / 2 + 80, 180, 60,20,20);
			g.drawRoundRect(431, Game.HEIGHT / 2 + 81, 178, 58,20,20);
			g.drawString("Try Again", 255, Game.HEIGHT / 2 + 115);
			g.drawString("Menu", 75, Game.HEIGHT / 2 + 115);
			g.drawString("Shop", 498, Game.HEIGHT / 2 + 115);
			while (MenuParticles > 0) {
				handler.addObject(new MenuParticles(r.nextInt(Game.WIDTH - 55), r.nextInt(Game.HEIGHT - 55),
						ID.MenuParticles, handler));
				MenuParticles--;
			}
		}
	}
}
