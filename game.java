import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.io.*;

public class game implements ActionListener, KeyListener{
	
	public final int FONTSIZE = 15;
	
	public static double totalTime = 0, totalExtraTime = 0;
	
	//player
	public static int lives = 4;
	public static boolean timerStarted = false, firstMode = true;

	//Mini games
	public static int NUMBEROFGAMES = 3;
	public int miniGames[] = new int[NUMBEROFGAMES];
	public static int currentGame = 0;
	public static int lastGame = 0;
	public static Random random = new Random();
	public static miniGame0 game0;
	public static miniGame1 game1;
	public static miniGame2 game2;
	
	public game() throws IOException {
		frame.playerNameLabel.setText(menu.getPlayerName());
		frame.playerNameLabel.setFont(new Font("SanSerif", Font.PLAIN, FONTSIZE));
		frame.livesLabel.setFont(new Font("SanSerif", Font.PLAIN, FONTSIZE));
		frame.totalTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, FONTSIZE));
		frame.totalExtraTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, FONTSIZE));

		frame.pnlHead.addKeyListener(this);
		frame.pnlGrid.addKeyListener(this);
		frame.playerNameLabel.addKeyListener(this);
		frame.livesLabel.addKeyListener(this);
		frame.totalTimeLabel.addKeyListener(this);
		frame.totalExtraTimeLabel.addKeyListener(this);
		frame.pnlHead.setFocusable(true);
		frame.pnlGrid.setFocusable(true);
		frame.playerNameLabel.setFocusable(true);
		frame.livesLabel.setFocusable(true);
		frame.totalTimeLabel.setFocusable(true);
		frame.totalExtraTimeLabel.setFocusable(true);

		runGame();	
	}
	
	public static void next() {
		while(currentGame == lastGame)
			currentGame = random.nextInt(NUMBEROFGAMES);
		
		game0 = null;
		game1 = null;
		game2 = null;

		switch(currentGame) {
		case 0:
			try {
				game0 = new miniGame0();
			} catch (IOException e) {
				e.printStackTrace();
			}
			lastGame = 0;
			break;
		case 1:
			try {
				game1 = new miniGame1();
			} catch (IOException e) {
				e.printStackTrace();
			}
			lastGame = 1;
			break;
		case 2:
			try {
				game2 = new miniGame2();
			} catch (IOException e) {
				e.printStackTrace();
			}
			lastGame = 2;
			break;
		}
	}

	public void runGame() {
		try {
			game0 = new miniGame0();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveScore() {
		highscore.sort(menu.getPlayerName(), totalTime); //sparar score
	}
	
	public void keyPressed(KeyEvent e) {
		switch(currentGame) {
			case 0:
				game0.keyPressed(e);
				break;
			case 1:
				game1.keyPressed(e);
				break;
			case 2:
				game2.keyPressed(e);
				break;
		}
    }

    public void keyTyped(KeyEvent e) {

    }
    
    public void keyReleased(KeyEvent e) {

    }
	
	public void actionPerformed(ActionEvent e) {
		
	}
}