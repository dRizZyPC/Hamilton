package com.drizzy.hamilton.entities.statics;

import java.awt.Graphics;

import com.drizzy.hamilton.Handler;
import com.drizzy.hamilton.gfx.Assets;
import com.drizzy.hamilton.tiles.Tile;

public class Chest extends StaticEntity {

	public static int chests = 0;
	
	public Chest(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = width;
		bounds.height = height;
		this.setHealth(1);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.chest,(int)(x - handler.getGameCamera().getxOffset()),
				(int)(y -handler.getGameCamera().getyOffset()),width,height,null);
	}

	@Override
	public void die() {
		chests++;
	}

}
