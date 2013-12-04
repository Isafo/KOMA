import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.io.*;

public class miniGame2 implements ActionListener, KeyListener {
	//timer
	Timer timer;
	public final double TIMECONSTANT = 7, TIMECONSTANTFIRST = TIMECONSTANT*2; //now doubles ---
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

	//mini game - 2 
	public final String texts[] = {"3.1415926535897", "this is written in java.", "this game is user friendly",
									"abcdefghijklm", "nopqrstuvwxyz", "now it's christmas again"};
	public final int TEXTSLENGTH = texts.length;
	public int textChosen, countChar = 0;
	public JLabel field;
	public StyledDocument styleDoc = new DefaultStyledDocument();
	public JTextPane textPane = new JTextPane(styleDoc);
	public SimpleAttributeSet attributeSet = new SimpleAttributeSet();
	
	public miniGame2() throws IOException {
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
		textPane.setText("");
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
		if(c == texts[textChosen].charAt(countChar)) {
			StyleConstants.setForeground(attributeSet, GREEN);
			styleDoc.setCharacterAttributes(countChar, 1, attributeSet, true);

			countChar++;
			if(countChar >= texts[textChosen].length()) {
				countChar = 0;
				nextGameMode();
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

	public void timerOver() {
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
	                timerOver();
	            }
	        }
        }
    }

	public void actionPerformed(ActionEvent e) {
		
	}

	public JPanel createField() {
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

		frame.pnlGrid.add(textPane);

		return frame.pnlGrid;
	}
}