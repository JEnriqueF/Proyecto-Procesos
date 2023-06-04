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
    private Button btnRegistrarMantenimiento;
    private Button btnConsultarMantenimiento;

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
    @FXML
    private void clicRegistrarMantenimiento(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLRegistrarMantenimiento.fxml"));
            Parent ventanaRegistrarMantenimiento = fxmlLoader.load();
            Scene escenarioRegistrarMantenimiento = new Scene(ventanaRegistrarMantenimiento);
            Stage nuevoEscenarioRegistrarMantenimiento = new Stage();
            nuevoEscenarioRegistrarMantenimiento.setScene(escenarioRegistrarMantenimiento);
            nuevoEscenarioRegistrarMantenimiento.initModality(Modality.APPLICATION_MODAL);
            nuevoEscenarioRegistrarMantenimiento.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FXMLServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void clicConsultarMantenimiento(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLConsultarEstadoMantenimiento.fxml"));
            Parent ventanaConsultarMantenimiento = fxmlLoader.load();
            Scene escenarioConsultarMantenimiento = new Scene(ventanaConsultarMantenimiento);
            Stage nuevoEscenarioConsultarMantenimiento = new Stage();
            nuevoEscenarioConsultarMantenimiento.setScene(escenarioConsultarMantenimiento);
            nuevoEscenarioConsultarMantenimiento.initModality(Modality.APPLICATION_MODAL);
            nuevoEscenarioConsultarMantenimiento.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FXMLServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}