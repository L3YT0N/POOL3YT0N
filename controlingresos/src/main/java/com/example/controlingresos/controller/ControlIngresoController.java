package com.example.controlingresos.controller;

import com.example.controlingresos.model.RegistroAcceso;
import com.example.controlingresos.model.Trabajador;
import com.example.controlingresos.service.RegistroAccesoService;
import com.example.controlingresos.service.TrabajadorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ControlIngresoController {

    @FXML private TextField txtDni;
    @FXML private TextField txtObservacion;
    @FXML private TableView<RegistroAcceso> tablaAccesos;
    @FXML private TableColumn<RegistroAcceso, String> colTrabajador;
    @FXML private TableColumn<RegistroAcceso, String> colDni;
    @FXML private TableColumn<RegistroAcceso, String> colIngreso;
    @FXML private TableColumn<RegistroAcceso, String> colSalida;
    @FXML private TableColumn<RegistroAcceso, String> colObservacion;

    @Autowired
    private TrabajadorService trabajadorService;

    @Autowired
    private RegistroAccesoService registroService;

    private ObservableList<RegistroAcceso> listaAccesos;

    @FXML
    private void initialize() {
        colTrabajador.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getTrabajador().getNombre()));
        colDni.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getTrabajador().getDni()));
        colIngreso.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getFechaHoraIngreso() != null ?
                                data.getValue().getFechaHoraIngreso().toString() : ""));
        colSalida.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getFechaHoraSalida() != null ?
                                data.getValue().getFechaHoraSalida().toString() : ""));
        colObservacion.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getObservacion()));

        listaAccesos = FXCollections.observableArrayList();
        tablaAccesos.setItems(listaAccesos);

        cargarTabla();
    }

    @FXML
    private void registrarIngreso() {
        String dni = txtDni.getText().trim();
        String obs = txtObservacion.getText().trim();

        if (dni.isEmpty()) {
            mostrarAlerta("Debe ingresar un DNI para registrar el ingreso.");
            return;
        }

        Optional<Trabajador> trabajadorOpt = trabajadorService.buscarPorDni(dni);
        if (trabajadorOpt.isEmpty()) {
            mostrarAlerta("No se encontró un trabajador con el DNI ingresado.");
            return;
        }

        Trabajador t = trabajadorOpt.get();

        RegistroAcceso acceso = new RegistroAcceso();
        acceso.setTrabajador(t);
        acceso.setFechaHoraIngreso(LocalDateTime.now());
        acceso.setObservacion(obs);

        registroService.guardar(acceso);

        mostrarAlerta("Ingreso registrado correctamente para " + t.getNombre());
        txtObservacion.clear();
        txtDni.clear();
        cargarTabla();
    }

    @FXML
    private void registrarSalida() {
        String dni = txtDni.getText().trim();

        if (dni.isEmpty()) {
            mostrarAlerta("Debe ingresar un DNI para registrar la salida.");
            return;
        }

        Optional<Trabajador> trabajadorOpt = trabajadorService.buscarPorDni(dni);
        if (trabajadorOpt.isEmpty()) {
            mostrarAlerta("No se encontró un trabajador con el DNI ingresado.");
            return;
        }

        Trabajador t = trabajadorOpt.get();

        // Buscar el último acceso sin salida
        List<RegistroAcceso> registros = registroService.listarPorTrabajador(t);
        registros.stream()
                .filter(r -> r.getFechaHoraSalida() == null)
                .reduce((first, second) -> second)
                .ifPresentOrElse(acceso -> {
                    acceso.setFechaHoraSalida(LocalDateTime.now());
                    registroService.guardar(acceso);
                    mostrarAlerta("Salida registrada correctamente para " + t.getNombre());
                    cargarTabla();
                }, () -> mostrarAlerta("No hay ingreso pendiente para este trabajador."));
    }

    private void cargarTabla() {
        listaAccesos.setAll(registroService.listar());
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Control de Ingreso");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
