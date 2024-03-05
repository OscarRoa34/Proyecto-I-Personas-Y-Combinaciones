package co.edu.uptc.controllers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import co.edu.uptc.models.*;

public class FileController {

    public List<NameAndGender> readNamesAndGendersFile(String path) {
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
