import java.awt.*;
import java.awt.Rectangle;

//Wall is added if symbol == #

public class Wall {
	
	static int width, height, x, y;
	
	public Wall(int x, int y){
		this.x = x;
		this.y = y;
		
		width = 30;
		height = 30;
	}
	
	//returns the bounds of a wall
	public static Rectangle getWall(){
		return new Rectangle (x, y, width, height);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.BLACK);
		g.drawRoundRect(x, y, width, height, 1, 1);
	}
}