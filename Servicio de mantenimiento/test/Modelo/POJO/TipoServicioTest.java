package Modelo.POJO;

import org.junit.Test;
import static org.junit.Assert.*;

public class TipoServicioTest {
    
    public TipoServicioTest() {
    }

    @Test
    public void testGetIdTipoServicio() {
        System.out.println("getIdTipoServicio");
        TipoServicio instance = new TipoServicio();
        int expResult = 0;
        int result = instance.getIdTipoServicio();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdTipoServicio() {
        System.out.println("setIdTipoServicio");
        int idTipoServicio = 0;
        TipoServicio instance = new TipoServicio();
        instance.setIdTipoServicio(idTipoServicio);
    }

    @Test
    public void testGetTipoServicio() {
        System.out.println("getTipoServicio");
        TipoServicio instance = new TipoServicio();
        String expResult = null;
        String result = instance.getTipoServicio();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetTipoServicio() {
        System.out.println("setTipoServicio");
        String tipoServicio = "";
        TipoServicio instance = new TipoServicio();
        instance.setTipoServicio(tipoServicio);
    }

    @Test
    public void testGetCobroManoObra() {
        System.out.println("getCobroManoObra");
        TipoServicio instance = new TipoServicio();
        double expResult = 0.0;
        double result = instance.getCobroManoObra();
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testSetCobroManoObra() {
        System.out.println("setCobroManoObra");
        double cobroManoObra = 0.0;
        TipoServicio instance = new TipoServicio();
        instance.setCobroManoObra(cobroManoObra);
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        TipoServicio instance = new TipoServicio();
        String expResult = null;
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}