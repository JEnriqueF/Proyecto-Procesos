import Refacciones.FXMLRefaccionesController;
import Servicios.FXMLServiciosController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FXMLMenuPrincipalController implements Initializable {

    private int tipoUsuario;
    @FXML
    private Label lbMenuPrincipal;
    @FXML
    private BorderPane borderPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicAdministracionServicios(ActionEvent event) {
        if(tipoUsuario == 2){
            cargarOpciones("Servicios/FXMLServicios");
        }
    }

    @FXML
    private void clicAdministracionRefacciones(ActionEvent event) {
        if(tipoUsuario == 1){
            cargarOpciones("Refacciones/FXMLRefacciones");
        }
    }

    @FXML
    private void clicCerrarSesion(ActionEvent event) {
        cerrarMenuPrincipal();
        mostrarInicioSesion();
    }
    
    private void cargarOpciones(String vista){
        try {                  
            borderPane.getChildren().remove(borderPane.getCenter()); //Remueve fxml existente del centro
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vista + ".fxml"));  
            Parent vistap = fxmlLoader.load();
            
            if(vista.startsWith("Servicios/")){
                FXMLServiciosController prestamos = fxmlLoader.getController();
            }
            
            if(vista.startsWith("Refacciones/")){
                FXMLRefaccionesController recursos = fxmlLoader.getController();
            }
            
            borderPane.setCenter(vistap);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cerrarMenuPrincipal(){
            Stage escenarioPrincipal = (Stage) lbMenuPrincipal.getScene().getWindow();
            escenarioPrincipal.close();
    }
    
    private void mostrarInicioSesion(){
        try {
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLInicioSesion.fxml"));
            Scene escenaPrincipal = new Scene(vista);
            Stage escenarioBase = (Stage) lbMenuPrincipal.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inicializarTipoUsuario(int tipoUsuario){
        this.tipoUsuario = tipoUsuario;
    }
}
