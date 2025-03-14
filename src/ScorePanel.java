import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ScorePanel extends JPanel {
    private static DefaultTableModel tableModel;
    private static ScoreTracker scoreTracker;

    public ScorePanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());

        JLabel title = Utilities.createTitleLabel("Score Memory");
        setBackground(new Color(160, 192, 140));

        scoreTracker = new ScoreTracker("scores.txt");
        String[] columns = {"Score", "Date"};
        tableModel = new DefaultTableModel(columns, 0);

        JTable scoreTable = new JTable(tableModel);
        scoreTable.setFont(new Font("Architectural", Font.TRUETYPE_FONT, 15));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        scoreTable.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader header = scoreTable.getTableHeader();
        header.setBackground(new Color(70, 100, 90));
        header.setFont(new Font("Architectural", Font.TRUETYPE_FONT, 25));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        scoreTable.setBackground(new Color(160, 192, 140));
        JScrollPane scrollPane = new JScrollPane(scoreTable);
        scrollPane.setBackground(new Color(160, 192, 140));

        loadScoresToTable();

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


    }

    public static void addScore(int guesses) {
        scoreTracker.addScore(guesses);
        loadScoresToTable();
    }

    private static void loadScoresToTable() {
        tableModel.setRowCount(0);
        List<String[]> scores = scoreTracker.getScores();
        scores.stream()
                .sorted((score1, score2) -> score2[1].compareTo(score1[1]))
                .forEach(row -> tableModel.addRow(row));
    }
}


