package com.metarush.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x, y;
	protected int diameter;
	protected ID id;
	protected float velx, vely;
	protected long initTime;
	protected boolean animation = true;
	protected int animationTime = 2, animationCounter = 0;
	protected boolean isCollidingPlayer = false;
	protected Color color;
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		initTime = System.currentTimeMillis();
	}

	public abstract void tick();

	public abstract void render(Graphics g);
	
	public abstract Rectangle getBounds();
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setID(ID id) {
		this.id = id;
	}

	public ID getID() {
		return id;
	}

	public void setVelX(int velx) {
		this.velx = velx;
	}

	public void setVelY(int vely) {
		this.vely = vely;
	}

	public float getVelX() {
		return velx;
	}

	public float getVelY() {
		return vely;
	}

	public abstract float getDamage();

	public boolean isPrevCollidedPlayer() {
		return isCollidingPlayer;
	}

	public void setCollidingPlayer(boolean isColliding) {
		this.isCollidingPlayer = isColliding;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
