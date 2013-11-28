import java.awt.*;
import java.awt.event.*;
import javax.management.timer.Timer;
import javax.swing.*;

//Player is added if symbol == P

public class Player extends JPanel implements ActionListener {

	//movestuff
	double xCord = 0, yCord = 0, velx = 0, vely = 0;
	Timer t = new Timer();
	int width, height, x, y;
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;
		
		t.start();
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		width = 30;
		height = 30;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillOval(x, y, width, height);	
	}
	
	//movefunctions
	
	public void up(){
		vely = -1.5;
		velx = 0;
	}
	
	public void down(){
		vely = 1.5;
		velx = 0;
	}
	
	public void left(){
		vely = 0;
		velx = -1.5;
	}
	
	public void right(){
		vely = 0;
		velx = 1.5;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		repaint();
		x += velx;
		y += vely;
	}
}
