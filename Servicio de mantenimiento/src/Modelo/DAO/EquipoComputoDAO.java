package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.EquipoComputo;
import Modelo.POJO.ResultadoOperacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        }
        return respuesta;  
    }
    
    /*public static ResultadoOperacion obtenerEquipo() throws SQLException{
        ResultadoOperacion usuariosBD = null;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        
        if(conexionBD != null){
            try{
                String consulta = "SELECT * FROM mantenimientoprocesos.cliente";
                
                PreparedStatement consultaUsuario = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = consultaUsuario.executeQuery();
                usuariosBD = new ArrayList<>();
                
                while(resultadoConsulta.next()){
                    Cliente temp = new Cliente();
                    temp.setIdCliente(resultadoConsulta.getInt("idCliente"));
                    temp.setNombre(resultadoConsulta.getString("nombre"));
                    temp.setNumTelefono(resultadoConsulta.getString("numTelefono"));
                    temp.setCorreo(resultadoConsulta.getString("correo"));
                    usuariosBD.add(temp);
                }
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Falló la conexión con la base de datos.\nInténtelo más tarde", 
                    Alert.AlertType.ERROR);
        }
        return usuariosBD;
    }*/
}