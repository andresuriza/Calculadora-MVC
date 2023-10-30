package MVC;

public final class MVC_Main {
    public static void main(String[] args) {
        new Controller(new Model(), new View());
    }
}