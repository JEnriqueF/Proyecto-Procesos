import Modelo.DAO.PersonalDAO;
import Modelo.POJO.Personal;
import Utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLInicioSesionController implements Initializable {
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField pfContrasenia;
    @FXML
    private Label lbErrorUsuario;
    @FXML
    private Label lbErrorContrasenia;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) {
        String noPersonal = tfUsuario.getText();
        String contraseña = pfContrasenia.getText();
        boolean valido = true;
        lbErrorUsuario.setText("");
        lbErrorContrasenia.setText("");
        
        if(noPersonal.isEmpty()){
            valido = false;
            lbErrorUsuario.setText("El campo es requerido");
        }
        
        if(contraseña.isEmpty()){
            valido = false;
            lbErrorContrasenia.setText("El campo es requerido");
        }
        
        if(valido){
            verificarCredencialesUsuario(noPersonal, contraseña);
        }
    }
    
    private void verificarCredencialesUsuario(String noPersonal, String contraseña) {
        try {
            Personal usuarioSesion = PersonalDAO.verificarUsuario(noPersonal, contraseña);
            if(usuarioSesion.getIdPersonal() != 0){
                Utilidades.mostrarAlertaSimple("Bienvenid@", "Bienvenid@ " + usuarioSesion.getUsuario() + ".", Alert.AlertType.INFORMATION);
                irMenuPrincipal(usuarioSesion.getIdTipoUsuario());
            }else{
                Utilidades.mostrarAlertaSimple("Credenciales incorrectas", "El número de personal y/o contraseña es incorrecto, favor de "
                        + "verificar", Alert.AlertType.WARNING);
            }
        } catch (SQLException | NullPointerException e) {
            Utilidades.mostrarAlertaSimple("Error de conexión", "Hubo un error en el proceso de "
                   + "comunicación. Intentelo más tarde.", Alert.AlertType.ERROR);
                    
        }
    }
    
    private void irMenuPrincipal(int tipoUsuario){
        try {
            FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("FXMLMenuPrincipal.fxml"));
            Parent vista = accesoControlador.load();
            /*FXMLMenuPrincipalController ventana = accesoControlador.getController();
            ventana.inicializarTipoUsuario(tipoUsuario);*/
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) tfUsuario.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}