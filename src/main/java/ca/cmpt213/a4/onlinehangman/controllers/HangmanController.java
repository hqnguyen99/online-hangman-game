package ca.cmpt213.a4.onlinehangman.controllers;

import ca.cmpt213.a4.onlinehangman.model.Game;
import ca.cmpt213.a4.onlinehangman.model.GameStatus;
import ca.cmpt213.a4.onlinehangman.model.RESTGameNotFoundException;
import ca.cmpt213.a4.onlinehangman.model.RandomWord;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class HangmanController {
    String name = "NGUYEN HOANG QUYEN";
    final int MAX_INCORRECT_GUESSES = 7;
    List<Game> gameList = new ArrayList<>();
    AtomicLong id = new AtomicLong(0);
    RandomWord randomWord = new RandomWord();


    @GetMapping("/welcome")
    public String getWelcomePage(Model model){
        model.addAttribute("name", name);
        return "welcome";
    }

    @PostMapping("/game")
    public String createNewGame(Model model) {
        Game newGame = new Game(id.incrementAndGet(),randomWord.generateRandomWord());
        System.out.println(id);
        gameList.add(newGame);
        model.addAttribute("id",newGame.getId());
        model.addAttribute("hiddenWord", newGame.getHiddenWord());
        model.addAttribute("word", newGame.getWord());
        model.addAttribute("guesses", newGame.getNumberOfGuesses());
        model.addAttribute("incorrectGuesses", newGame.getNumberOfIncorrectGuesses());
        switch (newGame.getStatus()){
            case Lost:
                model.addAttribute("status", "Lost");
                break;
            case Won:
                model.addAttribute("status", "Won");
                break;
            case Active:
                model.addAttribute("status", "Active");
                break;
        }
        return "game";
    }
    @PostMapping ("/game/{gameID}")
    public String receiveGuess(@PathVariable ("gameID") long gameID,@RequestParam String guess,Model model){
        Game chosenGame = null;
        Boolean isGameIDExist = false;
        for(Game game: gameList){
            if (game.getId() == gameID){
                chosenGame = game;
                isGameIDExist = true;
                break;
            }
        }
        if(isGameIDExist) {
            if(guess != null || guess != ""){
                chosenGame.makeAGuess(guess.charAt(0));
            }

            model.addAttribute("id", chosenGame.getId());
            model.addAttribute("hiddenWord", chosenGame.getHiddenWord());
            model.addAttribute("word", chosenGame.getWord());
            model.addAttribute("guesses", chosenGame.getNumberOfGuesses());
            model.addAttribute("incorrectGuesses", chosenGame.getNumberOfIncorrectGuesses());
            if(chosenGame.getNumberOfIncorrectGuesses() > MAX_INCORRECT_GUESSES){
                chosenGame.setStatus(GameStatus.Lost);
                return "gameover";
            }
            if(chosenGame.getHiddenWord().equals(chosenGame.getWord())){
                chosenGame.setStatus(GameStatus.Won);
                return "gameWin";
            }
            switch (chosenGame.getStatus()) {
                case Lost:
                    model.addAttribute("status", "Lost");
                    break;
                case Won:
                    model.addAttribute("status", "Won");
                    break;
                case Active:
                    model.addAttribute("status", "Active");
                    break;
            }
            return "game";
        }
        else {

            throw new RESTGameNotFoundException();
        }
    }
    @GetMapping("/game/{gameID}")
    public String getGame(@PathVariable ("gameID") long gameID,@RequestParam(required = false) String guess,Model model){
        Game chosenGame = null;
        Boolean isGameIDExist = false;
        for(Game game: gameList){
            if (game.getId() == gameID){
                chosenGame = game;
                isGameIDExist = true;
                break;
            }
        }
        if(isGameIDExist) {
            if(guess != null){
                chosenGame.makeAGuess(guess.charAt(0));
            }
            if(chosenGame.getStatus() == GameStatus.Lost || chosenGame.getStatus() == GameStatus.Won){
                return "gameover";
            }
            model.addAttribute("id", chosenGame.getId());
            model.addAttribute("hiddenWord", chosenGame.getHiddenWord());
            model.addAttribute("guesses", chosenGame.getNumberOfGuesses());
            model.addAttribute("incorrectGuesses", chosenGame.getNumberOfIncorrectGuesses());

            switch (chosenGame.getStatus()) {
                case Lost:
                    model.addAttribute("status", "Lost");
                    break;
                case Won:
                    model.addAttribute("status", "Won");
                    break;
                case Active:
                    model.addAttribute("status", "Active");
                    break;
            }
            return "game";
        }
        else {
            throw new RESTGameNotFoundException();
        }
    }
    @ExceptionHandler(RESTGameNotFoundException.class)
    public ModelAndView handleException(){
        ModelAndView model = new ModelAndView("gamenotfound");
        return model;
    }
}
