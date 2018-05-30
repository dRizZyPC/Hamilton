package com.drizzy.hamilton.entities.statics;

import com.drizzy.hamilton.Handler;
import com.drizzy.hamilton.entities.Entity;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler,float x,float y,int width,int height) {
		super(handler,x,y,width,height);
	}
	
}
