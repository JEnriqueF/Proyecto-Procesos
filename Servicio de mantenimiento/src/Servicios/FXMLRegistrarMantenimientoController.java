package Servicios;

import Modelo.DAO.ClienteDAO;
import Modelo.DAO.EquipoComputoDAO;
import Modelo.DAO.RefaccionDAO;
import Modelo.DAO.ServicioDAO;
import Modelo.DAO.TipoServicioDAO;
import Modelo.POJO.Cliente;
import Modelo.POJO.EquipoComputo;
import Modelo.POJO.Refaccion;
import Modelo.POJO.ResultadoOperacion;
import Modelo.POJO.Servicio;
import Modelo.POJO.TipoServicio;
import Utilidades.Utilidades;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
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
    private TableColumn<Refaccion, String> tcRefaccion;
    @FXML
    private TableColumn<Refaccion, Integer> tcUnidades;
    @FXML
    private TableView<Refaccion> tvRefacciones;
    @FXML
    private TextArea taDescripcionMantenimiento;
    @FXML
    private Label lbMenuPrincipal;
    @FXML
    private Button btGuardarMantenimiento;
    @FXML
    private Button btFinalizarMantenimiento;
    @FXML
    private Button btCancelar;
    @FXML
    private TextField tfTipoMantenimineto;
    @FXML
    private TextField tfUnidades;
    @FXML
    private ComboBox<Refaccion> cbRefacciones;

    private ObservableList<Cliente> listaClientes;
    private ObservableList<Refaccion> listaRefacciones;
    @FXML
    private TableColumn<?, ?> tcIdRefaccion;
    @FXML
    private Button btAñadir;
    @FXML
    private Button btEliminar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTablaClienteEquipo();
        cargarTablaClienteEquipo();
        buscarCliente();
        configurarTablaRefaccion();
        cargarListaRefaccion();
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
                    int idRefaccion = refaccionDAO.getIdRefaccion(); // Obtener el idRefaccion

                    Refaccion refaccionAgregada = new Refaccion(refaccionSeleccionada.getNombreRefaccion(), unidadesRestadas);
                    refaccionAgregada.setIdRefaccion(idRefaccion); // Establecer el idRefaccion
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


private void configurarTablaClienteEquipo() {
    tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
    tcNumeroTelefono.setCellValueFactory(new PropertyValueFactory("numTelefono"));
    tcCorreoElectronico.setCellValueFactory(new PropertyValueFactory("correo"));
    tcIDEquipoComputo.setCellValueFactory(new PropertyValueFactory("idEquipoComputo"));
}

private void agregarRefaccion(ActionEvent event) {
    Refaccion refaccionSeleccionada = cbRefacciones.getValue();
    int unidades = Integer.parseInt(tfUnidades.getText());

    if (refaccionSeleccionada != null) {
        Refaccion nuevaRefaccion = new Refaccion(refaccionSeleccionada.getNombreRefaccion(), unidades);
        listaRefacciones.add(nuevaRefaccion);
        tvRefacciones.setItems(listaRefacciones);
    }

    // Limpiar los campos
    cbRefacciones.getSelectionModel().clearSelection();
    tfUnidades.clear();
}



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



   private void configurarTablaRefaccion() {
       cbRefacciones.setItems(listaRefacciones);
       tcIdRefaccion.setCellValueFactory(new PropertyValueFactory<>("idRefaccion"));
    tcRefaccion.setCellValueFactory(new PropertyValueFactory<>("nombreRefaccion"));
    tcUnidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));  
    tcUnidades.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
}
private void cargarTablaRefaccion() {
    try {
        listaRefacciones = FXCollections.observableArrayList();
        ArrayList<Refaccion> refaccionesBD = RefaccionDAO.obtenerRefacciones();
        listaRefacciones.addAll(refaccionesBD);
        tvRefacciones.setItems(listaRefacciones);

        // Configurar la celda de la columna de nombre de refacción para mostrar el nombre utilizando una función de celda personalizada
        tcRefaccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreRefaccion()));
        tcUnidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));

    } catch (SQLException | NullPointerException e) {
        e.printStackTrace();
    }
}

    
    private void verificarExistencia(){
        
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
        Servicio servicio = ServicioDAO.obtenerServicioPorEquipoComputo(clienteSeleccionado.getIdEquipoComputo());
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
            
            // Obtener la descripción del mantenimiento
            String descripcionMantenimiento = ServicioDAO.obtenerDescripcionMantenimiento(clienteSeleccionado.getIdEquipoComputo());
            if (descripcionMantenimiento != null) {
                // Mostrar la descripción del mantenimiento en el TextArea y deshabilitar la edición
                taDescripcionMantenimiento.setText(descripcionMantenimiento);
                taDescripcionMantenimiento.setEditable(true);
            } else {
                // No se encontró una descripción de mantenimiento
                taDescripcionMantenimiento.setText("");
            }

            // Obtener las refacciones asociadas al servicio
            List<Refaccion> refacciones = (List<Refaccion>) RefaccionDAO.mostrarRefaccionesServicio(clienteSeleccionado.getIdEquipoComputo());
            if (!refacciones.isEmpty()) {
                // Limpiar la tabla tvRefacciones antes de agregar los nuevos datos
                tvRefacciones.getItems().clear();
                // Agregar las refacciones a la tabla tvRefacciones
                tvRefacciones.getItems().addAll(refacciones);
            } else {
                // No se encontraron refacciones
                tvRefacciones.getItems().clear(); // Limpiar la tabla tvRefacciones
            }
        } else {
            // No se encontró un servicio asociado al equipo de cómputo
            taDescripcionDiagnostico.setText("");
            tfTipoMantenimineto.setText("");
            taDescripcionMantenimiento.setText("");
            tvRefacciones.getItems().clear(); // Limpiar la tabla tvRefacciones
        }
    }
}

    @FXML
    private void guardarMantenimiento(ActionEvent event) throws NullPointerException {
        Cliente clienteSeleccionado = tvClienteEquipo.getSelectionModel().getSelectedItem();
        ObservableList<Refaccion> data = tvRefacciones.getItems();

    if (clienteSeleccionado != null) {
        String descripcionMantenimiento = taDescripcionMantenimiento.getText();

        // Check if all required fields are filled
        if (descripcionMantenimiento.isEmpty()) {
            mostrarAdvertencia("Campo incompleto", "Por favor, completa la descripción del mantenimiento antes de guardarlo.");
            return;
        }

        try {
            Servicio idServicio = ServicioDAO.obtenerIdServicio(clienteSeleccionado.getIdEquipoComputo());

            for (Refaccion rowData : data) {
                int columIdRefaccion = rowData.getIdRefaccion();
                int columUnidades = rowData.getUnidades();

                ResultadoOperacion comprobacionExistencia = ServicioDAO.ServicioRefaccion(idServicio.getIdServicio(), columIdRefaccion);
                if (comprobacionExistencia.isError()) {
                    ResultadoOperacion refaccionesServicios = ServicioDAO.obtenerRefaccionesServicio(idServicio.getIdServicio(), columIdRefaccion, columUnidades);
                } else {
                    // Exclude existing refacciones from saving
                    continue;
                }
            }

            ResultadoOperacion resultado = ServicioDAO.guardarMantenimiento(clienteSeleccionado.getIdEquipoComputo(), descripcionMantenimiento);

            if (!resultado.isError()) {
                // Show success message
                mostrarInformacion("Mantenimiento guardado", "La descripción del mantenimiento se ha guardado exitosamente.");

                // Limpiar los campos y restablecer los valores iniciales
                limpiarCampos();
            } else {
                mostrarAdvertencia("Error al guardar mantenimiento", "No se pudo guardar la descripción del mantenimiento en la base de datos.");
            }
        } catch (SQLException e) {
            mostrarAdvertencia("Error de base de datos", "Ocurrió un error al acceder a la base de datos: " + e.getMessage());
        }
    } else {
        mostrarAdvertencia("Selección de cliente", "Por favor, selecciona un cliente de la tabla.");
    }
    }
    
    private void limpiarCampos() {
    tvClienteEquipo.getSelectionModel().clearSelection();
    taDescripcionMantenimiento.clear();
    taDescripcionDiagnostico.clear();
    taDescripcionEquipo.clear();
   tfTipoMantenimineto.clear();
    tvRefacciones.getItems().clear();
}


    @FXML
    private void finalizarMantenimiento(ActionEvent event) throws NullPointerException {
        Cliente clienteSeleccionado = tvClienteEquipo.getSelectionModel().getSelectedItem();

    ObservableList<Refaccion> data = tvRefacciones.getItems();

    if (clienteSeleccionado != null) {
        String descripcionMantenimiento = taDescripcionMantenimiento.getText();

        try {
            ServicioDAO.actualizarEstadoServicio(clienteSeleccionado.getIdEquipoComputo(), "Finalizado");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Cierra la ventana actual
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        // Abre la nueva ventana
        abrirNuevaVentana();
    } else {
        // Muestra un mensaje de advertencia
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText("No se ha seleccionado un mantenimiento en la tabla de clientes.");
        alert.showAndWait();
    }
    }
    
    private void abrirNuevaVentana() {
    try {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLRegistrarMantenimiento.fxml"));
        Parent root = loader.load();

        // Crear un nuevo stage
        Stage nuevoStage = new Stage();

        // Configurar el controlador de la nueva ventana (si es necesario)
        FXMLRegistrarMantenimientoController controller = loader.getController();
        // Configura el controlador o pasa cualquier dato necesario

        // Establecer la escena y mostrar la nueva ventana
        Scene scene = new Scene(root);
        nuevoStage.setScene(scene);
        nuevoStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    @FXML
    private void cancelar(ActionEvent event) {
         Node sourceNode = (Node) event.getSource();
    
    Stage currentStage = (Stage) sourceNode.getScene().getWindow();
    currentStage.close();
    }

    @FXML
    private void eliminarRefaccion(ActionEvent event) throws SQLException {
        Refaccion refaccionSeleccionada = tvRefacciones.getSelectionModel().getSelectedItem();

    Cliente clienteSeleccionado = tvClienteEquipo.getSelectionModel().getSelectedItem();

    if (refaccionSeleccionada != null) {
        boolean confirmacion = mostrarConfirmacion("Eliminar refacción", "¿Estás seguro de eliminar la refacción seleccionada?");
        

        if (confirmacion) {
            tvRefacciones.getItems().remove(refaccionSeleccionada);

            listaRefacciones.remove(refaccionSeleccionada); 

            Refaccion refaccionDAO = obtenerRefaccionDAO(refaccionSeleccionada);
            

            if (refaccionDAO != null) {
                int unidadesAlmacenadas = refaccionDAO.getUnidades();
                int unidadesEliminadas = refaccionSeleccionada.getUnidades();
                int unidadesActualizadas = unidadesAlmacenadas + unidadesEliminadas;
                

                refaccionDAO.setUnidades(unidadesActualizadas);


                  Servicio idServicio = ServicioDAO.obtenerIdServicio(clienteSeleccionado.getIdEquipoComputo());
                  mostrarAdvertencia("idServicio",""+idServicio.getIdServicio());
                  mostrarAdvertencia("Precolumna","se va a seleccionar la columna idRefaccion");
                  /*mostrarAdvertencia("idRefaccion",""+tvRefacciones.getSelectionModel().getSelectedItem().getIdRefaccion());*/
                 TableColumn<Refaccion,String> columnaIdRefaccion = (TableColumn <Refaccion, String> ) tvRefacciones.getColumns().get(0);
                 mostrarAdvertencia("PostColumna","Se selecciono la columna idRefaccion  " +  tvRefacciones.getColumns().get(0));
                 mostrarAdvertencia("PostColumna 2",""+ columnaIdRefaccion);
                 mostrarAdvertencia("PreFila", "Se a obtener el idRefaccion de la fila seleccionada de la tabla Refacciones ");
                 mostrarAdvertencia("PreFila 2",""+  tvRefacciones.getSelectionModel().getSelectedItem().getIdRefaccion());
                 /*String textoCelda = columnaIdRefaccion.getCellData(tvRefacciones.getSelectionModel().getSelectedItem().getIdRefaccion());*/
                 int textoCelda = tvRefacciones.getSelectionModel().getSelectedItem().getIdRefaccion();
                 mostrarAdvertencia("PostFila","Se selecciono el IdRefaccion de la fila seleccionada "  );
                 mostrarAdvertencia("PosFila 2",""+columnaIdRefaccion.getCellData(tvRefacciones.getSelectionModel().getSelectedIndex()));

                 /*int refacionees = Integer.parseInt(textoCelda);*/
                /* mostrarAdvertencia("idRefaccion",""+refacionees);*/
                 ResultadoOperacion eliminar = ServicioDAO.eliminarRefaccion(idServicio.getIdServicio(),textoCelda );
                 mostrarAdvertencia("Eliminacion BD",""+eliminar.getMensaje());



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

    
}