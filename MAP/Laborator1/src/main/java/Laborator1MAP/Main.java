package Laborator1MAP;

import Laborator1MAP.Domain.ComplexExpressionFactory;
import Laborator1MAP.Domain.ComplexNumber;
import Laborator1MAP.Domain.ComplexParser;

import java.util.Deque;

public class Main {
    public static void main(String[] args) {

        /* Singleton */
        ComplexExpressionFactory solve = ComplexExpressionFactory.getInstance();
        if(solve.validCheck(args)) {
            System.out.println("Result: " + solve.parse(args).solve().toString());
        } else {
            System.out.println("Invalid expression");
        }
    }
}
