package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.ResultadoOperacion;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class ServicioDAO {
    public static ResultadoOperacion registrarDiagnostico(String descripcionDiagnostico, double cotizacion, double montoTotal, 
            int idTipoServicio, int idCliente, int idEquipoComputo) throws SQLException{
        
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        ResultadoOperacion respuesta = new ResultadoOperacion();
        respuesta.setError(true);
        respuesta.setFilasAfectadas(-1);
        
        if(conexionBD != null){
            try{
                String consulta = "INSERT INTO servicio (descripcionDiagnostico, cotizacion, estadoServicio, montoTotal, "
                        + "idTipoServicio, idCliente, idEquipoComputo) VALUES (?, ?, ?, ?, ?, ?, ?)";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, descripcionDiagnostico);
                prepararSentencia.setDouble(2, cotizacion);
                prepararSentencia.setString(3, "Activo");
                prepararSentencia.setDouble(4, montoTotal);
                prepararSentencia.setInt(5, idTipoServicio);
                prepararSentencia.setInt(6, idCliente);
                prepararSentencia.setInt(7, idEquipoComputo);
                int filasAfectadas = prepararSentencia.executeUpdate();
                
                if(filasAfectadas > 0){
                    respuesta.setError(false);
                    respuesta.setFilasAfectadas(filasAfectadas);
                }
            }catch(SQLException e){
                respuesta.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Falló la conexión con la base de datos.\nInténtelo más tarde", 
                    Alert.AlertType.ERROR);
        }
        return respuesta;
    }
}