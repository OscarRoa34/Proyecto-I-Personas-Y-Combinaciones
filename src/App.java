import java.util.List;

import co.edu.uptc.controllers.FileController;
import co.edu.uptc.models.NameAndGender;
import co.edu.uptc.services.CombinationService;

public class App {
    public static void main(String[] args) throws Exception {
        FileController fc = new FileController();
        List<String> apellidos = fc.readLastNamesFile("data\\Apellidos.txt");

        // for (String apellido : apellidos) {
        // System.out.println(apellido);
        // }

        List<NameAndGender> nombres = fc.readNamesFile("data\\Nombres.txt");

        // for (String nombre : nombres) {
        // System.out.println(nombre);
        // }

        CombinationService cs = new CombinationService();
        // List<String> personas = cs.combineTwoNamesWithTwoLastNames(nombres,
        // apellidos);
        List<String> personas2 = cs.combineNameWithTwoLastNames(nombres, apellidos);

        for (String persona : personas2) {
            System.out.println(persona);
        }
    }
}
