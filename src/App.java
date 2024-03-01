import co.edu.uptc.controllers.AppController;

public class App {
    public static void main(String[] args) {
        AppController ac = new AppController();
        ac.generatePersons(2, 2, "/");

        // TODO Revisar como hacer el .jar bien hecho
        // ac.generatePersons(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
        // args[2]);
    }
}
