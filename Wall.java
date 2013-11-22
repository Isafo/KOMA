import java.awt.*;

//Wall is added if symbol == #

public class Wall {
	
	int width, height, x, y;
	
	public Wall(int x, int y){
		this.x = x;
		this.y = y;
		
		width = 30;
		height = 30;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.BLACK);
		g.drawRoundRect(x, y, width, height, 1, 1);
	}	
}