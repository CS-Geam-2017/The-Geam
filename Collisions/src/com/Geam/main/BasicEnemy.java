package com.Geam.main;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GeamObject {
	private Random r;
	Handler handler;
	private static int Width = 20;
	private static int Height = 20;
	boolean collide = false;
	double elastic = 1;
	double friction = 1;
	double gravity = 0.0;
	
	public BasicEnemy(int x, int y, ID id, Handler handler, int radius, double elastic, double friction, double gravity) {
		// TODO Auto-generated constructor stub
		super(x, y, id);
		
		this.handler = handler;
		
		Width = radius;
		Height = radius;
		this.elastic = elastic;
		this.friction = friction;
		this.gravity = gravity;
		//this.ballnum = ballnum;
		
		r = new Random();
		
		//this.color = color;
		
		double speed = 0;
		
		if (r.nextInt(2) == 1) speedX = speed;
		else speedX = -speed;
		if (r.nextInt(2) == 1) speedY = speed;
		else speedY = -speed;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,Width,Height);
	}
	
	private void collision() {
		collide = false;
		for(int i = 0; i < Handler.object.size(); i++){
			GeamObject tempObject;
			if(Handler.object.get(i)!=this) {
				tempObject = Handler.object.get(i);
				if(tempObject.getID() == ID.BasicEnemy){
					
					//Space balls apart
					double xDis = (this.x)-(tempObject.x);
					double yDis = (this.y)-(tempObject.y);
					
					if((xDis*xDis)+(yDis*yDis)<(Width*Width) && true) {
						double disP1 = this.x-((this.x+tempObject.x)/2.0);
						double disP2 = this.y-((this.y+tempObject.y)/2.0);
						double Dis1 = Math.sqrt((disP1*disP1)+(disP2*disP2))-0;
						double Dis2 = Math.sqrt((disP1*disP1)+(disP2*disP2))+0;
						double slope = (tempObject.y-this.y)/(tempObject.x-this.x);
						double xSign = Math.abs(xDis)/xDis;
						double halfWidth = Width/2.0;
						
						double x2 = (xSign*((Dis2+halfWidth)/(Math.sqrt(1.0+(slope*slope)))))-this.x;
						double y2 = (xSign*slope*((Dis2+halfWidth)/(Math.sqrt(1.0+(slope*slope)))))-this.y;
						double x1 = (xSign*((Dis1-halfWidth)/(Math.sqrt(1.0+(slope*slope)))))-this.x;
						double y1 = (xSign*slope*((Dis1-halfWidth)/(Math.sqrt(1.0+(slope*slope)))))-this.y;
						
						
						if(Double.isNaN(slope)||Double.isInfinite(slope)) {
							System.out.println(slope);
							System.out.println(xDis);
							System.out.println(yDis);
							x2 = -(tempObject.x);
							y2 = -(tempObject.y-(((Width+.5)-yDis)/2.0));
							x1 = -(this.x);
							y1 = -(this.y+(((Width+.5)-yDis)/2.0));
							
						}
						
						this.x = -1*x1;
						this.y = -1*y1;
						
						tempObject.x = -1*x2;
						tempObject.y = -1*y2;
					}
					
					
					
					//xDis = (this.x+(Width/2))-(tempObject.getBounds().x+(Width/2));
					//yDis = (this.y+(Height/2))-(tempObject.getBounds().y+(Height/2));
					if(((xDis*xDis)+(yDis*yDis))<=(Width*Width)) {
						collide = true;
						
						double[] Ap = {this.x,this.y};
						double[] Bp = {tempObject.x, tempObject.y};
						
						double[] Av = {this.speedX, this.speedY};
						double[] Bv = {tempObject.speedX, tempObject.speedY};
						
						double Xdis = Ap[0]-Bp[0];
						double Ydis = Ap[1]-Bp[1];
						double hypo = Math.sqrt((Xdis*Xdis)+(Ydis*Ydis));
						double[] N = {(Xdis)/hypo,(Ydis)/hypo};
						
						double[] J = {Av[0]-Bv[0],Av[1]-Bv[1]};
						double dotPro = elastic*(J[0]*N[0])+(J[1]*N[1]);
						J[0] = N[0]*dotPro;
						J[1] = N[1]*dotPro;
						
						this.speedX = (Av[0]-J[0]);
						this.speedY = (Av[1]-J[1]);
						tempObject.speedX = (Bv[0]+J[0]);
						tempObject.speedY = (Bv[1]+J[1]);
					}
					else {
						//collide = false;
					}
				}
			}
			
			
			
		}
		
	}

	public void tick() {
		// TODO Auto-generated method stub
		collision();
		
		if(y <= 0 ) {
			y+=(0-y);
			speedY *= -elastic;
			speedX *= friction;
		}
		else if(y >= Geam.HEIGHT-Height){
			y-=(y-(Geam.HEIGHT-Height));
			speedY *= -elastic;
			speedX *= friction;
		}
			
		if(x <= 0 ) {
			x+=(0-x);
			speedX *= -elastic;
			speedY *= friction;
		}
		else if(x >= Geam.WIDTH-Width){
			x-=(x-(Geam.WIDTH-Width));
			speedX *= -elastic;
			speedY *= friction;
		}
		
		speedY+=gravity;
		//speedX+=.01;
		
		
		x+= speedX;
		y+= speedY;
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		if(collide) {
			g.setColor(Color.green);
		}
		//else if(color==0) {
		//	g.setColor(Color.red);
		//}
		else {
			g.setColor(Color.red);
		}
		
		//if(color == 0) {
		//	g.setColor(Color.yellow);
		//}
		//else if(color == 1) {
		//	g.setColor(Color.magenta);
		//}
		//g.setColor(Color.red);
		g.fillOval((int)x, (int)y, Width, Height);
		
		g.setColor(Color.blue);
		g.drawOval((int)x, (int)y, Width, Height);
		
		//g.setColor(Color.blue);
		//g.fillOval((int)x+(Width/2), (int)y+(Width/2), 5, 5);
	}
	
}
