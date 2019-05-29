import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Runner{

	private final static int WIDTH = 800;
	private final static int HEIGHT = 600;
	private static int LENGTH = 22;
	private static int SIZE = WIDTH > HEIGHT ? ((HEIGHT * HEIGHT) / (LENGTH * LENGTH)) : ((WIDTH * WIDTH) / (LENGTH * LENGTH));
	static JFrame frame;
	static StartMenu s;
	
	public static void main(String[] args) {
		start();
	}
	
	private static void start() {
		s = new StartMenu();
		frame = new JFrame("Maze Solver");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(s);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}
	
	public static void next(int n) {
		SIZE = WIDTH > HEIGHT ? ((HEIGHT * HEIGHT) / (n * n)) : ((WIDTH * WIDTH) / (n * n));
		frame.getContentPane().removeAll();
		frame.getContentPane().add(new Board(WIDTH, HEIGHT, n, SIZE));
		frame.revalidate();
	}


}
