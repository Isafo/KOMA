import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Maze extends JFrame {

	private Color background = new Color(35, 204, 80);
	Field map;
	public String filePath;
	
	public Maze() throws IOException {
	
		randomMap();
		
		map = new Field(filePath);
		
		setSize(465, 428);
		setTitle("Maze");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	//create random filePath for different maps
	public void randomMap(){
		
		randomInt();
		
		int temp = 1; //Only one bluprint is available ATM
		
		if(temp == 1)
			filePath = "blueprint.txt";
		
		else if(temp == 2)
			filePath = "blueprintV2.txt";
		
		else
			filePath = "blueprintV3.txt";
	}
	
	
	//generate random number between 1-3
	public void randomInt(){
		
		int number = 0;
		
		final int MIN = 1; 
		final int MAX = 100;
		
		number = MIN + ((int) Math.random() * (MAX - MIN + 1));
		
		//return number;
	}
	
	
	public void paint(Graphics g){		

		//background-color
		g.setColor(background);
		g.fillRect(0, 0, 500, 450);
		
		//paint the walls
		for(Wall b : map.getWalls()){
			b.draw(g);
		}
		
		//paint the players
		for(Player b : map.getPlayers()){
			b.draw(g);
		}
	}
}
