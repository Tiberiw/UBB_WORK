package Laborator1MAP.Domain;

import Laborator1MAP.Utils.ExpressionFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexExpressionFactory extends ExpressionFactory {

    private static final ComplexExpressionFactory instance = new ComplexExpressionFactory();

    private final ComplexParser complexParser = new ComplexParser();

    /* REGEX */
    /* Used for validation */
    private final Pattern complexPattern = Pattern.compile("^(-?\\d+|-?(\\d+\\*?)?i|-?\\d+[-+](\\d+\\*?)?i)([-+*/](-?\\d+|-?(\\d+\\*?)?i|-?\\d+[-+](\\d+\\*?)?i))*$");

    private ComplexExpressionFactory() {}

    public static ComplexExpressionFactory getInstance() {
        return instance;
    }

    @Override
    public boolean validCheck(String[] args) {

        StringBuilder finalString = new StringBuilder();

        for(String argument:args) {
            finalString.append(argument);
        }

        /* REGEX MATCHER */
       Matcher complexPatternMatcher = complexPattern.matcher(finalString.toString());

        /* RETURN IF THE EXPRESSION IS FOUND */
        return complexPatternMatcher.find();
    }

    @Override
    public ComplexExpression parse(String[] args) {

        /* Get Operator  object*/
        ComplexOperator operator = complexParser.parseOperator(args[1]);

        /* Get operands */
        ComplexNumber[] operands = new ComplexNumber[(args.length / 2) + 1];

        for(int i = 0, k = 0; i < args.length; i+=2,k++) {

            /* Get ComplexNumber object */
            operands[k] = complexParser.parseOperand(args[i]);
        }

        /* Return ComplexExpression object*/
        return new ComplexExpression(operands, operator);
    }
}
