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
        ArrayList<Refaccion> expResult = null;
        ArrayList<Refaccion> result = RefaccionDAO.obtenerRefacciones();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegistrarRefaccion() throws Exception {
        System.out.println("registrarRefaccion");
        Refaccion refaccionNueva = null;
        boolean expResult = false;
        boolean result = RefaccionDAO.registrarRefaccion(refaccionNueva);
        assertEquals(expResult, result);
    }

    @Test
    public void testAgregarUnidades() throws Exception {
        System.out.println("agregarUnidades");
        int unidadesNuevas = 0;
        int idRefaccion = 0;
        boolean expResult = false;
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
