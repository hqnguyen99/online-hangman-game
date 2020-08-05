package ca.cmpt213.a4.onlinehangman.model;

public class Game {
    long id;
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
    }

}
