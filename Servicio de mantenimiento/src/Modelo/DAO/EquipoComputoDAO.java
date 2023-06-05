package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.EquipoComputo;
import Modelo.POJO.ResultadoOperacion;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class EquipoComputoDAO {
    
    public static ResultadoOperacion registrarEquipo(String descripcion) throws SQLException{        
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        ResultadoOperacion respuesta = new ResultadoOperacion();
        respuesta.setError(true);
        respuesta.setFilasAfectadas(-1);
        
        if(conexionBD != null){
            try {
                String consulta = "INSERT INTO equipocomputo (descripcionEquipo) VALUES (?)";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, descripcion);
                int filasAfectadas = prepararSentencia.executeUpdate();
                
                if(filasAfectadas > 0){
                    respuesta.setError(false);
                    respuesta.setFilasAfectadas(filasAfectadas);
                }
            } catch (SQLException e) {
                respuesta.setMensaje(e.getMessage());
            } finally {
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Falló la conexión con la base de datos.\nInténtelo más tarde", 
                    Alert.AlertType.ERROR);
        }
        return respuesta;  
    }
    
    public static int obtenerEquipoNuevo(String descripcionEquipo) throws SQLException{
        int equipoBD = -1;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        
        if(conexionBD != null){
            try{
                String consulta = "SELECT MAX(idEquipoComputo) FROM equipocomputo WHERE descripcionEquipo = ?";
                
                PreparedStatement consultaEquipo = conexionBD.prepareStatement(consulta);
                consultaEquipo.setString(1, descripcionEquipo);
                ResultSet resultadoConsulta = consultaEquipo.executeQuery();
                
                if(resultadoConsulta.next()){
                    equipoBD = resultadoConsulta.getInt(1);
                }
                //equipoBD = resultadoConsulta.getInt("idEquipoComputo");
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Falló la conexión con la base de datos.\nInténtelo más tarde", 
                    Alert.AlertType.ERROR);
        }
        return equipoBD;
    }

public static EquipoComputo obtenerEquipoPorCliente(int idEquipoComputo) throws SQLException {
    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
    EquipoComputo equipo = null;
    
    if (conexionBD != null) {
        try {
            String consulta = "SELECT * FROM equipocomputo WHERE idEquipoComputo = ?";
            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            prepararSentencia.setInt(1, idEquipoComputo);
            ResultSet resultado = prepararSentencia.executeQuery();
            
            if (resultado.next()) {
                equipo = new EquipoComputo();
                equipo.setIdEquipoComputo(resultado.getInt("idEquipoComputo"));
                equipo.setDescripcionEquipo(resultado.getString("descripcionEquipo"));
                // Asigna los demás atributos del equipo según la estructura de tu tabla
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.close();
        }
    } else {
        Utilidades.mostrarAlertaSimple("Error", "Falló la conexión con la base de datos.\nInténtelo más tarde", 
                Alert.AlertType.ERROR);
    }
    
    return equipo;
}
public static String obtenerDescripcionEquipo(int idEquipoComputo) throws SQLException {
    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
    String descripcionEquipo = null;

    if (conexionBD != null) {
        try {
            String consulta = "SELECT descripcionEquipo FROM equipocomputo WHERE idEquipoComputo = ?";
            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            prepararSentencia.setInt(1, idEquipoComputo);
            ResultSet resultado = prepararSentencia.executeQuery();

            if (resultado.next()) {
                descripcionEquipo = resultado.getString("descripcionEquipo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.close();
        }
    } else {
        Utilidades.mostrarAlertaSimple("Error", "Falló la conexión con la base de datos.\nInténtelo más tarde",
                Alert.AlertType.ERROR);
    }

    return descripcionEquipo;
}





}