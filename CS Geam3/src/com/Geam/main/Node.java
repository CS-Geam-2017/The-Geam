package com.Geam.main;

import com.sun.javafx.geom.Point2D;

public class Node {
	public Point2D xy;
	public Node ParN;
	public int Gcost;
	public double Hcost;
	public double Fcost;
	
	public Node(Point2D xy, Node ParN, int Gcost, double d, double e) {
		this.xy = xy;
		this.ParN = ParN;
		this.Gcost = Gcost;
		this.Hcost = d;
		this.Fcost = e;
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
	public double getHcost() {
		return this.Hcost;
	}
	public double getFcost() {
		return this.Fcost;
	}
	public void changeParN(Node newParN) {
		this.ParN = newParN;
	}
}
