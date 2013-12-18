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
	public final double TIMECONSTANT = 37, TIMECONSTANTFIRST = 47; //now doubles ---
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

	//mini Game - 3
	public final String texts[] = {"this is an extra long mini game", "math is not as fun as programming", "koma is not the same thing as coma",
				"complete all rows for next level", "this is a good way to do instructions", "game modes become harder with time", "use your keyboard"};
	public final int TEXTSLENGTH = texts.length;
	public String totalText = "", totalTextPlain = "";
	public int countChar = 0, countChar2 = 0, countRows = 0;
	public int[] textChosen = new int[TEXTSLENGTH];
	public JLabel field;
	public StyledDocument styleDoc = new DefaultStyledDocument();
	public JTextPane textPane = new JTextPane(styleDoc);
	public SimpleAttributeSet attributeSet = new SimpleAttributeSet();
	public static Random random = new Random();
	
	public miniGame3() throws IOException {
		
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
		createField();
	}

	public void clearMode() {
		countChar = 0;
		//textPane.setText("");
		frame.pnlGrid.removeAll();
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
		if(c == totalTextPlain.charAt(countChar)) {
			StyleConstants.setForeground(attributeSet, GREEN);
			styleDoc.setCharacterAttributes(countChar+countRows*2, 1, attributeSet, true);

			countChar++;
			countChar2++;
			if(countChar2 >= texts[textChosen[countRows]].length()) {
				countChar2 = 0;
				countRows++;
			}
			if(countRows >= 4) {
				//Game.lives++; //win a life
				nextGameMode();
			}
			StyleConstants.setForeground(attributeSet, YELLOW);
			StyleConstants.setUnderline(attributeSet, true);
			styleDoc.setCharacterAttributes(countChar+countRows*2, 1, attributeSet, true);
		}
		else {
			if(countChar != 0)
				time -= 0.1;
			StyleConstants.setForeground(attributeSet, RED);
			styleDoc.setCharacterAttributes(countChar+countRows*2, 1, attributeSet, true);
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
			if(i == 0)
				totalText = texts[textChosen[i]];
			else
				totalText += "\n\n" + texts[textChosen[i]];
			totalTextPlain += texts[textChosen[i]];
		}
//		System.out.println(totalText);
//		System.out.println(totalTextPlain);

		textPane.setEditable(false);
		textPane.setText(totalText);
		textPane.setFont(new Font("SanSerif", Font.PLAIN, 30));
		textPane.addKeyListener(this);
		textPane.setFocusable(true);

		StyleConstants.setForeground(attributeSet, YELLOW);
		StyleConstants.setUnderline(attributeSet, true);

		styleDoc.setCharacterAttributes(0, 1, attributeSet, true);

		frame.pnlGrid.add(textPane);
		frame.c.add(frame.pnlGrid, BorderLayout.CENTER);

		return frame.pnlGrid;
	}
}