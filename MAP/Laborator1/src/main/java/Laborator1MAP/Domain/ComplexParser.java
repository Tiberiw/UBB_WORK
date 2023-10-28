package Laborator1MAP.Domain;


public class ComplexParser{

    /* Transform operator string in ComplexOperator enum value */
    public ComplexOperator parseOperator(String operator) {

        ComplexOperator result = ComplexOperator.ADDITION;;

        result = switch (operator) {
            case "-" -> ComplexOperator.SUBTRACTION;
            case "*" -> ComplexOperator.MULTIPLY;
            case "/" -> ComplexOperator.DIVIDE;
            default -> result;
        };


        return result;
    }

    /* Transform string operand in ComplexNumber type */
    public ComplexNumber parseOperand(String operand) {


        StringBuilder imaginary = new StringBuilder();
        StringBuilder real = new StringBuilder();

        StringBuilder current = real;

        /* If the number has only the real part */
        if(!operand.contains("i")) {
            real.append(operand);
            imaginary.append("0");
        } else {


            for( int i = 0; i < operand.length() - 1; i++) {

                /* + - */
                if(!Character.isDigit(operand.charAt(i)) && i != 0) {
                    current = imaginary;
                }
                if(operand.toCharArray()[i] == '*')
                    continue;

                current.append(operand.charAt(i));
            }

            /* 2i , -2i */
            if(current == real) {
                imaginary.append(current);
                real.setLength(0);
                real.append("0");
            }

            /* i  -i*/
            if(imaginary.toString().equals("-") || imaginary.isEmpty() || imaginary.toString().equals("+"))
                imaginary.append("1");

        }

        return new ComplexNumber(Integer.parseInt(real.toString()), Integer.parseInt(imaginary.toString()));

    }

}
