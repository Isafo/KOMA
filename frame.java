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
	public final int FONTSIZE = 15;
	public static JPanel pnlGrid;
	public static Container c;
	public static Header header;
	
	Game theGame;
		
	public frame() throws IOException {
		pnlGrid = new JPanel();
		header = new Header();

		addKeyListener(this);
		
		//Container		
    	c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(header, BorderLayout.NORTH);
        c.add(pnlGrid, BorderLayout.CENTER); 
		
		pack();
        setLocationRelativeTo(null);
        
        setSize(600, 600);
        setTitle("Projekt oklart");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

		Game();	
	}

	public void Game() {
		try {
			theGame = new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void keyPressed(KeyEvent arg0) {

	}

	public void keyReleased(KeyEvent arg0) {
		
	}

	public void keyTyped(KeyEvent arg0) {
		
	}

	public void actionPerformed(ActionEvent arg0) {
		
	}
}