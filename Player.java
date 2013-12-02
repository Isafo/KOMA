import java.awt.*;
import java.awt.event.*;
import javax.management.timer.Timer;
import javax.swing.*;

//Player is added if symbol == P

public class Player extends JPanel {

	//movestuff
	double xCord = 0, yCord = 0;
	static double vely = 0, velx = 0;
	int width, height, x, y;
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		width = 30;
		height = 30;
	}
	
	public void draw(Graphics g){
		setPosition();
		g.setColor(Color.BLUE);
		g.fillOval(x, y, width, height);	
	}
	
	//movefunctions
	
	public static void up(){
		vely = -1.5;
		velx = 0;
	}
	
	public static void down(){
		vely = 1.5;
		velx = 0;
	}
	
	public static void left(){
		vely = 0;
		velx = -1.5;
	}
	
	public static void right(){
		vely = 0;
		velx = 1.5;
	}

	public void setPosition(){	
		//to get position if the blue circle has been movedby the user
		x += velx;
		y += vely;
	}
}
