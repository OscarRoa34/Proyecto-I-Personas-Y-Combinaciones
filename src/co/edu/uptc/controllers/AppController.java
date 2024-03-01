package co.edu.uptc.controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import co.edu.uptc.models.NameAndGender;
import co.edu.uptc.services.CombinationService;

public class AppController {

    // TODO Poner el genero en frente de cada nombre generado
    public void generatePersons(int numNombres, int numApellidos, String separator) {
        // Crea una instancia de CombinationService para manejar combinaciones
        CombinationService combinationService = new CombinationService();

        // Lee los nombres y generos desde el archivo "Nombres.txt"
        List<NameAndGender> namesAndGenders = (new FileController().readNamesFile("data\\Nombres.txt"));

        // Lee los apellidos desde el archivo "Apellidos.txt"
        List<String> apellidos = (new FileController().readLastNamesFile("data\\Apellidos.txt"));

        // Genera todas las combinaciones posibles de nombres y apellidos
        List<String> allCombinations = combinationService.combineNamesWithLastNames(namesAndGenders, apellidos,
                numNombres, numApellidos);

        // Ruta del archivo de salida para las combinaciones
        String outputPath = "output\\Combinaciones.txt";

        // Escribe las combinaciones en el archivo especificado
        writeCombinationsToFile(allCombinations, outputPath, separator);

        // Imprime un mensaje indicando dónde se guardaron las combinaciones
        System.out.println("Combinaciones guardadas en: " + outputPath);
    }

    private void writeCombinationsToFile(List<String> combinations, String outputPath, String separator) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            // Itera sobre cada combinación en la lista
            for (String combination : combinations) {
                // Divide la combinación en partes usando el espacio como separador
                String[] parts = combination.split(" ");

                // Combina las partes con el separador proporcionado (el que se pasa como
                // parametro en el main)
                String line = String.join(separator, parts);

                // Escribe la linea en el archivo
                writer.write(line);

                // Agrega una nueva linea al archivo
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
