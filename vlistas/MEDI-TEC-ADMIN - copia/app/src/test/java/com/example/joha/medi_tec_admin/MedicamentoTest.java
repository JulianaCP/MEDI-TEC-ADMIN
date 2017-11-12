package com.example.joha.medi_tec_admin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pruebas on 26/09/2017.
 */
public class MedicamentoTest {
    private Medicamento medicamento;
    @Before
    public void setUp() throws Exception {
        medicamento = new Medicamento(1,"Salbutamol","esto es un medicamento");
    }

    @After
    public void tearDown() throws Exception {
        medicamento = null;
    }

    //29++
    @Test
    public void testGetIdMedicamento() throws Exception {
        assertEquals(1,medicamento.getIdMedicamento());
    }
    //30++
    @Test
    public void testSetIdMedicamento() throws Exception {
        medicamento.setIdMedicamento(2);
        assertEquals(2,medicamento.getIdMedicamento());
    }
    //31
    @Test
    public void testGetNombre() throws Exception {
        assertEquals("Salbutamol",medicamento.getNombre());
    }
    //32
    @Test
    public void testSetNombre() throws Exception {
        medicamento.setNombre("Acetaminofen");
        assertEquals("Acetaminofen",medicamento.getNombre());
    }
    //33
    @Test
    public void testGetDescripcion() throws Exception {
        assertEquals("esto es un medicamento",medicamento.getDescripcion());
    }
    //34
    @Test
    public void testSetDescripcion() throws Exception {
        medicamento.setDescripcion("Es para el dolor");
        assertEquals("Es para el dolor",medicamento.getDescripcion());

    }
}