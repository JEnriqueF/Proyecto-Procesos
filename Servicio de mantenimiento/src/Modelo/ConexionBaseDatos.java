package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String BASEDATOS = "mantenimientoprocesos";
    private static final String IP = "localhost";
    private static final String PUERTO = "3306";
    private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + BASEDATOS + "?allowPublicKeyRetrieval=true&useSSL=false";
    
    private static final String USUARIO = "ProcesosIS";
    private static final String CONTRASENIA = "procesosIS";
    
    public static Connection abrirConexionBaseDatos(){
        Connection dbConnection = null;
        try {
            Class.forName(CONTROLADOR);
            dbConnection = DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        
        return dbConnection;
    }
}