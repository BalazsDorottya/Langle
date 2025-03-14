import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DescriptionPanel extends JPanel {
    public DescriptionPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        JLabel title = Utilities.createTitleLabel("Description");
        setBackground(new Color(160, 192, 140));

        JTextPane descriptionArea = new JTextPane();
        descriptionArea.setContentType("text/html");
        descriptionArea.setText("<html>"
                + "<h2 style='font-size: 24px;'><i>Welcome to the <b>Langle Game</b>!</i></h2>"
                + "<p style='font-size: 18px;'>Get caught in the world of words.</p>"
                + "<p style='font-size: 16px;'>You can choose between two languages to play with: <b>Hungarian</b> and <b>English</b>.</p>"
                + "<p style='font-size: 16px;'> The goal of the game is to find the <b>5-letter</b> word the system has chosen for you in <b>6 tries</b>.</p>"
                + "<p style='font-size: 16px;'> When you find out a letter but <b>not</b> in the right place, the letter will turn orange.</p>"
                + "<img src='file:png/screenshot1.png' width='700' height='450'><br>"
                + "<p style='font-size: 16px;'> I you find out a letter and it`s also in the right spot, the letter will become green.</p>"
                + "<img src='file:png/screenshot2.png' width='700' height='450'><br>"
                + "<p style='font-size: 24px;'><i>Good luck and enjoy!</i></p>");

        descriptionArea.setEditable(false);
        descriptionArea.setBackground(new Color(160, 192, 140));

        JScrollPane scrollPane = new JScrollPane(descriptionArea);

        JButton backButton = Utilities.createCustomButton("Back", e -> cardLayout.show(mainPanel, "Main Menu"));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Main Menu");
            }
        });

        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    }
}
