/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakeGame;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Wasfee
 */
public class highScore extends JFrame{
    JLabel highScoreLabel;
    JLabel titleLabel;
    JButton closeButton;

    public highScore(int highScore) {
        setTitle("High Score");
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose when closed
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK); // Set background color to black

        titleLabel = new JLabel("High Score");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.RED); // Set text color to red
        panel.add(titleLabel, BorderLayout.NORTH);

        highScoreLabel = new JLabel("Score: " + highScore);
        highScoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        highScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        highScoreLabel.setForeground(Color.RED); // Set text color to red
        panel.add(highScoreLabel, BorderLayout.CENTER);

        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        closeButton.addActionListener(e -> dispose()); // Close the window when the button is clicked
        closeButton.setBackground(Color.BLACK); // Set button background color to black
        closeButton.setForeground(Color.RED); // Set button text color to red
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK); // Set panel background color to black
        buttonPanel.add(closeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }
}
