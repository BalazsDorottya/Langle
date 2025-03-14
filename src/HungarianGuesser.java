import java.io.File;
import java.util.List;
import java.util.Random;

public class HungarianGuesser implements WordGuesser {
    private List<String> words;
    private String targetWord;

    @Override
    public void loadWords() {
        File file = new File("txt/hungarian_words.txt");
        words = WordLoader.loadWords(file.getAbsolutePath());
        targetWord = getRandomWord();
    }


    @Override
    public String getRandomWord() {
        Random random = new Random(System.currentTimeMillis());
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }

}
