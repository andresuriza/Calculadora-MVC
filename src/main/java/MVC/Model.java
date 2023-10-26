package MVC;

import java.math.BigDecimal;
import java.math.RoundingMode;

final class Model {
    private String resultDisplay;
    private String currentOp;
    private double tempValue;
    private boolean inErrorMode;
    private boolean firstDigit;
    private final static int MAX_INPUT_DIGITS = 12;
    private final static int MAX_RESULT_DECIMALS = 5;

    public Model() {
        reset();
    }

    private static double doTheMath(char op, double v1, double v2)
            throws ArithmeticException {
        double result = 0.0;

        switch (op) {
            case '+':
                result = v1 + v2;
                break;
            case '-':
                result = v1 - v2;
                break;
            case '×':
                result = v1 * v2;
                break;
            case '÷':
                if (v2 == 0.0) {
                    throw new ArithmeticException("Division by 0");
                }
                result = v1 / v2;
                break;
        }

        return round(result, MAX_RESULT_DECIMALS);
    }

    private static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String getResultDisplay() {
        return resultDisplay;
    }

    public String getOperationDisplay() {
        return currentOp;
    }

    public void insertNumber(int n) {
        if (inErrorMode) {
            return;
        }

        // Control if we have to replace the display
        if (firstDigit) {
            resultDisplay = String.valueOf(n);
            firstDigit = false;
            return;
        }
        
        // Control we don't go over the limit of the display
        if (resultDisplay.length() >= MAX_INPUT_DIGITS) {
            return;
        }

        // Control we don't have 0s on the left
        if (resultDisplay.equals("0")) {
            resultDisplay = String.valueOf(n);
            return;
        }
        if (resultDisplay.equals("-0")) {
            resultDisplay = "-" + n;
            return;
        }

        resultDisplay += n;
    }

    public void insertDot() {
        if (inErrorMode) {
            return;
        }

        // Control that if it is the firstDigit it will add a 0 to the left
        if (firstDigit) {
            resultDisplay = "0.";
            firstDigit = false;
            return;
        }
        if (resultDisplay.contains("-")) {
            resultDisplay = "-0.";
            return;
        }

        // Control we don't have more than one dot at the same time
        if (resultDisplay.contains(".")) {
            return;
        }

        resultDisplay += ".";
    }

    public void switchSign() {
        if (inErrorMode) {
            return;
        }

        // Control if we are expecting the user to introduce a new number
        if (firstDigit && !currentOp.isEmpty()) {
            resultDisplay = "-0";
            firstDigit = false;
            return;
        }

        if (resultDisplay.charAt(0) == '-') {
            resultDisplay = resultDisplay.substring(1);
        } else {
            resultDisplay = "-" + resultDisplay;
        }

        if (firstDigit) {
            firstDigit = false;
        }
    }

    public void setOperation(char op) {
        calculate();

        if (inErrorMode) {
            return;
        }

        try {
            // Stores the current value on display so we don't loose it when the
            // user introduces a new number
            tempValue = Double.valueOf(resultDisplay);

            currentOp = String.valueOf(op);

            // After this operation we expect the user to introduce a new number
            firstDigit = true;
        } catch (Exception e) {
            enterErrorMode();
        }
    }

    public void calculate() {
        if (inErrorMode) {
            return;
        }

        if (currentOp.isEmpty()) {
            return;
        }

        try {
            char op = currentOp.charAt(0);
            Double valueIndisplay = Double.valueOf(resultDisplay);

            Double result = doTheMath(op, tempValue, valueIndisplay);

            resultDisplay = result.toString();
            currentOp = "";

            // After this operation we expect the user to introduce a new number
            firstDigit = true;
        } catch (NumberFormatException | ArithmeticException e) {
            enterErrorMode();
        }
    }

    public void clean() {
        if (inErrorMode) {
            return;
        }

        resultDisplay = "0";
        firstDigit = true;
    }

    public void reset() {
        tempValue = 0.0;

        resultDisplay = "0";
        firstDigit = true;
        inErrorMode = false;

        currentOp = "";
    }
    
    public void binary() {
        //TODO
        System.out.println("Binary button pressed");
    }
    
    public void average() {
        //TODO
        System.out.println("Average button pressed");
    }
    
    public void primo() {
        //TODO
        System.out.println("Primo button pressed");
    }
    
    public void memory() {
        //TODO
        System.out.println("Memory button pressed");
    }
    
    public void get_data() {
        //TODO
        System.out.println("Data button pressed");
    }

    private void enterErrorMode() {
        inErrorMode = true;
        resultDisplay = "Error";
        currentOp = "";
    }
}