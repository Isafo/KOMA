import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Maze extends JFrame {

	private Color background = new Color(35, 204, 80);
	Field map;
	public String filePath;
	
	public Maze() throws IOException {

		JFrame frame = new JFrame("test");
	
		randomMap();
		
		map = new Field(filePath);

		JPanel pane = new JPanel(){
			@Override		
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
			};
		};

        frame.add(pane);
        frame.setSize(467, 420);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		KeyStroke upKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
		Action upAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				System.out.println("upp");
			}
		};

		KeyStroke downKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false);
		Action downAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				System.out.println("down");
			}
		};

		KeyStroke leftKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
		Action leftAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				System.out.println("left");
			}
		};

		KeyStroke rightKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
		Action rightAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				System.out.println("right");
			}
		};

		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(upKeyStroke, "UP");
		frame.getRootPane().getActionMap().put("UP", upAction);
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(downKeyStroke, "DOWN");
		frame.getRootPane().getActionMap().put("DOWN", downAction);
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(leftKeyStroke, "LEFT");
		frame.getRootPane().getActionMap().put("LEFT", leftAction);
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(rightKeyStroke, "RIGHT");
		frame.getRootPane().getActionMap().put("RIGHT", rightAction);
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
}

