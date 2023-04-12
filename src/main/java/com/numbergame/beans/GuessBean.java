package com.numbergame.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
@Component
@ManagedBean(name = "guessBean")
//@ApplicationScoped //Guarda la información en general y no por sesión
@SessionScoped //Guarda la información por sesión y no por aplicación
public class GuessBean implements Serializable {

    @Autowired
    ConfigurationService configurationService;
    private int currentNumber;
    private int numAttempts;
    private int currentPrize;
    private int prize;
    private String gameStatus = "";
    private int guessNumber;
    private ArrayList<Integer> failedAttempts;

    public GuessBean() {
        gameStatus = "Comenzar a jugar ";
    }

    public ArrayList<Integer> getFailedAttempts() {
        return failedAttempts;
    }

    public int getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber (int guessNumber) {
        this.guessNumber = guessNumber;
    }
    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public int getNumAttempts() {
        return numAttempts;
    }

    public void setNumAttempts(int numAttempts) {
        this.numAttempts = numAttempts;
    }

    public int getCurrentPrize() {
        return currentPrize;
    }

    public void setCurrentPrize(int currentPrize) {
        this.currentPrize = currentPrize;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }


    public void guess(int guessNumber) {
        if (numAttempts == 0){
            gameStatus = "Comenzar a jugar ";
        }
        if (guessNumber == currentNumber) {
            gameStatus = "Ganaste $"+ currentPrize +"!";
            restart();
        } else {
            numAttempts++;
            currentPrize -= 10000;
            this.failedAttempts.add(0, guessNumber);
            this.guessNumber = 0;
            if (currentPrize == 0){
                gameStatus = "Perdiste :c";
                restart();
            }
        }
    }
    @Bean
    public CommandLineRunner currentPrice() throws Exception {
        return args -> {
            configurationService.addConfiguration(new ConfigutationB("Premio","100000"));
            configurationService.getAllConfiguration().forEach(configutationB -> System.out.println(configutationB));
            prize = Integer.parseInt(configurationService.getConfiguration("Premio").getValor());
            restart();
        };
    }
    public void restart() {
        Random random = new Random();
        currentNumber = random.nextInt(100) + 1;
        numAttempts = 0;
        guessNumber = 0;
        currentPrize = prize;
        failedAttempts = new ArrayList<>();
    }
}

