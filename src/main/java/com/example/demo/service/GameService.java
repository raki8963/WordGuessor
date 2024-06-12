package com.example.demo.service;

import com.example.demo.utils.GameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
@Scope("prototype")
public class GameService {


    private String[] randomWords = {"father","mother","sister","hello","light","java"};

    private String randomlyChoosenWord = null;

    Random random = new Random();

    private char[] allCharactersOfTheWords = null;

    public GameService() {
        randomlyChoosenWord = randomWords[random.nextInt(randomWords.length)];
        System.out.println(randomlyChoosenWord);
        allCharactersOfTheWords = new char[randomlyChoosenWord.length()];
    }

    @Override
    public String toString() {
        String ret="";
        for(char ch:allCharactersOfTheWords){
            if(ch == '\u0000'){
                ret = ret + "_";
            }else{
                ret = ret + ch;
            }
            ret += " ";
        }
        System.out.println(ret);
        return  ret;
    }

    public boolean addGuessChar(String guessedChar){

        boolean isGuessedCorrect = false;

        if (guessedChar==null || guessedChar.isEmpty()) {
            return false;
        }
        int i=0;
        for (char ch:randomlyChoosenWord.toCharArray()){
            System.out.print(ch);
            if(ch==guessedChar.charAt(0) && allCharactersOfTheWords[i]=='\u0000'){
                allCharactersOfTheWords[i] = ch;
                isGuessedCorrect = true;
            }
            i++;
        }
        System.out.println(randomlyChoosenWord +"  "+ Arrays.toString(allCharactersOfTheWords));
        return isGuessedCorrect;
    }
}
