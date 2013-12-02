import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class menu extends JFrame implements ActionListener{
	
	
	Container c = getContentPane();	
	JPanel menu = new JPanel();
	JPanel head = new JPanel();
	JPanel playerName = new JPanel();
	JButton startGame, setPlayerName;
	JTextField playerNameField;
	JTextArea txtHighscore;
	//highscore high;
	
	static String p = "Player"; 

	public menu() throws IOException {
		
		//high = new highscore();
		FlowLayout flow = new FlowLayout();
		
		JLabel start = new JLabel("Epic game!", JLabel.RIGHT);
		JLabel header = new JLabel("KOMA", JLabel.CENTER);
		header.setFont(new Font("SanSerif", Font.PLAIN, 99));
		
		//add Hiscores
		txtHighscore = new JTextArea();
		openFile();
		//for(int i = 0;  i < 5; i++){
		//txtHighscore.append(i+1 + ". " + high.getNames(i) + " " + high.getScores(i) + "s\n");
		//}
		
		
		startGame = new JButton("Play game");
		setPlayerName = new JButton("Set player name");

		playerNameField = new JTextField("Player name", 15);

		startGame.addActionListener(this);
		setPlayerName.addActionListener(this);

		playerName.add(playerNameField);
		playerName.add(setPlayerName);

		head.add(header);
		menu.add(start);
		menu.add(startGame);
		menu.add(txtHighscore);		
		menu.setLayout(flow);
		menu.setPreferredSize(new Dimension(150, 150));
		
		Container c = getContentPane();
	    c.setLayout(new BorderLayout());
	    c.add(head, BorderLayout.NORTH);
	    c.add(menu, BorderLayout.CENTER);
	    c.add(playerName, BorderLayout.SOUTH);
	    
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
				frame theFrame = new frame();
				//game theGame = new game();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
				//if set player name is clicked
		else if(e.getSource() == setPlayerName){
			//checks that p1 textField isn't empty
			if(!playerNameField.getText().equals(""))
				p = playerNameField.getText();
			else
				p = "Player"; // sets player 2 name to "Blå" if the TextField is empty
		}
		
		//Highscores?
		else{
			
		}
	}	
	
	public void openFile()
	{	
		
		//try{
			//high.load();
		//}
		//catch(IOException e){}
		
		
	}

	public static String getPlayerName(){
		return p;
	}
}
