package Modelo.POJO;

import org.junit.Test;
import static org.junit.Assert.*;

public class EquipoComputoTest {
    
    public EquipoComputoTest() {
    }

    @Test
    public void testGetIdEquipoComputo() {
        System.out.println("getIdEquipoComputo");
        EquipoComputo instance = new EquipoComputo();
        int expResult = 0;
        int result = instance.getIdEquipoComputo();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdEquipoComputo() {
        System.out.println("setIdEquipoComputo");
        int idEquipoComputo = 0;
        EquipoComputo instance = new EquipoComputo();
        instance.setIdEquipoComputo(idEquipoComputo);
    }

    @Test
    public void testGetDescripcionEquipo() {
        System.out.println("getDescripcionEquipo");
        EquipoComputo instance = new EquipoComputo();
        String expResult = null;
        String result = instance.getDescripcionEquipo();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDescripcionEquipo() {
        System.out.println("setDescripcionEquipo");
        String descripcionEquipo = "";
        EquipoComputo instance = new EquipoComputo();
        instance.setDescripcionEquipo(descripcionEquipo);
    }
}