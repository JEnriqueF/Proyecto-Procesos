package Servicios;

import Modelo.DAO.ClienteDAO;
import Modelo.DAO.TipoServicioDAO;
import Modelo.POJO.Cliente;
import Modelo.POJO.TipoServicio;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        cargarListaTipoServicio();
    }
    
    private void configurarTabla(){
        tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tcNumTelefono.setCellValueFactory(new PropertyValueFactory("numTelefono"));
        tcCorreo.setCellFactory(new PropertyValueFactory("correo"));
    }
    
    private void cargarTabla(){
        try{
            listaClientes = FXCollections.observableArrayList();
            ArrayList<Cliente> recursoBD = ClienteDAO.obtenerClientes();
            listaClientes.addAll(recursoBD);
            System.out.println(listaClientes);
            tvClientes.setItems(listaClientes);
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
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
    
    /*private void buscarCliente(ActionEvent event) {
        try {
            String matricula = tfIdentificador.getText();
            
            //¿Se introdujo un nombre de usuario de biblioteca?
            if(matricula.isEmpty()){
                Utilidades.mostrarAlertaSimple("", "Debe escribir el nombre de un usuario de la biblioteca", 
                        Alert.AlertType.WARNING);
                return;
            }
            
            ArrayList<UsuarioBiblioteca> prestamosBD = UsuarioBibliotecaDAO.obtenerUsuario(matricula);
            
            if(prestamosBD.isEmpty()){
                Utilidades.mostrarAlertaSimple("Usuario no encontrado", "El usuario ingresado no se encontró", 
                        Alert.AlertType.WARNING);
                return;
            }
            
            for(UsuarioBiblioteca usuario : prestamosBD){
                lbNombreUsuario.setText(usuario.getNombre());
                lbCorreoUsuario.setText(usuario.getCorreo());
                lbDireccionUsuario.setText(usuario.getDomicilio());
                lbTelefonoUsuario.setText(usuario.getTelefono());
                
                //¿El usuario del sistema dio clic en "Solicitar renovación"?
                if(esRenovacion){
                    cargarTablaRenovacion(usuario);
                }else{
                    cargarTabla(usuario);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }*/

    @FXML
    private void clicAgregarCliente(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLRegistrarCliente.fxml"));
            Parent ventanaBuscarRecurso = fxmlLoader.load();
            Scene escenarioBuscarRecurso = new Scene(ventanaBuscarRecurso);
            Stage nuevoEscenarioRegistrarRecursoDaniado = new Stage();
            nuevoEscenarioRegistrarRecursoDaniado.setScene(escenarioBuscarRecurso);
            nuevoEscenarioRegistrarRecursoDaniado.initModality(Modality.APPLICATION_MODAL);
            nuevoEscenarioRegistrarRecursoDaniado.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FXMLServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clicRegistrarDiagnostico(ActionEvent event) {
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
    }
}