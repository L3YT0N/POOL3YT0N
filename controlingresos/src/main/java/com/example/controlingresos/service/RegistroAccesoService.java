package com.example.controlingresos.service;

import com.example.controlingresos.model.RegistroAcceso;
import com.example.controlingresos.model.Trabajador;
import com.example.controlingresos.repository.RegistroAccesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroAccesoService {

    @Autowired
    private RegistroAccesoRepository repo;

    public RegistroAcceso guardar(RegistroAcceso r) {
        return repo.save(r);
    }

    public List<RegistroAcceso> listar() {
        return repo.findAll();
    }

    public List<RegistroAcceso> listarPorTrabajador(Trabajador t) {
        return repo.findByTrabajador(t);
    }

    public List<RegistroAcceso> listarPorRango(LocalDateTime desde, LocalDateTime hasta) {
        return repo.findByFechaHoraIngresoBetween(desde, hasta);
    }
}
