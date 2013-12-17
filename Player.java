import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.management.timer.Timer;
import javax.swing.*;

//Player is added if symbol == P

public class Player extends JPanel {

	double xCord = 0, yCord = 0;
	static int vely = 0, velx = 0;
	static int width, height, x, y;
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		width = 25;
		height = 25;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillOval(x, y, width, height);
	}
	
	//movefunctions
	
	public static void up(){
		vely = -4;
		velx = 0;
		setPosition();
	}
	
	public static void down(){
		vely = 4;
		velx = 0;
		setPosition();
	}
	
	public static void left(){
		vely = 0;
		velx = -4;
		setPosition();
	}
	
	public static void right(){
		vely = 0;
		velx = 4;
		setPosition();
	}

	public static void setPosition(){	
		//to get position if the blue circle has been movedby the user
		x += velx;
		y += vely;
	}

	public static void resetPosition(){	
		//reset position if collision was detected
		x -= velx;
		y -= vely;
	}
	
	public static Rectangle getPlayer(){
		return new Rectangle (x, y, width, height);
	}	
}
