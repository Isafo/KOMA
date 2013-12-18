import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.text.StyleConstants;

public class Between extends javax.swing.JPanel implements ActionListener, KeyListener {

    // Variables declaration                   
    private javax.swing.JLabel bouns;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel minigameInfo;
    private javax.swing.JLabel nextMiniGame;
    private javax.swing.JLabel totalTime;
    private javax.swing.JLabel bonusTime;
    
    // End of variables declaration

	//timer declatarions
    Timer timer;
    public double timeUntilNext = 5;
    public final double TIMECONSTANT = 5, TIMECONSTANTFIRST = TIMECONSTANT*2;
    public double time = TIMECONSTANT;
    public static Random random = new Random();
    public DecimalFormat df = new DecimalFormat("##.##");
    
    
    public Between() {
        
        
        timer = new Timer(100, new CountdownTimerListener());
        timer.start();
        
        initComponents();
        addKeyListener(this);
        frame.c.add(this);
    }

    @SuppressWarnings("unchecked")                        
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nextMiniGame = new javax.swing.JLabel();
        minigameInfo = new javax.swing.JLabel();
        totalTime = new javax.swing.JLabel();
        bonusTime = new javax.swing.JLabel();
        bouns = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setPreferredSize(new java.awt.Dimension(456, 450));

        nextMiniGame.setFont(new java.awt.Font("Tahoma", 0, 22));
        
//        //Maze minigame
//        if(Game.currentGame == 1){
//        	nextMiniGame.setText("Next minigame: Maze");
//        
//            minigameInfo.setFont(new java.awt.Font("Tahoma", 0, 22));
//            minigameInfo.setText("Find your way through the maze");
//        }
//        
//        //minigame0
//        if(Game.currentGame == 2){
//        	nextMiniGame.setText("Next minigame: minigame0");
//        
//            minigameInfo.setFont(new java.awt.Font("Tahoma", 0, 22));
//            minigameInfo.setText("Lorem ipsum");
//        }
        


        totalTime.setFont(new java.awt.Font("Tahoma", 0, 21));
        totalTime.setText("Total time played:" + df.format(Game.totalTime));
        
        bonusTime.setFont(new java.awt.Font("Tahoma", 0, 21));
        bonusTime.setText("Total bonus time:" + df.format(Game.totalExtraTime));

        bouns.setFont(new java.awt.Font("Tahoma", 0, 21)); 
        bouns.setText("Time until next gamemode:" + df.format(timeUntilNext));

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
                                .addComponent(bonusTime)
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
                .addComponent(bonusTime)
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
    	Header.setLives();
    	time = TIMECONSTANT;
    	frame.c.remove(this);
        Game.next();
    }
	

	class CountdownTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if((timeUntilNext -= 0.1) > 0 && Game.lives > 0) {
				bouns.setText("Time until next gamemode:" + df.format(timeUntilNext));
            }
			else{
				timer.stop();
				try{
	                timerOver();
                } catch(IOException e1) {
                    e1.printStackTrace();
                }
				bouns.setText("");
			}
		}
	}
	
	public void keyPressed(KeyEvent e){
		time -= 1.0;
    }

    public void keyTyped(KeyEvent e) {

    }
    
    public void keyReleased(KeyEvent e) {

    }
    
    public void actionPerformed(ActionEvent e) {
		
	}
}
