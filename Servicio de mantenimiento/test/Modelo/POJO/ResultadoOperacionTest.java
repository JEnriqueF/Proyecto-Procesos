package Modelo.POJO;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResultadoOperacionTest {
    
    public ResultadoOperacionTest() {
    }

    @Test
    public void testIsError() {
        System.out.println("isError");
        ResultadoOperacion instance = new ResultadoOperacion();
        boolean expResult = false;
        boolean result = instance.isError();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMensaje() {
        System.out.println("getMensaje");
        ResultadoOperacion instance = new ResultadoOperacion();
        String expResult = null;
        String result = instance.getMensaje();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFilasAfectadas() {
        System.out.println("getFilasAfectadas");
        ResultadoOperacion instance = new ResultadoOperacion();
        int expResult = 0;
        int result = instance.getFilasAfectadas();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetError() {
        System.out.println("setError");
        boolean error = false;
        ResultadoOperacion instance = new ResultadoOperacion();
        instance.setError(error);
    }

    @Test
    public void testSetMensaje() {
        System.out.println("setMensaje");
        String mensaje = "";
        ResultadoOperacion instance = new ResultadoOperacion();
        instance.setMensaje(mensaje);
    }

    @Test
    public void testSetFilasAfectadas() {
        System.out.println("setFilasAfectadas");
        int filasAfectadas = 0;
        ResultadoOperacion instance = new ResultadoOperacion();
        instance.setFilasAfectadas(filasAfectadas);
    }
}