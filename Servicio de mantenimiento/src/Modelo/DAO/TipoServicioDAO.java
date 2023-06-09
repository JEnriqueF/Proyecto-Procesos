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
    
   
    
    public static TipoServicio obtenerTipoServicio(int idTipoServicio) throws SQLException {
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        TipoServicio tipoServicio = null;

        if (conexionBD != null) {
            try {
                String consulta = "SELECT idTipoServicio, tipoServicio, cobroManoObra FROM tiposervicio WHERE idTipoServicio = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idTipoServicio);
                ResultSet resultado = prepararSentencia.executeQuery();
    
                if (resultado.next()) {
                    tipoServicio = new TipoServicio();
                    tipoServicio.setIdTipoServicio(resultado.getInt("idTipoServicio"));
                    tipoServicio.setTipoServicio(resultado.getString("tipoServicio"));
                    tipoServicio.setCobroManoObra(resultado.getDouble("cobroManoObra"));
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

        return tipoServicio;
    }

    public static ArrayList<TipoServicio> obtenerTipoServicio() throws SQLException {
        ArrayList<TipoServicio> usuariosBD = null;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();

        if(conexionBD != null){
            try{
                String consulta = "SELECT idTipoServicio, tipoServicio, cobroManoObra FROM tiposervicio "
                        + "WHERE idTipoServicio > 1";

                PreparedStatement consultaUsuario = conexionBD.prepareStatement(consulta);
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