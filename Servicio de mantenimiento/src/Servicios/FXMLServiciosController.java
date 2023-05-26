package Servicios;

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

public class FXMLServiciosController implements Initializable {

    @FXML
    private Button btnSolicitarDiagnostico;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicSolicitarDiagnostico(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLSolicitarDiagnostico.fxml"));
            Parent ventanaSolicitarDiagnostico = fxmlLoader.load();
            Scene escenarioSolicitarDiagnostico = new Scene(ventanaSolicitarDiagnostico);
            Stage nuevoEscenarioSolicitarDiagnostico = new Stage();
            nuevoEscenarioSolicitarDiagnostico.setScene(escenarioSolicitarDiagnostico);
            nuevoEscenarioSolicitarDiagnostico.initModality(Modality.APPLICATION_MODAL);
            nuevoEscenarioSolicitarDiagnostico.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FXMLServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}