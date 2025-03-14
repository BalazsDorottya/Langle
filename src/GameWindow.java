import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class GameWindow extends JPanel {
    private static final int ROWS = 6;
    private static final int COLS = 5;
    private static int currentRow = 0;
    private static JTextField[][] guessFields;
    private JPanel guessPanel;
    private JButton submitButton;
    private JButton quitButton;
    private static JLabel messageLabel;
    private static JLabel guessesLeftLabel;
    private static int remainingGuesses = 6;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public GameWindow(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        guessFields = new JTextField[ROWS][COLS];
        guessPanel = new JPanel(new GridLayout(ROWS, COLS, 20, 20));
        submitButton = new JButton("Submit");
        quitButton = new JButton("Quit");
        messageLabel = new JLabel("", SwingConstants.CENTER);
        guessesLeftLabel = new JLabel("Guesses Left: " + remainingGuesses, SwingConstants.CENTER);
        guessesLeftLabel.setFont(new Font("Architectural", Font.TRUETYPE_FONT, 25));

        IntStream.range(0, ROWS).forEach(row ->
                IntStream.range(0, COLS).forEach(col -> {
                    guessFields[row][col] = new JTextField(1);
                    guessFields[row][col].setFont(new Font("Architectural", Font.TRUETYPE_FONT, 30));
                    guessFields[row][col].setHorizontalAlignment(JTextField.CENTER);
                    guessFields[row][col].setPreferredSize(new Dimension(20, 20));
                    guessFields[row][col].setEnabled(row == 0);

                    int currentCol = col;

                    guessFields[row][col].addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            if (currentCol < COLS - 1 && !guessFields[row][currentCol].getText().isEmpty()) {
                                guessFields[row][currentCol + 1].requestFocus();
                            }
                        }
                    });
                    guessPanel.add(guessFields[row][col]);
                }));


        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout(30, 30));
        gamePanel.setPreferredSize(new Dimension(800, 500));
        gamePanel.add(guessPanel, BorderLayout.CENTER);
        JPanel complementPanel = new JPanel(new FlowLayout());
        complementPanel.add(submitButton);
        complementPanel.add(quitButton);
        add(complementPanel, BorderLayout.SOUTH);
        gamePanel.add(messageLabel, BorderLayout.NORTH);
        gamePanel.add(guessesLeftLabel, BorderLayout.EAST);

        add(gamePanel);

        submitButton.addActionListener(e -> {
            if (remainingGuesses > 0) {
                String guess = getGuess();
                boolean isCorrect = GameManager.checkGuess(guess, this);
                if (isCorrect) {
                    ScorePanel.addScore(6 - remainingGuesses + 1);
                    GameOver gameOver = new GameOver(cardLayout, mainPanel, "Great Job!!!", "win");
                    mainPanel.add(gameOver, "Game Over");
                    SoundManager.winGame();
                    cardLayout.show(mainPanel, "Game Over");
                    return;
                } else {
                    remainingGuesses--;
                    if (remainingGuesses == 0) {
                        ScorePanel.addScore(6);
                        GameOver gameOver = new GameOver(cardLayout, mainPanel, "Game Over! The word was: " + GameManager.getTargetWord(), "lose");
                        mainPanel.add(gameOver, "Game Over");
                        SoundManager.loseGame();
                        cardLayout.show(mainPanel, "Game Over");
                    } else {
                        messageLabel.setText("Try Again!");
                        disableCurrentRow();
                        enableNextRow();
                    }
                    guessesLeftLabel.setText("Guesses Left: " + remainingGuesses);
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Main Menu");
            }
        });

        setVisible(true);


    }

    private String getGuess() {
        StringBuilder guess = new StringBuilder();
        for (int col = 0; col < COLS; col++) {
            guess.append(guessFields[currentRow][col].getText());
        }
        return guess.toString();
    }

    private void disableCurrentRow() {
        for (int col = 0; col < COLS; col++) {
            guessFields[currentRow][col].setEnabled(false);
        }
    }

    private void enableNextRow() {
        if (currentRow < ROWS - 1) {
            currentRow++;
            for (int col = 0; col < COLS; col++) {
                guessFields[currentRow][col].setEnabled(true);
            }
            guessFields[currentRow][0].requestFocus();
        }
    }

    public static void updateGameWindow(char[] response) {
        System.out.println("Response: " + Arrays.toString(response));
        for (int col = 0; col < response.length; col++) {
            JTextField guessField = guessFields[currentRow][col];

            switch (response[col]) {
                case LetterChecker.CorrectPosition:
                    guessField.setBackground(Color.GREEN);
                    break;
                case LetterChecker.WrongPosition:
                    guessField.setBackground(Color.ORANGE);
                    break;
                case LetterChecker.Incorrect:
                    guessField.setBackground(Color.GRAY);
                    break;
                default:
                    guessField.setBackground(Color.WHITE);
                    break;
            }
            guessField.repaint();
        }
    }

    public static void resetGameWindow() {
        currentRow = 0;
        remainingGuesses = 6;
        guessesLeftLabel.setText("Guesses Left: " + remainingGuesses);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JTextField field = guessFields[row][col];
                field.setText("");
                field.setBackground(Color.WHITE);
                field.setEnabled(row == 0);
            }
        }

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JTextField field = guessFields[row][col];

                KeyListener[] listeners = field.getKeyListeners();
                for (KeyListener listener : listeners) {
                    field.removeKeyListener(listener);
                }


                final int finalRow = row;
                final int finalCol = col;
                field.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (finalCol < COLS - 1 && !guessFields[finalRow][finalCol].getText().isEmpty()) {
                            guessFields[finalRow][finalCol + 1].requestFocus();
                        }
                    }
                });

            }
        }

        messageLabel.setText("");
    }

}
