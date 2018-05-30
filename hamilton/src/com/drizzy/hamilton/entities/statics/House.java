package com.drizzy.hamilton.entities.statics;

import java.awt.Graphics;

import com.drizzy.hamilton.Handler;
import com.drizzy.hamilton.gfx.Assets;
import com.drizzy.hamilton.tiles.Tile;

public class House extends StaticEntity {
	
	public House(Handler handler, float x, float y) {
		super(handler, x, y, 6*Tile.TILEWIDTH, 8*Tile.TILEHEIGHT);
		
		bounds.x = 0;
		bounds.y = height/2 - Tile.TILEHEIGHT;
		bounds.width = width;
		bounds.height = height/2 +34;
	}

	@Override
	public void tick() {

	}

	@Override
	public void die() {
		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.house,(int)(x - handler.getGameCamera().getxOffset()),
					(int)(y -handler.getGameCamera().getyOffset()),width,height,null);
	}
	
}
