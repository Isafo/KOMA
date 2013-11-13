

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class menu extends JFrame implements MouseListener{
	
	
	Container c = getContentPane();	
	JPanel menu = new JPanel();
	JPanel head = new JPanel();
	JPanel eastMenu = new JPanel();
	JPanel playerNames = new JPanel();
	JLabel startGame, highscoreMenu, quit;

	public menu() throws IOException {
		
		GridLayout gridMenu = new GridLayout(3, 0);
		
		JLabel header = new JLabel("KOMA", JLabel.CENTER);
		header.setFont(new Font("SanSerif", Font.PLAIN, 99));
		
		startGame = new JLabel("Play game");
		startGame.setFont(new Font("SanSerif", Font.PLAIN, 30));
		startGame.addMouseListener(this);

		highscoreMenu = new JLabel("Highscore");
		highscoreMenu.setFont(new Font("SanSerif", Font.PLAIN, 30));
		highscoreMenu.addMouseListener(this);

		quit = new JLabel("Quit game");
		quit.setFont(new Font("SanSerif", Font.PLAIN, 30));
		quit.addMouseListener(this);

		head.add(header);
		menu.add(startGame);
		menu.add(highscoreMenu);
		menu.add(quit);
		menu.setLayout(gridMenu);

		//container displaying centerpositioned items
		Container c = getContentPane();
	    c.setLayout(new BorderLayout());
	    c.add(head, BorderLayout.NORTH);
	    c.add(menu, BorderLayout.EAST);
	    
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
    			if(e.getSource() == startGame){
		//	game theGame = new game();
			JOptionPane.showMessageDialog(null, "start game!");
		}

		//else if(e.getSource() == highscoreMenu){
		//	highscore showHighscore == new highscore();
		//}

		else{
			System.exit(0);
		}
    }

    public void mouseReleased(MouseEvent e) {
    	
    }

    public void mouseEntered(MouseEvent e) {
    	//sets cursor to hand cursor if hovering over a menu item
    	setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void mouseExited(MouseEvent e) {
    	//sets cursor to default cursor if exiting a menu item
    	setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));  
    }

    public void mouseClicked(MouseEvent e) {
    	
    }
}
