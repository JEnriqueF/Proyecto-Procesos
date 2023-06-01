/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.POJO;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LENOVO
 */
public class ClienteTest {
    
    public ClienteTest() {
    }

    @Test
    public void testGetIdCliente() {
        System.out.println("getIdCliente");
        Cliente instance = new Cliente();
        int expResult = 0;
        int result = instance.getIdCliente();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdCliente() {
        System.out.println("setIdCliente");
        int idCliente = 0;
        Cliente instance = new Cliente();
        instance.setIdCliente(idCliente);
    }

    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Cliente instance = new Cliente();
        String expResult = null;
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Cliente instance = new Cliente();
        instance.setNombre(nombre);
    }

    @Test
    public void testGetNumTelefono() {
        System.out.println("getNumTelefono");
        Cliente instance = new Cliente();
        String expResult = null;
        String result = instance.getNumTelefono();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetNumTelefono() {
        System.out.println("setNumTelefono");
        String numTelefono = "";
        Cliente instance = new Cliente();
        instance.setNumTelefono(numTelefono);
    }

    @Test
    public void testGetCorreo() {
        System.out.println("getCorreo");
        Cliente instance = new Cliente();
        String expResult = null;
        String result = instance.getCorreo();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetCorreo() {
        System.out.println("setCorreo");
        String correo = "";
        Cliente instance = new Cliente();
        instance.setCorreo(correo);
    }
}