package controller;

import dao.AlumnoDAO;
import dao.ProfesorDAO;
import model.Alumno;
import model.Profesor;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final AlumnoDAO alumnoDAO;
    private final ProfesorDAO profesorDAO;
    private final Scanner scanner;

    public Menu() {
        this.alumnoDAO = new AlumnoDAO();
        this.profesorDAO = new ProfesorDAO();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Insertar un profesor");
            System.out.println("2. Insertar un alumno");
            System.out.println("3. Mostrar todos los datos");
            System.out.println("4. Mostrar profesores");
            System.out.println("5. Mostrar alumnos");
            System.out.println("6. Buscar alumno por email");
            System.out.println("7. Buscar profesor por rango de edad");
            System.out.println("8. Actualizar clasificación de profesor");
            System.out.println("9. Dar de baja alumnos con calificación >= 5");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> insertarProfesorPOJO();
                case 2 -> insertarAlumnoPOJO();
                case 3 -> mostrarTodos();
                case 4 -> mostrarProfesoresPOJO();
                case 5 -> mostrarAlumnosPOJO();
                case 6 -> buscarAlumnoPorEmailPOJO();
                case 7 -> buscarProfesorPorRangoEdadPOJO();
                case 8 -> actualizarCalificacionProfesorPOJO();
                case 9 -> darDeBajaAlumnos();
                case 10 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (opcion != 10);
    }

    private void insertarProfesorPOJO() {
        try {
            System.out.print("ID del profesor: ");
            String id = scanner.nextLine();
            System.out.print("Clasificación del profesor: ");
            double rating = Double.parseDouble(scanner.nextLine());
            System.out.print("Nombre del profesor: ");
            String name = scanner.nextLine();
            System.out.print("Edad del profesor: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Género del profesor: ");
            String gender = scanner.nextLine();
            System.out.print("Email del profesor: ");
            String email = scanner.nextLine();
            System.out.print("Teléfono del profesor: ");
            String phone = scanner.nextLine();
            System.out.print("Título del profesor: ");
            String title = scanner.nextLine();
            System.out.print("Número de asignaturas que imparte: ");
            int numAsignaturas = scanner.nextInt();
            scanner.nextLine();

            String[] subjects = new String[numAsignaturas];
            for (int i = 0; i < numAsignaturas; i++) {
                System.out.print("Nombre de la asignatura " + (i + 1) + ": ");
                subjects[i] = scanner.nextLine();
            }
            Profesor profesor = new Profesor(id,rating,age,name,gender,email,phone, List.of(subjects),title);
            profesorDAO.insertarProfesorPOJO(profesor);
            System.out.println("Profesor insertado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al insertar profesor: " + e.getMessage());
        }
    }

    private void insertarAlumnoPOJO() {
        try {
            System.out.print("ID del alumno: ");
            String id = scanner.nextLine();
            System.out.print("Clasificación del alumno: ");
            double rating = Double.parseDouble(scanner.nextLine());
            System.out.print("Nombre del alumno: ");
            String name = scanner.nextLine();
            System.out.print("Edad del alumno: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Género del alumno: ");
            String gender = scanner.nextLine();
            System.out.print("Email del alumno: ");
            String email = scanner.nextLine();
            System.out.print("Teléfono del alumno: ");
            String phone = scanner.nextLine();
            System.out.print("Calificación del alumno: ");
            int calificaation = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ciclo del alumno: ");
            String higher_grade = scanner.nextLine();

            Alumno alumno = new Alumno(id,rating,age,name,gender,email,phone,calificaation,higher_grade);
            alumnoDAO.insertarAlumnoPOJO(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al insertar alumno: " + e.getMessage());
        }
    }

    private void mostrarTodos() {
        try {
            System.out.println("Listado de profesores: ");
            profesorDAO.mostrarProfesoresPOJO();
            System.out.println("Listado de alumnos: ");
            alumnoDAO.mostrarAlumnosPOJO();
        } catch (Exception e) {
            System.err.println("Error al mostrar datos: " + e.getMessage());
        }
    }

    private void mostrarProfesoresPOJO() {
        try {
            System.out.println("Listado de profesores: ");
            profesorDAO.mostrarProfesoresPOJO();
        } catch (Exception e) {
            System.err.println("Error al mostrar profesores: " + e.getMessage());
        }
    }

    private void mostrarAlumnosPOJO() {
        try {
            System.out.println("Listado de alumnos: ");
            alumnoDAO.mostrarAlumnosPOJO();
        } catch (Exception e) {
            System.err.println("Error al mostrar alumnos: " + e.getMessage());
        }
    }

    private void buscarAlumnoPorEmailPOJO() {
        try {
            System.out.print("Ingrese el email del alumno: ");
            String email = scanner.nextLine();
            System.out.print("Los datos del alumno asociados a ese E-Mail, son los siguientes: \n");
            alumnoDAO.buscarAlumnoPorEmailPOJO(email);
        } catch (Exception e) {
            System.err.println("Error al buscar alumno: " + e.getMessage());
        }
    }

    private void buscarProfesorPorRangoEdadPOJO() {
        try {
            System.out.print("Ingrese la edad mínima: ");
            int edadMin = scanner.nextInt();
            System.out.print("Ingrese la edad máxima: ");
            int edadMax = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Los profesores entre esos rangos de edad, son los siguientes: \n");
            profesorDAO.buscarProfesorPorRangoEdadPOJO(edadMin, edadMax);
        } catch (Exception e) {
            System.err.println("Error al buscar profesor: " + e.getMessage());
        }
    }

    private void actualizarCalificacionProfesorPOJO() {
        try {
            System.out.print("Ingrese el email del profesor: ");
            String email = scanner.nextLine();
            System.out.print("Ingrese la nueva clasificación: ");
            double nuevaClasificacion = scanner.nextDouble();
            scanner.nextLine();
            profesorDAO.actualizarCalificacionProfesorPOJO(email, nuevaClasificacion);
            System.out.println("Clasificación actualizada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al actualizar clasificación: " + e.getMessage());
        }
    }

    private void darDeBajaAlumnos() {
        try {
            alumnoDAO.eliminarAlumnosPorCalificacionPojo((int) 5.0);
            System.out.println("Alumnos con calificación >= 5 dados de baja correctamente.");
        } catch (Exception e) {
            System.err.println("Error al dar de baja alumnos: " + e.getMessage());
        }
    }
}
