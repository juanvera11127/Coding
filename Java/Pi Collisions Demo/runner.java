import java.awt.BorderLayout;

import javax.swing.JFrame;

public class runner {

	private final static int WIDTH = 800;
	private final static int HEIGHT = 600;
	static JFrame frame;
	static int digits = 5;
	static int velocity = 30;
	
	public static void main(String[] args) {
		frame = new JFrame("Maze Solver");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(new Canvas(digits, velocity));
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
}
