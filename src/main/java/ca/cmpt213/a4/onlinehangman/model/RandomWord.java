package ca.cmpt213.a4.onlinehangman.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Generate random word from text file
public class RandomWord {
    File file = new File("src/commonWords.txt");

    public String generateRandomWord()  {
        List<String> wordList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                wordList.add(scanner.nextLine());
            }
            Collections.shuffle(wordList);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return wordList.get(0);
    }
}
