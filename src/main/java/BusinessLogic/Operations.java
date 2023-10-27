package BusinessLogic;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Operations {
    
    private final static int MAX_INPUT_DIGITS = 12;
    private final static int MAX_RESULT_DECIMALS = 5;
    
    public double operationExec(char operator, double num1, double num2)
        throws ArithmeticException {
            double result = 0.0;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '×':
                    result = num1 * num2;
                    break;
                case '÷':
                    if (num2 == 0.0) {
                        throw new ArithmeticException("Division by 0");
                    }
                    result = num1 / num2;
                    break;
            }     

        return roundLogic(result, MAX_RESULT_DECIMALS);
    }

    public double roundLogic(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}