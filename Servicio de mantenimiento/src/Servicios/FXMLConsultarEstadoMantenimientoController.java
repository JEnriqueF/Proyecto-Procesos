/*package Servicios;

import Modelo.DAO.ServicioDAO;
import Modelo.POJO.Servicio;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class FXMLConsultarEstadoMantenimientoController implements Initializable {


   @FXML
   private Button btCancelar;
   @FXML
   private  TextField tfBuscarClienteEquipo;
  @FXML
    private TableView<Servicio> tvMantenimiento;
   @FXML
   private TableColumn<Servicio, String> tcNombre;
   @FXML
   private TableColumn<Servicio, String> tcEquipo;
   @FXML
   private TableColumn<Servicio, String> tcTipoMantenimiento;
    @FXML
   private Button btVisualizarMantenimiento;
   
    
    
    
    /*@Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTablaMantenimiento();
        cargarTablaMantenimientoEquipo();
        
        
     
    }    
    
    
    private void configurarTablaMantenimiento() {
    tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
    tcEquipo.setCellValueFactory(new PropertyValueFactory("descripcionEquipo"));
    tcTipoMantenimiento.setCellValueFactory(new PropertyValueFactory("TipoServicio"));
}
   
/*public void cargarTablaMantenimientoEquipo() {
    try {
        /*listaMantenimientos = FXCollections.observableArrayList();
        ArrayList<Servicio> ServicioBD = ServicioDAO.obtenerMantenimientosEquipo();
        listaMantenimientos.addAll(ServicioBD);
        tvMantenimiento.setItems(listaMantenimientos);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    
    
    
  
    
    
    
    
@FXML
private void cancelar(ActionEvent event) {
    Node sourceNode = (Node) event.getSource();
    
    Stage currentStage = (Stage) sourceNode.getScene().getWindow();
    currentStage.close();
}

}
/*/
