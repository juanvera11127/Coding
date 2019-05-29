import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private int length;
	private int sqrt;
	private boolean board[][];
	public static boolean solution[][];
	private int offX = 10;
	private int offY = 40;
	private int previ;
	private int prevj;
	private boolean isBlack;
	private int startCol;
	private int endCol;

	Board(int width, int height, int length, int size) {
		setFocusable(true);
		this.requestFocus();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		this.length = length;
		sqrt = ((int) Math.sqrt(size)) - 8;
		board = new boolean[sqrt][sqrt];
		solution = new boolean[sqrt][sqrt];
		for (int i = 0; i < sqrt; i++) {
			for (int j = 0; j < sqrt; j++) {
				if (i == 0 || j == 0 || i == sqrt - 1 || j == sqrt - 1) {
					board[i][j] = true;
				}
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("ComicSans", Font.BOLD, 16));
		if(startCol > 0) {
			g.drawString("start", offX - 10 + length*startCol, offY - 10);
		}
		if(endCol > 0) {
			g.drawString("goal", offX - 10 + length*endCol, offY + 15 + length*sqrt);

		}
		
		for (int i = 0; i < sqrt; i++) {
			for (int j = 0; j < sqrt; j++) {
				if (board[i][j]) {
					g.setColor(Color.BLACK);
					g.fillRect(offX + length * j, offY + length * i, length, length);

				} else if (solution[i][j]) {
					g.setColor(Color.green);
					g.fillRect(offX + length * j, offY + length * i, length, length);
				} else {
					g.setColor(Color.WHITE);
					g.fillRect(offX + length * j, offY + length * i, length, length);
					g.setColor(Color.black);
					g.drawRect(offX + length * j, offY + length * i, length, length);
				}
			}
			if (GraphTraversal.isDone) {
				System.out.println();
			}
		}
	}

	public void add(int i, int j) {
		if (i < sqrt && j < sqrt) {
			board[i][j] = true;
		} else {
			System.out.println("Error: invalid add");
		}
	}

	public void remove(int i, int j) {
		if (!board[i][j]) {
			System.out.println("Error: invalid remove");
		} else {
			board[i][j] = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		this.requestFocus();
		int i = (arg0.getY() - offY) / length;
		int j = (arg0.getX() - offX) / length;
		if (i < sqrt && j < sqrt) {
			if (i != previ || j != prevj) {
				if (board[i][j] && !isBlack) {
					remove(i, j);
				} else if (!board[i][j] && isBlack) {
					add(i, j);
				}
				previ = i;
				prevj = j;
			}
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		this.requestFocus();
		// TODO Auto-generated method stub
		int i = (arg0.getY() - offY) / length;
		int j = (arg0.getX() - offX) / length;
		previ = i;
		prevj = j;
		if (i < sqrt && j < sqrt) {
			if (board[i][j]) {
				isBlack = false;
				remove(i, j);
			} else {
				isBlack = true;
				add(i, j);
			}
			repaint();
		}
		
		for (int a = 0; a < sqrt; a++) {
			if (!board[0][a]) {
				startCol = a;
			}
			if(!board[board.length-1][a]) {
				endCol = a;
			}
		}
		if(board[0][startCol]) {
			startCol = 0;
		}
		if(board[board.length-1][endCol]) {
			endCol = 0;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == arg0.VK_SPACE) {

			for (int i = 0; i < solution.length; i++) {
				for (int j = 0; j < solution.length; j++) {
					solution[i][j] = false;
				}
			}

			int startCount = 0;
			int endCount = 0;
			for (int i = 0; i < sqrt; i++) {
				if (!board[0][i]) {
					startCount++;
				}
				if (!board[sqrt - 1][i]) {
					endCount++;
				}
			}
			if (startCount == 1 && endCount == 1) {
				GraphTraversal search = new GraphTraversal(board, startCol, endCol, this);
			} else {
				System.out.println("Error, invalid # of entrances/exits");
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
