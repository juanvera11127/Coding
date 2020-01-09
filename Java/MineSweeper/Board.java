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
	private int size;
	private int board[][];
	public static boolean mines[][];
	private int area[][];
	private int mineCount;
	private int offX;
	private int offY;
	private int winCount;
	private boolean isOver;

	Board(int width, int height, int length, int size) {
		
		setFocusable(true);
		this.requestFocus();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		this.length = length;
		this.size = size;
		reset(size);
		
		offX = (width - length * size)/2;
		offY = (height - length * size)/2 - 20;
		
	}

	public void reset(int size) {
		board = new int[size][size];
		mines = new boolean[size][size];
		area = new int[size][size];
		mineCount = (int)Math.floor(Math.pow(size, 2)/10);
		isOver = false;

		int randX = 0;
		int randY = 0;

		for (int i = 0; i < mineCount; i++) {
			randX = (int) (Math.random() * size);
			randY = (int) (Math.random() * size);
			while (mines[randX][randY]) {
				randX = (int) (Math.random() * size);
				randY = (int) (Math.random() * size);
			}
			mines[randX][randY] = true;
		}
		int areaCount = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				areaCount = 0;
				if (i > 0 && j > 0 && mines[i - 1][j - 1])
					areaCount++;
				if (i + 1 < size && j > 0 && mines[i + 1][j - 1])
					areaCount++;
				if (i > 0 && j + 1 < size && mines[i - 1][j + 1])
					areaCount++;
				if (i + 1 < size && j + 1 < size && mines[i + 1][j + 1])
					areaCount++;
				if (j > 0 && mines[i][j - 1])
					areaCount++;
				if (i > 0 && mines[i - 1][j])
					areaCount++;
				if (j + 1 < size && mines[i][j + 1])
					areaCount++;
				if (i + 1 < size && mines[i + 1][j])
					areaCount++;
				area[i][j] = areaCount;
			}
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Serif", Font.BOLD, length-2));
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == 1) {
					g.setColor(new Color(240, 240, 240));
				} else if (board[i][j] == 2) {
					g.setColor(new Color(255, 175, 10));
				} else if(board[i][j] == 0){
					g.setColor(new Color(200, 200, 200));
				}
				else {
					g.setColor(Color.red);
				}
				g.fillRect(offX + length * j, offY + length * i, length, length);
				g.setColor(Color.black);
				g.drawRect(offX + length * j, offY + length * i, length, length);

				if (board[i][j] == 1 && area[i][j] > 0 && !mines[i][j]) {
					g.drawString((area[i][j]) + "", offX + length * j + length / 3,
							offY + length * (i + 1) - length / 4);
				}
			}
		}
		
		winCount = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(board[i][j] == 0 || board[i][j] == 2) {
					winCount++;
				}
			}
		}
		if(winCount == mineCount) {
			g.setColor(Color.green);
			g.setFont(new Font("Serif", Font.BOLD, 3*length));
			g.drawString("You Win", offX, offY+200);
		}
		else if(isOver) {
			g.setColor(Color.red);
			g.setFont(new Font("Serif", Font.BOLD, 3*length));
			g.drawString("You Lose", offX, offY+200);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.requestFocus();
		if(isOver) {
			reset(size);
		}
		int i = (arg0.getY() - offY) / length;
		int j = (arg0.getX() - offX) / length;
		if (i < size && j < size && !isOver) {
			if (arg0.getButton() == arg0.BUTTON2 || arg0.getButton() == arg0.BUTTON3) {
				if (board[i][j] == 0) {
					board[i][j] = 2;
				} else if (board[i][j] == 2) {
					board[i][j] = 0;
				}
			} else if (arg0.getButton() == arg0.BUTTON1 && board[i][j] == 0) {
				if (mines[i][j]) {
					board[i][j] = 3;
					isOver = true;
					System.out.println("Game Over");
				} else {
					clear(i, j, false);
				}
			}
			repaint();
		}
	}

	public void clear(int i, int j, boolean isEnd) {
		if (board[i][j] == 0 && !mines[i][j]) {
			board[i][j] = 1;
		}
		if (!isEnd) {
			if (i + 1 < size && area[i + 1][j] == 0 && board[i + 1][j] == 0) {
				clear(i + 1, j, false);
			} else if (i + 1 < size) {
				clear(i + 1, j, true);
			}
			if (i > 0 && area[i - 1][j] == 0 && board[i - 1][j] == 0) {
				clear(i - 1, j, false);
			} else if (i > 0) {
				clear(i - 1, j, true);
			}
			if (j + 1 < size && area[i][j + 1] == 0 && board[i][j + 1] == 0) {
				clear(i, j + 1, false);
			} else if (j + 1 < size) {
				clear(i, j + 1, true);
			}
			if (j > 0 && area[i][j - 1] == 0 && board[i][j - 1] == 0) {
				clear(i, j - 1, false);
			} else if (j > 0) {
				clear(i, j - 1, true);
			}
			
			if (i + 1 < size && j + 1 < size && area[i + 1][j + 1] == 0 && board[i + 1][j + 1] == 0) {
				clear(i + 1, j + 1, false);
			} else if (i + 1 < size && j + 1 < size) {
				clear(i + 1, j + 1, true);
			}
			if (i > 0 &&  j > 0 && area[i - 1][j - 1] == 0 && board[i - 1][j - 1] == 0) {
				clear(i - 1, j - 1, false);
			} else if (i > 0 && j > 0) {
				clear(i - 1, j - 1, true);
			}
			if (i > 0 && j + 1 < size && area[i - 1][j + 1] == 0 && board[i - 1][j + 1] == 0) {
				clear(i - 1, j + 1, false);
			} else if (i > 0 && j + 1 < size) {
				clear(i - 1, j + 1, true);
			}
			if (i + 1 < size && j > 0 && area[i + 1][j - 1] == 0 && board[i + 1][j - 1] == 0) {
				clear(i + 1, j - 1, false);
			} else if (i +1 < size && j > 0) {
				clear(i + 1, j - 1, true);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}
}