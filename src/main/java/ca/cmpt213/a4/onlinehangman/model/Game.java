package ca.cmpt213.a4.onlinehangman.model;

public class Game {
    long id;
    char[] hiddenWord;
    String word;
    int numberOfGuesses;
    int numberOfIncorrectGuesses;
    GameStatus status;

    public Game(long id, String word) {
        this.id = id;
        this.word = word;
        status = GameStatus.Active;
        numberOfGuesses = 0;
        numberOfIncorrectGuesses = 0;
        hiddenWord = new char[word.length()];
        for(int i = 0; i < word.length(); i++){
            hiddenWord[i] = '_';
        }
    }

    public long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public int getNumberOfIncorrectGuesses() {
        return numberOfIncorrectGuesses;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setNumberOfGuesses(int numberOfGuesses) {
        this.numberOfGuesses = numberOfGuesses;
    }

    public void setNumberOfIncorrectGuesses(int numberOfIncorrectGuesses) {
        this.numberOfIncorrectGuesses = numberOfIncorrectGuesses;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public String getHiddenWord() {
        String hiddenWordToString = new String(hiddenWord);
        return hiddenWordToString;
    }
    public void makeAGuess(char guess){
        Boolean isGuessCorrect = false;
        numberOfGuesses++;
        for(int i = 0; i < word.length(); i++){
            if(Character.compare(guess, word.charAt(i)) == 0){
                isGuessCorrect = true;
                hiddenWord[i] = guess;
            }
        }
        if(!isGuessCorrect){
            numberOfIncorrectGuesses++;
        }
    }
}
