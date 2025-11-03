package com.example.controlingresos.service;

import com.example.controlingresos.model.Usuario;
import com.example.controlingresos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public Optional<Usuario> buscarPorUsername(String username) {
        return repo.findByUsername(username);
    }

    public Usuario guardar(Usuario usuario) {
        return repo.save(usuario);
    }

    public boolean validarCredenciales(String username, String password) {
        return repo.findByUsername(username)
                .map(u -> u.getPassword().equals(password))
                .orElse(false);
    }
}
