import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class Menu extends JFrame implements ActionListener{
	
	
	Container c = getContentPane();	
	JPanel menu = new JPanel();
	JPanel head = new JPanel();
	JPanel playerNames = new JPanel();
	JButton startGame;

	public Menu() throws IOException {
		
		FlowLayout flow = new FlowLayout();
		
		JLabel start = new JLabel("Epic game!", JLabel.RIGHT);
		JLabel header = new JLabel("KOMA", JLabel.CENTER);
		header.setFont(new Font("SanSerif", Font.PLAIN, 99));
		
		startGame = new JButton("Play game");

		startGame.addActionListener(this);

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
	    
	    pack();
	    setLocationRelativeTo(null); //makes the window will open in center of screen
	    setResizable(false);
	    setTitle("Fyra i Rad, jao");
	    setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}
	
	/*
	**ActionListener
	*/
	public void actionPerformed(ActionEvent e){

		//if startGame button is clicked
		if(e.getSource() == startGame){
			
		}
		
		//Highscores?
		else{
			
		}
	}	
}
