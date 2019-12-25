package com.epam.training.sportsbetting.ui.util;

import static com.epam.training.sportsbetting.ui.TextConstants.CHOOSE_BET;
import static com.epam.training.sportsbetting.ui.TextConstants.RESULTS;
import static com.epam.training.sportsbetting.ui.TextConstants.YOUR_NEW_BALANCE_IS;

import java.util.List;

import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.domain.user.Player;


public class ApplicationView {

    private static ApplicationView instance;

    private ApplicationView() {
    }

    public static ApplicationView getInstance() {
        if (instance == null) {
            synchronized (ApplicationView.class) {
                if (instance == null) {
                    instance = new ApplicationView();
                }
            }
        }
        return instance;
    }

    public void printLine(String s) {
        System.out.println(s);
    }

    public void suggestOutcomeWithOddsTable(List<SportEvent> sportEvents) {
        if (sportEvents == null) {
            System.out.println("No events.");
            return;
        }
        sportEvents.forEach(sportEvent -> sportEvent.getBets()
                .forEach(System.out::println));
        System.out.println(CHOOSE_BET);
    }

    public void printResults(Player player, List<Wager> wagers) {
        printLine(RESULTS + "\n");
        wagers.forEach(System.out::println);
        printLine(String.format(YOUR_NEW_BALANCE_IS, player.getBalance()));
    }

}
