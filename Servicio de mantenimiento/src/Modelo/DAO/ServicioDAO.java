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
import java.util.List;
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

    public static Servicio obtenerServicioPorEquipoComputo(int idEquipoComputo) throws SQLException {
        Servicio servicio = null;
        String consulta = "SELECT s.descripcionDiagnostico, s.idTipoServicio " +
                          "FROM servicio s " +
                          "JOIN equipocomputo e ON s.idEquipoComputo = e.idEquipoComputo " +
                          "WHERE e.idEquipoComputo = ?";

        try (Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
             PreparedStatement consultaServicio = conexionBD.prepareStatement(consulta)) {

            consultaServicio.setInt(1, idEquipoComputo);
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
            "JOIN servicio ON cliente.idCliente = servicio.idCliente  JOIN equipocomputo ON servicio.idEquipoComputo = equipocomputo.idEquipoComputo WHERE servicio.estadoServicio = 'Activo' ";

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

 
    
public static Servicio obtenerDiagnosticoPorEquipoComputo(int idEquipoComputo) throws SQLException {
    Servicio servicio = null;
    String consulta = "SELECT s.descripcionDiagnostico " +
                      "FROM servicio s " +
                      "JOIN equipocomputo e ON s.idEquipoComputo = e.idEquipoComputo " +
                      "WHERE e.idEquipoComputo = ?";

    try (Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
         PreparedStatement consultaDiagnostico = conexionBD.prepareStatement(consulta)) {

        consultaDiagnostico.setInt(1, idEquipoComputo);
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
   
   
   
   
   public static ResultadoOperacion obtenerRefaccionesServicio(int idServicio, int idRefaccion, int unidades) throws SQLException {
    ResultadoOperacion resultadoOperacion = new ResultadoOperacion();
    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();

    if (conexionBD != null) {
        try {
            String consulta = "INSERT INTO refaccionesenservicios VALUES (?, ?, ?, TRUE);";
            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            prepararSentencia.setInt(1,idServicio);
            prepararSentencia.setInt(2,idRefaccion);
            prepararSentencia.setInt(3,unidades);

           int resultado = prepararSentencia.executeUpdate();
             /*("Resultado",""+resultado);*/
            if (resultado>0) {
              resultadoOperacion.setError(false);
              resultadoOperacion.setFilasAfectadas(resultado);
            }
        } catch (SQLException e) {
           resultadoOperacion.setMensaje(e.getMessage());
        } finally {
            try {
                conexionBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } else {
           resultadoOperacion.setMensaje("No hay conexión con la base de datos.");
        
    }
    return resultadoOperacion;
}
   
   
   
   public static Servicio obtenerIdServicio(int idEquipoComputo) throws SQLException {
    Servicio servicio = null;
    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();

    if (conexionBD != null) {
        try {
            String consulta = "SELECT * FROM servicio " +
                              "JOIN equipocomputo ON equipocomputo.idEquipoComputo = servicio.idEquipoComputo " +
                              "WHERE equipocomputo.idEquipoComputo = ?";
            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            prepararSentencia.setInt(1, idEquipoComputo);

            ResultSet resultado = prepararSentencia.executeQuery();

            if (resultado.next()) {
                int idServicio = resultado.getInt("idServicio");
                servicio = new Servicio();
                servicio.setIdServicio(idServicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conexionBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } else {
        
    }
    return servicio;
}

  
   public static ResultadoOperacion ServicioRefaccion(int idServicio, int idRefaccion) {
    ResultadoOperacion respuesta = new ResultadoOperacion();
    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();

    respuesta.setError(true);
    respuesta.setFilasAfectadas(-1);

    try {
        if (conexionBD != null) {
            String consulta = "SELECT refaccionRegistrada FROM refaccionesenservicios WHERE idServicio = ? and idRefaccion = ?";

            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            prepararSentencia.setInt(1, idServicio);
            prepararSentencia.setInt(2, idRefaccion);

             int resultado = prepararSentencia.executeUpdate();

            if (resultado>0) {
              respuesta.setError(false);
              respuesta.setFilasAfectadas(resultado);
            }
            else{
                respuesta.setError(true);
            }
        }
    } catch (SQLException e) {
        respuesta.setMensaje(e.getMessage());
        respuesta.setError(true);
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


     public static ResultadoOperacion RefaccionUpdate(int idServicio, int idRefaccion, int unidades) {
    ResultadoOperacion respuesta = new ResultadoOperacion();
    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();

    respuesta.setError(true);
    respuesta.setFilasAfectadas(-1);

    try {
        if (conexionBD != null) {
            String consulta = "UPDATE refaccionesenservicios SET unidades = ? WHERE idServicio = ? AND idRefaccion = ?;";

            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            prepararSentencia.setInt(1, unidades);
            prepararSentencia.setInt(2, idServicio);
            prepararSentencia.setInt(3, idRefaccion);

             int resultado = prepararSentencia.executeUpdate();

            if (resultado>0) {
              respuesta.setError(false);
              respuesta.setFilasAfectadas(resultado);
            }
            else{
                respuesta.setError(true);
            }
        }
    } catch (SQLException e) {
        respuesta.setMensaje(e.getMessage());
        respuesta.setError(true);
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

       public static String obtenerDescripcionMantenimiento(int idEquipoComputo) throws SQLException {
    String descripcionMantenimiento = null;

    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();

    try {
        if (conexionBD != null) {
            String consulta = "SELECT descripcionMantenimiento FROM servicio WHERE idEquipoComputo = ?";
            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            prepararSentencia.setInt(1, idEquipoComputo);

            ResultSet resultado = prepararSentencia.executeQuery();

            if (resultado.next()) {
                descripcionMantenimiento = resultado.getString("descripcionMantenimiento");
            }
        } else {
            System.out.println("Error al establecer la conexión con la base de datos.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (conexionBD != null) {
                conexionBD.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return descripcionMantenimiento;
}

   
public static List<Refaccion> mostrarRefaccionesServicio(int idEquipoComputo) throws SQLException {
    List<Refaccion> refacciones = new ArrayList<>();
    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();

    if (conexionBD != null) {
        try {
            String consulta = "SELECT r.idRefaccion, r.nombreRefaccion, rs.unidadesUtilizadas " +
                  "FROM refaccion r " +
                  "INNER JOIN refaccionesenservicios rs ON r.idRefaccion = rs.idRefaccion " +
                  "INNER JOIN servicio s ON rs.idServicio = s.idServicio " +
                  "INNER JOIN equipocomputo ec ON s.idEquipoComputo = ec.idEquipoComputo " +
                  "WHERE ec.idEquipoComputo = ?";
            
            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            prepararSentencia.setInt(1, idEquipoComputo);
            ResultSet resultado = prepararSentencia.executeQuery();

         while (resultado.next()) {
    int idRefaccion = resultado.getInt("idRefaccion");
    String nombreRefaccion = resultado.getString("nombreRefaccion");
    int unidadesUtilizadas = resultado.getInt("unidadesUtilizadas");

  
    Refaccion refaccion = new Refaccion();
    refaccion.setIdRefaccion(idRefaccion);
    refaccion.setNombreRefaccion(nombreRefaccion);
    refaccion.setUnidades(unidadesUtilizadas);

    refacciones.add(refaccion);
}

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conexionBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return refacciones;
}
     
     
     
    public static ResultadoOperacion eliminarRefaccion(int idServicio, int idRefaccion) throws SQLException {
    ResultadoOperacion respuesta = new ResultadoOperacion();

    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();

    respuesta.setError(true);
    respuesta.setFilasAfectadas(-1);

    try {
        if (conexionBD != null) {
            String consulta = "DELETE FROM refaccionesenservicios WHERE idServicio = ? AND idRefaccion = ?";

            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            prepararSentencia.setInt(1, idServicio);
            prepararSentencia.setInt(2, idRefaccion);
            int filasAfectadas = prepararSentencia.executeUpdate();

            if (filasAfectadas > 0) {
                respuesta.setError(false);
                respuesta.setFilasAfectadas(filasAfectadas);
                System.out.println("Refacción eliminada exitosamente.");
            } else {
                System.out.println("Error al eliminar la refacción.");
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

public static ResultadoOperacion actualizarEstadoServicio(int idEquipoComputo, String estadoServicio) throws SQLException {
    ResultadoOperacion respuesta = new ResultadoOperacion();
    Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();

    respuesta.setError(true);
    respuesta.setFilasAfectadas(-1);

    try {
        if (conexionBD != null) {
            String consulta = "UPDATE servicio SET estadoServicio = ? WHERE idEquipoComputo = ?";

            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            prepararSentencia.setString(1, estadoServicio);
            prepararSentencia.setInt(2, idEquipoComputo);

            int resultado = prepararSentencia.executeUpdate();
            if (resultado > 0) {
                respuesta.setError(false);
                respuesta.setFilasAfectadas(resultado);
            } else {
                respuesta.setError(true);
            }
        }
    } catch (SQLException e) {
        respuesta.setMensaje(e.getMessage());
        respuesta.setError(true);
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

        





    
