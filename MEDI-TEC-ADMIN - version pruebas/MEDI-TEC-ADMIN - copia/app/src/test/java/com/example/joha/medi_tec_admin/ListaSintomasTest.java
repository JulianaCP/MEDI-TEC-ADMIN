package com.example.joha.medi_tec_admin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pruebas on 26/09/2017.
 */
public class ListaSintomasTest {

    private ListaSintomas listaSintomas;
    private Sintoma sintoma;

    @Before
    public void setUp() throws Exception {
        this.listaSintomas = new ListaSintomas();
        this.sintoma = new Sintoma(1,"tos");
        this.listaSintomas.addSintomaListaSintomas(sintoma);
    }

    @After
    public void tearDown() throws Exception {
        this.listaSintomas=null;
    }

    //17
    //verifica que el largo de la lista de sintomas este bien
    @Test
    public void testLenListaSintomas() throws Exception {
        assertEquals(1,this.listaSintomas.lenListaSintomas());
    }

    //18
    //verifica que un sintoma se elimine correctamente de la lista de sintomas. En este caso el elemento se encuentra en la lista
    @Test
    public void testEliminarSintomaListaSintomasTrue() throws Exception {
        listaSintomas.eliminarSintomaListaSintomas(sintoma);
        assertEquals(0, this.listaSintomas.lenListaSintomas());
    }
    //19
    //verifica que un sintoma se elimine correctamente de la lista de sintomas. En este caso el elemento no se encuentra en la lista
    @Test
    public void testEliminarSintomaListaSintomasFalse() throws Exception {
        Sintoma s = new Sintoma(10,"Dolor de cabeza");
        listaSintomas.eliminarSintomaListaSintomas(s);
        assertEquals(1, this.listaSintomas.lenListaSintomas());
    }
    //20
    //verifica que se agregue correctamente un elemento en la lista de sintomas . En este caso un
    //elemntos valido (no se habia inserado con anterioridad, [no pueden haber repetidos])
    @Test
    public void testAddSintomaListaSintomasValido() throws Exception {
        Sintoma s = new Sintoma(2,"Dolor de Cabeza");
        this.listaSintomas.addSintomaListaSintomas(s);
        assertEquals(2,this.listaSintomas.lenListaSintomas());
    }

    //21++
    //verifica que se agregue correctamente un elemento en la lista de sintomas.  En este caso es invalido
    @Test
    public void testAddSintomaListaSintomasInValido() throws Exception {
        this.listaSintomas.addSintomaListaSintomas(sintoma);
        assertEquals(1,this.listaSintomas.lenListaSintomas());
    }
    //22++
    // busca un elementos en la lista de sintomas. En este caso un elementos que se encuentra en la lista
    @Test
    public void testBuscarSintomaTrue() throws Exception {
        assertTrue(this.listaSintomas.buscarSintoma(sintoma));
    }
    //23++
    // busca un elementos en la lista de sintomas. En este caso un elementos que no se encuentra en la lista
    @Test
    public void testBuscarSintomaFalse() throws Exception {
        Sintoma s = new Sintoma(2,"Dolor de Cabeza");
        assertFalse(this.listaSintomas.buscarSintoma(s));
    }
    //24++
    //elimina todos los elementos de la lista de sintomas
    @Test
    public void testLimpiarListaSintomas() throws Exception {
        this.listaSintomas.limpiarListaSintomas();
        assertEquals(0,this.listaSintomas.lenListaSintomas());
    }
}