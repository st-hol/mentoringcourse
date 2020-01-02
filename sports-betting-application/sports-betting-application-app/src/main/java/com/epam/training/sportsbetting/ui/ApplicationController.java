package com.epam.training.sportsbetting.ui;

import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.domain.user.Player;
import com.epam.training.sportsbetting.ui.util.ApplicationView;
import com.epam.training.sportsbetting.ui.util.InputProvider;

import java.math.BigDecimal;
import java.util.List;

import static com.epam.training.sportsbetting.ui.TextConstants.*;

public class ApplicationController {

    private static ApplicationController instance;
    private ApplicationView applicationView;
    private InputProvider inputProvider;

    private ApplicationController() {
        this.applicationView = ApplicationView.getInstance();
        this.inputProvider = InputProvider.getInstance();
    }

    public static ApplicationController getInstance() {
        if (instance == null) {
            synchronized (ApplicationController.class) {
                if (instance == null) {
                    instance = new ApplicationController();
                }
            }
        }
        return instance;
    }

    public void printLine(String str) {
        applicationView.printLine(str);
    }

    public void suggestOutcomeWithOddsTable(List<SportEvent> sportEvents) {
        applicationView.suggestOutcomeWithOddsTable(sportEvents);
    }

    public void printResults(Player player, List<Wager> wagers) {
        applicationView.printResults(player, wagers);
    }

    public int readOrQuit() {
        return inputProvider.readOrQuit();
    }

    public BigDecimal readWagerAmount() {
        applicationView.printLine(HOW_MUCH_BET);
        return inputProvider.readBigDecimal();
    }

    public Player obtainPlayerData() {
        Player player = new Player();
        applicationView.printLine(ASK_NAME);
        player.setName(inputProvider.readWithScanner());
        applicationView.printLine(ASK_BALANCE);
        player.setBalance(inputProvider.readBigDecimal());
        return player;
    }

}
