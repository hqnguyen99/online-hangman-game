package ca.cmpt213.a4.onlinehangman;

import ca.cmpt213.a4.onlinehangman.model.Game;
import ca.cmpt213.a4.onlinehangman.model.RandomWord;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicLong;

// Test to run on IntelliJ terminal
public class Main {
    public static void main (String[] args) {
        AtomicLong id = new AtomicLong(0);
        RandomWord randomWord = new RandomWord();
        Game newGame = new Game(id.incrementAndGet(),randomWord.generateRandomWord());
        System.out.println(newGame.getWord());
    }
}
