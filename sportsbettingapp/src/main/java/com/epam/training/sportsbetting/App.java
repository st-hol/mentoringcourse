package com.epam.training.sportsbetting;

import static com.epam.training.sportsbetting.ui.TextConstants.NOT_ENOUGH_MONEY;
import static com.epam.training.sportsbetting.ui.TextConstants.WELCOME;
import static com.epam.training.sportsbetting.ui.TextConstants.YOUR_BALANCE_IS;
import static com.epam.training.sportsbetting.ui.TextConstants.YOU_HAVE_WON;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Result;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.domain.user.Player;
import com.epam.training.sportsbetting.service.ApplicationService;
import com.epam.training.sportsbetting.ui.ApplicationController;

public class App {

    private BigDecimal startBalance;
    private Player player;
    private List<Wager> userWagers = new ArrayList<>();
    private ApplicationService applicationService = ApplicationService.getInstance();
    private ApplicationController applicationController = ApplicationController.getInstance();

    public static void main(String[] args) {
        App app = new App();
        app.populatePlayer();
        app.processBetting();
        app.gatherResults();
    }

    private void populatePlayer() {
        player = applicationController.obtainPlayerData();
        startBalance = player.getBalance();
    }

    private void processBetting() {
        applicationController.printLine(String.format(WELCOME, player.getName()));
        List<SportEvent> allAvailableEvents = applicationService.getSportEventsData();
        while (checkPlayerHasMoney()) {
            applicationController.printLine(String.format(YOUR_BALANCE_IS, player.getBalance(), player.getCurrency()));
            applicationController.suggestOutcomeWithOddsTable(allAvailableEvents);
            int choice = applicationController.readOrQuit();
            if (choice == 0) {
                break; // quit command
            }
            placeWager(applicationService.findOutcomeOddByNumber(choice));
        }
    }

    private void placeWager(OutcomeOdd outcomeOdd) {
        while (true) {
            BigDecimal wagerAmount = applicationController.readWagerAmount();
            BigDecimal playerBalance = player.getBalance();
            if (checkPlayerHasEnoughMoney(wagerAmount)) {
                player.setBalance(playerBalance.subtract(wagerAmount));
                Wager wager = applicationService.createWager(player, wagerAmount, outcomeOdd);
                userWagers.add(wager);
                break;
            }
            applicationController.printLine(String.format(NOT_ENOUGH_MONEY, player.getBalance()));
        }
    }

    private void gatherResults() {
        Result result = applicationService.generateResult(applicationService.getSportEventsData());
        applicationService.calculateResults(result, userWagers, player);
        applicationController.printResults(player, userWagers);
        BigDecimal totalWonSum = applicationService.calculateTotalWonSum(startBalance, player.getBalance());
        applicationController.printLine(String.format(YOU_HAVE_WON, totalWonSum));
    }

    private boolean checkPlayerHasMoney() {
        return player.getBalance().compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean checkPlayerHasEnoughMoney(BigDecimal wagerAmount) {
        return player.getBalance().compareTo(wagerAmount) >= 0;
    }
}
