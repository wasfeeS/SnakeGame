/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package snakeGame;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Wasfee
 */
public class gamePanel extends JPanel implements ActionListener{
    JButton highScoreButton;
    JButton playAgainButton;
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 105;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    public static int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    gamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 500));
        UIManager.put("Button.background", new Color(255, 0, 0)); 
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 16));
        highScoreButton = new JButton("View High Score");
        highScoreButton.addActionListener(this);
        highScoreButton.setVisible(false);
        add(highScoreButton);
        add(Box.createRigidArea(new Dimension(100, 0)));
        playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(this);
        playAgainButton.setVisible(false);
        add(playAgainButton);
    }

    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }   
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g){
       if(running){
           for(int i = 0; i<SCREEN_HEIGHT/UNIT_SIZE;i++){
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
        
            for(int i = 0; i<bodyParts; i++){
            if(i==0){
                g.setColor(Color.green);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
            else{
                g.setColor(new Color(45, 180, 0));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
            }
        }
       else{
           g.setColor(Color.RED);
           g.setFont(new Font("EB GARAMOND", Font.BOLD, 75));
           FontMetrics metrics = getFontMetrics(g.getFont());
           g.drawString("GAME OVER", (SCREEN_WIDTH - metrics.stringWidth("GAME OVER"))/2, SCREEN_HEIGHT/2);
           highScoreButton.setVisible(true);
           playAgainButton.setVisible(true);
       }
    }
    
    public void newApple() {
    boolean appleOnSnake = true;
    while (appleOnSnake) {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        // Check if the apple is not on the snake's body
        appleOnSnake = false;
        for (int i = 0; i < bodyParts; i++) {
            if (x[i] == appleX && y[i] == appleY) {
                appleOnSnake = true;
                break;
            }
        }
    }
}

    
    public void move(){
        for(int i = bodyParts; i>0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];   
        }
        switch(direction){
            case 'U':
                y[0] = y[0]-UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0]+UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0]-UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0]+UNIT_SIZE;
                break;

        }
    }
    
    public void checkApple(){
        if ((x[0] == appleX)&& (y[0] == appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    
    public void checkCollisions(){
        for (int i = bodyParts; i>0; i--){
            if((x[0]==x[i])&& (y[0]==y[i])){
            running = false;
            }
            if(x[0]<0){
                running = false;
            }
            if(x[0]>SCREEN_WIDTH){
                running = false;
            }
            if(y[0]<0){
                running = false;
            }
            if(y[0]>SCREEN_HEIGHT){
                running = false;
            }
            if(!running){
                timer.stop();
            }
        }
    }
    
    private void resetGame() {
    bodyParts = 6;
    applesEaten = 0;
    direction = 'R';
    running = true;
    x[0] = SCREEN_WIDTH / 2; 
    y[0] = SCREEN_HEIGHT / 2;
    newApple();
    timer.restart();
    repaint();
    highScoreButton.setVisible(false);
    playAgainButton.setVisible(false);
    }
    
    private void showHighScore() {
    int highScore = getHighScore(); 
    highScore highScoreWindow = new highScore(highScore);
    highScoreWindow.setVisible(true);
}
    private int getHighScore() {
    int highScore = applesEaten;
    return highScore; 
}
    public  void gameOver(Graphics g){
        
    }
       @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
        if (e.getSource() == highScoreButton) {
            showHighScore();
        } 
        else if (e.getSource() == playAgainButton) {
           resetGame();
        }
    }
    
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }
        }
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
