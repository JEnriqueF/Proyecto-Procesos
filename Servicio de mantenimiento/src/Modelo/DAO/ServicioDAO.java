package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.Cliente;
import Modelo.POJO.Refaccion;
import java.sql.Statement;

import Modelo.POJO.ResultadoOperacion;
import Modelo.POJO.Servicio;
import Utilidades.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.ObservableList;

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
  
   
    public static String obtenerDescripcionEquipo(int idEquipoComputo) throws SQLException {
        String descripcionEquipo = null;
        String consulta = "SELECT descripcionEquipo FROM equipocomputo WHERE idEquipoComputo = ?";
        
        try (Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
             PreparedStatement consultaDescripcion = conexionBD.prepareStatement(consulta)) {

            consultaDescripcion.setInt(1, idEquipoComputo);
            ResultSet resultadoConsulta = consultaDescripcion.executeQuery();

            if (resultadoConsulta.next()) {
                descripcionEquipo = resultadoConsulta.getString("descripcionEquipo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return descripcionEquipo;
    }

    public static Servicio obtenerServicioPorEquipoComputo(String descripcionEquipo) throws SQLException {
        Servicio servicio = null;
        String consulta = "SELECT s.descripcionDiagnostico, s.idTipoServicio " +
                          "FROM servicio s " +
                          "JOIN equipocomputo e ON s.idEquipoComputo = e.idEquipoComputo " +
                          "WHERE e.descripcionEquipo = ?";

        try (Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
             PreparedStatement consultaServicio = conexionBD.prepareStatement(consulta)) {

            consultaServicio.setString(1, descripcionEquipo);
            ResultSet resultadoConsulta = consultaServicio.executeQuery();

            if (resultadoConsulta.next()) {
                servicio = new Servicio();
                servicio.setDescripcionDiagnostico(resultadoConsulta.getString("descripcionDiagnostico"));
                servicio.setIdTipoServicio(resultadoConsulta.getInt("idTipoServicio"));
                // Establecer los demás atributos del objeto
          }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return servicio;
    }

    
    public static ArrayList<Cliente> obtenerClientesConEquipo() throws SQLException {
    ArrayList<Cliente> clientes = new ArrayList<>();
    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();

    if (conexionBD != null) {
        try {
            String consulta = "SELECT cliente.idCliente, cliente.nombre, cliente.numTelefono, cliente.correo, equipocomputo.idEquipoComputo FROM cliente " +
"	JOIN servicio ON cliente.idCliente = servicio.idCliente JOIN equipocomputo ON servicio.idEquipoComputo = equipocomputo.idEquipoComputo;";

            PreparedStatement consultaClientes = conexionBD.prepareStatement(consulta);
            ResultSet resultadoConsulta = consultaClientes.executeQuery();

            while (resultadoConsulta.next()) {
                Cliente temp = new Cliente();
                
                temp.setIdCliente(resultadoConsulta.getInt("idCliente"));
                temp.setNombre(resultadoConsulta.getString("nombre"));
                temp.setNumTelefono(resultadoConsulta.getString("numTelefono"));
                temp.setCorreo(resultadoConsulta.getString("correo"));
                temp.setIdEquipoComputo(resultadoConsulta.getInt("idEquipoComputo"));
                
                clientes.add(temp);
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

 
    
public static Servicio obtenerDiagnosticoPorEquipoComputo(String descripcionEquipo) throws SQLException {
    Servicio servicio = null;
    String consulta = "SELECT s.descripcionDiagnostico " +
                      "FROM servicio s " +
                      "JOIN equipocomputo e ON s.idEquipoComputo = e.idEquipoComputo " +
                      "WHERE e.descripcionEquipo = ?";

    try (Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
         PreparedStatement consultaDiagnostico = conexionBD.prepareStatement(consulta)) {

        consultaDiagnostico.setString(1, descripcionEquipo);
        ResultSet resultadoConsulta = consultaDiagnostico.executeQuery();

        if (resultadoConsulta.next()) {
            servicio = new Servicio();
            servicio.setDescripcionDiagnostico(resultadoConsulta.getString("descripcionDiagnostico"));
            // Establecer los demás atributos del objeto Servicio según tu estructura de datos
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return servicio;
}



    
    
   public static Servicio obtenerServicioPorId(int idEquipoComputo) throws SQLException {
    Servicio servicio = null;
    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();

    if (conexionBD != null) {
        try {
            String consulta = "SELECT * FROM servicio WHERE idEquipoComputo = ?";
            PreparedStatement consultaServicio = conexionBD.prepareStatement(consulta);
            consultaServicio.setInt(1, idEquipoComputo);
            ResultSet resultadoConsulta = consultaServicio.executeQuery();

            if (resultadoConsulta.next()) {
                servicio = new Servicio();
                servicio.setIdServicio(resultadoConsulta.getInt("idServicio"));
                servicio.setDescripcionDiagnostico(resultadoConsulta.getString("descripcionDiagnostico"));
                servicio.setCotizacion(resultadoConsulta.getDouble("cotizacion"));
                servicio.setEstadoServicio(resultadoConsulta.getString("estadoServicio"));
                servicio.setMontoTotal(resultadoConsulta.getDouble("montoTotal"));
                servicio.setIdTipoServicio(resultadoConsulta.getInt("idTipoServicio"));
                servicio.setIdCliente(resultadoConsulta.getInt("idCliente"));
                servicio.setIdEquipoComputo(resultadoConsulta.getInt("idEquipoComputo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.close();
        }
    } else {
        Utilidades.mostrarAlertaSimple("Error", "Falló la conexión con la base de datos.\nInténtelo más tarde", Alert.AlertType.ERROR);
    }

    return servicio;
}
   
   
  
   public static ResultadoOperacion guardarMantenimiento(int idEquipoComputo, String descripcionMantenimiento) throws SQLException {
    ResultadoOperacion respuesta = new ResultadoOperacion();

    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();

    respuesta.setError(true);
    respuesta.setFilasAfectadas(-1);

    try {
        if (conexionBD != null) {
            String consulta = "UPDATE servicio SET descripcionMantenimiento = ? WHERE idEquipoComputo = ?";

            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            prepararSentencia.setString(1, descripcionMantenimiento);
            prepararSentencia.setInt(2, idEquipoComputo);
            int filasAfectadas = prepararSentencia.executeUpdate();

            if (filasAfectadas > 0) {
                respuesta.setError(false);
                respuesta.setFilasAfectadas(filasAfectadas);
                System.out.println("Descripción del mantenimiento guardada exitosamente.");
            } else {
                System.out.println("Error al guardar la descripción del mantenimiento.");
            }
        } else {
            System.out.println("Error al establecer la conexión con la base de datos.");
        }
    } catch (SQLException e) {
        respuesta.setMensaje(e.getMessage());
    } finally {
        try {
            if (conexionBD != null) {
                conexionBD.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return respuesta;
}







        }




    
    
