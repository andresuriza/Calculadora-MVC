package MVC;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
//Falta arreglar el binario y agregar lo del KeyListener
public class CalculatorApp extends JFrame implements ActionListener {
    private JTextField textField;
    private String currentInput;
    private double memory[];
    private int memoryIndex;

    private boolean isBinaryMode = false;
    private boolean isPrimoMode = false;


    public CalculatorApp() {
        setTitle("Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        currentInput = "";
        memory = new double[10];
        memoryIndex = 0;

        // Configura el textfield
        textField = new JTextField();
        add(textField, BorderLayout.NORTH);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));

        // Panel para botones de números y operaciones básicas
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        String[] buttonLabels = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "C", "0", ".", "+", "="};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);

        // Panel para operaciones avanzadas
        JPanel advancedPanel = new JPanel(new GridLayout(1, 5));
        String[] advancedLabels = {"Primo", "Binario", "Data", "Avg", "M+"};
        for (String label : advancedLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            advancedPanel.add(button);
        }
        add(advancedPanel, BorderLayout.SOUTH);

        //textField.addKeyListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "=":
                calculate();
                break;
            case "C":
                clear();
                break;
            case "M+":
                addToMemory();
                break;
            case "Avg":
                calculateAverage();
                break;
            case "Data":
                showData();
                break;
            case "Binario":
                convertToBinary();
                break;
            case "Primo":
                primo();
                break;


            default:
                handleInput(command);
                break;

        }
    }

    public void keyTyped(KeyEvent e) {
        // Método KeyListener para manejar las teclas presionadas
        char keyChar = e.getKeyChar();
        if (Character.isDigit(keyChar) || "+-*/=C.".indexOf(keyChar) != -1) {
            String keyText = String.valueOf(keyChar);
            handleInput(keyText);
        }
    }

    // Implementación de otras funciones

    private void calculate() {
        if (isBinaryMode) {
            textField.setText("Error: Modo Binario");
            return;
        }
        if (isPrimoMode) {
            textField.setText("Error: Modo Primo");
            return;
        }
        if (!currentInput.isEmpty()) {
            String[] parts = currentInput.split("[-+*/]");
            if (parts.length == 2) {
                double num1 = Double.parseDouble(parts[0]);
                double num2 = Double.parseDouble(parts[1]);
                String operator = currentInput.replaceAll("[0-9.]", "");
                double result = 0;

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            // Manejar la división por cero
                            textField.setText("Error");
                            return;
                        }
                        break;
                }

                // Mostrar el resultado en el textfield
                textField.setText(String.valueOf(result));

                // Registrar la operación en Bitacora.txt
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Bitacora.txt", true))) {
                    writer.write(currentInput + " = " + result);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                currentInput = String.valueOf(result);
            }
        }
    }

    private void clear() {
        isBinaryMode = false; // Sale del modo binario
        isPrimoMode = false; //sale del modo primo
        currentInput = "";
        textField.setText("");
    }

    private void addToMemory() {
        if (memoryIndex < memory.length) {
            memory[memoryIndex] = Double.parseDouble(currentInput);
            memoryIndex++;
        } else {
            // Si la memoria está llena, sustituye el número más antiguo
            memoryIndex = 0;
            memory[memoryIndex] = Double.parseDouble(currentInput);
        }
        clear();
    }

    private void calculateAverage() {
        double sum = 0;
        for (int i = 0; i < memoryIndex; i++) {
            sum += memory[i];
        }
        double average = memoryIndex > 0 ? sum / memoryIndex : 0;
        textField.setText(String.valueOf(average));
    }

    private void convertToBinary() {
        try {
            double number = Double.parseDouble(currentInput);
            String binaryValue = toBinary(number);
            textField.setText(binaryValue);
            isBinaryMode = true;
        } catch (NumberFormatException e) {
            textField.setText("Error");
        }
    }

    private void primo() {
        try {
            double number = Double.parseDouble(currentInput);
            boolean esPrimo = esPrimo(number);
            textField.setText(String.valueOf(esPrimo));
            isPrimoMode = true;
        } catch (NumberFormatException e) {
            textField.setText("Error");
        }
    }

    private void showData() {
        // Código para mostrar registros de Bitacora.txt en una nueva ventana
        try (BufferedReader reader = new BufferedReader(new FileReader("Bitacora.txt"))) {
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
            JTextArea textArea = new JTextArea(data.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            JOptionPane.showMessageDialog(this, scrollPane, "Bitácora", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleInput(String input) {
        if (isPrimoMode) {
            textField.setText("Error: Modo Primo");
            return;
        }
        currentInput += input;
        textField.setText(currentInput);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorApp calculator = new CalculatorApp();
            calculator.setVisible(true);
        });
    }

    // Implementar otros métodos de KeyListener
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    //binario
    private String toBinary(double number) {
        long binaryValue = Double.doubleToRawLongBits(number);
        return Long.toBinaryString(binaryValue);
    }
    //primo
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

}