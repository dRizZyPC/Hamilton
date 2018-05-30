package com.drizzy.hamilton.worlds;

import java.awt.Graphics;

import com.drizzy.hamilton.Handler;
import com.drizzy.hamilton.audio.Sound;
import com.drizzy.hamilton.entities.EntityManager;
import com.drizzy.hamilton.entities.creatures.Demon;
import com.drizzy.hamilton.entities.creatures.Player;
import com.drizzy.hamilton.entities.statics.Chest;
import com.drizzy.hamilton.entities.statics.House;
import com.drizzy.hamilton.entities.statics.HouseNarrow;
import com.drizzy.hamilton.entities.statics.NPC;
import com.drizzy.hamilton.entities.statics.Tree;
import com.drizzy.hamilton.tiles.Tile;
import com.drizzy.hamilton.utils.Utils;

public class World {

	private Handler handler;
	private int width,height;
	private int spawnX,spawnY;
	private int[][] tiles;
	//Entities
	private EntityManager entityManager;
	
	public World(Handler handler,String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler,new Player(handler,100,100));
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		Tile.init();
		
		init(entityManager);
	}
	
	private void init(EntityManager entityManager) {
		entityManager.addEntity(new Tree(handler,100,200));
		//House
		entityManager.addEntity(new House(handler,6*Tile.TILEWIDTH,2*Tile.TILEHEIGHT));
		entityManager.addEntity(new House(handler,32*Tile.TILEWIDTH,6*Tile.TILEHEIGHT));
		entityManager.addEntity(new House(handler,38*Tile.TILEWIDTH,6*Tile.TILEHEIGHT));
		entityManager.addEntity(new House(handler,68*Tile.TILEWIDTH,13*Tile.TILEHEIGHT));
		entityManager.addEntity(new House(handler,65*Tile.TILEWIDTH,25*Tile.TILEHEIGHT));
		entityManager.addEntity(new House(handler,45*Tile.TILEWIDTH,47*Tile.TILEHEIGHT));
		entityManager.addEntity(new House(handler,54*Tile.TILEWIDTH,47*Tile.TILEHEIGHT));
		entityManager.addEntity(new House(handler,88*Tile.TILEWIDTH,52*Tile.TILEHEIGHT));
		entityManager.addEntity(new House(handler,54*Tile.TILEWIDTH,65*Tile.TILEHEIGHT));
		entityManager.addEntity(new House(handler,64*Tile.TILEWIDTH,65*Tile.TILEHEIGHT));
		entityManager.addEntity(new House(handler,58*Tile.TILEWIDTH,82*Tile.TILEHEIGHT));
		//Narrow House
		entityManager.addEntity(new HouseNarrow(handler,29*Tile.TILEWIDTH,4*Tile.TILEHEIGHT));
		entityManager.addEntity(new HouseNarrow(handler,44*Tile.TILEWIDTH,4*Tile.TILEHEIGHT));
		entityManager.addEntity(new HouseNarrow(handler,71*Tile.TILEWIDTH,23*Tile.TILEHEIGHT));
		entityManager.addEntity(new HouseNarrow(handler,42*Tile.TILEWIDTH,45*Tile.TILEHEIGHT));
		entityManager.addEntity(new HouseNarrow(handler,51*Tile.TILEWIDTH,45*Tile.TILEHEIGHT));
		entityManager.addEntity(new HouseNarrow(handler,60*Tile.TILEWIDTH,45*Tile.TILEHEIGHT));
		entityManager.addEntity(new HouseNarrow(handler,36*Tile.TILEWIDTH,55*Tile.TILEHEIGHT));
		entityManager.addEntity(new HouseNarrow(handler,85*Tile.TILEWIDTH,50*Tile.TILEHEIGHT));
		entityManager.addEntity(new HouseNarrow(handler,51*Tile.TILEWIDTH,63*Tile.TILEHEIGHT));
		//Demon
		entityManager.addEntity(new Demon(handler,112*Tile.TILEWIDTH,88*Tile.TILEHEIGHT));
		//entityManager.addEntity(new Demon(handler,11*Tile.TILEWIDTH,14*Tile.TILEHEIGHT));
		//Chests
		entityManager.addEntity(new Chest(handler,64*Tile.TILEWIDTH,3*Tile.TILEHEIGHT));
		entityManager.addEntity(new Chest(handler,81*Tile.TILEWIDTH,14*Tile.TILEHEIGHT));
		entityManager.addEntity(new Chest(handler,1*Tile.TILEWIDTH,37*Tile.TILEHEIGHT));
		entityManager.addEntity(new Chest(handler,31*Tile.TILEWIDTH,83*Tile.TILEHEIGHT));
		//NPC
		entityManager.addEntity(new NPC(handler,11*Tile.TILEWIDTH,9*Tile.TILEHEIGHT));
		//Sound
		Sound.sound1.loop();
	}
	
	public void tick() {
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth())/Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight())/Tile.TILEHEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x,y).render(g, (int) (x*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
										(int) (y*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x,int y) {
		if(x<0 || y<0 || x>=width || y>=height)
			return Tile.specificTile[1];
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.specificTile[1];
		return t;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
	
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height ; y++) {
			for(int x = 0; x < width ; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y*width) + 4]);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
