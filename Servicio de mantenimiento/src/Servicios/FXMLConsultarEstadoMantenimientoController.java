package Servicios;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class FXMLConsultarEstadoMantenimientoController implements Initializable {


   @FXML
   private Button btCancelar;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
    
    
    
    
    
    
    
@FXML
private void cancelar(ActionEvent event) {
    Node sourceNode = (Node) event.getSource();
    
    Stage currentStage = (Stage) sourceNode.getScene().getWindow();
    currentStage.close();
}

}

