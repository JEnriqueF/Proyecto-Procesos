/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.POJO.EquipoComputo;
import Modelo.POJO.ResultadoOperacion;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LENOVO
 */
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
        int expResult = 43;
        int result = EquipoComputoDAO.obtenerEquipoNuevo(descripcionEquipo);
        assertEquals(expResult, result);
    }

//    @Test
//    public void testObtenerEquipoPorCliente() throws Exception {
//        System.out.println("obtenerEquipoPorCliente");
//        int idEquipoComputo = 0;
//        EquipoComputo expResult = null;
//        EquipoComputo result = EquipoComputoDAO.obtenerEquipoPorCliente(idEquipoComputo);
//        assertEquals(expResult, result);
//    }

    @Test
    public void testObtenerDescripcionEquipo() throws Exception {
        System.out.println("obtenerDescripcionEquipo");
        int idEquipoComputo = 29;
        String expResult = "Lenovo ideapad";
        String result = EquipoComputoDAO.obtenerDescripcionEquipo(idEquipoComputo);
        assertEquals(expResult, result);
    }
    
}
