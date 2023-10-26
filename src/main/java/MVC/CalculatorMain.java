package MVC;

public final class CalculatorMain {
    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
        new Controller(new Model(), new View());
    }
}