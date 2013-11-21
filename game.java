import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
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
	private final double TIMECONSTANT = 6, TIMECONSTANTFIRST = TIMECONSTANT*2; //now doubles ---
	private double time = TIMECONSTANTFIRST; 
	private double totalTime = 0, totalExtraTime = 0;
	private DecimalFormat df = new DecimalFormat("##.##");

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
	private final Color BLACK = new Color(0, 0, 0);
	JPanel pnlGrid = new JPanel();
	JPanel pnlHead = new JPanel();
	
	//player
	String name = "slots slots slots slots slots slots ERRBODY SLOTS SLOTS SLOTS SLOTS";
	private int lives = 4;
	private boolean started = false;
	private boolean timerStarted = false;

	//Mini games
	private static int NUMBEROFGAMES = 3;
	private int miniGames[] = new int[NUMBEROFGAMES];
	private double currentGame = 0;

	//mini game - 0
	private final String ALPHABET = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789"; //no O or 0
	private final int ALPHABETLENGTH = ALPHABET.length();
	private int countRows = 0, countColumns = 0;

	//mini game - 1
	private final String ALPHABETQWOP = "WOOOP"; //no O or 0
	private final int ALPHABETQWOPLENGTH = ALPHABETQWOP.length();

	//mini game - 2
	private final String texts[] = {"3.1415926535897932384", "This program is written in java", "this game is so user friendly"};
	private final int TEXTSLENGTH = texts.length;
	private int textChosen, countChar = 0;
	private JLabel field;
	private StyledDocument styleDoc = new DefaultStyledDocument();
	private JTextPane textPane = new JTextPane(styleDoc);
	private SimpleAttributeSet attributeSet = new SimpleAttributeSet();	
	
	public game() throws IOException {
		//header
		FlowLayout flow = new FlowLayout();
		pnlHead.setLayout(flow);
		pnlHead.setPreferredSize(new Dimension(40, 40));

		System.out.println("TEXTSLENGTH: " + TEXTSLENGTH);

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

		timeLeft.setText("Time: " + df.format(time));
		totalTimeLabel.setText("Total time: " + df.format(totalTime));
		totalExtraTimeLabel.setText("Extra time: " + df.format(totalExtraTime));

		pnlHead.add(playerNameLabel);
		
		if(true) {
			timer = new Timer(100, new CountdownTimerListener());
			timeLeft.setText("Time: " + df.format(time));
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

		runGame();	
	}

	public void nextGame() {
		totalTime += time*1.5;
		totalExtraTime += time*1.5;
		time = TIMECONSTANT;
		timer.restart();
		timeLeft.setText("Time: " + df.format(time));
		totalTimeLabel.setText("Total time: " + df.format(totalTime));
		totalExtraTimeLabel.setText("Extra time: " + df.format(totalExtraTime));

		clear();
		runGame();
	}

	public void selectRandomMiniGame() {
		if(!started) {
			currentGame = 2;
			started = true;
		}
		else 
			currentGame = Math.round(Math.random() * (miniGames.length - 1));
		switch((int) currentGame) {
			case 0:
				rows = (int) Math.round(Math.random() * 2 + 2);
				columns = (int) Math.round(Math.random() * 2 + 2);
				break;
			case 1:
				rows = 3;
				columns = 4;
				break;
			case 2:
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
			case 2:
				createField();
				break;
		}
	}

	public void clear() {
		switch((int) currentGame) {
			case 0:
				for(int j = 0; j < columns; j++){		
					for(int i = 0; i < rows; i++){
						slots [i] [j].setBackground(backGround);
						pnlGrid.remove(slots[i][j]);
						pnlGrid.removeAll();
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
						pnlGrid.removeAll();
					}
				}
				countRows = 0;
				countColumns = 0;
				break;
			case 2:
				countChar = 0;
				pnlGrid.remove(textPane);
				pnlGrid.removeAll();
				break;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if(!timerStarted) {
			timer.start();
			timerStarted = true;
		}
		if(dead())
			return;
		
		char c = e.getKeyChar();
		String keyString;
        switch((int) currentGame) {
			case 0:
			case 1:
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
		            	nextGame();
	            	}
	            }
	            else {
	            	if(countRows + countColumns != 0)
	            		time -= 0.2;
            		slots[countRows][countColumns].setBackground(RED);
	            }
				break;
			case 2:
				if(c == texts[textChosen].charAt(countChar)) {
					StyleConstants.setForeground(attributeSet, GREEN);
					styleDoc.setCharacterAttributes(countChar, 1, attributeSet, true);

					countChar++;
					if(countChar >= texts[textChosen].length()) {
						countChar = 0;
						nextGame();
					}

					StyleConstants.setForeground(attributeSet, YELLOW);
					StyleConstants.setUnderline(attributeSet, true);
					styleDoc.setCharacterAttributes(countChar, 1, attributeSet, true);
				}
				else {
					if(countChar != 0)
						time -= 0.1;
					StyleConstants.setForeground(attributeSet, RED);
					styleDoc.setCharacterAttributes(countChar, 1, attributeSet, true);
				}
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
			timeLeft.setText("Time: " + df.format(time));
			clear();
			runGame();
		}
		else {
			//score visas, avslutas
			System.out.println("Final score: " + df.format(totalTime));
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
			if(!dead()) {
				if((time -= 0.1) > 0) {
					totalTime += 0.1;
	                timeLeft.setText("Time: " + df.format(time));
					totalTimeLabel.setText("Total time: " + df.format(totalTime));
	            } 
	            else {
	            	totalTime += 0.1;
	                timeLeft.setText("Time's up!");
	                totalTimeLabel.setText("Total time: " + df.format(totalTime));
	                timer.stop();
	                timerOver();
	            }
	            switch((int) currentGame) {
	            	case 0:
	            	case 1:
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
			       		break;
			       	case 2:
			       		break;
			    }
	        }
        }
    }

	public void actionPerformed(ActionEvent e) {
		
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

	private JPanel createField() {
		textChosen = (int) Math.round(Math.random() * (TEXTSLENGTH-1));

		textPane.setText(texts[textChosen]);
		textPane.setFont(new Font("SanSerif", Font.PLAIN, 35));
		textPane.setEditable(false);
		textPane.addKeyListener(this);
		textPane.setFocusable(true);
		texts[textChosen].toUpperCase();

		StyleConstants.setForeground(attributeSet, YELLOW);
		StyleConstants.setUnderline(attributeSet, true);

		styleDoc.setCharacterAttributes(0, 1, attributeSet, true);

		pnlGrid.add(textPane);

		return pnlGrid;
	}

}