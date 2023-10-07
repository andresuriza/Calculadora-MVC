package GUI;

import Business_Logic.Controller;

class CalculatorMain {
    public static void main(String args[]) {
       CalculatorPanel calcP = new CalculatorPanel();
       Controller c1 = new Controller(calcP);
    }
}