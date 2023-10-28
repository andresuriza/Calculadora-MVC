package MVC;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

final class Model {
    private String resultDisplay;
    private String currentOp;
    private double tempValue;
    private boolean inErrorMode;
    private boolean firstDigit;
    private final static int MAX_INPUT_DIGITS = 12;
    private final static int MAX_RESULT_DECIMALS = 5;
//----------------------------------------------------------

    private double memory[];
    private int memoryIndex;

    private static boolean isBinaryMode = false;
    private static boolean isPrimoMode = false;


    public Model() {
        reset();
        memory = new double[10];
        memoryIndex = 0;
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

    public void setOperation(char op) {
        calculate();
        
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

    public void calculate() throws IOException {
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
        
        BufferedWriter writer = new BufferedWriter(new FileWriter("Bitacora.txt", true));
        writer.write(currentOp + " = " + resultDisplay);
        writer.newLine();
    }

    public void reset() {
        tempValue = 0.0;

        resultDisplay = "0";
        isBinaryMode = false; // Sale del modo binario
        isPrimoMode = false; //sale del modo primo
        firstDigit = true;
        inErrorMode = false;

        currentOp = "";
    }
    
    public void binary() {
        try {
            double number = Double.parseDouble(resultDisplay);
            String binaryValue = toBinary(number);
            resultDisplay = binaryValue;
            isBinaryMode = true;
        } catch (NumberFormatException e) {
            resultDisplay = "Error";
        }
    }
    
    private String toBinary(double number) {
        return Integer.toBinaryString((int) number);
    }
    
    public void average() {
        double sum = 0;
        for (int i = 0; i < memoryIndex; i++) {
            sum += memory[i];
        }
        double average = memoryIndex > 0 ? sum / memoryIndex : 0;
        resultDisplay = String.valueOf(average);
    }
    
    private boolean esPrimo(double number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public void primo() {
        try {
            double number = Double.valueOf(resultDisplay);
            boolean esPrimo = esPrimo(number);
            resultDisplay = String.valueOf(esPrimo);
            isPrimoMode = true;
        } catch (NumberFormatException e) {
            resultDisplay = "Error";
        }
    }
    
    public void memory() {
           if (memoryIndex < memory.length) {
            memory[memoryIndex] = Double.parseDouble(resultDisplay);
            memoryIndex++;
        } else {
            // Si la memoria está llena, sustituye el número más antiguo
            memoryIndex = 0;
            memory[memoryIndex] = Double.parseDouble(resultDisplay);
        }
        reset();
    }
    
    public JScrollPane getData() {
           // Código para mostrar registros de Bitacora.txt en una nueva ventana
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/Bitacora.txt"))) {
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
            JTextArea textArea = new JTextArea(data.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setEditable(false);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            return scrollPane;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void enterErrorMode() {
        inErrorMode = true;
        resultDisplay = "Error";
        currentOp = "";
    }
}