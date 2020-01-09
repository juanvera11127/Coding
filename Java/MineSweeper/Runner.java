import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Runner{

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final static int WIDTH = (int) screenSize.getWidth();
	private final static int HEIGHT = (int) screenSize.getHeight();;
	private static int SIZE;
	static JFrame frame;
	static StartMenu s;
	
	public static void main(String[] args) {
		start();
	}
	
	private static void start() {
		s = new StartMenu();
		frame = new JFrame("Minesweeper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(s);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}
	
	public static void next(int n) {
		SIZE = (WIDTH > HEIGHT ? HEIGHT/n : WIDTH/n) - 2;
		System.out.println(SIZE);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(new Board(WIDTH, HEIGHT, SIZE, n-2));
		frame.revalidate();
	}


}