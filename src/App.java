import co.edu.uptc.controllers.AppController;

public class App {
    public static void main(String[] args) {
        AppController ac = new AppController();

        ac.run(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]));
    }
}
