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
    
   
public static ArrayList<Cliente> obtenerClientesConEquipo() throws SQLException {
    ArrayList<Cliente> clientes = new ArrayList<>();
    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
    
    if (conexionBD != null) {
        try {
            String consulta = "SELECT ec.idEquipoComputo, c.nombre, c.numTelefono, c.correo " +
                "FROM cliente c " +
                "JOIN servicio s ON c.idCliente = s.idCliente " +
                "JOIN equipocomputo ec ON s.idEquipoComputo = ec.idEquipoComputo";
            PreparedStatement consultaClientes = conexionBD.prepareStatement(consulta);
            ResultSet resultadoConsulta = consultaClientes.executeQuery();
            
            while (resultadoConsulta.next()) {
                int idEquipoComputo = resultadoConsulta.getInt("idEquipoComputo");
                String nombreCliente = resultadoConsulta.getString("nombre");
                String numTelefono = resultadoConsulta.getString("numTelefono");
                String correo = resultadoConsulta.getString("correo");

                // Crea un objeto Cliente y asigna los valores correspondientes
                Cliente cliente = new Cliente(idEquipoComputo, nombreCliente, numTelefono, correo);
                clientes.add(cliente);
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

    return clientes;
}




}