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
        // Crear una instancia de CombinationService para manejar combinaciones
        CombinationService combinationService = new CombinationService();

        // Leer nombres y géneros desde el archivo "Nombres.txt"
        List<NameAndGender> namesAndGenders = (new FileController().readNamesFile("data\\Nombres.txt"));

        // Leer apellidos desde el archivo "Apellidos.txt"
        List<String> apellidos = (new FileController().readLastNamesFile("data\\Apellidos.txt"));

        // Generar todas las combinaciones posibles de nombres y apellidos
        List<String> allCombinations = combinationService.combineNamesWithLastNames(namesAndGenders, apellidos,
                numNombres, numApellidos);

        // Especificar la ruta del archivo de salida para las combinaciones
        String outputPath = "output\\Combinaciones.txt";

        // Escribir las combinaciones en el archivo especificado
        writeCombinationsToFile(allCombinations, outputPath, separator);

        // Imprimir un mensaje indicando dónde se guardaron las combinaciones
        System.out.println("Combinaciones guardadas en: " + outputPath);
    }

    private void writeCombinationsToFile(List<String> combinations, String outputPath, String separator) {
        // Utiliza try-with-resources para asegurar que el BufferedWriter se cierre
        // correctamente
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            // Itera sobre cada combinación en la lista
            for (String combination : combinations) {
                // Divide la combinación en partes usando el espacio como separador
                String[] parts = combination.split(" ");

                // Combina las partes con el separador proporcionado
                String line = String.join(separator, parts);

                // Escribe la línea en el archivo
                writer.write(line);

                // Agrega una nueva línea al archivo
                writer.newLine();
            }
        } catch (IOException e) {
            // Maneja las excepciones de entrada/salida (IOException)
            e.printStackTrace();
        }
    }

}
