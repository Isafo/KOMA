import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.io.*;

public class game extends JFrame 
	implements ActionListener, KeyListener{
	
	// header & labels
	public JLabel head, timeLeft, playerNameLabel, livesLabel, totalTimeLabel, totalExtraTimeLabel;
	private final int FONTSIZE = 15;
	
	//timer
	Timer timer;
	private final double TIMECONSTANT = 5.0, TIMECONSTANTFIRST = TIMECONSTANT*2; //now doubles ---
	private double time = TIMECONSTANTFIRST; 
	private double totalTime = 0, totalExtraTime = 0;

	//grid
	public int rows = 12;
	public int columns = 12;
	private String[][] theGrid = new String[rows][columns];
	private JLabel[][] slots = new JLabel[rows][columns];
	private final int pieceSize = 50;
	private Color scorelines = new Color(50, 50, 50);
	private final int GridW = rows * pieceSize;
	private final int GridH = columns * pieceSize;
	
	private Color backGround = new Color(245, 245, 245);
	private final Color GREEN = new Color(0, 245, 0);
	private final Color YELLOW = new Color(245, 245, 0);
	private final Color RED = new Color(245, 0, 0);
	JPanel pnlGrid = new JPanel();
	JPanel pnlHead = new JPanel();
	
	//player
	String name = "slots slots slots slots slots slots ERRBODY SLOTS SLOTS SLOTS SLOTS";
	private int lives = 4;

	//Mini games
	private static int NUMBEROFGAMES = 2;
	private int miniGames[] = new int[NUMBEROFGAMES];
	private double currentGame = 0;

	//mini game - 0
	private final String ALPHABET = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789"; //no O or 0
	private final int ALPHABETLENGTH = ALPHABET.length();
	private int countRows = 0, countColumns = 0;

	//mini game - 1
	private final String ALPHABETQWOP = "WOP"; //no O or 0
	private final int ALPHABETQWOPLENGTH = ALPHABETQWOP.length();
	
	public game() throws IOException {
		//header
		FlowLayout flow = new FlowLayout();
		pnlHead.setLayout(flow);
		pnlHead.setPreferredSize(new Dimension(40, 40));

		playerNameLabel = new JLabel();
		livesLabel = new JLabel();
		timeLeft = new JLabel();
		totalTimeLabel = new JLabel();
		totalExtraTimeLabel = new JLabel();

		playerNameLabel.setText(menu.getPlayerName());
		playerNameLabel.setFont(new Font("SanSerif", Font.PLAIN, FONTSIZE));
		livesLabel.setFont(new Font("SanSerif", Font.PLAIN, FONTSIZE));
		totalTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, FONTSIZE));
		totalExtraTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, FONTSIZE));

		timeLeft.setText("Time: " + new DecimalFormat("##.##").format(time));
		totalTimeLabel.setText("Total time: " + new DecimalFormat("##.##").format(totalTime));
		totalExtraTimeLabel.setText("Extra time: " + new DecimalFormat("##.##").format(totalExtraTime));

		pnlHead.add(playerNameLabel);
		
		if(true) {
			timer = new Timer(100, new CountdownTimerListener());
			timer.start();
			timeLeft.setText("Time: " + new DecimalFormat("##.##").format(time));
			timeLeft.setFont(new Font("SanSerif", Font.PLAIN, FONTSIZE));
			pnlHead.add(timeLeft);
		}
		
		pnlHead.add(livesLabel);
		pnlHead.add(totalTimeLabel);
		pnlHead.add(totalExtraTimeLabel);

		addKeyListener(this);
		pnlHead.addKeyListener(this);
		pnlGrid.addKeyListener(this);
		playerNameLabel.addKeyListener(this);
		livesLabel.addKeyListener(this);
		totalTimeLabel.addKeyListener(this);
		totalExtraTimeLabel.addKeyListener(this);
		pnlHead.setFocusable(true);
		pnlGrid.setFocusable(true);
		playerNameLabel.setFocusable(true);
		livesLabel.setFocusable(true);
		totalTimeLabel.setFocusable(true);
		totalExtraTimeLabel.setFocusable(true);

		//Container		
    	Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(pnlHead, BorderLayout.NORTH);
        c.add(pnlGrid, BorderLayout.CENTER); 

        pack();
        setLocationRelativeTo(null);
        
        setSize(600, 600);
        setTitle("Projekt oklart");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //pnlHead.requestFocusInWindow();
		runGame();	
	}

	private void fillGrid() {
		switch((int) currentGame) {
			case 0:
				for(int i = 0; i < rows; i++) {
					for(int j = 0; j < columns; j++) {
						char letter = ALPHABET.charAt((int) Math.round(Math.random()*(ALPHABETLENGTH-1)));
						theGrid[i][j] = String.valueOf(letter);
					}
				}
				break;
			case 1:
				for(int i = 0; i < rows; i++) {
					for(int j = 0; j < columns; j++) {
						char letter = ALPHABETQWOP.charAt((int) Math.round(Math.random()*(ALPHABETQWOPLENGTH-1)));
						theGrid[i][j] = String.valueOf(letter);
					}
				}
				break;
		}
	}

	private void initSlots() {
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

	private JPanel createGrid() {
		pnlGrid.setLayout(new GridLayout(1, 1));
		pnlGrid.setPreferredSize(new Dimension(GridW, GridH));
		pnlGrid.setLayout(new GridLayout(rows, columns));
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				pnlGrid.add(slots[i][j]);
			}
		}
		return pnlGrid;
	}

	public void clear() {
		switch((int) currentGame) {
			case 0:
				for(int j = 0; j < columns; j++){		
					for(int i = 0; i < rows; i++){
						slots [i] [j].setBackground(backGround);
						pnlGrid.remove(slots[i][j]);
					}
				}
				countRows = 0;
				countColumns = 0;
				break;
			case 1:
				for(int j = 0; j < columns; j++){		
					for(int i = 0; i < rows; i++){
						slots [i] [j].setBackground(backGround);
						pnlGrid.remove(slots[i][j]);
					}
				}
				countRows = 0;
				countColumns = 0;
				break;
		}
	}

	public void nextGame() {
		totalTime += time*2;
		totalExtraTime += time*2;
		time = TIMECONSTANT;
		timer.restart();
		timeLeft.setText("Time: " + new DecimalFormat("##.##").format(time));
		totalTimeLabel.setText("Total time: " + new DecimalFormat("##.##").format(totalTime));
		totalExtraTimeLabel.setText("Extra time: " + new DecimalFormat("##.##").format(totalExtraTime));

		clear();
		runGame();
	}

	public void selectRandomMiniGame() {
		currentGame = Math.round(Math.random() * (miniGames.length - 1));
		switch((int) currentGame) {
			case 0:
				rows = (int) Math.round(Math.random() * 2 + 2);
				columns = (int) Math.round(Math.random() * 2 + 2);
				break;
			case 1:
				rows = 4;
				columns = 4;
				break;
		}
	}

	public void runGame() {
		selectRandomMiniGame();

		switch((int) currentGame) {
			case 0:
				fillGrid();
				initSlots();
				createGrid();
				slots[countRows][countColumns].setBackground(YELLOW);
				break;
			case 1:
				fillGrid();
				initSlots();
				createGrid();
				slots[countRows][countColumns].setBackground(YELLOW);
				break;
		}
	}
	
	public void keyPressed(KeyEvent e) {
        switch((int) currentGame) {
			case 0:
	            char c = e.getKeyChar();
	            String keyString = String.valueOf(c).toUpperCase();
	            //System.out.println("KEY PRESSED char: " + c);
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
		            	nextGame();
	            	}
	            }
	            else {
	            	time -= 0.2;
	            	slots[countRows][countColumns].setBackground(RED);
	            }
	            //System.out.println("countRows, 2: " + countRows + ", " + countColumns);
	            //System.out.println("keyString: " + keyString + ", theGrid[][]: " + theGrid[countRows][countColumns-1]);
				break;
			case 1:
				c = e.getKeyChar();
	            keyString = String.valueOf(c).toUpperCase();
	            //System.out.println("KEY PRESSED char: " + c);
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
		            	nextGame();
	            	}
	            }
	            else {
	            	time -= 0.2;
	            	slots[countRows][countColumns].setBackground(RED);
	            }
	            //System.out.println("countRows, 2: " + countRows + ", " + countColumns);
	            //System.out.println("keyString: " + keyString + ", theGrid[][]: " + theGrid[countRows][countColumns-1]);
				break;
		}
    }

    public void keyTyped(KeyEvent e) {

    }
    
    public void keyReleased(KeyEvent e) {

    }

	public void timerOver() {
		lives--;
		livesLabel.setText("Lives remaining: " + String.valueOf(lives));
		if(!dead()) {
			time = TIMECONSTANT;
			timer.restart();
			timeLeft.setText("Time: " + new DecimalFormat("##.##").format(time));
			clear();
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
			if ((time -= 0.1) > 0) {
				totalTime += 0.1;
                timeLeft.setText("Time: " + new DecimalFormat("##.##").format(time));
				totalTimeLabel.setText("Total time: " + new DecimalFormat("##.##").format(totalTime));
            } 
            else {
            	totalTime += 0.1;
                timeLeft.setText("Time's up!");
                totalTimeLabel.setText("Total time: " + new DecimalFormat("##.##").format(totalTime));
                timer.stop();
                timerOver();
            }
        }
    }

	public void actionPerformed(ActionEvent e) {
		
	}
}