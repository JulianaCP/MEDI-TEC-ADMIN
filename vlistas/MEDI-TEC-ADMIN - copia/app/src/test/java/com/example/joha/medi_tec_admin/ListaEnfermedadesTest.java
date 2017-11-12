package com.example.joha.medi_tec_admin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Pruebas on 26/09/2017.
 */
public class ListaEnfermedadesTest {
    private ListaEnfermedades listaEnfermedades;
    private Enfermedad enfermedad;

    @Before
    public void setUp() throws Exception {
        this.listaEnfermedades = new ListaEnfermedades();
        this.enfermedad = new Enfermedad(1,"Asma","es una enfermedad");
        this.listaEnfermedades.addEnfermedadListaEnfermedades(enfermedad);
    }

    @After
    public void tearDown() throws Exception {
        this.listaEnfermedades=null;
    }

    //1
    //verifica que el largo de la lista de enfermedades este bien
    @Test
    public void testLenListaEnfermedades() throws Exception {
        assertEquals(1,this.listaEnfermedades.lenListaEnfermedades());
    }

    //2
    //verifica que una enfermedad se elimine correctamente de la lista de enfermedades. En este caso el elemento se encuentra en la lista
    @Test
    public void testEliminarEnfermedadListaEnfermedadesTrue() throws Exception {
        listaEnfermedades.eliminarEnfermedadListaEnfermedades(enfermedad);
        assertEquals(0, this.listaEnfermedades.lenListaEnfermedades());
    }
    //3
    //verifica que una enfermedad se elimine correctamente de la lista de enfermedades. En este caso el elemento no se encuentra en la lista
    @Test
    public void testEliminarEnfermedadListaEnfermedadesFalse() throws Exception {
        Enfermedad e = new Enfermedad(10,"Apendisitis","es una enfermedad");
        listaEnfermedades.eliminarEnfermedadListaEnfermedades(e);
        assertEquals(1, this.listaEnfermedades.lenListaEnfermedades());
    }
    //4
    //verifica que se agregue correctamente un elemento en la lista de enfermedades. En este caso un
    //elemntos valido (no se habia inserado con anterioridad, [no pueden haber repetidos])
    @Test
    public void testAddEnfermedadListaEnfermedadesValido() throws Exception {
        Enfermedad newEnfermedad = new Enfermedad(2,"Apendicitis","es una enfermedad 2");
        this.listaEnfermedades.addEnfermedadListaEnfermedades(newEnfermedad);
        assertEquals(2,this.listaEnfermedades.lenListaEnfermedades());
    }

    //5
    //verifica que se agregue correctamente un elemento en la lista de enfermedades.  En este caso es invalido
    @Test
    public void testAddEnfermedadListaEnfermedadesInValido() throws Exception {
        this.listaEnfermedades.addEnfermedadListaEnfermedades(enfermedad);
        assertEquals(1,this.listaEnfermedades.lenListaEnfermedades());
    }
    //6
    // busca un elementos en la lista. En este caso un elementos que se encuentra en la lista
    @Test
    public void testBuscarEnfermedadTrue() throws Exception {
        assertTrue(this.listaEnfermedades.buscarEnfermedad(enfermedad));
    }
    //7
    // busca un elementos en la lista. En este caso un elementos que no se encuentra en la lista
    @Test
    public void testBuscarEnfermedadFalse() throws Exception {
        Enfermedad newEnfermedad = new Enfermedad(2,"Apendicitis","es una enfermedad 2");
        assertFalse(this.listaEnfermedades.buscarEnfermedad(newEnfermedad));
    }

    //8
    //elimina todos los elementos de la lista de enfermedades
    @Test
    public void testLimpiarListaEnfermedades() throws Exception {
        this.listaEnfermedades.limpiarListaEnfermedades();
        assertEquals(0,this.listaEnfermedades.lenListaEnfermedades());
    }


}