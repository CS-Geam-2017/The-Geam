package com.Geam.main;

import com.sun.javafx.geom.Point2D;

public class Node {
	public Point2D xy;
	public Point2D Parxy;
	public int Gcost;
	public int Hcost;
	public int Fcost;
	
	public Node(Point2D xy, Point2D Parxy, int Gcost, int Hcost, int Fcost) {
		this.xy = xy;
		this.Parxy = Parxy;
		this.Gcost = Gcost;
		this.Hcost = Hcost;
		this.Fcost = Fcost;
	}
	
	public Point2D getXY() {
		return this.xy;
	}
	public Point2D getParXY() {
		return this.Parxy;
	}
	public int getGcost() {
		return this.Gcost;
	}
	public int getHcost() {
		return this.Hcost;
	}
	public int getFcost() {
		return this.Fcost;
	}
}
