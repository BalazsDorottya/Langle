import javax.swing.*;
import java.awt.*;

public class GameManager {
    private static WordGuesser wordGuesser;
    private static String currentGuess = "";
    private static String targetWord;
    private static ScoreTracker scoreTracker;

    public static void initializeGame(String mode, CardLayout cardLayout, JPanel mainPanel) {

        scoreTracker = new ScoreTracker("scores.txt");

        if (mode.equals("English")) {
            wordGuesser = new EnglishGuesser();
        } else if (mode.equals("Hungarian")) {
            wordGuesser = new HungarianGuesser();
        }
        wordGuesser.loadWords();
        targetWord = wordGuesser.getRandomWord();

        GameWindow.resetGameWindow();
    }

    public static boolean checkGuess(String guess, GameWindow gameWindow) {
        currentGuess = guess.toUpperCase();

        if (currentGuess.equals(targetWord.toUpperCase())) {
            scoreTracker.incrementScore(1);
            LetterChecker.checkLetter(guess, targetWord, gameWindow);
            return true;
        } else {
            scoreTracker.incrementScore(0);
        }

        LetterChecker.checkLetter(currentGuess, targetWord, gameWindow);
        return false;
    }

    public static String getTargetWord() {
        return targetWord;
    }


}
