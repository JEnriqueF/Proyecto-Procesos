package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.Cliente;
import Modelo.POJO.ResultadoOperacion;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;

public class ClienteDAO {
    public static ArrayList<Cliente> obtenerClientes() throws SQLException{
        ArrayList<Cliente> usuariosBD = null;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        System.out.println(conexionBD);
        
        if(conexionBD != null){
            try{
                String consulta = "SELECT nombre, numTelefono, correo FROM mantenimientoprocesos.cliente";
                
                System.out.println(consulta);
                PreparedStatement consultaUsuario = conexionBD.prepareStatement(consulta);
                System.out.println("consultaUsuario: "+consultaUsuario);
                ResultSet resultadoConsulta = consultaUsuario.executeQuery();
                System.out.println("resultadoConsulta: "+resultadoConsulta);
                usuariosBD = new ArrayList<>();
                System.out.println("UsuariosBD: "+usuariosBD);
                
                while(resultadoConsulta.next()){
                    Cliente temp = new Cliente();
                    temp.setNombre(resultadoConsulta.getString("nombre"));
                    temp.setNumTelefono(resultadoConsulta.getString("numTelefono"));
                    temp.setCorreo(resultadoConsulta.getString("correo"));
                    usuariosBD.add(temp);
                    System.out.println("Temp: "+temp);
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
    }
    
    public static ResultadoOperacion registrarCliente(Cliente cliente) throws SQLException{
        ResultadoOperacion respuesta = new ResultadoOperacion();
        respuesta.setError(true);
        respuesta.setFilasAfectadas(-1);
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        
        if(conexionBD != null){
            try{
                String sentencia = "INSERT INTO Cliente (nombre, numTelefono, correo) VALUES (?,?,?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, cliente.getNombre());
                prepararSentencia.setString(2, cliente.getNumTelefono());
                prepararSentencia.setString(3, cliente.getCorreo());
                
                int filasAfectadas = prepararSentencia.executeUpdate();
                
                if(filasAfectadas > 0){
                    respuesta.setError(false);
                    respuesta.setFilasAfectadas(filasAfectadas);
                }
            }catch(SQLException sqlExcepcion){
                respuesta.setMensaje(sqlExcepcion.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuesta.setMensaje("No hay conexión con la base de datos.");
        }
        
        return respuesta;
    }
}