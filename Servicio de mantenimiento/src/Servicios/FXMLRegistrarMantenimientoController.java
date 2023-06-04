package Servicios;

import Modelo.DAO.ClienteDAO;
import Modelo.DAO.EquipoComputoDAO;
import Modelo.DAO.RefaccionDAO;
import Modelo.DAO.ServicioDAO;
import Modelo.POJO.Cliente;
import Modelo.POJO.EquipoComputo;
import Modelo.POJO.Refaccion;
import Modelo.POJO.Servicio;
import Utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLRegistrarMantenimientoController implements Initializable {
    
    @FXML
    private Label lbRegistrarMantenimiento;
    @FXML
    private TextField tfBuscarCliente;
    @FXML
    private TableView<Cliente> tvClienteEquipo;
    @FXML
    private TableColumn tcNombre,tcCorreoElectronico,tcNumeroTelefono;
    @FXML
    private TableColumn tcIDEquipoComputo;
    @FXML
    private Button btAceptar;
    @FXML
    private TextArea taDescripcionDiagnostico;
    @FXML
    private TextArea taDescripcionEquipo;
    @FXML
    private TextField tfBuscarRefaccion;
    @FXML
    private TableColumn tcRefaccion;
    @FXML
    private TableColumn tcCantidad;
    @FXML
    private TableColumn tcSeleccionar;
    @FXML
    private TableView<Refaccion>tvRefacciones;
    @FXML
    private TextArea taDescripcionMantenimiento;  
    @FXML
    private ObservableList<Cliente> listaClientes;
    @FXML
    private ObservableList<Refaccion> listaRefacciones;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTablaClienteEquipo();
        cargarTablaClienteEquipo();
        buscarCliente();
        configurarTablaRefaccion();
        cargarTablaRefaccion();
        buscarRefaccion();
        
}


private void configurarTablaClienteEquipo() {
    tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
    tcNumeroTelefono.setCellValueFactory(new PropertyValueFactory("numTelefono"));
    tcCorreoElectronico.setCellValueFactory(new PropertyValueFactory("correo"));
    tcIDEquipoComputo.setCellValueFactory(new PropertyValueFactory("idEquipoComputo"));
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
    tcRefaccion.setCellValueFactory(new PropertyValueFactory<>("nombreRefaccion"));
    tcCantidad.setCellValueFactory(new PropertyValueFactory<>("unidades"));  
}

    private void cargarTablaRefaccion() {
            try{
            listaRefacciones = FXCollections.observableArrayList();
            ArrayList<Refaccion> refaccionesBD = RefaccionDAO.obtenerRefacciones();
            listaRefacciones.addAll(refaccionesBD);
            tvRefacciones.setItems(listaRefacciones);
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
    }
    
    private void buscarRefaccion(){
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
    }

     

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

    
    }

   

