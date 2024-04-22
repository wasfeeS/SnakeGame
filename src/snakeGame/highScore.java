package snakeGame;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import snakeGame.gamePanel;

public class highScore extends JFrame {
    JTextArea highScoreTextArea;
    JLabel titleLabel;
    JButton closeButton;

    public highScore(ArrayList<ArrayList<Object>> od) {
        ArrayList<ArrayList<Object>> oldData = od;
        setTitle("High Score");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);

        titleLabel = new JLabel("High Score");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.RED);
        panel.add(titleLabel, BorderLayout.NORTH);
        highScoreTextArea = new JTextArea();
        for(int i = 0; i < oldData.size(); i++){ 
            highScoreTextArea.append("Username: " + oldData.get(i).get(0) + " | Score: " + oldData.get(i).get(1) +"\n");
            highScoreTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
            highScoreTextArea.setEditable(false);
            highScoreTextArea.setBackground(Color.BLACK);
            highScoreTextArea.setForeground(Color.RED);
            JScrollPane scrollPane = new JScrollPane(highScoreTextArea);
            panel.add(scrollPane, BorderLayout.CENTER);}

        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        closeButton.addActionListener(e -> dispose());
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.RED);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(closeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }
}

