package com.epam.training.sportsbetting.ui.util;

import java.math.BigDecimal;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InputProvider {

    @Autowired
    private Scanner scanner;

    public String readWithScanner() {
        return scanner.next();
    }

    public BigDecimal readBigDecimal() {
        return scanner.nextBigDecimal();
    }

    public int readOrQuit() {
        try {
            return scanner.nextInt();
        } catch (NumberFormatException e) {
            log.error("User input failed:", e);
            return 0;
        }
    }

}
