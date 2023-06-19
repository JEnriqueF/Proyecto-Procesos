/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.POJO.Cliente;
import org.junit.Test;
import static org.junit.Assert.*;
import Modelo.POJO.ResultadoOperacion;
import java.util.ArrayList;


/**
 *
 * @author LENOVO
 */
public class ClienteDAOTest {
    
    public ClienteDAOTest() {
    }

    @Test
    public void testObtenerClientes() throws Exception {
        System.out.println("obtenerClientes");
        ArrayList<Cliente> clientesEsperados = new ArrayList<>();
        
        Cliente c1 = new Cliente();
        c1.setIdCliente(3);
        c1.setNombre("Óscar Daniel Hernández Hernández");
        c1.setNumTelefono("2281988765");
        c1.setCorreo("zs20019887@estudiantes.uv.mx");
        clientesEsperados.add(c1);
        
        Cliente c2 = new Cliente();
        c2.setIdCliente(4);
        c2.setNombre("Jesús Enrique Fernández González");
        c2.setNumTelefono("2281532121");
        c2.setCorreo("zs19014030@estudiantes.uv.mx");
        clientesEsperados.add(c2);
        
        Cliente c3 = new Cliente();
        c2.setIdCliente(13);
        c2.setNombre("Froylan de Jesús Álvarez Rodríguez");
        c2.setNumTelefono("2281987654");
        c2.setCorreo("zs20016554@estudiantes.uv.mx");
        clientesEsperados.add(c3);
        
        ArrayList<Cliente> resultado = ClienteDAO.obtenerClientes();
        assertEquals(clientesEsperados.size(), resultado.size());
        
        for (int i = 0; i < clientesEsperados.size(); i++) {
            Cliente clienteEsperado = clientesEsperados.get(i);
            Cliente clienteObtenido = resultado.get(i);
            
            assertEquals(clienteEsperado.getIdCliente(), clienteObtenido.getIdCliente());
            assertEquals(clienteEsperado.getNombre(), clienteObtenido.getNombre());
            assertEquals(clienteEsperado.getNumTelefono(), clienteObtenido.getNumTelefono());
            assertEquals(clienteEsperado.getCorreo(), clienteObtenido.getCorreo());
        }
    }

    @Test
    public void testRegistrarCliente() throws Exception {
        System.out.println("registrarCliente");
        Cliente cliente = new Cliente();
        
        ResultadoOperacion resultadoOperacion = new ResultadoOperacion();
        resultadoOperacion.setError(true);
        ResultadoOperacion result = ClienteDAO.registrarCliente(cliente);
        assertEquals(resultadoOperacion.isError(), result.isError());
    }

//    @Test
//    public void testObtenerClientesConEquipo() throws Exception {
//        System.out.println("obtenerClientesConEquipo");
//        ArrayList<Cliente> expResult = null;
//        ArrayList<Cliente> result = ClienteDAO.obtenerClientesConEquipo();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
    
}
