package Refacciones;

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
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLRefaccionesController implements Initializable {    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    
    
    @FXML
    private void registrarRefaccion(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLRegistrarRefaccion.fxml"));
            Parent ventanaRegistrarRefaccion = fxmlLoader.load();
            Scene escenarioRegistrarRefaccion = new Scene(ventanaRegistrarRefaccion);
            Stage nuevoEscenarioRegistrarRefaccion = new Stage();
            nuevoEscenarioRegistrarRefaccion.setScene(escenarioRegistrarRefaccion);
            nuevoEscenarioRegistrarRefaccion.initModality(Modality.APPLICATION_MODAL);
            nuevoEscenarioRegistrarRefaccion.showAndWait();
        }catch(IOException ex){
            Logger.getLogger(FXMLServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}