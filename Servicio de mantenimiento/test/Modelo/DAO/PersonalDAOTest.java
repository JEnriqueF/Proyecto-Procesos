package Modelo.DAO;

import Modelo.POJO.Personal;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonalDAOTest {
    
    public PersonalDAOTest() {
    }

    @Test
    public void testVerificarUsuario() throws Exception {
        System.out.println("verificarUsuario");
        Personal personal = new Personal();
        personal.setUsuario("administrador");
        personal.setContrasenia("123456");
        
        String usuario = "administrador";
        String contraseña = "123456";
        Personal expResult = personal;
        Personal result = PersonalDAO.verificarUsuario(usuario, contraseña);
        
        assertEquals(expResult.getUsuario(), result.getUsuario());
        assertEquals(expResult.getContrasenia(), result.getContrasenia());
    }
}