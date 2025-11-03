package com.example.controlingresos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "registros_acceso")
@Getter
@Setter
public class RegistroAcceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trabajador_id", nullable = false)
    private Trabajador trabajador;

    @Column(nullable = false)
    private LocalDateTime fechaHoraIngreso;

    @Column
    private LocalDateTime fechaHoraSalida;

    @Column
    private String observacion;

    public RegistroAcceso() {}

    public RegistroAcceso(Trabajador trabajador, LocalDateTime fechaHoraIngreso, String observacion) {
        this.trabajador = trabajador;
        this.fechaHoraIngreso = fechaHoraIngreso;
        this.observacion = observacion;
    }
}
