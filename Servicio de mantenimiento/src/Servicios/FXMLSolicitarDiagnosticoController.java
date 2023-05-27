package Servicios;

import Modelo.DAO.ClienteDAO;
import Modelo.DAO.EquipoComputoDAO;
import Modelo.DAO.TipoServicioDAO;
import Modelo.POJO.Cliente;
import Modelo.POJO.ResultadoOperacion;
import Modelo.POJO.TipoServicio;
import Utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLSolicitarDiagnosticoController implements Initializable {

    @FXML
    private Label lbMenuPrincipal;
    @FXML
    private TableColumn tcNombre;
    @FXML
    private TableColumn tcNumTelefono;
    @FXML
    private TableColumn tcCorreo;
    @FXML
    private TableView<Cliente> tvClientes;
    @FXML
    private TextField tfBuscarCliente;
    @FXML
    private TextArea taDescripcionDiagnostico;
    @FXML
    private TextArea taDescripcionEquipo;
    @FXML
    private TextField tfCotizacion;
    @FXML
    private TextField tfTotal;
    @FXML
    private TextField tfPrecioDiagnostico;
    @FXML
    private ComboBox<TipoServicio> cbTipoServicioSugerido;
    
    private ObservableList<Cliente> listaClientes;
    private ObservableList<TipoServicio> listaTipoServicios;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarTabla();
        buscarCliente();
        cargarListaTipoServicio();
    }
    
    private void configurarTabla(){
        tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tcNumTelefono.setCellValueFactory(new PropertyValueFactory("numTelefono"));
        tcCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
    }
    
    private void cargarTabla(){
        try{
            listaClientes = FXCollections.observableArrayList();
            ArrayList<Cliente> clienteBD = ClienteDAO.obtenerClientes();
            listaClientes.addAll(clienteBD);
            tvClientes.setItems(listaClientes);
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
    }
    
    public void actualizarTablaClientes() {
        cargarTabla();
    }
    
    private void cargarListaTipoServicio(){
        listaTipoServicios = FXCollections.observableArrayList();
        try{
            ArrayList<TipoServicio> tipoServiciosBD = TipoServicioDAO.obtenerTipoServicio();
            listaTipoServicios.addAll(tipoServiciosBD);
            cbTipoServicioSugerido.setItems(listaTipoServicios);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    private void buscarCliente() {
        if(listaClientes.size() > 0){
            FilteredList<Cliente> filtroClientes = new FilteredList<>(listaClientes, p -> true);
            
            tfBuscarCliente.textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    filtroClientes.setPredicate(busqueda -> {
                        if(newValue == null || newValue.isEmpty()){
                            return true;
                        }
                        
                        if(busqueda.getNombre().contains(newValue.toLowerCase())){
                            return true;
                        }
                        
                        return false;
                    });
                }                
            });
            
            SortedList<Cliente> recursosFiltrados = new SortedList<>(filtroClientes);
            recursosFiltrados.comparatorProperty().bind(tvClientes.comparatorProperty());
            tvClientes.setItems(recursosFiltrados);
        }
    }

    @FXML
    private void clicAgregarCliente(ActionEvent event) {
        //Se implementa el patrón de diseño Observer
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLRegistrarCliente.fxml"));
            Parent ventanaBuscarRecurso = fxmlLoader.load();
            FXMLRegistrarClienteController registrarClienteController = fxmlLoader.getController();

            Stage nuevoEscenarioRegistrarRecursoDaniado = new Stage();
            nuevoEscenarioRegistrarRecursoDaniado.setScene(new Scene(ventanaBuscarRecurso));
            nuevoEscenarioRegistrarRecursoDaniado.initModality(Modality.APPLICATION_MODAL);

            registrarClienteController.setClienteRegistroListener(() -> cargarTabla());
        
            nuevoEscenarioRegistrarRecursoDaniado.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FXMLServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clicRegistrarDiagnostico(ActionEvent event) {
        Cliente clienteSeleccionado = tvClientes.getSelectionModel().getSelectedItem();
        
        if(clienteSeleccionado != null){
            try{
                ResultadoOperacion registroEquipo = EquipoComputoDAO.registrarEquipo(taDescripcionEquipo.getText());
                
                if(!registroEquipo.isError()){
                    
                }else{
                    Utilidades.mostrarAlertaSimple("Error", registroEquipo.getMensaje(), Alert.AlertType.ERROR);
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error", "No se ha elegido al cliente", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
        Stage escenario = (Stage) lbMenuPrincipal.getScene().getWindow();
        escenario.close();
    }
}