package com.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Gestiona la lectura y escritura de registros en un archivo JSON.
 */
public class JsonStorage {
  private final Path filePath;

  public JsonStorage(String fileName) {
    this.filePath = Path.of(fileName);
  }

  public Map<String, Registro> cargar() {
    Map<String, Registro> datos = new HashMap<>();
    if (Files.notExists(filePath)) {
      return datos;
    }
    StringBuilder builder = new StringBuilder();
    try (FileReader reader = new FileReader(filePath.toFile())) {
      char[] buffer = new char[1024];
      int read;
      while ((read = reader.read(buffer)) != -1) {
        builder.append(buffer, 0, read);
      }
    } catch (FileNotFoundException e) {
      return datos;
    } catch (IOException e) {
      System.err.println("Error al leer el archivo JSON: " + e.getMessage());
      return datos;
    }

    if (builder.isEmpty()) {
      return datos;
    }

    try {
      JSONArray array = new JSONArray(builder.toString());
      for (int i = 0; i < array.length(); i++) {
        JSONObject json = array.getJSONObject(i);
        Registro registro = Registro.fromJson(json);
        datos.put(registro.getId(), registro);
      }
    } catch (JSONException e) {
      System.err.println("El archivo JSON está corrupto o tiene formato inválido: " + e.getMessage());
    }
    return datos;
  }

  public void guardar(Map<String, Registro> datos) {
    JSONArray array = new JSONArray();
    datos.values().forEach(registro -> array.put(registro.toJson()));
    try (FileWriter writer = new FileWriter(filePath.toFile())) {
      writer.write(array.toString(2));
    } catch (IOException e) {
      System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
    }
  }
}
