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
import java.util.Random;
import java.io.*;

public class frame extends JFrame 
	implements ActionListener, KeyListener{
	// header & labels
	public static JLabel head, timeLeft, playerNameLabel, livesLabel, totalTimeLabel, totalExtraTimeLabel;
	public final int FONTSIZE = 15;
	public static JPanel pnlGrid, pnlHead;
	public static Container c;
	
	game theGame;
		
	public frame() throws IOException {
		pnlHead = new JPanel();
		pnlGrid = new JPanel();
		
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
		timeLeft.setFont(new Font("SanSerif", Font.PLAIN, FONTSIZE));
		livesLabel.setFont(new Font("SanSerif", Font.PLAIN, FONTSIZE));
		totalTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, FONTSIZE));
		totalExtraTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, FONTSIZE));

		pnlHead.add(playerNameLabel);
		pnlHead.add(timeLeft);
		pnlHead.add(livesLabel);
		pnlHead.add(totalTimeLabel);
		pnlHead.add(totalExtraTimeLabel);

		addKeyListener(this);
		
		//Container		
    	c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(pnlHead, BorderLayout.NORTH);
        c.add(pnlGrid, BorderLayout.CENTER); 
		
		pack();
        setLocationRelativeTo(null);
        
        setSize(600, 600);
        setTitle("Projekt oklart");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

		game();	
	}

	public void game() {
		try {
			theGame = new game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void keyPressed(KeyEvent arg0) {
		// theGame.game0.keyPressed(arg0);
	}

	public void keyReleased(KeyEvent arg0) {
		
	}

	public void keyTyped(KeyEvent arg0) {
		
	}

	public void actionPerformed(ActionEvent arg0) {
		
	}
}