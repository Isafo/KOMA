
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import java.util.Arrays;
import java.io.*;

public class game extends JFrame implements ActionListener{
	
	// header Buttons and timer
	public JLabel head, timeLeft;
	JPanel pnlColumn = new JPanel();
	Timer timer;
	private final int TIMECONSTANT = 5;
	private int time = TIMECONSTANT; 
	
	private Color backGround = new Color(245, 245, 245);
	JPanel pnlGrid = new JPanel();
	JPanel pnlHead = new JPanel();
	
	//player
	String name = "thiswillbereplaced";

	//Mini games
	private static int NUMBEROFGAMES = 1;
	private int miniGames[] = new int[NUMBEROFGAMES];
	private int currentGame = 0;
	
	public game() throws IOException {
		//header
		FlowLayout flow = new FlowLayout();
		pnlHead.setLayout(flow);
		pnlHead.setPreferredSize(new Dimension(40, 40));
		
		if(true) {
			timer = new Timer(1000, new CountdownTimerListener());
			timer.start();
			timeLeft = new JLabel(String.valueOf(time));
			pnlHead.add(timeLeft);
		}

		selectRandomMiniGame();

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
	}

	public void selectRandomMiniGame() {
		currentGame = Math.round(Math.random() * (miniGames.length - 1));
		runGame();
	}

	public void runGame() {
		currentGame;
	}

}