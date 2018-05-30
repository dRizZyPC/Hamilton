package com.drizzy.hamilton.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.drizzy.hamilton.Handler;
import com.drizzy.hamilton.audio.Sound;

public abstract class Entity {


	public static final int DEFAULT_HEALTH = 50;
	
	protected Handler handler;
	protected float x,y;
	protected int width,height;
	
	protected int health;
	
	protected Rectangle  bounds;
	protected boolean active = true;
	
	public Entity(Handler handler,float x,float y,int width, int height) {
	
		this.handler = handler;
		this.x=x;
		this.y=y;
		this.width = width;
		this.height = height;
		health = DEFAULT_HEALTH;	
		bounds = new Rectangle(0,0,width,height);
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean checkEntityCollisions(float xOffset,float yOffset) {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset,yOffset)))
				return true;
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset,float yOffset) {
		return new Rectangle((int)(x+bounds.x+xOffset),(int)(y+bounds.y+yOffset),
							  bounds.width,bounds.height);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void die();
	
	public void hurt(int amt) {
		health -= amt;
		Sound.sound2.play();
		if(health <= 0) {
			active = false;
			die();
		}
	}
}
