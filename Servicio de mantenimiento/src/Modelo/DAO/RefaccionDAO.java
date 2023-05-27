package Modelo.DAO;

import Modelo.ConexionBaseDatos;
import Modelo.POJO.Refaccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author froyl
 */
public class RefaccionDAO {
    public static ArrayList<Refaccion> obtenerRefacciones() throws SQLException{
        ArrayList<Refaccion> refaccionesBD = null;
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idRefaccion, nombreRefaccion, precioCosto, unidades, proveedor FROM refaccion";
                PreparedStatement consultaUsuario = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = consultaUsuario.executeQuery();
                refaccionesBD = new ArrayList<>();
                
                while(resultadoConsulta.next()){
                    Refaccion refaccionTemporal = new Refaccion();
                    refaccionTemporal.setIdRefaccion(resultadoConsulta.getInt("idRefaccion"));
                    refaccionTemporal.setNombreRefaccion(resultadoConsulta.getString("nombreRefaccion"));
                    refaccionTemporal.setPrecioCosto(resultadoConsulta.getDouble("precioCosto"));
                    refaccionTemporal.setUnidades(resultadoConsulta.getInt("unidades"));
                    refaccionTemporal.setProveedor(resultadoConsulta.getString("proveedor"));
                    refaccionesBD.add(refaccionTemporal);
                }
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        
        return refaccionesBD;
    }
    
    public static boolean registrarRefaccion(Refaccion refaccionNueva) throws SQLException{
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        boolean seRegistro = false;
        
        if(conexionBD != null){
            try {
                String consulta = "INSERT INTO refaccion (nombreRefaccion, precioCosto, unidades, proveedor) VALUES (?,?,?,?)";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, refaccionNueva.getNombreRefaccion());
                prepararSentencia.setDouble(2, refaccionNueva.getPrecioCosto());
                prepararSentencia.setInt(3, refaccionNueva.getUnidades());
                prepararSentencia.setString(4, refaccionNueva.getProveedor());
                int filasAfectadas = prepararSentencia.executeUpdate();
                
                if(filasAfectadas > 0){
                    seRegistro = true;
                }       
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        
        return seRegistro;
    }
    
    public static boolean agregarUnidades(int unidadesNuevas, int idRefaccion) throws SQLException{
        Connection conexionBD = ConexionBaseDatos.abrirConexionBaseDatos();
        boolean seAgrego = false;
        
        if(conexionBD != null){
            try{
                String consulta = "UPDATE refaccion SET unidades = ? WHERE idRefaccion = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, unidadesNuevas);
                prepararSentencia.setInt(2, idRefaccion);
                int filasAfectadas = prepararSentencia.executeUpdate();
                
                if(filasAfectadas > 0){
                    seAgrego = true;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        
        return seAgrego;
    }
}
