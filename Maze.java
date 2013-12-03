import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Maze extends JFrame {

	private Color background = new Color(35, 204, 80);
	Field map;
	//Header header;
	public String filePath;
	
	public Maze() throws IOException {

		JFrame frame = new JFrame("Maze");

		randomMap();
		
		map = new Field(filePath);
		//header = new Header();


		//panel for the game graphics
		final JPanel pane = new JPanel(){
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

		//panel for header
		//JPanel headerPane = new JPanel();

		//headerPane.add(header);

		//frame.add(header, BorderLayout.NORTH);
        frame.add(pane);
        frame.setSize(456, 450);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		
		//Keystroke for moving blue circle up
		KeyStroke upKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
		Action upAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				Player.up();
				if(checkCollision())
					pane.repaint();
				else
					Player.resetPosition();
			}
		};

		//Keystroke for moving blue circle down
		KeyStroke downKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false);
		Action downAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				Player.down();
				if(checkCollision())
					pane.repaint();
				else
					Player.resetPosition();
			}
		};

		//Keystroke for moving blue circle left
		KeyStroke leftKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
		Action leftAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				Player.left();
				if(checkCollision())
					pane.repaint();
				else
					Player.resetPosition();
			}
		};
		
		//Keystroke for moving blue circle right
		KeyStroke rightKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
		Action rightAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				Player.right();
				if(checkCollision())
					pane.repaint();
				else
					Player.resetPosition();
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

    //returns false if collision is detected
    public boolean checkCollision(){
   
        Rectangle player = Player.getPlayer();

        for(Wall b : map.getWalls()){
            Rectangle wall = new Rectangle(b.x, b.y, b.width, b.height);

			if(player.intersects(wall)){
				System.out.println("collision");
           	    return false;
           	}
		}
        return true;
	}
}

