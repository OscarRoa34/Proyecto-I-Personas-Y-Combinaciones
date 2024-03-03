package co.edu.uptc.services;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.controllers.FileController;
import co.edu.uptc.models.NameAndGender;

public class CombinationService {

    private List<NameAndGender> namesAndGenders;
    private List<String> lastNames;
    private List<String> combinedPersons;
    private List<String> currentCombination;

    public CombinationService() {
        // Inicializacion de las listas y lectura de archivos con nombres y apellidos
        this.namesAndGenders = (new FileController().readNamesFile(
                "C:\\Users\\oscar\\OneDrive\\Escritorio\\Universidad\\Programacion II\\Proyecto I\\Proyecto I\\data\\Nombres.txt"));
        this.lastNames = (new FileController().readLastNamesFile(
                "C:\\Users\\oscar\\OneDrive\\Escritorio\\Universidad\\Programacion II\\Proyecto I\\Proyecto I\\data\\Apellidos.txt"));
        this.combinedPersons = new ArrayList<>();
        this.currentCombination = new ArrayList<>();
    }

    public List<String> combineNamesWithLastNames(int numberOfNames, int numberOfLastNames) {
        generateCombinations(numberOfNames, numberOfLastNames); // Llamada al metodo para generar combinaciones
        return combinedPersons; // Devuelve la lista de personas combinadas
    }

    private void generateCombinations(int numberOfNames, int numberOfLastNames) {
        if (currentCombination.size() == numberOfNames) { // Verificar si ya hay suficientes nombres
            generateCombinationsForlastNames(numberOfLastNames, 0); // Llamada al metodo para generar combinaciones de
                                                                    // apellidos
            return;
        }

        for (NameAndGender person : namesAndGenders) { // Bucle para recorrer la lista de nombres y generos
            if (!currentCombination.contains(person.getName()) &&
                    (currentCombination.isEmpty() || gendersAreCompatible(person))) {
                // Verificar si el nombre no esta en la combinacion actual y si los generos son
                // compatibles
                currentCombination.add(person.getName()); // Agrega el nombre a la combinacion actual
                generateCombinations(numberOfNames, numberOfLastNames); // Llamada recursiva para continuar generando
                                                                        // combinaciones
                currentCombination.remove(currentCombination.size() - 1); // Elimina el ultimo nombre agregado para
                                                                          // probar otras combinaciones
            }
        }
    }

    private void generateCombinationsForlastNames(int numberOfLastNames, int currentIndex) {
        if (currentIndex == numberOfLastNames) { // Verifica si se han seleccionado suficientes apellidos
            StringBuilder combination = new StringBuilder(); // StringBuilder para almacenar la combinación
            for (String name : currentCombination) {
                combination.append(name).append(" "); // Agrega cada nombre seguido de un espacio
            }
            combinedPersons.add(combination.toString().trim()); // Agrega la combinacion a la lista de personas
                                                                // combinadas
            return;
        }

        for (String apellido : lastNames) { // Bucle para recorrer la lista de apellidos
            currentCombination.add(apellido); // Agrega el apellido a la combinación actual
            generateCombinationsForlastNames(numberOfLastNames, currentIndex + 1); // Llamada recursiva para continuar
                                                                                   // generando combinaciones de
                                                                                   // apellidos
            currentCombination.remove(currentCombination.size() - 1); // Elimina el ultimo apellido agregado para probar
                                                                      // otras combinaciones
        }
    }

    private boolean gendersAreCompatible(NameAndGender person) { // Metodo privado para verificar la compatibilidad de
                                                                 // generos
        for (String name : currentCombination) { // Bucle para recorrer la combinacion actual
            NameAndGender existingPerson = findPersonByName(name); // Busca la persona en la lista de nombres y generos
            if (existingPerson != null && existingPerson.getGender() != person.getGender()) {
                // Verifica si existe la persona y si los generos son diferentes
                return false; // Retorna falso si los generos no son compatibles
            }
        }
        return true; // Retorna verdadero si los generos son compatibles
    }

    private NameAndGender findPersonByName(String name) {
        for (NameAndGender person : namesAndGenders) { // Bucle para recorrer la lista de nombres y generos
            if (person.getName().equals(name)) { // Compara el nombre de la persona con el nombre proporcionado
                return person; // Retorna la persona si se encuentra
            }
        }
        return null; // Retorna nulo si no se encuentra la persona
    }
}
