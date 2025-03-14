public class LetterChecker {
    public static final char CorrectPosition = 'G';
    public static final char WrongPosition = 'Y';
    public static final char Incorrect = 'X';

    public static void checkLetter(String guess, String targetWord, GameWindow gameWindow) {
        char[] response = new char[guess.length()];
        Thread[] threads = new Thread[guess.length()];
        LetterCheckTask[] tasks = new LetterCheckTask[guess.length()];

        String normalizedGuess = guess.toUpperCase();
        String normalizedTarget = targetWord.toUpperCase();
        System.out.println("Normalized Guess: " + normalizedGuess);
        System.out.println("Normalized Target: " + normalizedTarget);

        for (int i = 0; i < guess.length(); i++) {
            tasks[i] = new LetterCheckTask(normalizedGuess, normalizedTarget, i, response);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        for (int i = 0; i < guess.length(); i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameWindow.updateGameWindow(response);
    }


    static class LetterCheckTask implements Runnable {
        private String guess;
        private String targetWord;
        private int index;
        private char[] response;

        public LetterCheckTask(String guess, String targetWord, int index, char[] response) {
            this.guess = guess;
            this.targetWord = targetWord;
            this.index = index;
            this.response = response;
        }

        @Override
        public void run() {
            char guessChar = guess.charAt(index);
            if (guessChar == targetWord.charAt(index)) {
                response[index] = CorrectPosition;
            } else if (targetWord.indexOf(guessChar) != -1) {
                response[index] = WrongPosition;
            } else {
                response[index] = Incorrect;
            }
        }

    }

}
