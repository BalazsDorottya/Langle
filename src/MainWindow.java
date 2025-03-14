import javax.swing.*;
import java.awt.*;


public class MainWindow {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private static GameManager gameManager;

    public MainWindow() {
        gameManager = new GameManager();

        frame = new JFrame("Langle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new MainMenuPanel(cardLayout, mainPanel), "Main Menu");
        mainPanel.add(new LanguagePanel(cardLayout, mainPanel), "Select Language");
        mainPanel.add(new DescriptionPanel(cardLayout, mainPanel), "Description");
        mainPanel.add(new ScorePanel(cardLayout, mainPanel), "Score");
        mainPanel.add(new GameWindow(cardLayout, mainPanel), "Game Window");

        frame.add(mainPanel);
        frame.setVisible(true);

    }

}
