package javafirst;

import java.util.Random;

public class Wall {

	private int x;
	private int height;
	private int width;
	
	public Wall(int x, int height, int width) {
		this.x = x;
		Random rand = new Random();
		int r = rand.nextInt(50)+100;
		this.height = r;
		r = rand.nextInt(25)+25;
		this.width=r;
	}
	
	public static boolean isOnScreen(Wall a) {
		return(a.x >= 0 && a.x <= GraphicsDemo.xpos);			
	}
	
	public static int getX(Wall a) {
		return a.x;
	}
	
	public static void move(Wall a, int n) {
		a.x -= n;
	}
	
	public static int getHeight(Wall a) {
		return a.height;
	}
	
	public static int getWidth(Wall a) {
		return a.width;
	}
}
