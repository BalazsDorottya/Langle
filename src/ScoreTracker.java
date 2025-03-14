import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScoreTracker {
    private int score = 0;
    private final List<String[]> scoreData;
    private final File scoreFile;

    public ScoreTracker(String fileName) {
        scoreFile = new File(fileName);
        scoreData = new ArrayList<>();
        loadScore();
    }

    public void addScore(int guesses) {
        String date = getCurrentDate();
        String[] newRow = {String.valueOf(guesses), date};
        scoreData.add(newRow);
        saveScores();
    }

    public List<String[]> getScores() {
        return new ArrayList<>(scoreData);
    }

    private void loadScore() {
        if (!scoreFile.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(scoreFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    scoreData.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(scoreFile))) {
            for (String[] row : scoreData) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentDate() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(new Date());
    }

    public void incrementScore(int addition) {
        score += addition;
    }


}
