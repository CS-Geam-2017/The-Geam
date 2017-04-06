package com.Geam.main;


import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	static LinkedList<GeamObject> object  = new LinkedList<GeamObject>();
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GeamObject tempObject = object.get(i);
			
			tempObject.tick();
		
		}
	}
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			GeamObject tempObject = object.get(i);
			
			tempObject.render(g);
		
		}
	}
	public void addObject(GeamObject object){
		Handler.object.add(object);
	}
	
	public void removeObject(GeamObject object){
		Handler.object.remove(object);
		
	}
}

