package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.Cliente;
import Modelo.POJO.EquipoComputo;
import Modelo.POJO.ResultadoOperacion;
import Modelo.POJO.Servicio;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import java.sql.ResultSet;
import java.util.ArrayList;

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
  
    
    public static ArrayList<Cliente> obtenerClientesConEquipo() throws SQLException {
    ArrayList<Cliente> clientes = new ArrayList<>();
    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();

    if (conexionBD != null) {
        try {
            String consulta = "SELECT c.idCliente, c.nombre, c.numTelefono, c.correo, ec.idEquipoComputo " +
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
        Utilidades.mostrarAlertaSimple("Error", "Falló la conexión con la base de datos.\nInténtelo más tarde", Alert.AlertType.ERROR);
    }

    return clientes;
}

    
    
    

    
   /*public static Servicio obtenerDiagnosticoPorEquipoComputo(int idEquipoComputo) throws SQLException {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Servicio servicio = null;

    try {
        conn = ConexionBaseDatos.abrirConexionBaseDatos();
        String query = "SELECT descripcionDiagnostico FROM Servicio WHERE idEquipoComputo = ?";
        stmt = conn.prepareStatement(query);
        stmt.setInt(1, idEquipoComputo);
        rs = stmt.executeQuery();

        if (rs.next()) {
            String descripcionDiagnostico = rs.getString("descripcionDiagnostico");
            servicio = new Servicio();
            servicio.setDescripcionDiagnostico(descripcionDiagnostico);
        }
    } finally {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    return servicio;
}/*/



    
    
    
    
}