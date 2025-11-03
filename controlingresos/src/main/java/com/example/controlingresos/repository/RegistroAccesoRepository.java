package com.example.controlingresos.repository;

import com.example.controlingresos.model.RegistroAcceso;
import com.example.controlingresos.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroAccesoRepository extends JpaRepository<RegistroAcceso, Long> {

    // Buscar los registros de un trabajador específico
    List<RegistroAcceso> findByTrabajador(Trabajador trabajador);

    // Buscar registros por rango de fecha/hora
    List<RegistroAcceso> findByFechaHoraIngresoBetween(LocalDateTime desde, LocalDateTime hasta);
}
