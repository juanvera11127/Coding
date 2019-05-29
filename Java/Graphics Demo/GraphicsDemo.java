package javafirst;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.*;

public class GraphicsDemo extends JPanel implements Runnable {

	static final long serialVersionUID = 1;
	private int FPS = 60;
	private int count = 0;
	public static boolean isJumping = false;
	private long targetTime = 1000 / FPS;
	private Thread clock;
	private boolean isOver = false;
	private boolean isRunning = false;
	public static final int xpos = 1800;
	public static final int y = 1000;
	public static final int floor = y / 3 * 2;
	Wall n;
	static Bunchie b;
	public static int[] xb = { 0, 0, 50, 50, 75, 75, 150, 150, 175, 175, 200, 200, 140, 140, 0 };
	public static int[] yb = { floor - 250 + 140, floor - 250 + 200, floor - 250 + 200, floor - 250 + 250,
			floor - 250 + 250, floor - 250 + 200, floor - 250 + 200, floor - 250 + 250, floor - 250 + 250,
			floor - 250 + 200, floor - 250 + 200, floor - 250 + 0, floor - 250 + 0, floor - 250 + 140,
			floor - 250 + 140 };
	Font font = new Font("Arial", Font.BOLD, 24);

	public GraphicsDemo() {
		setPreferredSize(new Dimension(xpos, y));
		addKeyListener(new InputKeyEvents());

		setFocusable(true);
		start();
	}

	@Override
	public void paintComponent(Graphics gr) {
		if (!isOver) {
			count++;
			super.paintComponent(gr);
			gr.setFont(font);
			gr.drawString("Score: " + count, xpos/2, y/2-200);
			for (int i = 0; i < xb.length; i++) {
				if (xb[i] == Wall.getX(n) && yb[i] > floor - Wall.getHeight(n)
						|| yb[i] == floor - Wall.getHeight(n) && xb[i] == Wall.getX(n) + Wall.getWidth(n)) {
					isOver = true;
				}
			}

			b = new Bunchie();
			gr.setColor(Bunchie.getColor(b));
			gr.fillPolygon(xb, yb, 15);
			gr.setColor(Color.black);
			gr.fillRect(Wall.getX(n), (y / 3 * 2) - Wall.getHeight(n), Wall.getWidth(n), Wall.getHeight(n));
			gr.fillRect(0, y / 3 * 2, xpos, y / 50);
			if (Wall.isOnScreen(n)) {
				Wall.move(n, 10);
			} else {
				n = new Wall(xpos, 0, 0);
			}

		} else {
			gr.setFont(font);
			gr.drawString("Game Over", xpos / 2, y / 2);
		}
	}

	@Override
	public void run() {
		long start, elapsed, wait;
		while (isRunning) {
			start = System.nanoTime();

			// tick();
			repaint();

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

	public void tick() {
		System.out.println("key Pressed");
	}

	public void start() {

		n = new Wall(xpos, 0, 0);
		isRunning = true;
		clock = new Thread(this);
		clock.start();
	}

}
