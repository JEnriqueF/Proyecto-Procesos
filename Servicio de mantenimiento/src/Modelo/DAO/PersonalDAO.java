package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.Personal;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class PersonalDAO {
    public static Personal verificarUsuario(String usuario, String contraseña) throws SQLException{
        Personal usuarioSesion = null;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        
        if(conexionBD != null){
            try {
                String consulta = "SELECT * FROM personal WHERE usuario = ? AND contrasenia = ?";
                
                PreparedStatement consultaLogin = conexionBD.prepareStatement(consulta);
                consultaLogin.setString(1, usuario);
                consultaLogin.setString(2, contraseña);
                ResultSet resultadoConsulta = consultaLogin.executeQuery();
                usuarioSesion = new Personal();
                
                if(resultadoConsulta.next()){
                    usuarioSesion.setIdPersonal(resultadoConsulta.getInt("idPersonal"));
                    usuarioSesion.setUsuario(usuario);
                    usuarioSesion.setContrasenia(contraseña);
                    usuarioSesion.setIdTipoUsuario(resultadoConsulta.getInt("idTipoUsuario"));
                }else{
                    usuarioSesion.setIdPersonal(0);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "No hay conexion con la base de datos.", Alert.AlertType.ERROR);
        }
        
        return usuarioSesion;
    }
}