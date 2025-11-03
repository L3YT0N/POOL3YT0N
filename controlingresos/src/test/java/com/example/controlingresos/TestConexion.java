package com.example.controlingresos;

import com.example.controlingresos.model.Trabajador;
import com.example.controlingresos.repository.TrabajadorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Prueba básica para verificar la conexión con SQLite.
 */
@SpringBootTest
public class TestConexion {

    @Autowired
    private TrabajadorRepository repo;

    @Test
    void crearTrabajador() {
        Trabajador t = new Trabajador("12345678", "Juan Pérez", "Producción");
        repo.save(t);
        System.out.println("✅ Trabajador guardado correctamente en SQLite");
    }
}
