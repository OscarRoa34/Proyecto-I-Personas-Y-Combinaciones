package co.edu.uptc.controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import co.edu.uptc.services.CombinationService;

public class CombinationController {

    public void generatePersons(int numNombres, int numApellidos, String separator, int modificacionMayusMinus) {
        CombinationService combinationService = new CombinationService();

        List<String> allCombinations = combinationService.combineNamesWithLastNames(numApellidos, numNombres);
        String outputPath = "C:\\Users\\oscar\\OneDrive\\Escritorio\\Universidad\\Programacion II\\Proyecto I\\Proyecto I\\output\\combinaciones.txt";

        writeCombinationsToFile(allCombinations, outputPath, separator, modificacionMayusMinus);

        System.out.println("Combinaciones guardadas en: " + outputPath);
    }

    private void writeCombinationsToFile(List<String> combinations, String outputPath, String separator,
            int modificacionMayusMinus) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (String combination : combinations) {
                // Aplicar las modificaciones según el valor de modificacionMayusMinus
                switch (modificacionMayusMinus) {
                    case 1:
                        combination = combination.toUpperCase();
                        break;
                    case 2:
                        combination = combination.toLowerCase();
                        break;
                    case 3:
                        combination = capitalizeEachWord(combination);
                        break;
                    // Agregar más casos según sea necesario

                    default:
                        // No hacer ninguna modificación
                }

                String[] parts = combination.split(" ");
                String line = String.join(separator, parts);

                writer.write(line);

                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para capitalizar cada palabra en una cadena
    private String capitalizeEachWord(String input) {
        StringBuilder result = new StringBuilder();
        String[] words = input.split(" ");

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)));
                result.append(word.substring(1).toLowerCase());
                result.append(" ");
            }
        }

        return result.toString().trim();
    }
}
