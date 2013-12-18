import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Maze extends JFrame {

	private Color background = new Color(35, 204, 80);
	Field map;
	Header header;
	static JFrame frame;
	public String filePath;
	boolean won = false;

	//timer declatarions
    Timer timer;
    public final double TIMECONSTANT = 20, TIMECONSTANTFIRST = TIMECONSTANT*2;
    public double time = TIMECONSTANT;
    public static Random random = new Random();
    public DecimalFormat df = new DecimalFormat("##.##");
    JPanel pane = new JPanel();
	
	public Maze() throws IOException {

		frame = new JFrame("Maze");

		randomMap();
		
		map = new Field(filePath);
		
		//start timer
        timer = new Timer(100, new CountdownTimerListener());
        timer.start();
		
		header = new Header();


		//panel for the game graphics
		pane = new JPanel(){
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
		JPanel headerPane = new JPanel();

		headerPane.add(header);

		frame.add(header, BorderLayout.NORTH);
        frame.add(pane);
        frame.setSize(456, 450);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		
		/*
		 * Keystroke
		 */
		
		//Keystroke for moving blue circle up
		KeyStroke upKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
		KeyStroke wKeyStroke = KeyStroke.getKeyStroke('w');
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
		KeyStroke sKeyStroke = KeyStroke.getKeyStroke('s');
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
		KeyStroke aKeyStroke = KeyStroke.getKeyStroke('a');
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
		KeyStroke dKeyStroke = KeyStroke.getKeyStroke('d');
		Action rightAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				Player.right();
				if(checkCollision())
					pane.repaint();
				else
					Player.resetPosition();
			}
		};

		//Up
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(upKeyStroke, "UP");
		frame.getRootPane().getActionMap().put("UP", upAction);
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(wKeyStroke, "UP");
		frame.getRootPane().getActionMap().put("UP", upAction);
		
		//Down
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(downKeyStroke, "DOWN");
		frame.getRootPane().getActionMap().put("DOWN", downAction);
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(sKeyStroke, "DOWN");
		frame.getRootPane().getActionMap().put("DOWN", downAction);
		
		//left
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(leftKeyStroke, "LEFT");
		frame.getRootPane().getActionMap().put("LEFT", leftAction);
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(aKeyStroke, "LEFT");
		frame.getRootPane().getActionMap().put("LEFT", leftAction);
		
		//right
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(rightKeyStroke, "RIGHT");
		frame.getRootPane().getActionMap().put("RIGHT", rightAction);
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(dKeyStroke, "RIGHT");
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

    /*
     * Collision detection
     */
    public boolean checkCollision(){
   
        Rectangle player = Player.getPlayer();
        Rectangle end  = Ending.getEnding();

        for(Wall b : map.getWalls()){
            Rectangle wall = new Rectangle(b.x, b.y, b.width, b.height);

			if(player.intersects(wall)){
           	    return false;
           	}
			
			else if(player.intersects(end)){
				if(won == false){
					//try {
						frame.dispose();
						Game.next();
						won = true;
					//} catch (IOException e) {
						// TODO Auto-generated catch block
					//	e.printStackTrace();
					//}			
				}
			}
		}
        return true;
	}
    
    /*
     * Timer
     */
    
    public void timerOver() throws IOException {
        Game.lives--;
        if(!dead()) {
        	frame.remove(pane);
        	frame.invalidate();
            frame.validate();
            
            //choose next minigame
            Game.currentGame = random.nextInt(Game.NUMBEROFGAMES);
            Game.currentGame = 1; // until other minigames are added
            Between b = new Between();
            frame.add(b);
            
        	//frame.dispose();
//        	Header.setLives();
//			
//            Game.next();
        }
        else{
                //score visas, avslutas
                System.out.println("Final score: " + df.format(Game.totalTime));
                Game.saveScore();
        }
    }

	public boolean dead() {
	        if(Game.lives <= 0)
	                return true;
	        else
	                return false;
	}

	class CountdownTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            if(!dead()){
                if((time -= 0.1) > 0) {
                    Game.totalTime += 0.1;
                    Header.setTime(time);
	            } 
	            
                else {
	                timer.stop();
	                try{
		                timerOver();
	                } catch(IOException e1) {
                        e1.printStackTrace();
	                }
	            }
	        }
            
            else{
            	frame.dispose();
            } 
		}
	}
}

