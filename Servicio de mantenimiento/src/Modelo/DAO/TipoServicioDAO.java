package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.TipoServicio;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;

public class TipoServicioDAO {
    public static ArrayList<TipoServicio> obtenerTipoServicio() throws SQLException{
        ArrayList<TipoServicio> usuariosBD = null;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        
        if(conexionBD != null){
            try{
                String consulta = "SELECT * FROM TipoServicio WHERE idTipoServicio > ?";
                
                PreparedStatement consultaUsuario = conexionBD.prepareStatement(consulta);
                consultaUsuario.setInt(1, 1);
                ResultSet resultadoConsulta = consultaUsuario.executeQuery();
                usuariosBD = new ArrayList<>();
                
                while(resultadoConsulta.next()){
                    TipoServicio temp = new TipoServicio();
                    temp.setIdTipoServicio(resultadoConsulta.getInt("idTipoServicio"));
                    temp.setTipoServicio(resultadoConsulta.getString("tipoServicio"));
                    temp.setCobroManoObra(resultadoConsulta.getDouble("cobroManoObra"));
                    
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