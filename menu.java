import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class menu extends JFrame implements ActionListener{
	
	
	Container c = getContentPane();	
	JPanel menu = new JPanel();
	JPanel head = new JPanel();
	JPanel playerNames = new JPanel();
	JButton startGame;

	public menu() throws IOException {
		
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
	    setTitle("OKLART");
	    setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}
	
	/*
	**ActionListener
	*/
	public void actionPerformed(ActionEvent e){

		//if startGame button is clicked
		if(e.getSource() == startGame){
			try {
				game theGame = new game();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//Highscores?
		else{
			
		}
	}	
}
