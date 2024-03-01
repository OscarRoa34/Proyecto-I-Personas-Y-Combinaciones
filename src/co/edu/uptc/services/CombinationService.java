package co.edu.uptc.services;

import java.util.ArrayList;
import java.util.List;
import co.edu.uptc.models.NameAndGender;

public class CombinationService {

    public List<String> combineNamesWithLastNames(List<NameAndGender> namesAndGenders, List<String> apellidos,
            int numNombres, int numApellidos) {
        // Lista que almacena todas las combinaciones de personas ficticias
        List<String> combinedPersons = new ArrayList<>();

        // Inicia el proceso de generación de combinaciones
        generateCombinations(namesAndGenders, apellidos, numNombres, numApellidos, 0, new ArrayList<>(),
                combinedPersons);

        // Devuelve la lista de combinaciones generadas
        return combinedPersons;
    }

    private void generateCombinations(List<NameAndGender> namesAndGenders, List<String> apellidos, int numNombres,
            int numApellidos, int currentIndex, List<String> currentCombination, List<String> combinedPersons) {
        // Verifica si se alcanzo el numero de nombres
        if (currentIndex == numNombres) {
            // Llama a generateCombinationsForApellidos para generar combinaciones de
            // apellidos
            generateCombinationsForApellidos(apellidos, numApellidos, 0, currentCombination, combinedPersons);
            return;
        }

        // Itera sobre cada persona en la lista de nombres y generos
        for (NameAndGender person : namesAndGenders) {
            // Verifica el genero antes de agregar el nombre a la combinación
            if (currentCombination.isEmpty() || gendersAreCompatible(person, currentCombination, namesAndGenders)) {
                // Agrega el nombre a la combinación actual
                currentCombination.add(person.getName());

                // Llama recursivamente para generar combinaciones para el siguiente nombre
                generateCombinations(namesAndGenders, apellidos, numNombres, numApellidos, currentIndex + 1,
                        currentCombination, combinedPersons);

                // Eliminar el ultimo nombre agregado para probar con otro
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }

    private void generateCombinationsForApellidos(List<String> apellidos, int numApellidos, int currentIndex,
            List<String> currentCombination, List<String> combinedPersons) {
        // Verifica si se alcanzo el numero de apellidos
        if (currentIndex == numApellidos) {
            // Agrega la combinación de nombres y apellidos a una cadena
            StringBuilder combination = new StringBuilder();
            for (String name : currentCombination) {
                combination.append(name).append(" ");
            }
            // Agrega la combinacion a la lista de personas combinadas
            combinedPersons.add(combination.toString().trim());
            return;
        }

        // Itera sobre cada apellido en la lista de apellidos
        for (String apellido : apellidos) {
            // Agrega el apellido a la combinación actual
            currentCombination.add(apellido);

            // LLama recursivamente para generar combinaciones para el siguiente apellido
            generateCombinationsForApellidos(apellidos, numApellidos, currentIndex + 1, currentCombination,
                    combinedPersons);

            // Eliminar el ultimo apellido agregado para probar con otro
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    private boolean gendersAreCompatible(NameAndGender person, List<String> currentCombination,
            List<NameAndGender> namesAndGenders) {
        // Itera sobre cada nombre en la combinación actual
        for (String name : currentCombination) {
            // Encontrar la persona correspondiente al nombre en la lista completa de
            // nombres y generos
            NameAndGender existingPerson = findPersonByName(name, namesAndGenders);
            // Verifica si las personas tienen generos diferentes
            if (existingPerson != null && existingPerson.getGender() != person.getGender()) {
                return false; // Generos incompatibles
            }
        }
        return true; // Generos compatibles
    }

    private NameAndGender findPersonByName(String name, List<NameAndGender> namesAndGenders) {
        // Itera sobre cada persona en la lista de nombres y generos
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
