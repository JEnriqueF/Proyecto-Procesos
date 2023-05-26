package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.Cliente;
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
                String consulta = "SELECT idCliente, nombre, numTelefono, correo FROM Cliente";
                
                PreparedStatement consultaUsuario = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = consultaUsuario.executeQuery();
                usuariosBD = new ArrayList<>();
                
                while(resultadoConsulta.next()){
                    Cliente temp = new Cliente();
                    temp.setIdCliente(resultadoConsulta.getInt("id"));
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
}