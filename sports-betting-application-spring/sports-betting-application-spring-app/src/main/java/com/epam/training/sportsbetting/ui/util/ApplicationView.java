package com.epam.training.sportsbetting.ui.util;

import static com.epam.training.sportsbetting.ui.TextConstants.RESULTS;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.domain.user.Player;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationView {

    @Autowired
    private MessageSource messageSource;

    private Locale currentLocale = Locale.ENGLISH;

    public void printLine(String s) {
        log.info(s);
    }

    public void suggestOutcomeWithOddsTable(List<SportEvent> sportEvents) {
        if (sportEvents == null) {
            log.warn("No events.");
            return;
        }
        sportEvents.forEach(sportEvent -> sportEvent.getBets()
                .forEach(bet -> log.info(bet.toString())));
        log.info(messageSource.getMessage("choose.bet", null, currentLocale));
    }

    public void printResults(Player player, List<Wager> wagers) {
        printLine(RESULTS + "\n");
        wagers.forEach(wager -> log.info(wager.toString()));
        printLine(messageSource.getMessage("your.new.balance", new Object[]{player.getBalance()}, currentLocale));
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public void setCurrentLocaleFromString(String stringLocale) {
        setCurrentLocale(new Locale(stringLocale));
    }
}
