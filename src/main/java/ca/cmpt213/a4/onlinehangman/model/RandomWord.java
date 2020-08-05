package ca.cmpt213.a4.onlinehangman.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RandomWord {
    File file;

    public RandomWord(String path) {
        this.file = new File(path);
    }
    public String generateRandomWord() throws FileNotFoundException {
        List<String> wordList = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            wordList.add(scanner.nextLine());
        }
        Collections.shuffle(wordList);
        return wordList.get(0);
    }
}
