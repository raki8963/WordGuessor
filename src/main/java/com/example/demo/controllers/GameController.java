package com.example.demo.controllers;


import com.example.demo.service.GameService;
import com.example.demo.utils.GameUtils;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameUtils gameUtils;

    @GetMapping("/game-home")
    public String showGameHome(Model model,@RequestParam(value = "guessedChar",required = false) String guessedchar){
        boolean isGuessed = gameService.addGuessChar(guessedchar);
        if (guessedchar!=null && !guessedchar.isEmpty() && !isGuessed){
            gameUtils.reduceTry();
        }
        String randomword = gameService.toString();
        model.addAttribute("word",randomword);
        model.addAttribute("numberoftries",gameUtils.getTriesRemaining());
        return "game-home-page";
    }

    @GetMapping("/reload")
    public String reloadGame(){
        gameService = gameUtils.reload();
        return "redirect:/game-home";
    }

}
