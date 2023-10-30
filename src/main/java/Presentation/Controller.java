package Presentation;

import BusinessLogic.Operations;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

final class Controller {

    private final Model model;
    private final View view;
    private Operations operations;
    
    public Controller(Model m, View v) {
        model = m;
        view = v;
        operations = new Operations();
        
        initView();
        initController();
    }

    private void initView() {
        updateView();
    }

    private void updateView() {
        view.getResultField().setText(model.getResultDisplay());
    }

    private void initController() {
        bindButtons();

        view.addKeyListener(new CalculatorControllerKeyListener(this));
    }

    private void bindButtons() {
        view.getNum0Button().addActionListener(e -> pressNumberButton(0));
        view.getNum1Button().addActionListener(e -> pressNumberButton(1));
        view.getNum2Button().addActionListener(e -> pressNumberButton(2));
        view.getNum3Button().addActionListener(e -> pressNumberButton(3));
        view.getNum4Button().addActionListener(e -> pressNumberButton(4));
        view.getNum5Button().addActionListener(e -> pressNumberButton(5));
        view.getNum6Button().addActionListener(e -> pressNumberButton(6));
        view.getNum7Button().addActionListener(e -> pressNumberButton(7));
        view.getNum8Button().addActionListener(e -> pressNumberButton(8));
        view.getNum9Button().addActionListener(e -> pressNumberButton(9));
        view.getResetButton().addActionListener(e -> pressResetButton());
        view.getDivButton().addActionListener(e -> pressOperationButton('÷'));
        view.getMulButton().addActionListener(e -> pressOperationButton('×'));
        view.getSubButton().addActionListener(e -> pressOperationButton('-'));
        view.getAddButton().addActionListener(e -> pressOperationButton('+'));
        view.getEqualButton().addActionListener(e -> pressEqualButton());
        view.getDotButton().addActionListener(e -> pressDotButton());
        view.getMemButton().addActionListener(e -> pressMemButton());
        view.getAvgButton().addActionListener(e -> pressAvgButton());
        view.getPrimoButton().addActionListener(e -> pressPrimoButton());
        view.getBinButton().addActionListener(e -> pressBinButton());
        view.getDataButton().addActionListener(e -> pressDataButton());
    }

    private final class CalculatorControllerKeyListener implements KeyListener {

        private final Controller controller;

        public CalculatorControllerKeyListener(Controller c) {
            controller = c;
        }
        
        @Override
        public void keyTyped(KeyEvent e) {
            switch (e.getKeyChar()) {
                case '0':
                    controller.pressNumberButton(0);
                    break;
                case '1':
                    controller.pressNumberButton(1);
                    break;
                case '2':
                    controller.pressNumberButton(2);
                    break;
                case '3':
                    controller.pressNumberButton(3);
                    break;
                case '4':
                    controller.pressNumberButton(4);
                    break;
                case '5':
                    controller.pressNumberButton(5);
                    break;
                case '6':
                    controller.pressNumberButton(6);
                    break;
                case '7':
                    controller.pressNumberButton(7);
                    break;
                case '8':
                    controller.pressNumberButton(8);
                    break;
                case '9':
                    controller.pressNumberButton(9);
                    break;
 
                case '-':
                    controller.pressOperationButton('-');
                    break;
                case '+':
                    controller.pressOperationButton('+');
                    break;
                case '.':
                case ',':
                    controller.pressDotButton();
                    break;
                case '/':
                    controller.pressOperationButton('÷');
                    break;
                case '*':
                    controller.pressOperationButton('×');
                    break;
                case '=':
                    controller.pressEqualButton();
                    break;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DELETE:
                    controller.pressResetButton();
                    break;
                case KeyEvent.VK_ENTER:
                    controller.pressEqualButton();
                    break;
            }
        }
    }

    private void pressNumberButton(int n) {
        model.insertNumber(n);
        updateView();
    }

    private void pressOperationButton(char op) {
        pressEqualButton();
        model.setOperation(op);
        updateView();
    }

    private void pressDotButton() {
        model.insertDot();
        updateView();
    }

    private void pressResetButton() {
        model.reset();
        updateView();
    }

    private void pressEqualButton() {
        if (model.isBinaryMode) {
            model.resultDisplay = "Error: Modo Binario";
            return;
        }
        if (model.isPrimoMode) {
            model.resultDisplay = "Error: Modo Primo";
            return;
        }
        
        if (model.inErrorMode) {
            return;
        }

        if (model.currentOp.isEmpty()) {
            return;
        }
 
        char op = model.currentOp.charAt(0);
        Double valueIndisplay = Double.valueOf(model.resultDisplay);
        double result = Operations.doTheMath(op, model.tempValue, valueIndisplay);
        
        model.calculate(result);
        operations.writeOperation(Double.toString(model.tempValue), String.valueOf(op), valueIndisplay.toString(), model.resultDisplay);
        model.firstDigit = true;
        updateView();
    }
    
    private void pressMemButton() {
        operations.memory(model.resultDisplay);
        pressResetButton();
        updateView();
    }
    
    private void pressBinButton() {
        double number = Double.parseDouble(model.resultDisplay);
        String binaryValue = operations.toBinary(number);
        model.binary(binaryValue);
        operations.writeAction("binary", number, binaryValue);
        updateView();
    }
    
    private void pressAvgButton() {
        double sum = 0;
        model.average(operations.averageCalc(sum));
        operations.writeAction("avg", 0, model.resultDisplay);
        updateView();
    }
    
    private void pressPrimoButton() {
        double number = Double.valueOf(model.resultDisplay);
        boolean esPrimo = operations.esPrimo(number);
        model.primo(esPrimo);
        operations.writeAction("primo", number, Boolean.toString(esPrimo));
        updateView();
    }
    
    private void pressDataButton() {
        JScrollPane scrollPane = operations.getData();
        JOptionPane.showMessageDialog(view, scrollPane, "Bitácora", JOptionPane.PLAIN_MESSAGE);
        updateView();
    }
}