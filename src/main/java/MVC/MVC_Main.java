package MVC;

public final class MVC_Main {
    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
        new Controller(new Model(), new View());
    }
}