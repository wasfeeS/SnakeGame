/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package snakeGame;
import javax.swing.*;
import java.awt.*;
import static snakeGame.gamePanel.applesEaten;


public class snakeGame {
    public static String username;
   public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }

    public static class MainMenu extends JFrame {
        JButton highScoreButton;
        JButton playButton;
        JButton exitButton;

        public MainMenu() {
            setTitle("Main Menu");
            setSize(600, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setBackground(Color.BLACK);
            JLabel titleLabel = new JLabel("Snake");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
            titleLabel.setForeground(Color.RED);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 1, 10, 10));
            panel.setBackground(Color.BLACK);
            panel.add(titleLabel);
            highScoreButton = new JButton("View High Score");
            customizeButton(highScoreButton);
            highScoreButton.addActionListener(e -> showHighScore());
            panel.add(highScoreButton);
            playButton = new JButton("Play Game");
            customizeButton(playButton);
            playButton.addActionListener(e -> startGame());
            panel.add(playButton);
            exitButton = new JButton("Exit");
            customizeButton(exitButton);
            exitButton.addActionListener(e -> System.exit(0));
            panel.add(exitButton);
            add(panel);
            setVisible(true);
        }

        private void showHighScore() {
            int highScore = gamePanel.applesEaten;
            highScore highScoreWindow = new highScore((snakeGame.username), highScore);
            highScoreWindow.setVisible(true);
        }

        public void startGame() {
            username = JOptionPane.showInputDialog(this, "Enter your username:");
            if (username != null && !username.isEmpty()) {
                gameFrame gameFrame = new gameFrame();
                gamePanel.applesEaten = 0;
                gameFrame.setVisible(true);
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid username.");
            }
        }

        private void customizeButton(JButton button) {
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            button.setForeground(Color.RED);
            button.setBackground(Color.BLACK);
            button.setFocusPainted(false);
        }
    }
    }
    

