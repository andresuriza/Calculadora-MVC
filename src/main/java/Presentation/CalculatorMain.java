package Presentation;

public final class CalculatorMain {
    public static void main(String[] args) {
        new Controller(new Model(), new View());
    }
}