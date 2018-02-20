package com.Geam.main;

import java.awt.Graphics;

/**
 * @author S59587
 *
 */
public abstract class GeamObject {

	protected int x, y;
	protected ID id;
	protected String text;
	protected int Sp;
	protected int Sp2;
	
	public GeamObject(int x, int y, ID id, String text){
		this.x = x;
		this.y = y;
		this.id = id;
		this.text = text;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setText(String text) {
		this.text = text;
		if(text.length()>30&&id==ID.act5) {
			Sp=0;
			for(int i = 0; i<text.length(); i++) {
				if(text.charAt(i)==' '&&i<30) {
					Sp=i;
					//System.out.println(Sp);
				}
			}
		}
		else if(text.length()>83) {
			Sp=0;
			for(int i = 0; i<text.length(); i++) {
				if(text.charAt(i)==' '&&i<83) {
					Sp=i;
				}
				else if(text.charAt(i)==' '&&i<166) {
					Sp2=i;
				}
			}
		}
		
	}
	public String getText() {
		return text;
	}
	public int getx(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setId(ID id){
		this.id = id;
	}
	public ID getID(){
		return id;
	}
	
}
