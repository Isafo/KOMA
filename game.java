import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.io.*;

public class Game implements ActionListener, KeyListener{
	
	public final int FONTSIZE = 15;
	
	public static double totalTime = 0, totalExtraTime = 0;
	
	//player
	public static int lives = 3;
	public static boolean timerStarted = false, firstMode = true;

	//Mini games
	public static int NUMBEROFGAMES = 3;
	public static int gamesWithoutBonus = 0;
	public int miniGames[] = new int[NUMBEROFGAMES];
	public static int currentGame = 0;
	public static int lastGame = 100;
	public static Random random = new Random();
	public static boolean transitionDone = false;
	public static miniGame0 game0;
	public static miniGame1 game1;
	public static miniGame2 game2;
	public static miniGame3 game3;
	public static Between transition;
	private static highscore high;
	public static Maze maze;
	
	public Game() throws IOException {
		high = new highscore();

		frame.pnlGrid.addKeyListener(this);
		// frame.timeLabel.addKeyListener(this);
		frame.pnlGrid.setFocusable(true);

		runGame();	
	}
	
	public static void trans() {
		transitionDone = true;
		Between transition = new Between();
	}
	
	public static void next() {
		if(!transitionDone) {
			trans();
		}
		else if(lives > 0){
			gamesWithoutBonus++;
			if(gamesWithoutBonus <= 7) {
				do{
					currentGame = random.nextInt(NUMBEROFGAMES);
				}while(currentGame == lastGame);//Don't play the same gamemode twice in a row
			}
			
			game0 = null;
			game1 = null;
			game2 = null;
			game3 = null;
			if(gamesWithoutBonus <= 7) {
				switch(currentGame) {
				case 0:
					try {
						game0 = new miniGame0();
						lastGame = 0;
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 1:
					try {
						game1 = new miniGame1();
						lastGame = 1;
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						game2 = new miniGame2();
						lastGame = 2;
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
			}
			else {
				try {
					game3 = new miniGame3();
					currentGame = 3;
					lastGame = 3;
					gamesWithoutBonus = 0;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void runGame() {
		try {
			 game3 = new miniGame3();
			 currentGame = 3;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveScore() throws IOException {
		high.sort(menu.getPlayerName(), totalTime); //sparar score
	}
	
	public void keyPressed(KeyEvent e) {
		if(!transitionDone) {
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
				case 3:
					game3.keyPressed(e);
					break;
				default:
					break;
			}
		}
    }

    public void keyTyped(KeyEvent e) {

    }
    
    public void keyReleased(KeyEvent e) {

    }
	
	public void actionPerformed(ActionEvent e) {
		
	}
}