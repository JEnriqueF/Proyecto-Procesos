package Modelo.DAO;

import Modelo.POJO.ResultadoOperacion;
import org.junit.Test;
import static org.junit.Assert.*;

public class EquipoComputoDAOTest {
    
    public EquipoComputoDAOTest() {
    }

    @Test
    public void testRegistrarEquipo() throws Exception {
        System.out.println("registrarEquipo");
        String descripcion = "";
        ResultadoOperacion respuesta = new ResultadoOperacion();
        ResultadoOperacion expResult = respuesta;
        ResultadoOperacion result = EquipoComputoDAO.registrarEquipo(descripcion);
        assertEquals(expResult.isError(), result.isError());
    }

    @Test
    public void testObtenerEquipoNuevo() throws Exception {
        System.out.println("obtenerEquipoNuevo");
        String descripcionEquipo = "";
        int expResult = 33;
        int result = EquipoComputoDAO.obtenerEquipoNuevo(descripcionEquipo);
        assertEquals(expResult, result);
    }
}