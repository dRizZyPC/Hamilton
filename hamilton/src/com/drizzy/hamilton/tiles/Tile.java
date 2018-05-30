package com.drizzy.hamilton.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.drizzy.hamilton.gfx.Assets;

public class Tile {
	
	public static Tile[] tiles = new Tile[256];
	public static Tile[] specificTile;
	protected BufferedImage texture;
	//____//
	public boolean solid =false;
	public static final int TILEWIDTH =64,
							TILEHEIGHT = 64;
	
	
	protected final int id;

	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public static void init() {
		specificTile = new SpecificTile[57];
		
		for(int i=0;i<57;i++) {
			specificTile[i] = new SpecificTile(Assets.tile[i],i+1);
		}
		specificTile[0].solid = true;
		specificTile[2].solid = true;
		specificTile[3].solid = true;
		specificTile[4].solid = true;
		specificTile[5].solid = true;
		specificTile[6].solid = true;
		specificTile[7].solid = true;
		specificTile[8].solid = true;
		specificTile[18].solid = true;
		specificTile[23].solid = true;
		specificTile[46].solid = true;
		specificTile[47].solid = true;
		specificTile[48].solid = true;
		specificTile[49].solid = true;
		specificTile[50].solid = true;
		specificTile[52].solid = true;
		specificTile[53].solid = true;
		specificTile[54].solid = true;
		specificTile[55].solid = true;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g,int x,int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public int getId() {
		return id;
	}
}
