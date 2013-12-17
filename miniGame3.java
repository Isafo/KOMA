import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.io.*;
import java.util.Random;

public class miniGame3 implements ActionListener, KeyListener {
	//timer
	Timer timer;
	public final double TIMECONSTANT = 30, TIMECONSTANTFIRST = 40; //now doubles ---
	public double time = TIMECONSTANT; 
	public DecimalFormat df = new DecimalFormat("##.##");

	//grid
	public int rows = 12;
	public int columns = 12;
	private final int pieceSize = 50;
	public Color scorelines = new Color(50, 50, 50);
	public final int GridW = rows * pieceSize;
	public final int GridH = columns * pieceSize;

	private final Color GREEN = new Color(0, 245, 0);
	private final Color YELLOW = new Color(245, 245, 0);
	private final Color RED = new Color(245, 0, 0);

	//mini game - 3
	public final String texts[] = {"this is a bonus mini game", "math is not as fun as programming", "koma isn't the same thing as coma",
				"complete all rows for a bonus life", "this is a good way to do instructions", "game modes become harder with time"};
	public final int TEXTSLENGTH = texts.length;
	public int countChar = 0, countRows = 0;
	public int[] textChosen = new int[TEXTSLENGTH];
	public JLabel field;
	public StyledDocument styleDoc = new DefaultStyledDocument();
	public JTextPane[] textPane = new JTextPane[TEXTSLENGTH]; //(styleDoc)
	public JTextPane textPane0 = new JTextPane(styleDoc);
	public JTextPane textPane1 = new JTextPane(styleDoc);
	public SimpleAttributeSet attributeSet = new SimpleAttributeSet();
	public static Random random = new Random();
	
	public miniGame3() throws IOException {
		frame.playerNameLabel.setText(menu.getPlayerName());
		frame.timeLeft.setText("Time: " + df.format(time));
		frame.livesLabel.setText("Lives: " + df.format(game.lives));
		frame.totalTimeLabel.setText("Total time: " + df.format(game.totalTime));
		frame.totalExtraTimeLabel.setText("Extra time: " + df.format(game.totalExtraTime));
		
		timer = new Timer(100, new CountdownTimerListener());
		if(!game.firstMode) {
			timer.start();
		}
		else
			time = TIMECONSTANTFIRST;
		 
		setupGame();
	}

	public void nextGameMode() {
		game.totalTime += time*1.5;
		game.totalExtraTime += time*1.5;
		time = TIMECONSTANT;
		timer.restart();
		frame.livesLabel.setText("Lives: " + String.valueOf(game.lives));
		frame.timeLeft.setText("Time: " + df.format(time));
		frame.totalTimeLabel.setText("Total time: " + df.format(game.totalTime));
		frame.totalExtraTimeLabel.setText("Extra time: " + df.format(game.totalExtraTime));

		clearMode();
		game.next();
	}

	public void setupGame() {
		createField();
	}

	public void clearMode() {
		countChar = 0;
		//textPane.setText("");
		frame.pnlGrid.removeAll();
		timer.stop();
	}
	
	public void keyPressed(KeyEvent e) {
		if(!game.timerStarted && game.firstMode) {
			timer.start();
			game.timerStarted = true;
			game.firstMode = false;
		}
		if(dead())
			return;
		
		char c = e.getKeyChar();
		if(c == texts[textChosen[countRows]].charAt(countChar)) {
			StyleConstants.setForeground(attributeSet, GREEN);
			styleDoc.setCharacterAttributes(countChar, 1, attributeSet, true);

			countChar++;
			if(countChar >= texts[textChosen[countRows]].length()) {
				countChar = 0;
				countRows++;
				System.out.println(countRows);
				if(countRows >= 4) {
					game.lives++; //win a life
					nextGameMode();
				}
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
    }

    public void keyTyped(KeyEvent e) {

    }
    
    public void keyReleased(KeyEvent e) {

    }

	public void timerOver() throws IOException {
		game.lives--;
		frame.livesLabel.setText("Lives remaining: " + String.valueOf(game.lives));
		if(!dead()) {
			nextGameMode();
		}
		else {
			//score visas, avslutas
			System.out.println("Final score: " + df.format(game.totalTime));
			game.saveScore();
		}
	}

	public boolean dead() {
		if(game.lives <= 0)
			return true;
		else
			return false;
	}
	
   class CountdownTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
			if(!dead()) {
				if((time -= 0.1) > 0) {
					game.totalTime += 0.1;
					frame.timeLeft.setText("Time: " + df.format(time));
					frame.totalTimeLabel.setText("Total time: " + df.format(game.totalTime));
	            } 
	            else {
	            	frame.timeLeft.setText("Time's up!");
	            	frame.totalTimeLabel.setText("Total time: " + df.format(game.totalTime));
	                timer.stop();
	                try {
						timerOver();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	            }
	            if(time < 1) {
	            	if((time < 1.6 && time > 1.4) || (time < 1.2 && time > 1.0) || (time < 0.8 && time > 0.6) || (time < 0.4 && time > 0.2)) {
						// StyleConstants.setFontSize(attributeSet, 35);
						// styleDoc.setCharacterAttributes(countChar, 1, attributeSet, true);
					}
					else {
						// StyleConstants.setFontSize(attributeSet, 45);
						// styleDoc.setCharacterAttributes(countChar, 1, attributeSet, true);
					}
	            }
	        }
        }
    }

	public void actionPerformed(ActionEvent e) {
		
	}

	public JPanel createField() {
		frame.pnlGrid.removeAll();
		frame.pnlGrid = new JPanel();
		for(int i = 0; i < TEXTSLENGTH-2; i++) {
			textChosen[i] = random.nextInt(TEXTSLENGTH);
			texts[textChosen[i]].toUpperCase();
			System.out.println(texts[textChosen[i]]);
			textPane[i] = new JTextPane(styleDoc);
			textPane[i].setEditable(false);
			textPane[i].setText(texts[textChosen[i]]);
			textPane[i].setFont(new Font("SanSerif", Font.PLAIN, 30));
			textPane[i].addKeyListener(this);
			textPane[i].setFocusable(true);
			frame.pnlGrid.add(textPane[i]);
		}

		// textPane0.setEditable(false);
		// textPane0.setText(texts[textChosen[0]]);
		// textPane0.setFont(new Font("SanSerif", Font.PLAIN, 30));
		// textPane0.addKeyListener(this);
		// textPane0.setFocusable(true);
		// textPane1.setEditable(false);
		// textPane1.setText(texts[textChosen[1]]);
		// textPane1.setFont(new Font("SanSerif", Font.PLAIN, 30));
		// textPane1.addKeyListener(this);
		// textPane1.setFocusable(true);

		StyleConstants.setForeground(attributeSet, YELLOW);
		StyleConstants.setUnderline(attributeSet, true);

		styleDoc.setCharacterAttributes(0, 1, attributeSet, true);

		// frame.pnlGrid.add(textPane0);
		// frame.pnlGrid.add(textPane1);
		frame.c.add(frame.pnlGrid, BorderLayout.CENTER);

		return frame.pnlGrid;
	}
}