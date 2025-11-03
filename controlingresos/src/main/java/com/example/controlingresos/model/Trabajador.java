package com.example.controlingresos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trabajadores")
@Getter
@Setter
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private String nombre;

    @Column
    private String area;

    @Column
    private boolean activo = true;

    public Trabajador() {}

    public Trabajador(String dni, String nombre, String area) {
        this.dni = dni;
        this.nombre = nombre;
        this.area = area;
        this.activo = true;
    }

    @Override
    public String toString() {
        return nombre + " (" + dni + ")";
    }
}
