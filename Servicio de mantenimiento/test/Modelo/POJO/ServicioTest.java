package Modelo.POJO;

import org.junit.Test;
import static org.junit.Assert.*;

public class ServicioTest {
    
    public ServicioTest() {
    }

    @Test
    public void testGetIdServicio() {
        System.out.println("getIdServicio");
        Servicio instance = new Servicio();
        int expResult = 0;
        int result = instance.getIdServicio();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdServicio() {
        System.out.println("setIdServicio");
        int idServicio = 0;
        Servicio instance = new Servicio();
        instance.setIdServicio(idServicio);
    }

    @Test
    public void testGetIdTipoServicio() {
        System.out.println("getIdTipoServicio");
        Servicio instance = new Servicio();
        int expResult = 0;
        int result = instance.getIdTipoServicio();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdTipoServicio() {
        System.out.println("setIdTipoServicio");
        int idTipoServicio = 0;
        Servicio instance = new Servicio();
        instance.setIdTipoServicio(idTipoServicio);
    }

    @Test
    public void testGetIdCliente() {
        System.out.println("getIdCliente");
        Servicio instance = new Servicio();
        int expResult = 0;
        int result = instance.getIdCliente();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdCliente() {
        System.out.println("setIdCliente");
        int idCliente = 0;
        Servicio instance = new Servicio();
        instance.setIdCliente(idCliente);
    }

    @Test
    public void testGetIdEquipoComputo() {
        System.out.println("getIdEquipoComputo");
        Servicio instance = new Servicio();
        int expResult = 0;
        int result = instance.getIdEquipoComputo();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdEquipoComputo() {
        System.out.println("setIdEquipoComputo");
        int idEquipoComputo = 0;
        Servicio instance = new Servicio();
        instance.setIdEquipoComputo(idEquipoComputo);
    }

    @Test
    public void testGetIdPersonal() {
        System.out.println("getIdPersonal");
        Servicio instance = new Servicio();
        int expResult = 0;
        int result = instance.getIdPersonal();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdPersonal() {
        System.out.println("setIdPersonal");
        int idPersonal = 0;
        Servicio instance = new Servicio();
        instance.setIdPersonal(idPersonal);
    }

    @Test
    public void testGetDescripcionDiagnostico() {
        System.out.println("getDescripcionDiagnostico");
        Servicio instance = new Servicio();
        String expResult = null;
        String result = instance.getDescripcionDiagnostico();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDescripcionDiagnostico() {
        System.out.println("setDescripcionDiagnostico");
        String descripcionDiagnostico = "";
        Servicio instance = new Servicio();
        instance.setDescripcionDiagnostico(descripcionDiagnostico);
    }

    @Test
    public void testGetDescripcionMantenimiento() {
        System.out.println("getDescripcionMantenimiento");
        Servicio instance = new Servicio();
        String expResult = null;
        String result = instance.getDescripcionMantenimiento();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDescripcionMantenimiento() {
        System.out.println("setDescripcionMantenimiento");
        String descripcionMantenimiento = "";
        Servicio instance = new Servicio();
        instance.setDescripcionMantenimiento(descripcionMantenimiento);
    }

    @Test
    public void testGetEstadoServicio() {
        System.out.println("getEstadoServicio");
        Servicio instance = new Servicio();
        String expResult = null;
        String result = instance.getEstadoServicio();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetEstadoServicio() {
        System.out.println("setEstadoServicio");
        String estadoServicio = "";
        Servicio instance = new Servicio();
        instance.setEstadoServicio(estadoServicio);
    }

    @Test
    public void testGetCotizacion() {
        System.out.println("getCotizacion");
        Servicio instance = new Servicio();
        double expResult = 0.0;
        double result = instance.getCotizacion();
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testSetCotizacion() {
        System.out.println("setCotizacion");
        double cotizacion = 0.0;
        Servicio instance = new Servicio();
        instance.setCotizacion(cotizacion);
    }

    @Test
    public void testGetMontoTotal() {
        System.out.println("getMontoTotal");
        Servicio instance = new Servicio();
        double expResult = 0.0;
        double result = instance.getMontoTotal();
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testSetMontoTotal() {
        System.out.println("setMontoTotal");
        double montoTotal = 0.0;
        Servicio instance = new Servicio();
        instance.setMontoTotal(montoTotal);
    }
}