package com.Geam.main;

import com.sun.javafx.geom.Point2D;

public class Node {
	public Point2D xy;
	public Node ParN;
	public int Gcost;
	public int Hcost;
	public int Fcost;
	
	public Node(Point2D xy, Node ParN, int Gcost, int Hcost, int Fcost) {
		this.xy = xy;
		this.ParN = ParN;
		this.Gcost = Gcost;
		this.Hcost = Hcost;
		this.Fcost = Fcost;
	}
	
	public Point2D getXY() {
		return this.xy;
	}
	public Node getParN() {
		return this.ParN;
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
	public void changeParN(Node newParN) {
		this.ParN = newParN;
	}
}
