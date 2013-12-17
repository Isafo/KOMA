public class Between extends javax.swing.JPanel {

    // Variables declaration                   
    private javax.swing.JLabel bouns;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel minigameInfo;
    private javax.swing.JLabel nextMiniGame;
    private javax.swing.JLabel totalTime;
    // End of variables declaration


    public Between() {
        initComponents();
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

        nextMiniGame.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nextMiniGame.setText("Next minigame:");

        minigameInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        minigameInfo.setText("Lorem ipsum");

        totalTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        totalTime.setText("Total time played:");

        bouns.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bouns.setText("Bonus points:");

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
}
