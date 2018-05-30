package com.drizzy.hamilton.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 16, height = 16;
	
	public static BufferedImage tree,treeBunch,treeForrestTile;
	public static BufferedImage house,houseNarrow;
	public static BufferedImage npc;
	public static BufferedImage chest;
	public static BufferedImage [] tile;
	public static BufferedImage [] player_down,player_up,player_left,player_right;
	public static BufferedImage [] player_aDown,player_aUp,player_aLeft,player_aRight;
	public static BufferedImage [] btn_start;
	public static BufferedImage [] demon_impale,demon_stand;
	
	public static void init() {
		SpriteSheet pSheet = new SpriteSheet(ImageLoader.loadImage("/textures/hero.png"));
		SpriteSheet eSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tilest.png"));
		SpriteSheet ySheet = new SpriteSheet(ImageLoader.loadImage("/textures/yellowSheet.png"));
		SpriteSheet Sheet = new SpriteSheet(ImageLoader.loadImage("/textures/ezset.png"));
		SpriteSheet npcSheet = new SpriteSheet(ImageLoader.loadImage("/textures/npc.png"));
		SpriteSheet dSheet = new SpriteSheet(ImageLoader.loadImage("/textures/demonattack.png"));
		SpriteSheet wSheet = new SpriteSheet(ImageLoader.loadImage("/textures/demonwalk.png"));
		
		btn_start = new BufferedImage[2];
	
		btn_start[0] = ySheet.crop(0, 49, 190, 46);
		btn_start[1] = ySheet.crop(190, 45, 190, 47);
		
		demon_impale = new BufferedImage[4];
		demon_stand = new BufferedImage[2];
		
		player_down = new BufferedImage[4];
		player_up = new BufferedImage[4];
		player_left = new BufferedImage[4];
		player_right = new BufferedImage[4];
		
		player_aDown = new BufferedImage[2];
		player_aUp = new BufferedImage[2];
		player_aLeft = new BufferedImage[2];
		player_aRight = new BufferedImage[2];
		
		player_down[0] = pSheet.crop(0, 2*height, width, height);
		player_down[1] = pSheet.crop(width, 2*height, width, height);
		player_down[2] = pSheet.crop(2*width , 2*height, width, height);
		player_down[3] = pSheet.crop(width, 2*height, width, height);
		
		player_up[0] = pSheet.crop(0, height, width, height);
		player_up[1] = pSheet.crop(width, height, width, height);
		player_up[2] = pSheet.crop(2*width , height, width, height);
		player_up[3] = pSheet.crop(width, height, width, height);
		
		player_left[0] = pSheet.crop(0, 0, width, height);
		player_left[1] = pSheet.crop(width, 0, width, height);
		player_left[2] = pSheet.crop(2*width , 0, width, height);
		player_left[3] = pSheet.crop(width, 0, width, height);
		
		player_right[0] = pSheet.crop(0, 4*height, width, height);
		player_right[1] = pSheet.crop(width, 4*height, width, height);
		player_right[2] = pSheet.crop(2*width , 4*height, width, height);
		player_right[3] = pSheet.crop(width, 4*height, width, height);
		
		player_aDown[0] = pSheet.crop(0, 7*height, width, height);
		player_aDown[1] = pSheet.crop(width, 7*height, width, height);

		player_aUp[0] = pSheet.crop(0, 6*height, width, height);
		player_aUp[1] = pSheet.crop(width, 6*height, width, height);
		
		player_aLeft[0] = pSheet.crop(0, 5*height, width, height);
		player_aLeft[1] = pSheet.crop(width, 5*height, width, height);

		player_aRight[0] = pSheet.crop(0, 8*height, width, height);
		player_aRight[1] = pSheet.crop(width, 8*height, width, height);
		
		demon_impale[0] = dSheet.crop(0, 0, 4*width, 4*height);
		demon_impale[1] = dSheet.crop(4*width, 0, 4*width, 4*height);
		demon_impale[2] = dSheet.crop(8*width, 0, 4*width, 4*height);
		demon_impale[3] = dSheet.crop(4*width, 0, 4*width, 4*height);
		
		demon_stand[0] = wSheet.crop(0, 0, 4*width, 4*height);
		demon_stand[1] = wSheet.crop(4*width, 0, 4*width, 4*height);
		
		tree = eSheet.crop(8*width, 2*height, width, 6*height);
		treeBunch = eSheet.crop(4*width, height, 4*width, 7*height);
		treeForrestTile = eSheet.crop(5*width, height, 3*width, 7*height);
		
		house = eSheet.crop(9*width, 0, 6*width, 8*height);
		houseNarrow = eSheet.crop(18*width, 0, 3*width, 9*height);
		
		chest = eSheet.crop(8*width, 8*height, width, height);
		
		npc = npcSheet.crop(0, 0, width, height);
		
		tile = new BufferedImage[57];
		for(int i=0;i<57;i++) {
			tile[i] = Sheet.crop(i*width, 0, width, height);
		}
	}

}
