package com.example;

import java.util.Map;
import java.util.Scanner;

/**
 * Punto de entrada para la aplicación de consola que gestiona registros.
 */
public class RegistroApp {
  private static final String RUTA_ARCHIVO = "registros.json";

  private final RegistroRepository repository = new RegistroRepository();
  private final JsonStorage storage = new JsonStorage(RUTA_ARCHIVO);
  private final Scanner scanner = new Scanner(System.in);
  private boolean hayCambiosPendientes;

  public static void main(String[] args) {
    new RegistroApp().run();
  }

  private void run() {
    System.out.println("Cargando datos...");
    Map<String, Registro> datos = storage.cargar();
    repository.reemplazarTodo(datos);
    System.out.println("Registros cargados: " + datos.size());
    boolean activo = true;
    while (activo) {
      mostrarMenu();
      String opcion = scanner.nextLine().trim();
      switch (opcion) {
        case "1" -> agregarRegistro();
        case "2" -> listarRegistros();
        case "3" -> buscarRegistro();
        case "4" -> eliminarRegistro();
        case "5" -> guardarDatos();
        case "6" -> activo = confirmarSalida();
        default -> System.out.println("Opción no válida, intenta de nuevo.");
      }
    }
    scanner.close();
    System.out.println("Aplicación finalizada.");
  }

  private void mostrarMenu() {
    System.out.println();
    System.out.println("===== MENÚ DE REGISTROS =====");
    System.out.println("1. Agregar registro");
    System.out.println("2. Listar registros");
    System.out.println("3. Buscar registro por id");
    System.out.println("4. Eliminar registro");
    System.out.println("5. Guardar datos en JSON");
    System.out.println("6. Salir");
    System.out.print("Selecciona una opción: ");
  }

  private void agregarRegistro() {
    System.out.print("Ingresa id: ");
    String id = scanner.nextLine().trim();
    if (id.isEmpty()) {
      System.out.println("El id no puede estar vacío.");
      return;
    }
    if (repository.obtener(id) != null) {
      System.out.println("Ya existe un registro con ese id.");
      return;
    }
    System.out.print("Ingresa nombre: ");
    String nombre = scanner.nextLine().trim();
    if (nombre.isEmpty()) {
      System.out.println("El nombre no puede estar vacío.");
      return;
    }
    System.out.print("Ingresa atributo adicional: ");
    String extra = scanner.nextLine().trim();
    Registro registro = new Registro(id, nombre, extra);
    repository.agregar(registro);
    hayCambiosPendientes = true;
    System.out.println("Registro agregado correctamente.");
  }

  private void listarRegistros() {
    if (repository.estaVacio()) {
      System.out.println("No hay registros.");
      return;
    }
    System.out.println("Listado de registros:");
    repository.listar().forEach(registro -> System.out.println("- " + registro));
  }

  private void buscarRegistro() {
    System.out.print("Ingresa el id a buscar: ");
    String id = scanner.nextLine().trim();
    Registro registro = repository.obtener(id);
    if (registro == null) {
      System.out.println("No se encontró un registro con id " + id);
    } else {
      System.out.println(registro);
    }
  }

  private void eliminarRegistro() {
    System.out.print("Ingresa el id a eliminar: ");
    String id = scanner.nextLine().trim();
    boolean eliminado = repository.eliminar(id);
    if (eliminado) {
      hayCambiosPendientes = true;
      System.out.println("Registro eliminado.");
    } else {
      System.out.println("No se encontró un registro con ese id.");
    }
  }

  private void guardarDatos() {
    storage.guardar(repository.comoMapaMutable());
    hayCambiosPendientes = false;
    System.out.println("Datos guardados en " + RUTA_ARCHIVO);
  }

  private boolean confirmarSalida() {
    if (hayCambiosPendientes) {
      System.out.print("Hay cambios sin guardar. ¿Deseas guardar antes de salir? (s/n): ");
      String respuesta = scanner.nextLine().trim().toLowerCase();
      if ("s".equals(respuesta)) {
        guardarDatos();
      }
    }
    return false;
  }
}
