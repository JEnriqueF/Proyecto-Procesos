/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.POJO.Personal;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LENOVO
 */
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
