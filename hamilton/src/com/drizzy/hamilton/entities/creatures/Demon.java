package com.drizzy.hamilton.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.drizzy.hamilton.Handler;
import com.drizzy.hamilton.entities.Entity;
import com.drizzy.hamilton.gfx.Animation;
import com.drizzy.hamilton.gfx.Assets;
import com.drizzy.hamilton.tiles.Tile;

public class Demon extends Creature {

	private Animation standAnim,attackAnim;
	private boolean proximity = false;
	
	private long lastAttackTimer, attackCooldown = 1200, attackTimer = attackCooldown;
	
	public Demon(Handler handler, float x, float y) {
		super(handler, x, y, 4*Creature.DEFAULT_CREATURE_WIDTH, 4*Creature.DEFAULT_CREATURE_HEIGHT);
		
		standAnim = new Animation(400,Assets.demon_stand);
		attackAnim = new Animation(300,Assets.demon_impale);
		this.setHealth(100);
		
		
		bounds.x = 64;
		bounds.y = 64;
		bounds.width = 128;
		bounds.height = 140;

	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(proximity)
			return attackAnim.getCurrentFrame();
		else
			return standAnim.getCurrentFrame();
	}
	

	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		if(attackTimer < attackCooldown) {
			return;
		}
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 60;
		ar.width = arSize;
		ar.height = arSize;
		
		attackTimer = 0;
		
		ar.x = cb.x + cb.width/2 - arSize/2;
		ar.y = cb.y + cb.height;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(10);
				return;
			}
		}
		
	}
	
	private void isNear() {
		if(Math.abs(x + 2*Tile.TILEWIDTH - handler.getWorld().getEntityManager().getPlayer().getX()) <= 4*Tile.TILEWIDTH &&
				Math.abs(y- handler.getWorld().getEntityManager().getPlayer().getY()) <= 4*Tile.TILEHEIGHT) {
			proximity = true;
		}else {
			proximity = false;
		}
	}

	@Override
	public void tick() {
		isNear();
		standAnim.tick();
		attackAnim.tick();
		checkAttacks();
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),
				width, height, null);
	}

	@Override
	public void die() {
		
	}

}
