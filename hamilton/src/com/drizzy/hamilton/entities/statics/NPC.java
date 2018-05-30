package com.drizzy.hamilton.entities.statics;

import java.awt.Graphics;

import com.drizzy.hamilton.Handler;
import com.drizzy.hamilton.entities.creatures.Creature;
import com.drizzy.hamilton.gfx.Assets;
import com.drizzy.hamilton.tiles.Tile;

public class NPC extends StaticEntity {

	private static boolean flag = false;
	
	public NPC(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		

		bounds.x = 20;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
	}

	public static boolean enable() {
		if(Chest.chests == 4 && flag )
			return true;
		else
			return false;
	}
	
	@Override
	public void tick() {
		if(Math.abs(x - handler.getWorld().getEntityManager().getPlayer().getX()) >= 2*Tile.TILEWIDTH
				&& Math.abs(y - handler.getWorld().getEntityManager().getPlayer().getY()) >= 2*Tile.TILEHEIGHT)
			flag = true;
		else
			flag = false;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.npc,(int)(x - handler.getGameCamera().getxOffset()),
				(int)(y -handler.getGameCamera().getyOffset()),width,height,null);
	}

	@Override
	public void die() {
		
	}

}
