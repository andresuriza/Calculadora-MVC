package MVC;

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
    private String resultDisplay;
    private String currentOp;
    private double tempValue;
    private boolean inErrorMode;
    private boolean firstDigit;
    private final static int MAX_INPUT_DIGITS = 12;
    private final static int MAX_RESULT_DECIMALS = 5;
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
            tempValue = Double.valueOf(resultDisplay);

            currentOp = String.valueOf(op);

            firstDigit = true;
        } catch (Exception e) {
            enterErrorMode();
        }
    }

    public void calculate() {
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

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Bitacora.txt", true))) {
                writer.write(Double.toString(tempValue) + String.valueOf(op) + valueIndisplay.toString() + " = " + resultDisplay);
                writer.newLine();
            } 
            
            catch (IOException e) {
                e.printStackTrace();
            }

            firstDigit = true;
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
    
    private String toBinary(double number) {
        return Integer.toBinaryString((int) number);
    }
    
    public void binary() {
        try {
            double number = Double.parseDouble(resultDisplay);
            String binaryValue = toBinary(number);
            resultDisplay = binaryValue;
            isBinaryMode = true;
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Bitacora.txt", true))) {
                writer.write("Binario " + Double.toString(number) + " " + resultDisplay);
                writer.newLine();
            } 
            
            catch (IOException e) {
                e.printStackTrace();
            }
        } 
        
        catch (NumberFormatException e) {
            resultDisplay = "Error";
        }
    }
    
    public void average() {
        double sum = 0;
        for (int i = 0; i < memoryIndex; i++) {
            sum += memory[i];
        }
        double average = memoryIndex > 0 ? sum / memoryIndex : 0;
        resultDisplay = String.valueOf(average);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Bitacora.txt", true))) {
            writer.write("Avg ");
           
            for (int i = 0; i < memoryIndex; i++) {
                writer.write(Double.toString(memory[i]) + " ");
            }
             
            writer.write(" = " + resultDisplay);
            writer.newLine();
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }
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
                
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Bitacora.txt", true))) {
                writer.write("Primo " + Double.toString(number) + " " + resultDisplay);
                writer.newLine();
            } 
            
            catch (IOException e) {
                e.printStackTrace();
            }
            
        } 
        
        catch (NumberFormatException e) {
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
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Bitacora.txt", true))) {
            writer.write("M+ " + resultDisplay + " > ");
           
            for (int i = 0; i < memoryIndex; i++) {
                writer.write(Double.toString(memory[i]) + " ");
             }
             
            writer.newLine();
        } 
        
        catch (IOException e) {
                e.printStackTrace();
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
            
        } 
        
        catch (IOException e) {
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