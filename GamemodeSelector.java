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

public class GamemodeSelector{

	private boolean started = false;
	public double currentGame = 0;
	public int rows, columns;
	public static boolean cleanThis = false;
	public boolean notDead = true;
	private static int NUMBEROFGAMES = 3;
	private int miniGames[] = new int[NUMBEROFGAMES];

	public GamemodeSelector() throws IOException {
		while(!notDead){
			selectRandomMiniGame();

			currentGame = 0;

			switch((int) currentGame) {
				case 0:
					Maze minigame_maze = new Maze();

				case 1:
					//run minigame 2
					// minigame2(rows, columns, currentGame);
				case 2:
					//run minigame 3
					// minigame3(rows, columns, currentGame);
			}
		//nextGame();
		}
	}

	public void nextGame() {
/*		totalTime += time*2;
		totalExtraTime += time*2;
		time = TIMECONSTANT;
		timer.restart();
		timeLeft.setText("Time: " + df.format(time));
		totalTimeLabel.setText("Total time: " + df.format(totalTime));
		totalExtraTimeLabel.setText("Extra time: " + df.format(totalExtraTime));

		clear();*/
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


/*	public void clear() {
	switch((int) currentGame) {
		case 0:
			cleanThis = true;
			Maze.paint();
			break;
		}
	}*/
}