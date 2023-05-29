package Refacciones;

import Modelo.DAO.RefaccionDAO;
import Modelo.POJO.Refaccion;
import Servicios.FXMLServiciosController;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author froyl
 */
public class FXMLRegistrarRefaccionController implements Initializable {

    @FXML
    private TableColumn tcNombre;
    @FXML
    private TableColumn tcProveedor;
    @FXML
    private TableColumn tcPrecio;
    @FXML
    private TableColumn tcUnidades;
    @FXML
    private TableView<Refaccion> tvRefacciones;
    @FXML
    private TextField tfBusqueda;
    @FXML
    private Button btnSalir;
    @FXML
    private TextField tfUnidades;
    @FXML
    private Label lbUnidadesError;
    
    private ObservableList<Refaccion> listaRefacciones;    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarTabla();
        inicializarBusquedaRefacciones();
    }    
    
    @FXML
    private void clicBtnRegistrarNueva(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLRegistrarRefaccionNueva.fxml"));
            Parent ventanaRegistrarRefaccionNueva = fxmlLoader.load();
            Scene escenarioRegistrarRefaccionNueva = new Scene(ventanaRegistrarRefaccionNueva);
            Stage nuevoEscenarioRegistrarRefaccionNueva = new Stage();
            nuevoEscenarioRegistrarRefaccionNueva.setScene(escenarioRegistrarRefaccionNueva);
            nuevoEscenarioRegistrarRefaccionNueva.initModality(Modality.APPLICATION_MODAL);
            nuevoEscenarioRegistrarRefaccionNueva.showAndWait();
            cargarTabla();
        }catch(IOException ex){
            Logger.getLogger(FXMLServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void clicBtnAgregarUnidades(){
        Refaccion refaccionSeleccionada = tvRefacciones.getSelectionModel().getSelectedItem();
        if(refaccionSeleccionada != null){
            if(!tfUnidades.getText().isEmpty()){
                lbUnidadesError.setText("");
                tfUnidades.setStyle("");

                if(esNumerico(tfUnidades.getText())){
                    try {
                        lbUnidadesError.setText("");
                        tfUnidades.setStyle("");
                        
                        int unidadesSumadas = Integer.parseInt(tfUnidades.getText()) + refaccionSeleccionada.getUnidades();
                        
                        if(RefaccionDAO.agregarUnidades(unidadesSumadas, refaccionSeleccionada.getIdRefaccion())){
                            Utilidades.mostrarAlertaSimple("Éxito", "Se agregaron " + tfUnidades.getText() + " unidades de la refacción: " + refaccionSeleccionada.getNombreRefaccion(), Alert.AlertType.INFORMATION);
                            cargarTabla();
                        }else{
                            Utilidades.mostrarAlertaSimple("Error", "No hay conexión a la base de datos. Inténtelo más tarde", Alert.AlertType.ERROR);
                        }
                  } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    lbUnidadesError.setText("Tipo de dato incorrecto");
                    tfUnidades.setStyle("-fx-border-color: red");
                }
            }else{
                lbUnidadesError.setText("Información faltante");
                tfUnidades.setStyle("-fx-border-color: red");
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error", "No se ha seleccionado la refacción", Alert.AlertType.WARNING);
        }     
    }
    
    @FXML
    private void cerrarVentana(){
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    private void configurarTabla(){
        tcNombre.setCellValueFactory(new PropertyValueFactory("nombreRefaccion"));
        tcProveedor.setCellValueFactory(new PropertyValueFactory("proveedor"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory("precioCosto"));
        tcUnidades.setCellValueFactory(new PropertyValueFactory("unidades"));
    }
    
    private void cargarTabla(){
        try{
            listaRefacciones = FXCollections.observableArrayList();
            ArrayList<Refaccion> refaccionesBD = RefaccionDAO.obtenerRefacciones();
            
            if(!refaccionesBD.isEmpty()){
                listaRefacciones.addAll(refaccionesBD);
                tvRefacciones.setItems(listaRefacciones);
            }else{
                Utilidades.mostrarAlertaSimple("No hay refacciones", "Aun no hay refacciones registradas.", Alert.AlertType.ERROR);
            }
            
        }catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
    }
    
    private void inicializarBusquedaRefacciones(){
        if(listaRefacciones.size() > 0){
            FilteredList<Refaccion> filtro = new FilteredList<>(listaRefacciones, p -> true);
            
            tfBusqueda.textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    filtro.setPredicate(busqueda -> {
                        if(newValue == null || newValue.isEmpty()){
                            return true;
                        }
                        
                        String filtroMinusculas = newValue.toLowerCase();
                        if(busqueda.getNombreRefaccion().toLowerCase().contains(filtroMinusculas)){
                            return true;
                        }
                        
                        return false;
                    });
                }
                
            });
            
            SortedList<Refaccion> sortedRefaccion = new SortedList<>(filtro);
            sortedRefaccion.comparatorProperty().bind(tvRefacciones.comparatorProperty());
            tvRefacciones.setItems(sortedRefaccion);
        }
    }
    
    private boolean esNumerico(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
