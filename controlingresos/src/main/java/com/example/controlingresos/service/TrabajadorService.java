package com.example.controlingresos.service;

import com.example.controlingresos.model.Trabajador;
import com.example.controlingresos.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository repo;

    public List<Trabajador> listar() {
        return repo.findAll();
    }

    public List<Trabajador> listarActivos() {
        return repo.findByActivoTrue();
    }

    public Trabajador guardar(Trabajador t) {
        return repo.save(t);
    }

    public Optional<Trabajador> buscarPorDni(String dni) {
        return repo.findByDni(dni);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
