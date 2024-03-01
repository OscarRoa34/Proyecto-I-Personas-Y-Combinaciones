package co.edu.uptc.services;

import java.util.ArrayList;
import java.util.List;
import co.edu.uptc.models.NameAndGender;

public class CombinationService {

    public List<String> combineNamesWithLastNames(List<NameAndGender> namesAndGenders, List<String> apellidos,
            int numNombres, int numApellidos) {
        // Lista que almacenará todas las combinaciones de personas ficticias
        List<String> combinedPersons = new ArrayList<>();

        // Iniciar el proceso de generación de combinaciones
        generateCombinations(namesAndGenders, apellidos, numNombres, numApellidos, 0, new ArrayList<>(),
                combinedPersons);

        // Devolver la lista de combinaciones generadas
        return combinedPersons;
    }

    private void generateCombinations(List<NameAndGender> namesAndGenders, List<String> apellidos, int numNombres,
            int numApellidos, int currentIndex, List<String> currentCombination, List<String> combinedPersons) {
        // Verificar si se ha alcanzado el número deseado de nombres
        if (currentIndex == numNombres) {
            // Llamar a generateCombinationsForApellidos para generar combinaciones de
            // apellidos
            generateCombinationsForApellidos(apellidos, numApellidos, 0, currentCombination, combinedPersons);
            return;
        }

        // Iterar sobre cada persona en la lista de nombres y géneros
        for (NameAndGender person : namesAndGenders) {
            // Verificar género antes de agregar el nombre a la combinación
            if (currentCombination.isEmpty() || gendersAreCompatible(person, currentCombination, namesAndGenders)) {
                // Agregar el nombre a la combinación actual
                currentCombination.add(person.getName());

                // Llamada recursiva para generar combinaciones para el siguiente nombre
                generateCombinations(namesAndGenders, apellidos, numNombres, numApellidos, currentIndex + 1,
                        currentCombination, combinedPersons);

                // Retroceder: eliminar el último nombre agregado para probar con otro
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }

    private void generateCombinationsForApellidos(List<String> apellidos, int numApellidos, int currentIndex,
            List<String> currentCombination, List<String> combinedPersons) {
        // Verificar si se ha alcanzado el número deseado de apellidos
        if (currentIndex == numApellidos) {
            // Construir la combinación de nombres y apellidos en una cadena
            StringBuilder combination = new StringBuilder();
            for (String name : currentCombination) {
                combination.append(name).append(" ");
            }
            // Agregar la combinación a la lista de personas combinadas
            combinedPersons.add(combination.toString().trim());
            return;
        }

        // Iterar sobre cada apellido en la lista de apellidos
        for (String apellido : apellidos) {
            // Agregar el apellido a la combinación actual
            currentCombination.add(apellido);

            // Llamada recursiva para generar combinaciones para el siguiente apellido
            generateCombinationsForApellidos(apellidos, numApellidos, currentIndex + 1, currentCombination,
                    combinedPersons);

            // Retroceder: eliminar el último apellido agregado para probar con otro
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    private boolean gendersAreCompatible(NameAndGender person, List<String> currentCombination,
            List<NameAndGender> namesAndGenders) {
        // Iterar sobre cada nombre en la combinación actual
        for (String name : currentCombination) {
            // Encontrar la persona correspondiente al nombre en la lista completa de
            // nombres y géneros
            NameAndGender existingPerson = findPersonByName(name, namesAndGenders);
            // Verificar si las personas tienen géneros diferentes
            if (existingPerson != null && existingPerson.getGender() != person.getGender()) {
                return false; // Géneros incompatibles
            }
        }
        return true; // Géneros compatibles
    }

    private NameAndGender findPersonByName(String name, List<NameAndGender> namesAndGenders) {
        // Itera sobre cada persona en la lista de nombres y géneros
        for (NameAndGender person : namesAndGenders) {
            // Compara el nombre de la persona con el nombre proporcionado
            if (person.getName().equals(name)) {
                // Retorna la persona si se encuentra una coincidencia
                return person;
            }
        }
        // Retorna null si no se encuentra ninguna persona con el nombre dado
        return null;
    }

}
