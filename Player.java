import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.management.timer.Timer;
import javax.swing.*;

//Player is added if symbol == P

public class Player extends JPanel {

	//movestuff
	double xCord = 0, yCord = 0;
	static double vely = 0, velx = 0;
	static int width, height, x, y;
	
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
		if(checkCollision()){
			g.setColor(Color.BLUE);
			g.fillOval(x, y, width, height);
		}
		
		else{
			x -= velx;
			y -= vely;
			g.setColor(Color.BLUE);
			g.fillOval(x, y, width, height);
		}
	}
	
	//movefunctions
	
	public static void up(){
		vely = -4;
		velx = 0;
	}
	
	public static void down(){
		vely = 4;
		velx = 0;
	}
	
	public static void left(){
		vely = 0;
		velx = -4;
	}
	
	public static void right(){
		vely = 0;
		velx = 4;
	}

	public void setPosition(){	
		//to get position if the blue circle has been movedby the user
		x += velx;
		y += vely;
	}
	
	public static Rectangle getPlayer(){
		return new Rectangle (x, y, width, height);
	}
	
	//returns false if collision is detected
	public boolean checkCollision(){
		
		Rectangle player = getPlayer();
		Rectangle wall = Wall.getWall();
		
		for(int j = 0; j < Field.getHowManyWalls(); j++){
			if(player.intersects(wall)){
				return false;
			}
		}
		return true;
	}
}
