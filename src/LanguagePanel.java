import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguagePanel extends JPanel {
    public LanguagePanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        JLabel title = Utilities.createTitleLabel("Select Language");
        setBackground(new Color(160, 192, 140));

        JPanel buttonPanel = Utilities.createButtonPanel(
                Utilities.createCustomButton("Hungarian", e -> LanguagePanel.startNewGame("Hungarian", cardLayout, mainPanel)),
                Utilities.createCustomButton("English", e -> LanguagePanel.startNewGame("English", cardLayout, mainPanel)),
                Utilities.createCustomButton("Back", e -> cardLayout.show(mainPanel, "Main Menu"))
        );
        buttonPanel.setBackground(new Color(160, 192, 140));

        JButton hungarianButton = new JButton("Hungarian");
        hungarianButton.setBounds(600, 300, 300, 60);
        hungarianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame("Hungarian", cardLayout, mainPanel);
            }
        });

        JButton englishButton = new JButton("English");
        englishButton.setBounds(600, 400, 300, 60);
        englishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame("English", cardLayout, mainPanel);
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(600, 500, 300, 60);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Main Menu");
            }
        });

        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

    }

    private static void startNewGame(String language, CardLayout cardLayout, JPanel mainPanel) {
        GameManager.initializeGame(language, cardLayout, mainPanel);
        cardLayout.show(mainPanel, "Game Window");
    }
}
