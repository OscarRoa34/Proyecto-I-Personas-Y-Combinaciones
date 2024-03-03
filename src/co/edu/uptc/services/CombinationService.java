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
        this.namesAndGenders = (new FileController().readNamesFile(
                "C:\\Users\\oscar\\OneDrive\\Escritorio\\Universidad\\Programacion II\\Proyecto I\\Proyecto I\\data\\Nombres.txt"));
        this.lastNames = (new FileController().readLastNamesFile(
                "C:\\Users\\oscar\\OneDrive\\Escritorio\\Universidad\\Programacion II\\Proyecto I\\Proyecto I\\data\\Apellidos.txt"));
        this.combinedPersons = new ArrayList<>();
        this.currentCombination = new ArrayList<>();
    }

    public List<String> combineNamesWithLastNames(int numberOfNames, int numberOfLastNames) {
        generateCombinations(numberOfNames, numberOfLastNames);
        return combinedPersons;
    }

    private void generateCombinations(int numberOfNames, int numberOfLastNames) {
        if (currentCombination.size() == numberOfNames) {
            generateCombinationsForlastNames(numberOfLastNames, 0);
            return;
        }

        for (NameAndGender person : namesAndGenders) {
            if (!currentCombination.contains(person.getName()) &&
                    (currentCombination.isEmpty() || gendersAreCompatible(person))) {
                currentCombination.add(person.getName());
                generateCombinations(numberOfNames, numberOfLastNames);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }

    private void generateCombinationsForlastNames(int numberOfLastNames, int currentIndex) {
        if (currentIndex == numberOfLastNames) {
            StringBuilder combination = new StringBuilder();
            for (String name : currentCombination) {
                combination.append(name).append(" ");
            }
            combinedPersons.add(combination.toString().trim());
            return;
        }

        for (String apellido : lastNames) {
            currentCombination.add(apellido);
            generateCombinationsForlastNames(numberOfLastNames, currentIndex + 1);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    private boolean gendersAreCompatible(NameAndGender person) {
        for (String name : currentCombination) {
            NameAndGender existingPerson = findPersonByName(name);
            if (existingPerson != null && existingPerson.getGender() != person.getGender()) {
                return false;
            }
        }
        return true;
    }

    private NameAndGender findPersonByName(String name) {
        for (NameAndGender person : namesAndGenders) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }
}
