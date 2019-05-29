package javafirst;
import java.awt.Color;
import java.util.Random;

public class Bunchie {
	
	private Color color;
	private int r;
	private int g;
	private int b;

	
	public Bunchie() {
		Random rand = new Random();
		r = rand.nextInt(100)+100;
		g = rand.nextInt(100)+100;
		b = rand.nextInt(100)+100;
		color = new Color(r,g,b);
	}
	
	public static Color getColor(Bunchie b) {
		return b.color;
	}
	
	public static void Jump(Bunchie b) {
		Player.isJumping();
		if(GraphicsDemo.isJumping) {
			for(int i = 0; i <GraphicsDemo.yb.length; i++) {
				GraphicsDemo.yb[i] -= 200;
			}

		}
		else if(!GraphicsDemo.isJumping) {
		
			for(int i = 0; i <GraphicsDemo.yb.length; i++) {
				GraphicsDemo.yb[i] += 200;
			}
		}
	}


	
}
