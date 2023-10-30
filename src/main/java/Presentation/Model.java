package Presentation;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

final class Model {
    public String resultDisplay;
    public String currentOp;
    public double tempValue;
    public boolean inErrorMode;
    public boolean firstDigit;
    private final static int MAX_INPUT_DIGITS = 12;

    public static boolean isBinaryMode = false;
    public static boolean isPrimoMode = false;
    
    public Model() {
        reset();
    }

    public String getResultDisplay() {
        return resultDisplay;
    }

    public String getOperationDisplay() {
        return currentOp;
    }

    public void insertNumber(int n) {
        if (isBinaryMode) {
            resultDisplay = "Error: Modo Binario";
            return;
        }
        if (isPrimoMode) {
            resultDisplay = "Error: Modo Primo";
            return;
        }
        
        if (inErrorMode) {
            return;
        }

        if (firstDigit) {
            resultDisplay = String.valueOf(n);
            firstDigit = false;
            return;
        }

        if (resultDisplay.length() >= MAX_INPUT_DIGITS) {
            return;
        }

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

        if (firstDigit) {
            resultDisplay = "0.";
            firstDigit = false;
            return;
        }
        if (resultDisplay.contains("-")) {
            resultDisplay = "-0.";
            return;
        }

        if (resultDisplay.contains(".")) {
            return;
        }

        resultDisplay += ".";
    }

    public void setOperation(char op) {
        if (isBinaryMode) {
            resultDisplay = "Error: Modo Binario";
            return;
        }
        if (isPrimoMode) {
            resultDisplay = "Error: Modo Primo";
            return;
        }
        if (inErrorMode) {
            return;
        }

        try {
            tempValue = Double.valueOf(resultDisplay);

            currentOp = String.valueOf(op);

            firstDigit = true;
        } catch (Exception e) {
            enterErrorMode();
        }
    }

    public void calculate(double result) {
                                        
        try {
            resultDisplay = Double.toString(result);
            currentOp = "";          
        } 
        
        catch (NumberFormatException | ArithmeticException e) {
            enterErrorMode();
        }
    }

    public void reset() {
        tempValue = 0.0;

        resultDisplay = "0";
        firstDigit = true;
        inErrorMode = false;
        isBinaryMode = false; // Sale del modo binario
        isPrimoMode = false; //sale del modo primo
        currentOp = "";
    }
    
    public void binary(String binaryValue) {
        try {
            resultDisplay = binaryValue;
            isBinaryMode = true;
        }    
        
        catch (NumberFormatException e) {
            resultDisplay = "Error";
        }

    }
    
    public void average(double average) {
        resultDisplay = String.valueOf(average);
    }
    
    public void primo(boolean esPrimo) {
        try {
            resultDisplay = String.valueOf(esPrimo);
            isPrimoMode = true;
            
        } 
        
        catch (NumberFormatException e) {
            resultDisplay = "Error";
        }
    }

    private void enterErrorMode() {
        inErrorMode = true;
        resultDisplay = "Error";
        currentOp = "";
    }
}