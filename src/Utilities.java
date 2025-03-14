import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Utilities {
    public static JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        return label;
    }

    public static JPanel createButtonPanel(JButton... buttons) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 50));
        for (JButton button : buttons) {
            panel.add(button);
        }

        JPanel outerPanel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(160, 192, 140));
        outerPanel.add(panel);
        return outerPanel;
    }


    public static JButton createCustomButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.BOLD, 25));
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(new Color(40, 69, 10));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(new Color(40, 50, 15), 2, true));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(actionListener);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(120, 149, 75));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(40, 69, 10));
            }
        });

        return button;
    }


}
