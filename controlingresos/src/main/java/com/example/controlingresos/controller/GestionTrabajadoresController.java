package com.example.controlingresos.controller;

import com.example.controlingresos.model.Trabajador;
import com.example.controlingresos.service.TrabajadorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GestionTrabajadoresController {

    @FXML private TextField txtDni;
    @FXML private TextField txtNombre;
    @FXML private TextField txtArea;

    @FXML private TableView<Trabajador> tablaTrabajadores;
    @FXML private TableColumn<Trabajador, Long> colId;
    @FXML private TableColumn<Trabajador, String> colDni;
    @FXML private TableColumn<Trabajador, String> colNombre;
    @FXML private TableColumn<Trabajador, String> colArea;
    @FXML private TableColumn<Trabajador, String> colActivo;

    @Autowired
    private TrabajadorService trabajadorService;

    private ObservableList<Trabajador> listaTrabajadores;

    @FXML
    private void initialize() {
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleLongProperty(cell.getValue().getId()).asObject());
        colDni.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDni()));
        colNombre.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNombre()));
        colArea.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getArea()));
        colActivo.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().isActivo() ? "Sí" : "No"));

        listaTrabajadores = FXCollections.observableArrayList();
        tablaTrabajadores.setItems(listaTrabajadores);
        actualizarTabla();
    }

    @FXML
    private void registrarTrabajador() {
        String dni = txtDni.getText().trim();
        String nombre = txtNombre.getText().trim();
        String area = txtArea.getText().trim();

        if (dni.isEmpty() || nombre.isEmpty()) {
            mostrarAlerta("Debe completar al menos el DNI y el nombre.");
            return;
        }

        Trabajador t = new Trabajador(dni, nombre, area);
        trabajadorService.guardar(t);

        mostrarAlerta("Trabajador registrado correctamente.");
        limpiarCampos();
        actualizarTabla();
    }

    @FXML
    private void eliminarTrabajador() {
        Trabajador seleccionado = tablaTrabajadores.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Seleccione un trabajador para eliminar.");
            return;
        }

        trabajadorService.eliminar(seleccionado.getId());
        mostrarAlerta("Trabajador eliminado correctamente.");
        actualizarTabla();
    }

    @FXML
    private void actualizarTabla() {
        listaTrabajadores.setAll(trabajadorService.listar());
    }

    private void limpiarCampos() {
        txtDni.clear();
        txtNombre.clear();
        txtArea.clear();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestión de Trabajadores");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
