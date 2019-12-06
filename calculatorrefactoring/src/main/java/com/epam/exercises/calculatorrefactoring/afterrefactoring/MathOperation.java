package com.epam.exercises.calculatorrefactoring.afterrefactoring;

@FunctionalInterface
public interface MathOperation {
    int calculate(int firstOperand, int secondOperand);
}
