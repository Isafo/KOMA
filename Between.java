import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.Timer;

public class Between extends javax.swing.JPanel {

    // Variables declaration                   
    private javax.swing.JLabel bouns;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel minigameInfo;
    private javax.swing.JLabel nextMiniGame;
    private javax.swing.JLabel totalTime;
    // End of variables declaration

	//timer declatarions
    Timer timer;
    public double timeUntilNext = 7;
    public final double TIMECONSTANT = 20, TIMECONSTANTFIRST = TIMECONSTANT*2;
    public double time = TIMECONSTANT;
    public static Random random = new Random();
    public DecimalFormat df = new DecimalFormat("##.##");
    
    
    public Between() {
        initComponents();
        
        timer = new Timer(100, new CountdownTimerListener());
        timer.start();
        
    }

    @SuppressWarnings("unchecked")                        
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nextMiniGame = new javax.swing.JLabel();
        minigameInfo = new javax.swing.JLabel();
        totalTime = new javax.swing.JLabel();
        bouns = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setPreferredSize(new java.awt.Dimension(456, 450));

        nextMiniGame.setFont(new java.awt.Font("Tahoma", 0, 14));
        
        //Maze minigame
        if(Game.currentGame == 1){
        	nextMiniGame.setText("Next minigame: Maze");
        
            minigameInfo.setFont(new java.awt.Font("Tahoma", 0, 12));
            minigameInfo.setText("Find your way through the maze");
        }
        
        //minigame0
        if(Game.currentGame == 2){
        	nextMiniGame.setText("Next minigame: minigame0");
        
            minigameInfo.setFont(new java.awt.Font("Tahoma", 0, 12));
            minigameInfo.setText("Lorem ipsum");
        }
        


        totalTime.setFont(new java.awt.Font("Tahoma", 0, 14));
        totalTime.setText("Total time played:" + df.format(Game.totalTime));

        bouns.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        bouns.setText("time until next gamemode:" + df.format(timeUntilNext));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(totalTime)
                                .addComponent(bouns))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(nextMiniGame))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(minigameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(nextMiniGame, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(minigameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(totalTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bouns)
                .addGap(57, 57, 57))
        );
    }
    
    /*
     * Timer
     */

	public boolean dead() {
	        if(Game.lives <= 0)
	                return true;
	        else
	                return false;
	}
	
    public void timerOver() throws IOException {
    	Maze.frame.dispose();
    	Header.setLives();
        Game.next();
    }
	

	class CountdownTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if((timeUntilNext -= 0.1) > 0) {
				bouns.setText("time until next gamemode:" + df.format(timeUntilNext));
            }
			
			else{
				timer.stop();
				try{
	                timerOver();
                } catch(IOException e1) {
                    e1.printStackTrace();
                }
			}
		}
	}
}
