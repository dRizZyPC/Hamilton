package com.drizzy.hamilton.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.drizzy.hamilton.Handler;
import com.drizzy.hamilton.entities.Entity;
import com.drizzy.hamilton.entities.statics.NPC;
import com.drizzy.hamilton.gfx.Animation;
import com.drizzy.hamilton.gfx.Assets;

public class Player extends Creature {

	private int damage;
	//Animations
	private Animation animDown,animUp,animLeft,animRight;
	private Animation animADown,animAUp,animALeft,animARight;
	//Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	private int attackFlag = -1;
	
	public Player(Handler handler,float x, float y) {
		super(handler,x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		damage = 1;
		
		bounds.x = 20;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
		
		this.setHealth(60);
		
		//Animations
		animDown = new Animation(200, Assets.player_down);
		animUp = new Animation(200,Assets.player_up);
		animLeft = new Animation(200,Assets.player_left);
		animRight = new Animation(200,Assets.player_right);

		animADown = new Animation(150, Assets.player_aDown);
		animAUp = new Animation(150,Assets.player_aUp);
		animALeft = new Animation(150,Assets.player_aLeft);
		animARight = new Animation(150,Assets.player_aRight);
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		animADown.tick();
		animAUp.tick();
		animALeft.tick();
		animARight.tick();
		//__________//
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		//Attack
		if(NPC.enable())
			damage = 10;
		
		checkAttacks(damage);
	}
	
	private void checkAttacks(int damage) {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		if(attackTimer < attackCooldown) {
			return;
		}
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y - arSize;
			attackFlag = 1;
		}else if(handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y + cb.height;
			attackFlag = 2;
		}else if(handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height/2 - arSize/2;
			attackFlag = 3;
		}else if(handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height/2 - arSize/2;
			attackFlag = 4;
	    }else {
	    	attackFlag = -1;
	    	return;
	    }
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(damage);
				return;
			}
		}
		
	}
	@Override
	public void die() {
		System.out.println("Game Over");
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),
					width, height, null);
	}

	private BufferedImage getCurrentAnimationFrame() {
		if(NPC.enable()) {
			if(attackFlag == 1) {
				return animAUp.getCurrentFrame();
			}else if(attackFlag == 2) {
				return animADown.getCurrentFrame();
			}else if(attackFlag == 3) {
				return animALeft.getCurrentFrame();
			}else if(attackFlag == 4) {
				return animARight.getCurrentFrame();
			}
		}
		if(xMove < 0) {
			return animLeft.getCurrentFrame();
		}else if(xMove > 0) {
			return animRight.getCurrentFrame();
		}else if(yMove < 0) {
			return animUp.getCurrentFrame();
		}else {
			return animDown.getCurrentFrame();
		}
	}
}
