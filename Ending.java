import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.*;


public class Ending extends JPanel{
	
	static int x, y;
	static final int WIDTH = 30, HEIGHT = 30;
	
	public Ending(int x, int y){
		this.x = x;
		this.y = y;
		
		System.out.println("in ending");
	}
	
	public static Rectangle getEnding(){
		return new Rectangle (x, y, WIDTH, HEIGHT);
	}
}
