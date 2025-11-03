package com.example.controlingresos.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class MenuPrincipalController {

    @FXML
    private void abrirControlIngreso() {
        abrirVentana("/fxml/control_ingreso.fxml", "Control de Ingreso de Trabajadores");
    }

    @FXML
    private void abrirGestionTrabajadores() {
        abrirVentana("/fxml/gestion_trabajadores.fxml", "Gestión de Trabajadores");
    }

    @FXML
    private void cerrarSesion() {
        abrirVentana("/fxml/login.fxml", "Iniciar Sesión");
        Stage stage = (Stage) javafx.stage.Window.getWindows().stream()
                .filter(Window -> Window.isShowing())
                .findFirst().orElse(null);
        if (stage != null) stage.close();
    }

    private void abrirVentana(String ruta, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle(titulo);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
