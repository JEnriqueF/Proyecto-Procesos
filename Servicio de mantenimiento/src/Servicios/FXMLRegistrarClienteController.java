package Servicios;

import Modelo.DAO.ClienteDAO;
import Modelo.POJO.Cliente;
import Modelo.POJO.ResultadoOperacion;
import Utilidades.Utilidades;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    
    private ClienteRegistroListener clienteRegistroListener;
    private boolean camposLlenos = false, datosCorrectos = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setClienteRegistroListener(ClienteRegistroListener listener) {
        this.clienteRegistroListener = listener;
    }
    
    @FXML
    private void clicRegistrarCliente(ActionEvent event) {
        lbErrorNombre.setText("");
        lbErrorNum.setText("");
        lbErrorCorreo.setText("");
        
        camposLlenos = validarCamposLlenos();
        datosCorrectos = validarTipoDato();
        if(camposLlenos == false || datosCorrectos == false){
            return;
        }
        registrarCliente();
        cerrarVentana();
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
        cerrarVentana();
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
                
                if (clienteRegistroListener != null) {
                    clienteRegistroListener.onClienteRegistrado();
                }
            }else{
                Utilidades.mostrarAlertaSimple("ERROR", resultado.getMensaje(), Alert.AlertType.ERROR);
            }                
        } catch (SQLException ex) {
            Utilidades.mostrarAlertaSimple("Algo salió mal", "Hubo un error al comunicarse con la base de datos. "
                    + "Por favor inténtelo más tarde.", Alert.AlertType.ERROR);
        }
    }
    
    private boolean validarCamposLlenos(){
        boolean campoLleno = true;
        
        String nombreCliente = tfNombreCliente.getText();
        String telefonoCliente = tfTelefonoCliente.getText();
        
        if(nombreCliente.isEmpty()){
            lbErrorNombre.setText("Información faltante");
            campoLleno = false;
        }
        if(telefonoCliente.isEmpty()){
            lbErrorNum.setText("Información faltante");
            campoLleno = false;
        }
        return campoLleno;
    }
    
    private boolean validarTipoDato(){
        boolean datoCorrecto = true;
        
        //Se define la expresión que contiene los caracteres que no tomaremos como válidos
        String invalidosEspeciales = ".*[0-9!@#$%^&*()].*";
        String invalidosLetras = ".*[a-zA-Z].*";
        String arroba = ".*[@].*";
        
        //Se compila la expresión en un objeto Pattern
        Pattern patternEspeciales = Pattern.compile(invalidosEspeciales);
        Pattern patternLetras = Pattern.compile(invalidosLetras);
        Pattern patternArroba = Pattern.compile(arroba);
        
        String nombreCliente = tfNombreCliente.getText();
        String telefonoCliente = tfTelefonoCliente.getText();
        String correoCliente = tfCorreoCliente.getText();
        
        //Creación de un objeto Matcher con la cadena a evaluar
        Matcher validacionNombre = patternEspeciales.matcher(nombreCliente);
        
        //Valida si un caracter de la expresión está dentro de la cadena
        if(validacionNombre.matches()){
            lbErrorNombre.setText("Tipo de dato incorrecto");
            datoCorrecto = false;
        }
        
        Matcher validacionTelefono = patternLetras.matcher(telefonoCliente);
        if(validacionTelefono.matches()){
            lbErrorNum.setText("Tipo de dato incorrecto");
            datoCorrecto = false;
        }
        
        Matcher validacionCorreo = patternArroba.matcher(correoCliente);
        if(!validacionCorreo.matches()){
            lbErrorCorreo.setText("Información faltante o tipo de dato incorrecto");
            datoCorrecto = false;
        }
        
        return datoCorrecto;
    }
    
    private void cerrarVentana(){
        Stage escenario = (Stage) lbMenuPrincipal.getScene().getWindow();
        escenario.close();
    }
}