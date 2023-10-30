package BusinessLogic;

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


public class Operations {
    private final static int MAX_RESULT_DECIMALS = 5;
    
    private double memory[];
    private int memoryIndex;
    
    public Operations() {
        memory = new double[10];
        memoryIndex = 0;
    }
    
    public static double doTheMath(char op, double v1, double v2)
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
    
    public String toBinary(double number) {
        return Integer.toBinaryString((int) number);
    }
    
    public boolean esPrimo(double number) {
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
    
        
    public void memory(String resultDisplay) {
        if (memoryIndex < memory.length) {
            memory[memoryIndex] = Double.parseDouble(resultDisplay);
            memoryIndex++;
            
        } else {
            // Si la memoria está llena, sustituye el número más antiguo
            memoryIndex = 0;
            memory[memoryIndex] = Double.parseDouble(resultDisplay);
        }
        
        writeAction("mem", 0, resultDisplay);
    }
    
    public double averageCalc(double sum) {
        for (int i = 0; i < memoryIndex; i++) {
                sum += memory[i];
        }
        double average = memoryIndex > 0 ? sum / memoryIndex : 0;
        return average;
    }
    
    public void writeOperation(String tempValue, String op, String valueIndisplay, String resultDisplay) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Data/Bitacora.txt", true))) {
                writer.write(tempValue + op + valueIndisplay + " = " + resultDisplay);
                writer.newLine();
        } 
            
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void writeAction(String what, double number, String resultDisplay) {
        if ("mem".equals(what)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Data/Bitacora.txt", true))) {
                writer.write("M+ " + resultDisplay + " > ");

                for (int i = 0; i < memoryIndex; i++) {
                    writer.write(Double.toString(memory[i]) + " ");
                 }

                writer.newLine();
            } 
        
            catch (IOException e) {
                    e.printStackTrace();
            }
        }
        
        if ("avg".equals(what)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Data/Bitacora.txt", true))) {
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
        
        else if ("binary".equals(what) || "primo".equals(what)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/Data/Bitacora.txt", true))) {
                    writer.write(what + " " + Double.toString(number) + " " + resultDisplay);
                    writer.newLine();
                } 

                catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    
    public JScrollPane getData() {
        // Código para mostrar registros de Bitacora.txt en una nueva ventana
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Data/Bitacora.txt"))) {
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
}