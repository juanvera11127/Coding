import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.w3c.dom.Text;

public class StartMenu extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	Button b1;
	Button b2;
	Button b3;
	JLabel j;
	
	StartMenu() {
		setFocusable(true);
		setLayout(new FlowLayout());
		addMouseListener(this);
		j = new JLabel();
		j.setFont(new Font("ComicSans", Font.PLAIN, 16));
		j.setText("Choose a maze size.");
		add(j);
		b1 = new Button("small");
		b2 = new Button("medium");
		b3 = new Button("large");
		add(b1);
		add(b2);
		add(b3);
		
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Runner.next(24);
			}
			
		});
		
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Runner.next(19);
			}
			
		});
		
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Runner.next(14);
			}
			
		});
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("ComicSans", Font.BOLD, 16));
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		//int i = (arg0.getY() - offY) / length;
		//int j = (arg0.getX() - offX) / length;

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
