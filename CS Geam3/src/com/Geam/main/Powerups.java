package com.Geam.main;

import java.util.Random;

public class Powerups{
	static Random r;
	static Handler handler;
	private static int x;
	private static int y;
	public static boolean PU = false;
	public static void tick(){
		x = r.nextInt(Geam.WIDTH-50);
		y = r.nextInt(Geam.HEIGHT-50);
		if (PU == false) {
			Geam.PowerT = System.currentTimeMillis();
			PU = true;
			handler.addObject(new Shield(x, y, ID.Shield, handler));
		}
	}
}
