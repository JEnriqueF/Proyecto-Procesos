/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.POJO.TipoServicio;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LENOVO
 */
public class TipoServicioDAOTest {
    
    public TipoServicioDAOTest() {
    }

    @Test
    public void testObtenerTipoServicio_int() throws Exception {
        System.out.println("obtenerTipoServicio");
        int idTipoServicio = 0;
        TipoServicio expResult = null;
        TipoServicio result = TipoServicioDAO.obtenerTipoServicio(idTipoServicio);
        assertEquals(expResult, result);
    }

    @Test
    public void testObtenerTipoServicio() throws Exception {
        System.out.println("obtenerTipoServicio");
        ArrayList<TipoServicio> tipoServiciosEsperados = new ArrayList<TipoServicio>();
        
        TipoServicio tp1 = new TipoServicio();
        tp1.setTipoServicio("Correctivo");
        tipoServiciosEsperados.add(tp1);
        
        TipoServicio tp2 = new TipoServicio();
        tp2.setTipoServicio("Preventivo");
        tipoServiciosEsperados.add(tp2);
        
        ArrayList<TipoServicio> result = TipoServicioDAO.obtenerTipoServicio();
        
        for(int i = 0; i < tipoServiciosEsperados.size(); i++){
            TipoServicio tipoServicioEsperado = tipoServiciosEsperados.get(i);
            TipoServicio tipoServicioObtenido = result.get(i);
            
            assertEquals(tipoServicioEsperado.getTipoServicio(), tipoServicioObtenido.getTipoServicio());
        }
    }
    
}
