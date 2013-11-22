import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class menu extends JFrame implements MouseListener{
	
    private JPanel disp;
    private JLabel jLabel1;
    private JMenu jMenu1;
    private JMenuItem jMenuItem1;
    private JScrollPane jScrollPane1;
    private JPanel menuItems;
	JLabel startGame, highscoreMenu, quit;
	JTextField playerName;
	static String p = "Player";

	JTextArea txtHighscore;
	highscore high = new highscore();

	public menu() throws IOException {
		
		
		initComponents();

		//add highscores to textArea
 		openFile();
 		for(int i = 0;  i < 5; i++){
 			txtHighscore.append(i+1 + ". " + high.getNames(i) + " " + high.getScores(i) + "s\n");
 		}

 		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        setLocationRelativeTo(null); //makes the window will open in center of screen
        setResizable(false);
		setVisible(true);

	  	
	}
        
        private void initComponents() {

            jMenu1 = new javax.swing.JMenu();
            jMenuItem1 = new javax.swing.JMenuItem();
            menuItems = new javax.swing.JPanel();
            startGame = new javax.swing.JLabel();
            highscoreMenu = new javax.swing.JLabel();
            quit = new javax.swing.JLabel();
            playerName = new javax.swing.JTextField();
            jLabel1 = new javax.swing.JLabel();
            disp = new javax.swing.JPanel();
            jScrollPane1 = new javax.swing.JScrollPane();
            txtHighscore = new javax.swing.JTextArea();

            jMenu1.setText("jMenu1");

            jMenuItem1.setText("jMenuItem1");

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setBackground(new java.awt.Color(153, 153, 153));

            menuItems.setBackground(new java.awt.Color(0, 153, 204));
            menuItems.setName("menuItems"); // NOI18N

            startGame.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            startGame.setLabelFor(menuItems);
            startGame.setText("Play");
            startGame.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            startGame.setName("startGame"); // NOI18N
            startGame.addMouseListener(this);

            highscoreMenu.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            highscoreMenu.setLabelFor(menuItems);
            highscoreMenu.setText("Highscore");
            highscoreMenu.setName("highscore"); // NOI18N
            highscoreMenu.addMouseListener(this);

            playerName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            playerName.setText("Playername");

            jLabel1.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
            jLabel1.setText("KOMA");

            quit.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            quit.setText("Quit");
            quit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            quit.addMouseListener(this);

            javax.swing.GroupLayout menuItemsLayout = new javax.swing.GroupLayout(menuItems);
            menuItems.setLayout(menuItemsLayout);
            menuItemsLayout.setHorizontalGroup(
                menuItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(menuItemsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(menuItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuItemsLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(menuItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(startGame, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(highscoreMenu, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(playerName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE,
                                				116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(quit, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addContainerGap())
            );
            menuItemsLayout.setVerticalGroup(
                menuItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(menuItemsLayout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addComponent(startGame, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(highscoreMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(playerName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quit)
                    .addContainerGap(227, Short.MAX_VALUE))
            );

            disp.setBackground(new java.awt.Color(153, 153, 153));
            disp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            disp.setName(""); // NOI18N

            txtHighscore.setEditable(false);
            txtHighscore.setBackground(new java.awt.Color(153, 153, 153));
            txtHighscore.setColumns(10);
            txtHighscore.setRows(7);
            txtHighscore.setText("Highscore" + "\n");
            txtHighscore.setBorder(null);
            txtHighscore.setFocusable(false);
            jScrollPane1.setViewportView(txtHighscore);

            javax.swing.GroupLayout dispLayout = new javax.swing.GroupLayout(disp);
            disp.setLayout(dispLayout);
            dispLayout.setHorizontalGroup(
                dispLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispLayout.createSequentialGroup()
                    .addContainerGap(327, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            dispLayout.setVerticalGroup(
                dispLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(menuItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(disp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(menuItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(disp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            pack();
    }        
	
	public void openFile(){  
 		try{
 			high.load();
 		}
 		catch(IOException e){}  
 	}

	/*
	**MouseListener
	*/

    public void mousePressed(MouseEvent e) {
    	if(e.getSource() == startGame){
    		//set player name

    		if(!playerName.getText().equals(""))
    			p = playerName.getText();
    		
    		else
    			p = "player";

    		try {
                game theGame = new game();
            } catch (IOException e1) {
            	// TODO Auto-generated catch block
                e1.printStackTrace();
            }
		}

		else if(e.getSource() == highscoreMenu){
		//	highscore showHighscore == new highscore();
			JOptionPane.showMessageDialog(null, "highscore clicked");	
		}

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


    //set player name

    public static String getPlayerName(){
        
        return p;
    }

}

