   class CountdownTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                        if(!dead()) {
                                if((time -= 0.1) > 0) {
                                        game.totalTime += 0.1;
                                        frame.timeLeft.setText("Time: " + df.format(time));
                                        frame.totalTimeLabel.setText("Total time: " + df.format(game.totalTime));
                    } 
                    else {
                            frame.timeLeft.setText("Time's up!");
                            frame.totalTimeLabel.setText("Total time: " + df.format(game.totalTime));
                        timer.stop();
                        try{
                        timerOver();
                        } catch(IOException e1) {
                                e1.printStackTrace();
                        }
                    }
                    if(time < 1) {
                            if((time < 1.6 && time > 1.4) || (time < 1.2 && time > 1.0) || (time < 0.8 && time > 0.6) || (time < 0.4 && time > 0.2)) {
                                    for (int i = 0; i < rows; i++) {
                                                        for (int j = 0; j < columns; j++) {
                                                                slots [i] [j].setFont(new Font("SanSerif", Font.PLAIN, 45));
                                                        }
                                                }
                                        }
                                        else {
                                                for (int i = 0; i < rows; i++) {
                                                        for (int j = 0; j < columns; j++) {
                                                                slots [i] [j].setFont(new Font("SanSerif", Font.PLAIN, 75));
                                                        }
                                                }
                                        }
                    }
                }
        }
    }