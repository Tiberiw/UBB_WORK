package Laborator1MAP.Domain;

public class ComplexExpression {

    ComplexNumber[] operands;
    ComplexOperator operator;

    public ComplexExpression(ComplexNumber[] operands, ComplexOperator operator) {
        this.operands = operands;
        this.operator = operator;
    }

    public ComplexNumber solve() {

        ComplexNumber solution = operator.operate(operands[0],operands[1]);
        for(int i = 2; i < operands.length; i++) {
            solution.copy(operator.operate(solution,operands[i]));
            /* solution = solution op operand[i] */
        }

        return solution;
    }
}
