/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.POJO.Refaccion;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LENOVO
 */
public class RefaccionDAOTest {
    
    public RefaccionDAOTest() {
    }

    @Test
    public void testObtenerRefacciones() throws Exception {
        System.out.println("obtenerRefacciones");
        ArrayList<Refaccion> expResult = new ArrayList();
        ArrayList<Refaccion> result = RefaccionDAO.obtenerRefacciones();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegistrarRefaccion() throws Exception {
        System.out.println("registrarRefaccion");
        Refaccion refaccionNueva = new Refaccion();
        refaccionNueva.setNombreRefaccion("TestFile");
        refaccionNueva.setPrecioCosto(1);
        refaccionNueva.setUnidades(1);
        refaccionNueva.setProveedor("Testers");
        refaccionNueva.setUnidadesAlmacenadas(10);
        boolean expResult = true;
        boolean result = RefaccionDAO.registrarRefaccion(refaccionNueva);
        assertEquals(expResult, result);
    }

    @Test
    public void testAgregarUnidades() throws Exception {
        System.out.println("agregarUnidades");
        int unidadesNuevas = 4;
        int idRefaccion = 8;
        boolean expResult = true;
        boolean result = RefaccionDAO.agregarUnidades(unidadesNuevas, idRefaccion);
        assertEquals(expResult, result);
    }

    @Test
    public void testVerificarExistencia() throws Exception {
        System.out.println("verificarExistencia");
        int idRefaccion = 0;
        ArrayList<Refaccion> expResult = null;
        ArrayList<Refaccion> result = RefaccionDAO.verificarExistencia(idRefaccion);
        assertEquals(expResult, result);
    }

//    @Test
//    public void testMostrarRefaccionesServicio() throws Exception {
//        System.out.println("mostrarRefaccionesServicio");
//        int idEquipoComputo = 0;
//        List<Refaccion> expResult = null;
//        List<Refaccion> result = RefaccionDAO.mostrarRefaccionesServicio(idEquipoComputo);
//        assertEquals(expResult, result);
//    }
    
}
