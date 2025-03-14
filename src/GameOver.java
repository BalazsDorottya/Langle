import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameOver extends JPanel {
    private static JLabel picLabel;

    public GameOver(CardLayout cardLayout, JPanel mainPanel, String message, String state) {
        setLayout(new BorderLayout());
        setBackground(new Color(160, 192, 140));

        JLabel messageLabel = Utilities.createTitleLabel(message);
        add(messageLabel, BorderLayout.CENTER);

        picLabel = new JLabel();

        JPanel picPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        picPanel.setBackground(new Color(160, 192, 140));
        picPanel.add(picLabel);
        add(picPanel, BorderLayout.SOUTH);

        if (state.equals("win")) {
            picLoad("png/confetti.png");
        } else if (state.equals("lose")) {
            picLoad("png/sad.png");
        }


        int delay = 2000;//milliszekundum
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> cardLayout.show(mainPanel, "Main Menu"));
            }
        }, delay);
    }

    public void picLoad(String path) {
        ImageIcon confettiIcon = new ImageIcon(path);
        Image image = confettiIcon.getImage();
        picLabel.setIcon(new ImageIcon(image));
        revalidate();
        repaint();
    }

}
