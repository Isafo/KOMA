import java.text.DecimalFormat;

public class Header extends javax.swing.JPanel {

    // Variables declaration                    
    private javax.swing.JLabel dispTimeLabel;
    private javax.swing.JLabel life1;
    private static javax.swing.JLabel life2;
    private static javax.swing.JLabel life3;
    private static javax.swing.JLabel timeLabel;
    public static DecimalFormat df = new DecimalFormat("##.##");
    

    public Header() {
        initComponents();
    }
    
    public static void setTime(double t){
    	timeLabel.setText(df.format(t));
    }
    
    
    @SuppressWarnings("unchecked")                         
    private void initComponents() {

        life1 = new javax.swing.JLabel();
        life2 = new javax.swing.JLabel();
        life3 = new javax.swing.JLabel();
        dispTimeLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();

        life1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Heart.jpg"))); // NOI18N

        life2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Heart.jpg"))); // NOI18N

        life3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Heart.jpg"))); // NOI18N

        dispTimeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dispTimeLabel.setText("Remaining time");

        timeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timeLabel.setText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(dispTimeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 387, Short.MAX_VALUE)
                .addComponent(life3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(life2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(life1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dispTimeLabel)
                        .addComponent(timeLabel))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(life2)
                        .addComponent(life1)
                        .addComponent(life3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        if(Game.lives < 3){
        	life3.setVisible(false);
        }
        
        if(Game.lives < 2){
        	life2.setVisible(false);
        }
    }                                           
}