package Servicios;

import Modelo.DAO.ClienteDAO;
import Modelo.POJO.Cliente;
import Modelo.POJO.ResultadoOperacion;
import Utilidades.Utilidades;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLRegistrarClienteController implements Initializable {

    @FXML
    private Label lbMenuPrincipal;
    @FXML
    private TextField tfNombreCliente;
    @FXML
    private TextField tfTelefonoCliente;
    @FXML
    private TextField tfCorreoCliente;
    @FXML
    private Label lbErrorNombre;
    @FXML
    private Label lbErrorCorreo;
    @FXML
    private Label lbErrorNum;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clicRegistrarCliente(ActionEvent event) {
        lbErrorNombre.setText("");
        lbErrorNum.setText("");
        lbErrorCorreo.setText("");
        
        String nombreCliente = tfNombreCliente.getText();
        String telefonoCliente = tfTelefonoCliente.getText();
        String correoCliente = tfCorreoCliente.getText();
        
        if(nombreCliente.isEmpty()){
            lbErrorNombre.setText("Información faltante");
        }
        if(telefonoCliente.isEmpty()){
            lbErrorNum.setText("Información faltante");
        }
        if(correoCliente.isEmpty()){
            lbErrorCorreo.setText("Información faltante");
            return;
        }
        registrarCliente();
        cerrarVentana();
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
    }
    
    private void registrarCliente(){
        Cliente cliente = new Cliente();
        int idRecurso, idUsuarioBiblioteca;
        
        try{
            cliente.setNombre(tfNombreCliente.getText());
            cliente.setNumTelefono(tfTelefonoCliente.getText());
            cliente.setCorreo(tfCorreoCliente.getText());
            
            ResultadoOperacion resultado = ClienteDAO.registrarCliente(cliente);
            if(!resultado.isError()){
                Utilidades.mostrarAlertaSimple("Éxito", "Cliente guardado.", Alert.AlertType.CONFIRMATION);
            }else{
                Utilidades.mostrarAlertaSimple("ERROR", resultado.getMensaje(), Alert.AlertType.ERROR);
            }                
        } catch (SQLException ex) {
            Utilidades.mostrarAlertaSimple("Algo salió mal", "Hubo un error al comunicarse con la base de datos. "
                    + "Por favor inténtelo más tarde.", Alert.AlertType.ERROR);
        }
    }
    
    private void cerrarVentana(){
        Stage escenario = (Stage) lbMenuPrincipal.getScene().getWindow();
        escenario.close();
    }
}