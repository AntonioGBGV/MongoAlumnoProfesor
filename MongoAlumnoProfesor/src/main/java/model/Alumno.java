package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alumno implements Serializable {

    private String id;
    private double rating;
    private int age;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private int calificaation;
    private String higher_grade;

    public void mostrarDatos() {
        System.out.print("ID: " + id +", ");
        System.out.print("Nombre: " + name +", ");
        System.out.print("Edad: " + age +", ");
        System.out.print("Calificación: " + calificaation +", ");
        System.out.print("Teléfono: " + phone +", ");
        System.out.print("Clasificación: " + rating +", ");
        System.out.print("Género: " + gender +", ");
        System.out.print("E-Mail: " + email +", ");
        System.out.print("Grado Superior: " + higher_grade +", \n");
    }
}
