package Modelo.POJO;

import org.junit.Test;
import static org.junit.Assert.*;

public class PersonalTest {
    
    public PersonalTest() {
    }

    @Test
    public void testGetIdPersonal() {
        System.out.println("getIdPersonal");
        Personal instance = new Personal();
        int expResult = 0;
        int result = instance.getIdPersonal();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdPersonal() {
        System.out.println("setIdPersonal");
        int idPersonal = 0;
        Personal instance = new Personal();
        instance.setIdPersonal(idPersonal);
    }

    @Test
    public void testGetIdTipoUsuario() {
        System.out.println("getIdTipoUsuario");
        Personal instance = new Personal();
        int expResult = 0;
        int result = instance.getIdTipoUsuario();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdTipoUsuario() {
        System.out.println("setIdTipoUsuario");
        int idTipoUsuario = 0;
        Personal instance = new Personal();
        instance.setIdTipoUsuario(idTipoUsuario);
    }

    @Test
    public void testGetUsuario() {
        System.out.println("getUsuario");
        Personal instance = new Personal();
        String expResult = null;
        String result = instance.getUsuario();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetUsuario() {
        System.out.println("setUsuario");
        String usuario = "";
        Personal instance = new Personal();
        instance.setUsuario(usuario);
    }

    @Test
    public void testGetContrasenia() {
        System.out.println("getContrasenia");
        Personal instance = new Personal();
        String expResult = null;
        String result = instance.getContrasenia();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetContrasenia() {
        System.out.println("setContrasenia");
        String contrasenia = "";
        Personal instance = new Personal();
        instance.setContrasenia(contrasenia);
    }
}