import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.LineBorder;

import java.text.DecimalFormat;
import java.io.*;

public class miniGame1 implements ActionListener, KeyListener {
	//timer
	Timer timer;
	public final double TIMECONSTANT = 5.5, TIMECONSTANTFIRST = TIMECONSTANT*2; //now doubles ---
	public double time = TIMECONSTANT; 
	public DecimalFormat df = new DecimalFormat("##.##");

	//grid
	public int rows = 4;
	public int columns = 4;
	private String[][] theGrid = new String[rows][columns];
	private JLabel[][] slots = new JLabel[rows][columns];
	private final int pieceSize = 50;
	public Color scorelines = new Color(50, 50, 50);
	public final int GridW = rows * pieceSize;
	public final int GridH = columns * pieceSize;

	private Color backGround = new Color(245, 245, 245);
	private final Color GREEN = new Color(0, 245, 0);
	private final Color YELLOW = new Color(245, 245, 0);
	private final Color RED = new Color(245, 0, 0);
	
	//mini Game - 1
	public final String ALPHABETQWOP = "WOOOP"; //no O or 0
	public final String ALPHABETQWOPHARD = "WWOOOPP";
	public final int ALPHABETQWOPLENGTH = ALPHABETQWOP.length();
	public final int ALPHABETQWOPHARDLENGTH = ALPHABETQWOPHARD.length();
	public int countRows = 0, countColumns = 0;
	
	public miniGame1() throws IOException {
		
		timer = new Timer(100, new CountdownTimerListener());
		if(!Game.firstMode) {
			timer.start();
		}
		else
			time = TIMECONSTANTFIRST;
		 
		setupGame();
	}

	public void nextGameMode() {
		Game.totalTime += time*1.5;
		Game.totalExtraTime += time*1.5;
		time = TIMECONSTANT;
		timer.restart();

		clearMode();
		Game.next();
	}

	public void setupGame() {
		fillGrid();
		initSlots();
		createGrid();
		slots[countRows][countColumns].setBackground(YELLOW);
	}

	public void clearMode() {
		for(int j = 0; j < columns; j++){		
			for(int i = 0; i < rows; i++){
				slots [i] [j].setBackground(backGround);
				frame.pnlGrid.remove(slots[i][j]);
				frame.pnlGrid.removeAll();
			}
		}
		countRows = 0;
		countColumns = 0;
		timer.stop();
	}
	
	public void keyPressed(KeyEvent e) {
		if(!Game.timerStarted && Game.firstMode) {
			timer.start();
			Game.timerStarted = true;
			Game.firstMode = false;
		}
		if(dead())
			return;
		
		char c = e.getKeyChar();
		String keyString;
        keyString = String.valueOf(c).toUpperCase();

        if(keyString.equals(theGrid[countRows][countColumns])) {
        	slots[countRows][countColumns].setBackground(GREEN);
        	if(countColumns < columns-1)
        		slots[countRows][countColumns+1].setBackground(YELLOW);
        	countColumns++;
        	if(countColumns >= columns && countRows < rows-1) {
        		countColumns = 0;
        		countRows++;
        		slots[countRows][countColumns].setBackground(YELLOW);
        	}
        	if(countColumns >= columns) {//fix for not out of bounds on last row
            	countColumns = 0;
            	nextGameMode();
        	}
        }
        else {
        	if(countRows + countColumns != 0)
        		time -= 0.2;
    		slots[countRows][countColumns].setBackground(RED);
        }
    }

    public void keyTyped(KeyEvent e) {

    }
    
    public void keyReleased(KeyEvent e) {

    }

	public void timerOver() throws IOException {
		Game.lives--;
		Header.setLives();
		if(!dead()) {
			nextGameMode();
		}
		else {
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
			if(!dead()) {
				if((time -= 0.1) > 0) {
					Header.setTime(time);
					Game.totalTime += 0.1;
	            } 
	            else {
	                timer.stop();

	                try {
						timerOver();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	            }
	            if(time < 1) {
	            	if((time < 1.6 && time > 1.4) || (time < 1.2 && time > 1.0) || (time < 0.8 && time > 0.6) || (time < 0.4 && time > 0.2)) {
		            	for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								slots [i] [j].setFont(new Font("SanSerif", Font.PLAIN, 45));
							}
						}
					}
					else {
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								slots [i] [j].setFont(new Font("SanSerif", Font.PLAIN, 75));
							}
						}
					}
	            }
	        }
        }
    }

	public void actionPerformed(ActionEvent e) {
		
	}

	public void fillGrid() {
		if(Game.totalTime <= 80) {
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < columns; j++) {
					char letter = ALPHABETQWOP.charAt((int) Math.round(Math.random()*(ALPHABETQWOPLENGTH-1)));
					theGrid[i][j] = String.valueOf(letter);
				}
			}
		}
		else {
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < columns; j++) {
					char letter = ALPHABETQWOPHARD.charAt((int) Math.round(Math.random()*(ALPHABETQWOPHARDLENGTH-1)));
					theGrid[i][j] = String.valueOf(letter);
				}
			}
		}
	}

	public void initSlots() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				slots [i] [j] = new JLabel ();
				slots [i] [j].setPreferredSize(new Dimension(pieceSize, pieceSize));
				slots [i] [j].setHorizontalAlignment (SwingConstants.CENTER);
				slots [i] [j].setBorder (new LineBorder (scorelines));
				slots [i] [j].setOpaque(true);
				slots [i] [j].setBackground(backGround);
				slots [i] [j].setText(theGrid[i][j]);
				slots [i] [j].setFont(new Font("SanSerif", Font.PLAIN, 65));
			}
		}
	}

	public JPanel createGrid() {
		frame.pnlGrid.setLayout(new GridLayout(1, 1));
		frame.pnlGrid.setPreferredSize(new Dimension(GridW, GridH));
		frame.pnlGrid.setLayout(new GridLayout(rows, columns));
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				frame.pnlGrid.add(slots[i][j]);
			}
		}
		return frame.pnlGrid;
	}
}