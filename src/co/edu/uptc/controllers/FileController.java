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
        // Lista que almacena los nombres y generos leidos del archivo
        List<NameAndGender> namesAndGenders = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))) {

            // Lee cada linea del archivo
            String line;
            while ((line = br.readLine()) != null) {
                // Verifica si la línea contiene un espacio (nombre y genero)
                if (line.contains(" ")) {
                    // Divide la línea en partes usando el espacio como separador
                    String[] parts = line.split(" ");
                    // Extrae el nombre y el genero
                    String nombre = parts[0];
                    String genero = parts[1].toUpperCase();

                    // Crea un objeto NameAndGender y lo agrega a la lista
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

        // Devuelve la lista de nombres y generos leidos del archivo
        return namesAndGenders;
    }

    public List<String> readLastNamesFile(String path) {
        // Lista que almacena los apellidos leidos del archivo
        List<String> listaApellidos = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))) {

            // Lee cada linea del archivo y agrega el apellido a la lista
            String line;
            while ((line = br.readLine()) != null) {
                listaApellidos.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Devuelve la lista de apellidos leidos del archivo
        return listaApellidos;
    }
}
