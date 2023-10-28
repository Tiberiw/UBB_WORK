package Laborator1MAP.Utils;

import Laborator1MAP.Domain.ComplexExpression;

public abstract class ExpressionFactory {

    public abstract boolean validCheck(String[] args);

    public abstract ComplexExpression parse(String[] args);

}
