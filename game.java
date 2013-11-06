
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.LineBorder;

import java.util.Arrays;
import java.io.*;

public class game extends JFrame implements ActionListener{
	
	// header Buttons and timer
	public JLabel head, timeLeft, playerNameLabel, livesLabel;
	JPanel pnlColumn = new JPanel();
	Timer timer;
	private final int TIMECONSTANT = 5;
	private int time = TIMECONSTANT; 
	
	private Color backGround = new Color(245, 245, 245);
	JPanel pnlGrid = new JPanel();
	JPanel pnlHead = new JPanel();
	
	//player
	String name = "thiswillbereplaced";
	private int lives = 5;

	//Mini games
	private static int NUMBEROFGAMES = 1;
	private int miniGames[] = new int[NUMBEROFGAMES];
	private double currentGame = 0;
	
	public game() throws IOException {
		//header
		FlowLayout flow = new FlowLayout();
		pnlHead.setLayout(flow);
		pnlHead.setPreferredSize(new Dimension(40, 40));

		playerNameLabel = new JLabel();
		livesLabel = new JLabel();

		pnlHead.add(playerNameLabel);
		pnlHead.add(livesLabel);

		playerNameLabel.setText(menu.getPlayerName());
		
		if(true) {
			timer = new Timer(1000, new CountdownTimerListener());
			timer.start();
			timeLeft = new JLabel(String.valueOf(time));
			pnlHead.add(timeLeft);
		}

		//Container		
    	Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(pnlHead, BorderLayout.NORTH);
        c.add(pnlColumn, BorderLayout.CENTER);
        c.add(pnlGrid, BorderLayout.SOUTH); 

        pack();
        setLocationRelativeTo(null);
        
        setSize(600, 600);
        setTitle("Projekt oklart");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        selectRandomMiniGame();
		runGame();	
	}

	public void selectRandomMiniGame() {
		currentGame = Math.round(Math.random() * (miniGames.length - 1));
	}

	public void runGame() {
		selectRandomMiniGame();

	}
	
	public void timerOver() {
		lives--;
		if(!dead()) {
			time = TIMECONSTANT;
			timeLeft.setText(String.valueOf(time));
			runGame();
		}
	}

	public boolean dead() {
		if(lives <= 0)
			return true;
		else
			return false;
	}
	
   class CountdownTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
			if (--time > 0) {
                timeLeft.setText(String.valueOf(time));
            } else {
                timeLeft.setText("Time's up, bitch!");
                timer.stop();
                timerOver();
            }
        }
    }

	public void actionPerformed(ActionEvent e) {
		
	}

}