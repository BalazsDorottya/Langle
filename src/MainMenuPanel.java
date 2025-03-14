import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(160, 192, 140));

        ImageIcon titleImage = new ImageIcon("png/Langle.png");
        JLabel title = new JLabel(titleImage);
        Image image = titleImage.getImage();
        Image scaledImage = image.getScaledInstance(700, 394, Image.SCALE_SMOOTH);
        title.setIcon(new ImageIcon(scaledImage));

        JPanel buttonPanel = Utilities.createButtonPanel(
                Utilities.createCustomButton("Start", e -> cardLayout.show(mainPanel, "Select Language")),
                Utilities.createCustomButton("Description", e -> cardLayout.show(mainPanel, "Description")),
                Utilities.createCustomButton("Score", e -> cardLayout.show(mainPanel, "Score"))
        );
        buttonPanel.setBackground(new Color(160, 192, 140));

        JButton startButton = new JButton("Start");
        startButton.setBounds(600, 300, 300, 60);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Select Language");
            }
        });

        JButton descriptionButton = new JButton("Description");
        descriptionButton.setBounds(600, 400, 300, 60);
        descriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Description");
            }
        });

        JButton scoreButton = new JButton("Score");
        scoreButton.setBounds(600, 500, 300, 60);
        scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Score");
            }
        });

        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

    }
}
