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
        // Lista que almacenará nombres y géneros leídos del archivo
        List<NameAndGender> namesAndGenders = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))) {

            // Leer cada línea del archivo
            String line;
            while ((line = br.readLine()) != null) {
                // Verificar si la línea contiene un espacio (nombre y género)
                if (line.contains(" ")) {
                    // Dividir la línea en partes usando el espacio como separador
                    String[] parts = line.split(" ");
                    // Extraer el nombre y el género
                    String nombre = parts[0];
                    String genero = parts[1].toUpperCase();

                    // Crear un objeto NameAndGender y agregarlo a la lista
                    if ("MASCULINO".equals(genero)) {
                        namesAndGenders.add(new NameAndGender(nombre, Gender.MALE));
                    } else if ("FEMENINO".equals(genero)) {
                        namesAndGenders.add(new NameAndGender(nombre, Gender.FEMALE));
                    }
                }
            }

        } catch (FileNotFoundException e) {
            // Manejar la excepción de archivo no encontrado
            e.printStackTrace();
        } catch (IOException e) {
            // Manejar la excepción de entrada/salida
            e.printStackTrace();
        }

        // Devolver la lista de nombres y géneros leídos del archivo
        return namesAndGenders;
    }

    public List<String> readLastNamesFile(String path) {
        // Lista que almacenará apellidos leídos del archivo
        List<String> listaApellidos = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))) {

            // Leer cada línea del archivo y agregar el apellido a la lista
            String line;
            while ((line = br.readLine()) != null) {
                listaApellidos.add(line);
            }

        } catch (FileNotFoundException e) {
            // Manejar la excepción de archivo no encontrado
            e.printStackTrace();
        } catch (IOException e) {
            // Manejar la excepción de entrada/salida
            e.printStackTrace();
        }

        // Devolver la lista de apellidos leídos del archivo
        return listaApellidos;
    }
}
