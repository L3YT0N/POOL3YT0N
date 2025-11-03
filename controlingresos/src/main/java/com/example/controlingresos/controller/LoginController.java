package com.example.controlingresos.controller;

import com.example.controlingresos.model.Usuario;
import com.example.controlingresos.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMensaje;

    @Autowired
    private UsuarioService usuarioService;

    @FXML
    private void initialize() {
        // Crea un usuario admin si no existe
        if (usuarioService.buscarPorUsername("admin").isEmpty()) {
            usuarioService.guardar(new Usuario("admin", "1234", "ADMIN"));
            System.out.println("✅ Usuario 'admin' creado automáticamente");
        }
    }

    @FXML
    private void iniciarSesion() {
        String user = txtUsuario.getText().trim();
        String pass = txtPassword.getText().trim();

        if (usuarioService.validarCredenciales(user, pass)) {
            abrirMenuPrincipal();
            ((Stage) txtUsuario.getScene().getWindow()).close();
        } else {
            lblMensaje.setText("Credenciales incorrectas");
        }
    }

    private void abrirMenuPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu_principal.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Menú Principal - Control de Ingreso");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
