package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.TipoServicio;
import Utilidades.Utilidades;
import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoServicioDAO {
    
    public static TipoServicio obtenerTipoServicio(int idTipoServicio) throws SQLException {
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        TipoServicio tipoServicio = null;

        if (conexionBD != null) {
            try {
                String consulta = "SELECT tipoServicio, cobroManoObra FROM tiposervicio WHERE idTipoServicio = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idTipoServicio);
                ResultSet resultado = prepararSentencia.executeQuery();

                if (resultado.next()) {
                    tipoServicio = new TipoServicio();
                    tipoServicio.setIdTipoServicio(idTipoServicio);
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
}
