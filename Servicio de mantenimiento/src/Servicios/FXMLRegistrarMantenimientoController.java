package Servicios;


import Modelo.DAO.RefaccionDAO;
import Modelo.DAO.ServicioDAO;
import Modelo.DAO.TipoServicioDAO;
import Modelo.POJO.Cliente;
import Modelo.POJO.Refaccion;
import Modelo.POJO.Servicio;
import Modelo.POJO.TipoServicio;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class FXMLRegistrarMantenimientoController implements Initializable {

    @FXML
    private Label lbRegistrarMantenimiento;
    @FXML
    private TextField tfBuscarCliente;
    @FXML
    private TableView<Cliente> tvClienteEquipo;
    @FXML
    private TableColumn<Cliente, String> tcNombre;
    @FXML
    private TableColumn<Cliente, String> tcCorreoElectronico;
    @FXML
    private TableColumn<Cliente, String> tcNumeroTelefono;
    @FXML
    private TableColumn<Cliente, String> tcIDEquipoComputo;
    @FXML
    private Button btAceptar;
    @FXML
    private TextArea taDescripcionDiagnostico;
    @FXML
    private TextArea taDescripcionEquipo;
    @FXML
    private TextField tfTipoMantenimineto;
    @FXML
    private ComboBox<Refaccion> cbRefacciones;
    @FXML
    private TextField tfUnidades;
    @FXML
    private Button btAñadir;
    @FXML
    private TableView<Refaccion>tvRefacciones;
    @FXML
    private TableColumn <Refaccion , String> tcRefaccion;
    @FXML
    private TableColumn <Refaccion, Integer >tcUnidades;
    @FXML
    private TextArea taDescripcionMantenimiento;  
    @FXML
    private ObservableList<Cliente> listaClientes;
    @FXML
    private ObservableList<Refaccion> listaRefacciones;
    @FXML
    private Button btEliminar;
    @FXML
    private Label lbMenuPrincipal;
    @FXML
    private Button btGuardarMantenimiento;
    @FXML
    private Button btFinalizarMantenimiento;
    @FXML
    private Button btCancelar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTablaClienteEquipo();
        cargarTablaClienteEquipo();
        buscarCliente();
        configurarTablaRefaccion();
        cargarListaRefaccion();
        
    }

private void configurarTablaClienteEquipo() {
    tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
    tcNumeroTelefono.setCellValueFactory(new PropertyValueFactory("numTelefono"));
    tcCorreoElectronico.setCellValueFactory(new PropertyValueFactory("correo"));
    tcIDEquipoComputo.setCellValueFactory(new PropertyValueFactory("idEquipoComputo"));
}
    @FXML
    public void cargarTablaClienteEquipo() {
    try {
        listaClientes = FXCollections.observableArrayList();
        ArrayList<Cliente> clientesBD = ServicioDAO.obtenerClientesConEquipo();
        listaClientes.addAll(clientesBD);
        tvClienteEquipo.setItems(listaClientes);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    private void buscarCliente() {
    FilteredList<Cliente> filteredData = new FilteredList<>(listaClientes, p -> true);

    tfBuscarCliente.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(cliente -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String filterText = newValue.toLowerCase();
            return cliente.getNombre().toLowerCase().contains(filterText);
        });
    });

    SortedList<Cliente> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(tvClienteEquipo.comparatorProperty());
    tvClienteEquipo.setItems(sortedData);
}

    @FXML
    private void cargarDescripcionDiagnostico(ActionEvent event) throws SQLException {
    Cliente clienteSeleccionado = tvClienteEquipo.getSelectionModel().getSelectedItem();

    if (clienteSeleccionado != null) {
        // Obtener la descripción del equipo seleccionado
        String descripcionEquipo = ServicioDAO.obtenerDescripcionEquipo(clienteSeleccionado.getIdEquipoComputo());
        // Mostrar la descripción del equipo en el TextArea y deshabilitar la edición
        taDescripcionEquipo.setText(descripcionEquipo);
        taDescripcionEquipo.setEditable(false);
        
        // Obtener el servicio asociado al equipo de cómputo
        Servicio servicio = ServicioDAO.obtenerServicioPorEquipoComputo(descripcionEquipo);
        if (servicio != null) {
            // Mostrar la descripción del diagnóstico en el TextArea y deshabilitar la edición
            String descripcionDiagnostico = servicio.getDescripcionDiagnostico();
            taDescripcionDiagnostico.setText(descripcionDiagnostico);
            taDescripcionDiagnostico.setEditable(false);
            
            // Obtener el tipo de servicio
            TipoServicio tipoServicio = TipoServicioDAO.obtenerTipoServicio(servicio.getIdTipoServicio());
            
            if (tipoServicio != null) {
                // Mostrar el tipo de servicio en el TextField y deshabilitar la edición
                tfTipoMantenimineto.setText(tipoServicio.getTipoServicio());
                tfTipoMantenimineto.setEditable(false);
            } else {
                // No se encontró el tipo de servicio
                tfTipoMantenimineto.setText("");
            }
        } else {
            // No se encontró un servicio asociado al equipo de cómputo
            taDescripcionDiagnostico.setText("");
            tfTipoMantenimineto.setText("");
        }
    }
}

    
    
   private void configurarTablaRefaccion() {
       cbRefacciones.setItems(listaRefacciones);
    tcRefaccion.setCellValueFactory(new PropertyValueFactory<>("nombreRefaccion"));
    tcUnidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));  
    tcUnidades.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

}

private void cargarListaRefaccion() {
    listaRefacciones = FXCollections.observableArrayList();
    try {
        ArrayList<Refaccion> refaccionesBD = RefaccionDAO.obtenerRefacciones();
        listaRefacciones.addAll(refaccionesBD);

        // Configurar la fábrica de celdas para mostrar el nombre de la refacción
        cbRefacciones.setCellFactory((comboBox) -> {
            return new ListCell<Refaccion>() {
                @Override
                protected void updateItem(Refaccion refaccion, boolean empty) {
                    super.updateItem(refaccion, empty);

                    if (refaccion == null || empty) {
                        setText(null);
                    } else {
                        setText(refaccion.getNombreRefaccion());
                    }
                }
            };
        });
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    cbRefacciones.setItems(listaRefacciones);

}


@FXML
private void guardarRefaccion(ActionEvent event) {
    Refaccion refaccionSeleccionada = cbRefacciones.getSelectionModel().getSelectedItem();
    String unidadesString = tfUnidades.getText();

    if (refaccionSeleccionada != null && !unidadesString.isEmpty()) {
        try {
            int unidadesRestadas = Integer.parseInt(unidadesString);

            if (unidadesRestadas < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Cantidad inválida");
                alert.setContentText("La cantidad de unidades debe ser mayor o igual a 1.");
                alert.showAndWait();
                return;
            }

            Refaccion refaccionDAO = null;
            for (Refaccion refaccion : listaRefacciones) {
                if (refaccion.getNombreRefaccion().equals(refaccionSeleccionada.getNombreRefaccion())) {
                    refaccionDAO = refaccion;
                    break;
                }
            }

            if (refaccionDAO != null) {
                int unidadesAlmacenadas = refaccionDAO.getUnidades();
                int unidadesActualizadas = unidadesAlmacenadas - unidadesRestadas;

                if (unidadesActualizadas >= 0) {
                    refaccionDAO.setUnidades(unidadesActualizadas);

                    Refaccion refaccionAgregada = new Refaccion(refaccionSeleccionada.getNombreRefaccion(), unidadesRestadas);
                    refaccionAgregada.setUnidades(unidadesRestadas);
                    tvRefacciones.getItems().add(refaccionAgregada);

                    // Mostrar mensaje con las unidades restantes
                    int unidadesRestantesEnAlmacen = unidadesAlmacenadas - unidadesRestadas;
                    String mensaje = "Unidades restantes en el almacén: " + unidadesRestantesEnAlmacen;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Unidades restantes");
                    alert.setHeaderText(null);
                    alert.setContentText(mensaje);
                    alert.showAndWait();

                    // Limpiar los campos de entrada
                    cbRefacciones.getSelectionModel().clearSelection();
                    tfUnidades.clear();
                    
                    // Configurar la celda de la columna "tcUnidades" con el convertidor de enteros
                    tcUnidades.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unidades insuficientes");
                    alert.setContentText("La cantidad de unidades ingresadas es mayor a las unidades disponibles en el almacén.");
                    alert.showAndWait();
                }
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unidades no válidas");
            alert.setContentText("Por favor, ingrese un número válido para las unidades.");
            alert.showAndWait();
        }
    }
}
@FXML
private void eliminarRefaccion(ActionEvent event) {
    Refaccion refaccionSeleccionada = tvRefacciones.getSelectionModel().getSelectedItem();
    
    if (refaccionSeleccionada != null) {
        boolean confirmacion = mostrarConfirmacion("Eliminar refacción", "¿Estás seguro de eliminar la refacción seleccionada?");
        
        if (confirmacion) {
            // Eliminar la refacción de la tabla
            tvRefacciones.getItems().remove(refaccionSeleccionada);
            
            // Actualizar las unidades en el almacén
            Refaccion refaccionDAO = obtenerRefaccionDAO(refaccionSeleccionada);
            
            if (refaccionDAO != null) {
                int unidadesAlmacenadas = refaccionDAO.getUnidades();
                int unidadesEliminadas = refaccionSeleccionada.getUnidades();
                int unidadesActualizadas = unidadesAlmacenadas + unidadesEliminadas;
                
                refaccionDAO.setUnidades(unidadesActualizadas);
                
                // Mostrar mensaje con las unidades actualizadas
                String mensaje = "Unidades en el almacén después de eliminar: " + unidadesActualizadas;
                mostrarInformacion("Unidades actualizadas", mensaje);
            }
        }
    } else {
        mostrarAdvertencia("Selección de refacción", "Por favor, selecciona una refacción de la tabla.");
    }
}

private Refaccion obtenerRefaccionDAO(Refaccion refaccion) {
    for (Refaccion refaccionDAO : listaRefacciones) {
        if (refaccionDAO.getNombreRefaccion().equals(refaccion.getNombreRefaccion())) {
            return refaccionDAO;
        }
    }
    
    return null;
}

private void mostrarAdvertencia(String titulo, String mensaje) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(titulo);
    alert.setHeaderText(null);
    alert.setContentText(mensaje);
    alert.showAndWait();
}

private void mostrarInformacion(String titulo, String mensaje) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(titulo);
    alert.setHeaderText(null);
    alert.setContentText(mensaje);
    alert.showAndWait();
}

private boolean mostrarConfirmacion(String titulo, String mensaje) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(titulo);
    alert.setHeaderText(null);
    alert.setContentText(mensaje);
    
    ButtonType botonAceptar = new ButtonType("Aceptar");
    ButtonType botonCancelar = new ButtonType("Cancelar");
    alert.getButtonTypes().setAll(botonAceptar, botonCancelar);
    
    Optional<ButtonType> resultado = alert.showAndWait();
    return resultado.isPresent() && resultado.get() == botonAceptar;
}










@FXML
private void cancelar(ActionEvent event) {
    // Obtener el nodo fuente del evento
    Node sourceNode = (Node) event.getSource();
    
    // Obtener la ventana actual a partir del nodo fuente
    Stage currentStage = (Stage) sourceNode.getScene().getWindow();
    
    // Cerrar la ventana actual
    currentStage.close();
}







}


    