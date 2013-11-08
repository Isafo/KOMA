import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import java.util.Arrays;
import java.io.*;

public class game extends JFrame 
	implements ActionListener, KeyListener{
	
	// header Buttons and timer
	public JLabel head, timeLeft, playerNameLabel, livesLabel, totalTimeLabel, totalExtraTimeLabel;
	
	Timer timer;
	private final int TIMECONSTANT = 6;
	private int time = TIMECONSTANT; 
	private int totalTime = 0, totalExtraTime = 0;

	//grid
	private int rows = 2;
	private int columns = 6;
	private String[][] theGrid = new String[rows][columns];
	private JLabel[][] slots = new JLabel[rows][columns];
	private final int pieceSize = 50;
	private Color scorelines = new Color(50, 50, 50);
	private final int GridW = rows * pieceSize;
	private final int GridH = columns * pieceSize;
	
	private Color backGround = new Color(245, 245, 245);
	private final Color GREEN = new Color(0, 245, 0);
	private final Color YELLOW = new Color(245, 245, 0);
	JPanel pnlGrid = new JPanel();
	JPanel pnlColumn = new JPanel();
	JPanel pnlHead = new JPanel();
	
	//player
	String name = "slots slots slots slots slots slots ERRBODY SLOTS SLOTS SLOTS SLOTS";
	private int lives = 5;

	//Mini games
	private static int NUMBEROFGAMES = 1;
	private int miniGames[] = new int[NUMBEROFGAMES];
	private double currentGame = 0;

	//0
	private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private final int ALPHABETLENGTH = ALPHABET.length();
	private int count1 = 0, count2 = 0;
	
	public game() throws IOException {
		//header
		FlowLayout flow = new FlowLayout();
		pnlHead.setLayout(flow);
		pnlHead.setPreferredSize(new Dimension(40, 40));

		playerNameLabel = new JLabel();
		livesLabel = new JLabel();
		totalTimeLabel = new JLabel();
		totalExtraTimeLabel = new JLabel();

		playerNameLabel.setText(menu.getPlayerName());
		livesLabel.setText("Lives remaining: " + String.valueOf(lives));
		totalTimeLabel.setText("Total time: " + String.valueOf(totalTime));
		totalExtraTimeLabel.setText("Extra time: " + String.valueOf(totalExtraTime));

		pnlHead.add(playerNameLabel);
		
		if(true) {
			timer = new Timer(1000, new CountdownTimerListener());
			timer.start();
			timeLeft = new JLabel("Time: " + String.valueOf(time));
			pnlHead.add(timeLeft);
		}
		
		pnlHead.add(livesLabel);
		pnlHead.add(totalTimeLabel);
		pnlHead.add(totalExtraTimeLabel);

		addKeyListener(this);
		pnlHead.addKeyListener(this);
		pnlColumn.addKeyListener(this);
		pnlGrid.addKeyListener(this);
		playerNameLabel.addKeyListener(this);
		livesLabel.addKeyListener(this);
		totalTimeLabel.addKeyListener(this);
		totalExtraTimeLabel.addKeyListener(this);
		pnlHead.setFocusable(true);
		pnlColumn.setFocusable(true);
		pnlGrid.setFocusable(true);
		playerNameLabel.setFocusable(true);
		livesLabel.setFocusable(true);
		totalTimeLabel.setFocusable(true);
		totalExtraTimeLabel.setFocusable(true);

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
        //pnlHead.requestFocusInWindow();
		runGame();	
	}

	private void fillGrid(){
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				char letter = ALPHABET.charAt((int) Math.round(Math.random()*(ALPHABETLENGTH-1)));
				theGrid[i][j] = String.valueOf(letter);
			}
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
			}
		}
	}

	private JPanel CreateGrid() {
		pnlGrid.setLayout(new GridLayout(1, 1));
		pnlGrid.setPreferredSize(new Dimension(GridW, GridH));
		pnlGrid.setLayout(new GridLayout(rows, columns));
		
		//pnlGrid.setBackground(p1c);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				pnlGrid.add(slots[i][j]);
			}
		}
		return pnlGrid;
	}

	public void clear(){
		switch((int) currentGame) {
			case 0:
				for(int j = 0; j < columns; j++){		
					for(int i = 0; i < rows; i++){
						slots [i] [j].setBackground(backGround);
						pnlGrid.remove(slots[i][j]);
					}
				}
				count1 = 0;
				count2 = 0;
				break;
			case 1:
				break;
		}
	}

	public void nextGame() {
		totalTime += time*2;
		totalExtraTime += time*2;
		time = TIMECONSTANT;
		timer.restart();
		timeLeft.setText("Time: " + String.valueOf(time));
		totalTimeLabel.setText("Total time: " + String.valueOf(totalTime));
		totalExtraTimeLabel.setText("Extra time: " + String.valueOf(totalExtraTime));

		clear();
		runGame();
	}

	public void selectRandomMiniGame() {
		currentGame = Math.round(Math.random() * (miniGames.length - 1));
	}

	public void runGame() {
		selectRandomMiniGame();

		switch((int) currentGame) {
			case 0:
				//gör ett rutsystem där bakgrunden ändras från grön till grått när den blivit klickad
				//neutral färg när den inte är nästa som ska klickas
				//Grid
				fillGrid();
				initSlots();
				CreateGrid();
				slots[count1][count2].setBackground(YELLOW);
				break;
			case 1:
				break;
		}
	}
	
	public void keyPressed(KeyEvent e) {
        switch((int) currentGame) {
			case 0:
	            char c = e.getKeyChar();
	            String keyString = String.valueOf(c).toUpperCase();
	            //System.out.println("KEY PRESSED char: " + c);
	            if(keyString.equals(theGrid[count1][count2])) {
	            	slots[count1][count2].setBackground(GREEN);
	            	if(count2 < columns-1)
	            		slots[count1][count2+1].setBackground(YELLOW);
	            	count2++;
	            	if(count2 >= columns && count1 < rows-1) {
	            		count2 = 0;
	            		count1++;
	            	}
	            	if(count2 >= columns) {//fix for not out of bounds on last row
		            	count2 = 0;
		            	nextGame();
	            	}
	            }
	            System.out.println("COUNT1, 2: " + count1 + ", " + count2);
	            //System.out.println("keyString: " + keyString + ", theGrid[][]: " + theGrid[count1][count2-1]);
				break;
			case 1:
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
			timeLeft.setText("Time: " + String.valueOf(time));
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