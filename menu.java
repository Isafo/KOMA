

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class menu extends JFrame implements MouseListener{
	
	
	Container c = getContentPane();	
	JPanel menu = new JPanel();
	JPanel head = new JPanel();
	JPanel playerNames = new JPanel();
	JLabel startGame;

	public menu() throws IOException {
		
		FlowLayout flow = new FlowLayout();
		
		JLabel start = new JLabel("Epic game!", JLabel.RIGHT);
		JLabel header = new JLabel("KOMA", JLabel.CENTER);
		header.setFont(new Font("SanSerif", Font.PLAIN, 99));
		
		startGame = new JLabel("Play game");
		startGame.setFont(new Font("SanSerif", Font.PLAIN, 30));
		startGame.addMouseListener(this);

		head.add(header);
		menu.add(start);
		menu.add(startGame);
		menu.setLayout(flow);
		menu.setPreferredSize(new Dimension(150, 150));
		
		Container c = getContentPane();
	    c.setLayout(new BorderLayout());
	    c.add(head, BorderLayout.NORTH);
	    c.add(menu, BorderLayout.CENTER);
	    c.add(playerNames, BorderLayout.SOUTH);
	    
	    setSize(600,600);
	    setLocationRelativeTo(null); //makes the window will open in center of screen
	    setResizable(false);
	    setTitle("Awesome game!");
	    setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}
	
	/*
	**ActionListener
	*/
	public void actionPerformed(ActionEvent e){

	}


    public void mousePressed(MouseEvent e) {
       
    }

    public void mouseReleased(MouseEvent e) {
       
    }

    public void mouseEntered(MouseEvent e) {
    	setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void mouseExited(MouseEvent e) {
    	setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));  
    }

    public void mouseClicked(MouseEvent e) {
       
    }
}
