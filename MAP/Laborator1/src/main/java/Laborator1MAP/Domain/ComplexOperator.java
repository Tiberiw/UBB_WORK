package Laborator1MAP.Domain;

public enum ComplexOperator {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private String operator;
    ComplexOperator(String operator) {
        this.operator = operator;
    }

    public ComplexNumber operate(ComplexNumber number1, ComplexNumber number2) {



        switch(this.operator) {

            case "-":
                return new ComplexNumber(number1.getReal() - number2.getReal(),
                                    number1.getImaginary() - number2.getImaginary());

            case "*":
                return new ComplexNumber(number1.getReal() * number2.getReal() -
                                            number1.getImaginary() * number2.getImaginary(),
                                            number1.getReal() * number2.getImaginary() +
                                                    number1.getImaginary() * number2.getReal());

            case "/":
                double a = number1.getReal();
                double b = number1.getImaginary();
                double c = number2.getReal();
                double d = number2.getImaginary();


                return new ComplexNumber( ( a*c + b*d ) / ( c*c + d*d ), ( b*c - a*d ) / ( c*c + d*d) );
        }

        return new ComplexNumber(number1.getReal() + number2.getReal(), number1.getImaginary() + number2.getImaginary());
    }
}
