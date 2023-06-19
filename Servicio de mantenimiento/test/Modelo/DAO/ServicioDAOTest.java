/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.POJO.Cliente;
import Modelo.POJO.ResultadoOperacion;
import Modelo.POJO.Servicio;
import java.util.ArrayList;
import java.util.HashSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LENOVO
 */
public class ServicioDAOTest {
    
    public ServicioDAOTest() {
    }

    @Test
    public void testRegistrarDiagnostico() throws Exception {
        System.out.println("registrarDiagnostico");
        String descripcionDiagnostico = "Lo que sea";
        double cotizacion = 111.0;
        double montoTotal = 1111.0;
        int idTipoServicio = 2;
        int idCliente = 4;
        int idEquipoComputo = 3;
        ResultadoOperacion expResult = new ResultadoOperacion();
        expResult.setError(true);
        ResultadoOperacion result = ServicioDAO.registrarDiagnostico(descripcionDiagnostico, cotizacion, montoTotal, idTipoServicio, idCliente, idEquipoComputo);
        assertEquals(expResult.isError(), result.isError());
    }

    @Test
    public void testObtenerDescripcionEquipo() throws Exception {
        System.out.println("obtenerDescripcionEquipo");
        int idEquipoComputo = 38;
        String expResult = "Lenovo AirBook gris plateada";
        String result = ServicioDAO.obtenerDescripcionEquipo(idEquipoComputo);
        assertEquals(expResult, result);
    }

    @Test
    public void testObtenerServicioPorEquipoComputo() throws Exception {
        System.out.println("obtenerServicioPorEquipoComputo");
        int idEquipoComputo = 0;
        Servicio expResult = null;
        Servicio result = ServicioDAO.obtenerServicioPorEquipoComputo(idEquipoComputo);
        assertEquals(expResult, result);
    }

//    @Test
//    public void testObtenerClientesConEquipo() throws Exception {
//        System.out.println("obtenerClientesConEquipo");
//        ArrayList<Cliente> expResult = null;
//        ArrayList<Cliente> result = ServicioDAO.obtenerClientesConEquipo();
//        assertEquals(expResult, result);
//    }

    @Test
    public void testObtenerDiagnosticoPorEquipoComputo() throws Exception {
        System.out.println("obtenerDiagnosticoPorEquipoComputo");
        int idEquipoComputo = 38;
        Servicio expResult = new Servicio();
        expResult.setDescripcionDiagnostico("Pantalla de 17 pulgada rota");
        Servicio result = ServicioDAO.obtenerDiagnosticoPorEquipoComputo(idEquipoComputo);
        assertEquals(expResult.getDescripcionDiagnostico(), result.getDescripcionDiagnostico());
    }

    @Test
    public void testObtenerServicioPorId() throws Exception {
        System.out.println("obtenerServicioPorId");
        int idEquipoComputo = 29;
        Servicio expResult = new Servicio();
        expResult.setIdServicio(3);
        Servicio result = ServicioDAO.obtenerServicioPorId(idEquipoComputo);
        assertEquals(expResult.getIdServicio(), result.getIdServicio());
    }

//    @Test
//    public void testObtenerRefaccionesServicio() throws Exception {
//        System.out.println("obtenerRefaccionesServicio");
//        int idServicio = 3;
//        int idRefaccion = 2;
//        int unidades = 5;
//        ResultadoOperacion expResult = new ResultadoOperacion();
//        expResult.setError(false);
//        ResultadoOperacion result = ServicioDAO.obtenerRefaccionesServicio(idServicio, idRefaccion, unidades);
//        assertEquals(expResult.isError(), result.isError());
//    }

    @Test
    public void testObtenerIdServicio() throws Exception {
        System.out.println("obtenerIdServicio");
        int idEquipoComputo = 29;
        Servicio expResult = new Servicio();
        expResult.setIdServicio(3);
        Servicio result = ServicioDAO.obtenerIdServicio(idEquipoComputo);
        assertEquals(expResult.getIdServicio(), result.getIdServicio());
    }

//    @Test
//    public void testServicioRefaccion() {
//        System.out.println("ServicioRefaccion");
//        int idServicio = 0;
//        int idRefaccion = 0;
//        ResultadoOperacion expResult = null;
//        ResultadoOperacion result = ServicioDAO.ServicioRefaccion(idServicio, idRefaccion);
//        assertEquals(expResult, result);
//    }

//    @Test
//    public void testRefaccionUpdate() {
//        System.out.println("RefaccionUpdate");
//        int idServicio = 0;
//        int idRefaccion = 0;
//        int unidades = 0;
//        ResultadoOperacion expResult = null;
//        ResultadoOperacion result = ServicioDAO.RefaccionUpdate(idServicio, idRefaccion, unidades);
//        assertEquals(expResult, result);
//    }

    @Test
    public void testObtenerDescripcionMantenimiento() throws Exception {
        System.out.println("obtenerDescripcionMantenimiento");
        int idEquipoComputo = 29;
        String expResult = null;
        String result = ServicioDAO.obtenerDescripcionMantenimiento(idEquipoComputo);
        assertEquals(expResult, result);
    }

//    @Test
//    public void testEliminarRefaccion() throws Exception {
//        System.out.println("eliminarRefaccion");
//        int idServicio = 0;
//        int idRefaccion = 0;
//        ResultadoOperacion expResult = null;
//        ResultadoOperacion result = ServicioDAO.eliminarRefaccion(idServicio, idRefaccion);
//        assertEquals(expResult, result);
//    }

    @Test
    public void testGuardarMantenimiento() throws Exception {
        System.out.println("guardarMantenimiento");
        int idEquipoComputo = 38;
        String descripcionMantenimiento = "Lo que sea";
        ResultadoOperacion expResult = new ResultadoOperacion();
        expResult.setError(true);
        ResultadoOperacion result = ServicioDAO.guardarMantenimiento(idEquipoComputo, descripcionMantenimiento);
        assertEquals(expResult.isError(), result.isError());
    }

    @Test
    public void testActualizarEstadoServicio() throws Exception {
        System.out.println("actualizarEstadoServicio");
        int idEquipoComputo = 38;
        String estadoServicio = "Finalizado";
        ResultadoOperacion expResult = null;
        ResultadoOperacion result = ServicioDAO.actualizarEstadoServicio(idEquipoComputo, estadoServicio);
        assertEquals(expResult, result);
    }
    
}
