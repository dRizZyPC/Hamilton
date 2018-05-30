package com.drizzy.hamilton.entities.statics;

import java.awt.Graphics;

import com.drizzy.hamilton.Handler;
import com.drizzy.hamilton.gfx.Assets;
import com.drizzy.hamilton.tiles.Tile;

public class TreeBunch extends StaticEntity {
	
	public TreeBunch(Handler handler, float x, float y) {
		super(handler, x, y, 4*Tile.TILEWIDTH, 7*Tile.TILEHEIGHT);
		
		bounds.x = 12;
		bounds.y = (int)(height - height/6);
		bounds.width = width - 12;
		bounds.height =31;
	}

	@Override
	public void tick() {

	}
	
	@Override
	public void die() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.treeBunch,(int)(x - handler.getGameCamera().getxOffset()),
					(int)(y -handler.getGameCamera().getyOffset()),width,height,null);
	}
	

}
