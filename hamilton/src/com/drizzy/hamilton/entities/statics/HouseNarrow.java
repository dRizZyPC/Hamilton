package com.drizzy.hamilton.entities.statics;

import java.awt.Graphics;

import com.drizzy.hamilton.Handler;
import com.drizzy.hamilton.gfx.Assets;
import com.drizzy.hamilton.tiles.Tile;

public class HouseNarrow extends StaticEntity {

	public HouseNarrow(Handler handler, float x, float y) {
		super(handler, x, y, 3*Tile.TILEWIDTH, 9*Tile.TILEHEIGHT);
		
		bounds.x = 0;
		bounds.y = (int)height/3;
		bounds.width = width;
		bounds.height = (int)height*2/3;
	}

	@Override
	public void tick() {

	}
	
	@Override
	public void die() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.houseNarrow,(int)(x - handler.getGameCamera().getxOffset()),
					(int)(y -handler.getGameCamera().getyOffset()),width,height,null);
	}
	
	
}
