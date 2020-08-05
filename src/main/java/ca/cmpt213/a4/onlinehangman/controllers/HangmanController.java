package ca.cmpt213.a4.onlinehangman.controllers;

import ca.cmpt213.a4.onlinehangman.model.Game;
import ca.cmpt213.a4.onlinehangman.model.RandomWord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class HangmanController {
    String name = "NGUYEN VIET DUC MINH";
    List<Game> gameList = new ArrayList<>();
    AtomicLong id = new AtomicLong(0);
    RandomWord randomWord = new RandomWord("commonWords.txt");


    @GetMapping("/welcome")
    public String getWelcomePage(Model model){
        model.addAttribute("name", name);
        return "welcome";
    }

    @PostMapping("/game")
    public String createNewGame(Model model) throws FileNotFoundException {
        Game newGame = new Game(id.incrementAndGet(),randomWord.generateRandomWord());
        gameList.add(newGame);
        model.addAttribute("game",newGame);
        return "game";
    }
    @GetMapping("/game")
    public String getGame(Model model){
        model.addAttribute("name", name);
        return "game";
    }
}
