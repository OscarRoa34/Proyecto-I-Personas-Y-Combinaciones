package co.edu.uptc.services;

import java.util.ArrayList;
import java.util.List;
import co.edu.uptc.models.NameAndGender;

public class CombinationService {

    public List<String> combineTwoNamesWithTwoLastNames(List<NameAndGender> namesAndGenders, List<String> apellidos) {
        List<String> combinedPersons = new ArrayList<>();
        for (NameAndGender person1 : namesAndGenders) {
            for (NameAndGender person2 : namesAndGenders) {
                if (!person1.getName().equals(person2.getName()) && person1.getGender() == person2.getGender()) {
                    for (String apellido1 : apellidos) {
                        for (String apellido2 : apellidos) {
                            if (!apellido1.equals(apellido2)) {
                                combinedPersons.add(
                                        person1.getName() + " " + person2.getName() + " " + apellido1 + " "
                                                + apellido2);
                            }
                        }
                    }
                }
            }
        }

        return combinedPersons;
    }

    public List<String> combineNameWithTwoLastNames(List<NameAndGender> namesAndGenders, List<String> apellidos) {
        List<String> combinedPersons = new ArrayList<>();
        for (NameAndGender person1 : namesAndGenders) {
            for (NameAndGender person2 : namesAndGenders) {
                if (!person1.getName().equals(person2.getName()) && person1.getGender() == person2.getGender()) {
                    for (String apellido1 : apellidos) {
                        for (String apellido2 : apellidos) {
                            if (!apellido1.equals(apellido2)) {
                                combinedPersons.add(
                                        person1.getName() + " " + apellido1 + " " + apellido2);
                            }
                        }
                    }
                }
            }
        }

        return combinedPersons;
    }

}
