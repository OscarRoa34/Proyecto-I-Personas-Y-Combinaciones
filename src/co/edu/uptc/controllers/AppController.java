package co.edu.uptc.controllers;

public class AppController {
    public void run(int numberOfNames, int numberOfLastNames, String separator, int modif) {
        CombinationController cc = new CombinationController();

        cc.generatePersons(numberOfNames, numberOfLastNames, "/", 3);
    }
}
