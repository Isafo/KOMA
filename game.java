
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.LineBorder;

import java.util.Arrays;
import java.io.*;

public class game extends JFrame implements ActionListener{
	
	// header Buttons and timer
	public JLabel head, timeLeft, playerNameLabel, livesLabel, totalTimeLabel;
	JPanel pnlColumn = new JPanel();
	Timer timer;
	private final int TIMECONSTANT = 5;
	private int time = TIMECONSTANT; 
	private int totalTime = 0;
	
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
		totalTimeLabel = new JLabel();

		playerNameLabel.setText(menu.getPlayerName());
		livesLabel.setText("Lives remaining: " + String.valueOf(lives));
		totalTimeLabel.setText("Total time: " + String.valueOf(totalTime));

		pnlHead.add(playerNameLabel);
		
		if(true) {
			timer = new Timer(1000, new CountdownTimerListener());
			timer.start();
			timeLeft = new JLabel("Time: " + String.valueOf(time));
			pnlHead.add(timeLeft);
		}

		
		pnlHead.add(livesLabel);
		pnlHead.add(totalTimeLabel);


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

		switch(currentGame) {
			case 0:
				
				break;
			case 1:
				break;
		}
	}
	
	public void timerOver() {
		lives--;
		livesLabel.setText("Lives remaining: " + String.valueOf(lives));
		if(!dead()) {
			time = TIMECONSTANT;
			timer.restart();
			timeLeft.setText("Time: " + String.valueOf(time));
			runGame();
		}
		else {
			//score visas, avslutas
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
			if (--time > -1) {
				totalTime++;
                timeLeft.setText("Time: " + String.valueOf(time));
                totalTimeLabel.setText("Total time: " + String.valueOf(totalTime));
            } 
            else {
            	totalTime++;
                timeLeft.setText("Time's up, bitch!");
                totalTimeLabel.setText("Total time: " + String.valueOf(totalTime));
                timer.stop();
                timerOver();
            }
        }
    }

	public void actionPerformed(ActionEvent e) {
		
	}

}