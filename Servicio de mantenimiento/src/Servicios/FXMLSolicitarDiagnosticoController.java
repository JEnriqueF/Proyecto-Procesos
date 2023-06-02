package Servicios;

import Modelo.DAO.ClienteDAO;
import Modelo.DAO.EquipoComputoDAO;
import Modelo.DAO.ServicioDAO;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private ComboBox<TipoServicio> cbTipoServicioSugerido;
    @FXML
    private Label lbErrorDiagnostico;
    @FXML
    private Label lbErrorCotizacion;
    @FXML
    private Label lbErrorTipoServicio;
    
    private ObservableList<Cliente> listaClientes;
    private ObservableList<TipoServicio> listaTipoServicios;
    @FXML
    private Label lbErrorEquipo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarTabla();
        buscarCliente();
        cargarListaTipoServicio();
        cbTipoServicioSugerido.valueProperty().addListener(new ChangeListener<TipoServicio>(){
            @Override
            public void changed(ObservableValue<? extends TipoServicio> observable, TipoServicio oldValue, TipoServicio newValue) {
                actualizarTotal(tfCotizacion.getText(), newValue, tfTotal);
            }
        });
        
        tfCotizacion.textProperty().addListener((observable, oldValue, newValue) -> {
            actualizarTotal(newValue, cbTipoServicioSugerido.getValue(), tfTotal);
        });
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
    
    private void actualizarTotal(String cotizacionStr, TipoServicio tipoServicio, TextField tfTotal) {
        try {
            double cotizacion = Double.parseDouble(cotizacionStr);
            double total = cotizacion + (tipoServicio != null ? tipoServicio.getCobroManoObra() : 0);
            tfTotal.setText(String.valueOf(total));
        } catch (NumberFormatException ex) {
            tfTotal.setText("");
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
                        
                        String filtroMinusculas = newValue.toLowerCase();
                        if(busqueda.getNombre().toLowerCase().contains(filtroMinusculas) 
                                || busqueda.getNumTelefono().toLowerCase().contains(filtroMinusculas)
                                || busqueda.getCorreo().toLowerCase().contains(filtroMinusculas)){
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
                if(!validarCamposLlenos()){
                    Utilidades.mostrarAlertaSimple("Error", "Información faltante", Alert.AlertType.ERROR);
                    return;
                }
                if(!validarTipoDato()){
                    return;
                }
                        
                ResultadoOperacion registroEquipo = EquipoComputoDAO.registrarEquipo(taDescripcionEquipo.getText());
                
                int equipoNuevo = EquipoComputoDAO.obtenerEquipoNuevo(taDescripcionEquipo.getText());
                
                ResultadoOperacion registroDiagnostico = ServicioDAO.registrarDiagnostico(
                        taDescripcionDiagnostico.getText(), Double.parseDouble(tfCotizacion.getText()), 
                        Double.parseDouble(tfTotal.getText()), cbTipoServicioSugerido.getValue().getIdTipoServicio(), 
                        clienteSeleccionado.getIdCliente(), equipoNuevo);
                        
                if(!registroDiagnostico.isError()){
                    Utilidades.mostrarAlertaSimple("Éxito", "Diagnóstico guardado", Alert.AlertType.CONFIRMATION);
                    
                    taDescripcionDiagnostico.setText("");
                    taDescripcionEquipo.setText("");
                    tfCotizacion.setText("");
                    cbTipoServicioSugerido.setValue(null);
                }else{
                    Utilidades.mostrarAlertaSimple("Error", "No fue posible guardar el diagnóstico", Alert.AlertType.ERROR);
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
    
    private boolean validarCamposLlenos(){
        boolean campoLleno = true;    
        
        lbErrorDiagnostico.setText("");
        lbErrorEquipo.setText("");
        lbErrorCotizacion.setText("");
        lbErrorTipoServicio.setText("");
        
        taDescripcionDiagnostico.setStyle("");
        taDescripcionEquipo.setStyle("");
        tfCotizacion.setStyle("");
        cbTipoServicioSugerido.setStyle("");
        
        if(taDescripcionDiagnostico.getText().isEmpty()){
            lbErrorDiagnostico.setText("Información faltante");
            taDescripcionDiagnostico.setStyle("-fx-border-color: red;");
            campoLleno = false;
        }
        if(taDescripcionEquipo.getText().isEmpty()){
            lbErrorEquipo.setText("Información faltante");
            taDescripcionEquipo.setStyle("-fx-border-color: red;");
            campoLleno = false;
        }
        if(tfCotizacion.getText().isEmpty()){
            lbErrorCotizacion.setText("Información faltante");
            tfCotizacion.setStyle("-fx-border-color: red;");
            campoLleno = false;
        }
        if(cbTipoServicioSugerido.getValue() == null){
            lbErrorTipoServicio.setText("Información faltante");
            cbTipoServicioSugerido.setStyle("-fx-border-color: red;");
            campoLleno = false;
        }
        return campoLleno;
    }
    
    private boolean validarTipoDato(){
        boolean tipoDato = true;
        
        lbErrorCotizacion.setText("");
        tfCotizacion.setStyle("");
        
        //Se define la expresión que contiene los caracteres que no tomaremos como válidos
        String invalidosEspeciales = ".*[a-zA-Z!@#$%^&*()].*";
        
        //Se compila la expresión en un objeto Pattern
        Pattern patternEspeciales = Pattern.compile(invalidosEspeciales);
        
        //Creación de un objeto Matcher con la cadena a evaluar
        Matcher validacionCotizacion = patternEspeciales.matcher(tfCotizacion.getText());
        
        //Valida si un caracter de la expresión está dentro de la cadena
        if(validacionCotizacion.matches()){
            lbErrorCotizacion.setText("Tipo de dato incorrecto");
            tfCotizacion.setStyle("-fx-border-color: red;");
            tipoDato = false;
        }
        return tipoDato;
    }
}