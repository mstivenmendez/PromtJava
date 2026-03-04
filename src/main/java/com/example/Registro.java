package com.example;

import org.json.JSONObject;

/**
 * Representa un registro sencillo con id, nombre y un atributo extra
 * configurable.
 */
public class Registro {
  private String id;
  private String nombre;
  private String atributoExtra;

  public Registro(String id, String nombre, String atributoExtra) {
    if (id == null || id.isBlank()) {
      throw new IllegalArgumentException("El id no puede ser nulo o vacío");
    }
    if (nombre == null || nombre.isBlank()) {
      throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
    }
    this.id = id.trim();
    this.nombre = nombre.trim();
    this.atributoExtra = atributoExtra == null ? "" : atributoExtra.trim();
  }

  public String getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    if (nombre == null || nombre.isBlank()) {
      throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
    }
    this.nombre = nombre.trim();
  }

  public String getAtributoExtra() {
    return atributoExtra;
  }

  public void setAtributoExtra(String atributoExtra) {
    this.atributoExtra = atributoExtra == null ? "" : atributoExtra.trim();
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("nombre", nombre);
    json.put("atributoExtra", atributoExtra);
    return json;
  }

  public static Registro fromJson(JSONObject json) {
    String id = json.optString("id", "");
    String nombre = json.optString("nombre", "");
    String atributoExtra = json.optString("atributoExtra", "");
    return new Registro(id, nombre, atributoExtra);
  }

  @Override
  public String toString() {
    return "Registro{" +
        "id='" + id + '\'' +
        ", nombre='" + nombre + '\'' +
        ", atributoExtra='" + atributoExtra + '\'' +
        '}';
  }
}
