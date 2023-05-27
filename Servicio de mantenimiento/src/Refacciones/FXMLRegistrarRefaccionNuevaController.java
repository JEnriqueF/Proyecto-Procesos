package Refacciones;

import Modelo.DAO.RefaccionDAO;
import Modelo.POJO.Refaccion;
import Utilidades.Utilidades;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author froyl
 */
public class FXMLRegistrarRefaccionNuevaController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfPrecio;
    @FXML
    private TextField tfUnidades;
    @FXML
    private TextField tfProveedor;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lbNombreError;
    @FXML
    private Label lbPrecioError;
    @FXML
    private Label lbUnidadesError;
    @FXML
    private Label lbProveedorError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicBtnRegistrar(ActionEvent event) {
        if(!hayCamposVacios()){
            if(!hayCamposInvalidos()){
                Refaccion refaccionNueva = new Refaccion();
                refaccionNueva.setNombreRefaccion(tfNombre.getText());
                refaccionNueva.setPrecioCosto(Double.parseDouble(tfPrecio.getText()));
                refaccionNueva.setUnidades(Integer.parseInt(tfUnidades.getText()));
                refaccionNueva.setProveedor(tfProveedor.getText());

                try {
                    if(RefaccionDAO.registrarRefaccion(refaccionNueva)){
                        Utilidades.mostrarAlertaSimple("Éxito", "Se registró la nueva refacción con éxito", Alert.AlertType.INFORMATION);
                        cerrarVentana();
                    }else{
                        Utilidades.mostrarAlertaSimple("Error", "No hay conexión a la base de datos. Inténtelo más tarde", Alert.AlertType.ERROR);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }    
            }                  
        }        
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    private boolean hayCamposVacios(){
        boolean camposVacios = false;
        
        if(tfNombre.getText().isEmpty()){
            lbNombreError.setText("Información faltante");
            tfNombre.setStyle("-fx-border-color: red");
            camposVacios = true;
        }else{
            lbNombreError.setText("");
            tfNombre.setStyle("");
        }
        
        if(tfPrecio.getText().isEmpty()){
            lbPrecioError.setText("Información faltante");
            tfPrecio.setStyle("-fx-border-color: red");
            camposVacios = true;
        }else{
            lbPrecioError.setText("");
            tfPrecio.setStyle("");
        }
        
        if(tfUnidades.getText().isEmpty()){
            lbUnidadesError.setText("Información faltante");
            tfUnidades.setStyle("-fx-border-color: red");
            camposVacios = true;
        }else{
            lbUnidadesError.setText("");
            tfUnidades.setStyle("");
        }
        
        if(tfProveedor.getText().isEmpty()){
            lbProveedorError.setText("Información faltante");
            tfProveedor.setStyle("-fx-border-color: red");
            camposVacios = true;
        }else{
            lbProveedorError.setText("");
            tfProveedor.setStyle("");
        }
        
        return camposVacios;
    }
    
    private boolean hayCamposInvalidos(){
        boolean camposInvalidos = false;
        
        if(!esDouble(tfPrecio.getText())){
            lbPrecioError.setText("Tipo de dato incorrecto");
            tfPrecio.setStyle("-fx-border-color: red");
            camposInvalidos = true;
        }else{
            lbPrecioError.setText("");
            tfPrecio.setStyle("");
        }
        
        if(!esNumerico(tfUnidades.getText())){
            lbUnidadesError.setText("Tipo de dato incorrecto");
            tfUnidades.setStyle("-fx-border-color: red");
            camposInvalidos = true;
        }else{
            lbUnidadesError.setText("");
            tfUnidades.setStyle("");
        }
        
        return camposInvalidos;
    }
    
    private boolean esNumerico(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    private boolean esDouble(String cadena){
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
