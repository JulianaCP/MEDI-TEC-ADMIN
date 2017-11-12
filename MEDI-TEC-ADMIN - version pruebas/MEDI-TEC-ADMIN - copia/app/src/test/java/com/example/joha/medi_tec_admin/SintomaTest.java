package com.example.joha.medi_tec_admin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pruebas on 26/09/2017.
 */
public class SintomaTest {
    private Sintoma sintoma;

    @Before
    public void setUp() throws Exception {
        sintoma = new Sintoma(1,"Dolor de cabeza");
    }

    @After
    public void tearDown() throws Exception {
        sintoma = null;
    }

    //25
    @Test
    public void testGetId() throws Exception {
        assertEquals(1,sintoma.getId());
    }
    //26
    @Test
    public void testSetId() throws Exception {
        sintoma.setId(2);
        assertEquals(2,sintoma.getId());
    }
    //27
    @Test
    public void testGetNombre() throws Exception {
        assertEquals("Dolor de cabeza",sintoma.getNombre());
    }
    //28
    @Test
    public void testSetNombre() throws Exception {
        sintoma.setNombre("Dolor");
        assertEquals("Dolor",sintoma.getNombre());
    }
}