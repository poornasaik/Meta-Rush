package com.metarush.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.metarush.game.Game.STATE;

public class KeyInput extends KeyAdapter {

	private boolean uP = false;
	private boolean dP = false;
	private boolean lP = false;
	private boolean rP = false;
	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		for (int i = 0; i < handler.playerObject.size(); i++) {
			GameObject tempObject = handler.playerObject.get(i);
			if (tempObject.getID() == ID.Player) {
				if (key == KeyEvent.VK_UP) {
					uP = true;
					tempObject.setVelY(-5);
				}

				if (key == KeyEvent.VK_DOWN) {
					dP = true;
					tempObject.setVelY(5);
				}

				if (key == KeyEvent.VK_LEFT) {
					lP = true;
					tempObject.setVelX(-5);
				}

				if (key == KeyEvent.VK_RIGHT) {
					rP = true;
					tempObject.setVelX(5);
				}
			}
			if (key == KeyEvent.VK_P||key ==KeyEvent.VK_ESCAPE) {
				Game.pause = !Game.pause;
			}
			if (Game.pause &&key == KeyEvent.VK_Q) {
				Game.setGameState(STATE.End);
				handler.init();
				AudioPlayer.stopMusic();
				AudioPlayer.playMusic("Menu",-1);
			}
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (handler.playerObject.size() == 0) {
			uP = false;
			dP = false;
			lP = false;
			rP = false;
		}
		for (int i = 0; i < handler.playerObject.size(); i++) {
			GameObject tempObject = handler.playerObject.get(i);
			if (tempObject.getID() == ID.Player) {
				if (key == KeyEvent.VK_UP) {
					uP = false;
					if (dP)
						tempObject.setVelY(5);
					else
						tempObject.setVelY(0);
				}

				if (key == KeyEvent.VK_DOWN) {
					dP = false;
					if (uP)
						tempObject.setVelY(-5);
					else
						tempObject.setVelY(0);
				}

				if (key == KeyEvent.VK_LEFT) {
					lP = false;
					if (rP)
						tempObject.setVelX(5);
					else
						tempObject.setVelX(0);
				}

				if (key == KeyEvent.VK_RIGHT) {
					rP = false;
					if (lP)
						tempObject.setVelX(-5);
					else
						tempObject.setVelX(0);
				}
			}
		}
	}
}
