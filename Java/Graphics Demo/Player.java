package javafirst;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Player extends Thread {

	private boolean isRunning = true;
	private long wait, start, targetTime, elapsed;
	public static boolean done = true;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("SUPER BUNCHIE ADVENTURE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(new GraphicsDemo(), BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setBackground(Color.black);
	}
	private static long elapsed2;
	
    public void run(){  
    
    	System.out.println("thread is running...");  
    	while (isRunning) {
			start = System.nanoTime();
			if(System.currentTimeMillis()-elapsed2 > 1000) {
				done = true;
				GraphicsDemo.isJumping = false;
				Bunchie.Jump(GraphicsDemo.b);
				this.stop();
			}

			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;

			if (wait <= 0) {
				wait = 5;
			}
			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }  
    
    public static void isJumping(){  
    	if(GraphicsDemo.isJumping) {
    		elapsed2 = System.currentTimeMillis();
    		Player t1=new Player();  
    		t1.start();  
    		
    	}
     
    }  
}
