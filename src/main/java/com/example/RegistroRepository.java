package com.example;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Mantiene los registros en memoria usando un HashMap para accesos rápidos.
 */
public class RegistroRepository {
  private final Map<String, Registro> registros = new HashMap<>();

  public boolean agregar(Registro registro) {
    if (registro == null) {
      return false;
    }
    if (registros.containsKey(registro.getId())) {
      return false;
    }
    registros.put(registro.getId(), registro);
    return true;
  }

  public Registro obtener(String id) {
    return registros.get(id);
  }

  public boolean eliminar(String id) {
    return registros.remove(id) != null;
  }

  public Collection<Registro> listar() {
    return Collections.unmodifiableCollection(registros.values());
  }

  public boolean estaVacio() {
    return registros.isEmpty();
  }

  public Map<String, Registro> comoMapaMutable() {
    return new HashMap<>(registros);
  }

  public void reemplazarTodo(Map<String, Registro> nuevoContenido) {
    registros.clear();
    if (nuevoContenido != null) {
      registros.putAll(nuevoContenido);
    }
  }
}
