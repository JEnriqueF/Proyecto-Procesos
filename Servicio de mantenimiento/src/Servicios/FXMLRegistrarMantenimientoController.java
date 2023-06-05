package Servicios;

import Modelo.DAO.ClienteDAO;
import Modelo.DAO.EquipoComputoDAO;
import Modelo.DAO.RefaccionDAO;
import Modelo.DAO.ServicioDAO;
import Modelo.DAO.TipoServicioDAO;
import Modelo.POJO.Cliente;
import Modelo.POJO.EquipoComputo;
import Modelo.POJO.Refaccion;
import Modelo.POJO.Servicio;
import Modelo.POJO.TipoServicio;
import Utilidades.Utilidades;
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
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ListCell;

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
    private TextField tfBuscarRefaccion;
    @FXML
<<<<<<< HEAD
    private TableColumn tcRefaccion;
    private TableColumn tcCantidad;
    @FXML
    private TableView<Refaccion>tvRefacciones;
    @FXML
    private TextArea taDescripcionMantenimiento;  
    private ObservableList<Cliente> listaClientes;
    private ObservableList<Refaccion> listaRefacciones;
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
    @FXML
    private TableColumn tcUnidades;
    
    
=======
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

>>>>>>> 66ee3da583e324017380cadeac1aeae27251cb39
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTablaClienteEquipo();
        cargarTablaClienteEquipo();
        buscarCliente();
        configurarTablaRefaccion();
        cargarListaRefaccion();
<<<<<<< HEAD
        //cargarTablaRefaccion(null, 0);
        //buscarRefaccion();
        
=======
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
>>>>>>> 66ee3da583e324017380cadeac1aeae27251cb39
}

    
    private void cargarListaRefaccion(){
        listaRefacciones = FXCollections.observableArrayList();
        try{
            ArrayList<Refaccion> refaccionesBD = RefaccionDAO.obtenerRefacciones();
            listaRefacciones.addAll(refaccionesBD);
            cbRefacciones.setItems(listaRefacciones);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

@FXML
private void guardarRefaccion(ActionEvent event) {
    Refaccion refaccionSeleccionada = cbRefacciones.getValue();
    String unidadesString = tfUnidades.getText();

    if (!unidadesString.isEmpty()) {
        int unidades = Integer.parseInt(unidadesString);
        if (refaccionSeleccionada != null) {
            refaccionSeleccionada.setUnidades(unidades);
            tvRefacciones.refresh(); // Actualizar la tabla para reflejar los cambios
        } else {
            // Manejo de error o notificación al usuario de que no se ha seleccionado una refacción
        }
    } else {
        // Manejo de error o notificación al usuario de que el campo de unidades está vacío
    }
}

    // Limpiar los campos
    //cbRefacciones.getSelectionModel().clearSelection();
    //tfUnidades.clear();





/*private boolean validarCampos() {
    Refaccion refaccionSeleccionada = cbRefacciones.getSelectionModel().getSelectedItem();
    String unidades = tfUnidades.getText();

    if (refaccionSeleccionada == null || unidades.isEmpty() || !unidades.matches("\\d+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Seleccione una refacción y asegúrese de ingresar una cantidad válida (solo números enteros).");
        alert.showAndWait();
        return false;
    }

    return true;
}
*/



private void configurarTablaClienteEquipo() {
    tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
    tcNumeroTelefono.setCellValueFactory(new PropertyValueFactory("numTelefono"));
    tcCorreoElectronico.setCellValueFactory(new PropertyValueFactory("correo"));
    tcIDEquipoComputo.setCellValueFactory(new PropertyValueFactory("idEquipoComputo"));
}

<<<<<<< HEAD
=======
 @FXML
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



>>>>>>> 66ee3da583e324017380cadeac1aeae27251cb39
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



   private void configurarTablaRefaccion() {
       cbRefacciones.setItems(listaRefacciones);
    tcRefaccion.setCellValueFactory(new PropertyValueFactory<>("nombreRefaccion"));
    tcUnidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));  
<<<<<<< HEAD
}

    
    
    private void verificarExistencia(){
        try {
            ArrayList<Refaccion> existen = RefaccionDAO.verificarExistencia(cbRefacciones.valueProperty().getValue().getIdRefaccion());
            
            if(existen != null){
                cargarTablaRefacciones(cbRefacciones.valueProperty().getValue().getNombreRefaccion(), Integer.getInteger(tfUnidades.getText()));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
=======
    
}
@FXML
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
        
>>>>>>> 66ee3da583e324017380cadeac1aeae27251cb39
    }
    
    /*private void buscarRefaccion(){
        if(listaRefacciones.size() > 0){
            FilteredList<Refaccion > filtroRefacciones = new FilteredList<>(listaRefacciones, p -> true);
            
            tfBuscarRefaccion.textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    filtroRefacciones.setPredicate(busqueda -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }return busqueda.getNombreRefaccion().toLowerCase().startsWith(newValue.toLowerCase());
                    });
                }                
            });
            SortedList<Refaccion> recursosFiltrados = new SortedList<>(filtroRefacciones);
            recursosFiltrados.comparatorProperty().bind(tvRefacciones.comparatorProperty());
            tvRefacciones.setItems(recursosFiltrados);
        }
    }*/

     
/*@FXML
private void cargarTablaClienteEquipo(ActionEvent event) {
    Servicio servicioSeleccionado = tvClienteEquipo.getSelectionModel().getSelectedItem();

    if (servicioSeleccionado != null) {
        try {
            // Obtener el servicio asociado al cliente
            Servicio servicio = ServicioDAO.obtenerDiagnosticoPorEquipoComputo(servicioSeleccionado.getIdEquipoComputo());

            if (servicio != null) {
                // Mostrar la descripción del diagnóstico en el TextArea
                String descripcionDiagnostico = servicio.getDescripcionDiagnostico();
                taDescripcionDiagnostico.setText(descripcionDiagnostico);
            } else {
                // No se encontró un servicio asociado al cliente
                taDescripcionDiagnostico.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

*/
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


    @FXML
    private void cargarTablaRefaccion(ActionEvent event) {
        try{
            verificarExistencia();
            
            /*Refaccion r = new Refaccion(nombreRefaccion, unidades);
            tvRefacciones.getItems().addAll(r);*/
            
            /*listaRefacciones = FXCollections.observableArrayList();
            ArrayList<Refaccion> refaccionesBD = RefaccionDAO.obtenerRefacciones();
            listaRefacciones.addAll(refaccionesBD);
            tvRefacciones.setItems(listaRefacciones);*/
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    
    private void cargarTablaRefacciones(String nombreRefaccion, int unidades) {
        try{
            Refaccion r = new Refaccion(nombreRefaccion, unidades);
            tvRefacciones.getItems().addAll(r);
            
            /*listaRefacciones = FXCollections.observableArrayList();
            ArrayList<Refaccion> refaccionesBD = RefaccionDAO.obtenerRefacciones();
            listaRefacciones.addAll(refaccionesBD);
            tvRefacciones.setItems(listaRefacciones);*/
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }
    
    }

   

