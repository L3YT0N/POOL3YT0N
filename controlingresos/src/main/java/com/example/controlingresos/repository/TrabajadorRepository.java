package com.example.controlingresos.repository;

import com.example.controlingresos.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {

    // Buscar trabajador por DNI
    Optional<Trabajador> findByDni(String dni);

    // Buscar trabajadores activos
    java.util.List<Trabajador> findByActivoTrue();
}
