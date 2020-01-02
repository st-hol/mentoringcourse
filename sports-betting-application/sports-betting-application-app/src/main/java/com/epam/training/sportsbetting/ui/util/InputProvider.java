package com.epam.training.sportsbetting.ui.util;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputProvider {

    private static InputProvider instance;
    private Scanner scanner;

    private InputProvider() {
        scanner = new Scanner(System.in);
    }

    public static InputProvider getInstance() {
        if (instance == null) {
            synchronized (InputProvider.class) {
                if (instance == null) {
                    instance = new InputProvider();
                }
            }
        }
        return instance;
    }

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
            return 0;
        }
    }

}
