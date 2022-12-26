package com.gametraining.in;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 7820846447883914036L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private Spawner spawner;
	private Menu menu;
	private ProgressSave ps;
	
	public enum STATE {
		Menu, Game, Timer, Help, End, Select;
	}

	public static STATE gameState = STATE.Menu;
	public static boolean pause = false;

	public Game() {
		AudioPlayer.load();
		AudioPlayer.playMusic("Menu",-1);
		handler = new Handler();
		hud = new HUD(handler);
		this.addKeyListener(new KeyInput(handler));
		menu= new Menu(handler, hud);
		this.addMouseListener(menu);
		ps = loadData();
		
		if(ps==null) {
			ps = new ProgressSave();
		} else {
			hud.setHighScore(ps.getHighScore());
		}
		spawner = new Spawner(handler, hud);
		new Window(WIDTH, HEIGHT, "META RUSH", this);

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;

	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void run() {
		this.setFocusable(true);
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		/*
		 * long timer = System.currentTimeMillis(); int frames = 0;
		 */
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			//frames++;

			/*
			 * if (System.currentTimeMillis() - timer > 1000) { timer += 1000;
			 * System.out.println("FPS: " + frames); frames = 0; }
			 */
		}
		stop();

	}

	private void tick() {
		
		if (gameState == STATE.Game) {
			if(!pause) {
			handler.tick();
			hud.tick();
			spawner.tick();}
		} else if(gameState == STATE.Menu||gameState == STATE.Timer||gameState==STATE.Help||gameState==STATE.End||gameState==STATE.Select) {
			handler.tick();
			menu.tick();
		}
		if (gameState==STATE.End) {
			ps.setHighScore(hud.getHighScore());
			saveData(ps);
		}
		Toolkit.getDefaultToolkit().sync();

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (gameState == STATE.Game) {
			
			handler.render(g);
			hud.render(g);
			if(pause) {
				Font myFont = new Font("Courier", Font.BOLD, 20);
				g.setFont(myFont);
				g.setColor(Color.gray);
				g.fillRect(Game.WIDTH / 2-55, Game.HEIGHT / 2-40, 80, 28);
				g.setColor(Color.white);
				g.drawRect(Game.WIDTH / 2-55, Game.HEIGHT / 2-40, 80, 28);
				g.drawRect(Game.WIDTH / 2-56, Game.HEIGHT / 2-41, 82, 30);
				g.drawRect(Game.WIDTH / 2-57, Game.HEIGHT / 2-42, 84, 32);
				g.drawString("Paused", Game.WIDTH / 2-50, Game.HEIGHT / 2-20);
			}
		}else if(gameState == STATE.Menu||gameState == STATE.Timer||gameState==STATE.Help||gameState==STATE.End||gameState==STATE.Select) {
			handler.render(g);
			menu.render(g);
		}
		g.dispose();
		bs.show();
		Toolkit.getDefaultToolkit().sync();
	}

	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public static void main(String[] args) {
		new Game();
	}
	
public static void saveData(ProgressSave ps) {	
		try(FileOutputStream fs = new FileOutputStream("gs.raw")){
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(ps);
			os.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ProgressSave loadData() {
		ProgressSave ps=null;
		try(FileInputStream fi = new FileInputStream("gs.raw")){
			ObjectInputStream oi = new ObjectInputStream(fi);
			ps= (ProgressSave)oi.readObject();
		} catch (FileNotFoundException e1) {
			System.out.println("Previous Game Data is not available");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ps;
	}

}
