package co.edu.uptc.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import co.edu.uptc.models.Gender;
import co.edu.uptc.models.NameAndGender;

public class FileController {

    public List<NameAndGender> readNamesFile(String path) {
        List<NameAndGender> namesAndGenders = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(" ")) {
                    String[] parts = line.split(" ");
                    String nombre = parts[0];
                    String genero = parts[1].toUpperCase();
                    if ("MASCULINO".equals(genero)) {
                        namesAndGenders.add(new NameAndGender(nombre, Gender.MALE));
                    } else if ("FEMENINO".equals(genero)) {
                        namesAndGenders.add(new NameAndGender(nombre, Gender.FEMALE));
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return namesAndGenders;
    }

    /*
     * public List<Gender> readGendersFile(String path) {
     * List<Gender> listaGeneros = new ArrayList<>();
     * try (FileInputStream fis = new FileInputStream(path);
     * BufferedReader br = new BufferedReader(new InputStreamReader(fis,
     * StandardCharsets.UTF_8))) {
     * 
     * String line;
     * while ((line = br.readLine()) != null) {
     * if (line.contains(" ")) {
     * String generoStr = line.substring(line.indexOf(" ") + 1);
     * 
     * Gender genero;
     * if (generoStr.equalsIgnoreCase("Masculino")) {
     * genero = Gender.MALE;
     * } else if (generoStr.equalsIgnoreCase("Femenino")) {
     * genero = Gender.FEMALE;
     * } else {
     * throw new IllegalArgumentException("Género desconocido: " + generoStr);
     * }
     * 
     * listaGeneros.add(genero);
     * }
     * }
     * 
     * } catch (FileNotFoundException e) {
     * e.printStackTrace();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * return listaGeneros;
     * }
     */
    public List<String> readLastNamesFile(String path) {
        List<String> listaApellidos = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                listaApellidos.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaApellidos;
    }
}
