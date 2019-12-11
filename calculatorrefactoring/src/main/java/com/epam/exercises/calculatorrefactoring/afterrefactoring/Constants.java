package com.epam.exercises.calculatorrefactoring.afterrefactoring;

public interface Constants {
    String ENTER_EXPRESSION = "Enter an expression: ";
    int RESULT_POSITION = 0;

    interface Operation {
        String MULTIPLY = "*";
        String DIVIDE = "/";
        String PLUS = "+";
        String MINUS = "-";
    }

    interface Regex {
        String OPERATIONS = "[0-9]+";
        String OPERANDS = "[\\+\\-\\*\\/]";
    }
}
