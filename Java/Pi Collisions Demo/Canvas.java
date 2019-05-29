import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Canvas extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private int digits;
	private int velocity;
	private boolean isDone = false;
	Timer t;
	
	Block a;
	Block b;
	
	private float ax;
	private float bx;
	private float av;
	private float bv;
	int count;
	

	Canvas(int digits, int velocity) {
		setFocusable(true);
		this.requestFocus();
		ax = 100;
		bx = 200;
		bv = -velocity;
		this.digits = digits;
		this.velocity = velocity;
		t = new Timer(5, this);
		a = new Block(1, 0, 20);
		b = new Block((int)Math.pow(100, digits-1), velocity, 20);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.fillRect((int)ax, 20, a.getSize(), a.getSize());
		g.fillRect((int)bx, 50, b.getSize(), b.getSize());
		t.start();
	}

	public void collision() {
		//System.out.println("collision! " + av + ", " + bv + ", " + a.getMass() + ", " + b.getMass());
		float newa;
		float newb;
		newa = av * (a.getMass() - b.getMass()) / (a.getMass() + b.getMass()) + bv * 2 * b.getMass() / (a.getMass() + b.getMass());
		newb = av * 2 * a.getMass() / (a.getMass() + b.getMass()) + bv * (b.getMass() - a.getMass()) / (a.getMass() + b.getMass());
		av = newa;
		bv = newb;
		//System.out.println("new av bv: " + av + ", " + bv);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		ax += av;
		bx += bv;

		if(ax <= 0) {
			count++;
			av = -av;
		}
		if(ax+a.getSize() > bx) {
			collision();
			count++;
		}
		
		if(av > 0 && bv > 0 && av < bv && !isDone) {
			isDone = true;
			System.out.println(count);
		}
		
		if(count % 100 == 0) {
			System.out.println(ax + ", " + bx);
			System.out.println(av + ", " + bv);
		}
		

		repaint();
	}

}
