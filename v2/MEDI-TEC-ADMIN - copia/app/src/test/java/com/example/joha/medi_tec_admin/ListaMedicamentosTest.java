package com.example.joha.medi_tec_admin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pruebas on 26/09/2017.
 */
public class ListaMedicamentosTest {
    private ListaMedicamentos listaMedicamentos;
    private Medicamento medicamento;

    @Before
    public void setUp() throws Exception {
        this.listaMedicamentos = new ListaMedicamentos();
        this.medicamento = new Medicamento(1,"Salbutamol","es una medicina");
        this.listaMedicamentos.addMedicamentoListaMedicamentos(medicamento);
    }

    @After
    public void tearDown() throws Exception {
        this.listaMedicamentos=null;
    }

    //9
    //verifica que el largo de la lista de medicamentos este bien
    @Test
    public void testLenListaEnfermedades() throws Exception {
        assertEquals(1,this.listaMedicamentos.lenListaMedicamentos());
    }

    //10
    //verifica que un medicamento se elimine correctamente de la lista de medicamentos. En este caso el elemento se encuentra en la lista
    @Test
    public void testEliminarMedicamentoListaMedicamentosTrue() throws Exception {
        listaMedicamentos.eliminarMedicamentoListaMedicamentos(medicamento);
        assertEquals(0, this.listaMedicamentos.lenListaMedicamentos());
    }
    //11
    //verifica que un medicamento se elimine correctamente de la lista de medicamentos. En este caso el elemento no se encuentra en la lista
    @Test
    public void testEliminarMedicamentoListaMedicamentosFalse() throws Exception {
        Medicamento m = new Medicamento(10,"Acetaminofen","es una medicamento 3");
        listaMedicamentos.eliminarMedicamentoListaMedicamentos(m);
        assertEquals(1, this.listaMedicamentos.lenListaMedicamentos());
    }
    //12
    //verifica que se agregue correctamente un elemento en la lista de medicamentos. En este caso un
    //elemntos valido (no se habia inserado con anterioridad, [no pueden haber repetidos])
    @Test
    public void testAddMedicamentoListaMedicamentosValido() throws Exception {
        Medicamento newMedicamento = new Medicamento(2,"Acetaminofen","es un medicamento 2");
        this.listaMedicamentos.addMedicamentoListaMedicamentos(newMedicamento);
        assertEquals(2,this.listaMedicamentos.lenListaMedicamentos());
    }

    //13
    //verifica que se agregue correctamente un elemento en la lista de medicamentos.  En este caso es invalido
    @Test
    public void testAddMedicamentoListaMedicamentosInValido() throws Exception {
        this.listaMedicamentos.addMedicamentoListaMedicamentos(medicamento);
        assertEquals(1,this.listaMedicamentos.lenListaMedicamentos());
    }
    //14
    // busca un elementos en la lista de medicamentos. En este caso un elementos que se encuentra en la lista
    @Test
    public void testBuscarMedicamentoTrue() throws Exception {
        assertTrue(this.listaMedicamentos.buscarMedicamento(medicamento));
    }
    //15
    // busca un elementos en la lista de medicamentos. En este caso un elementos que no se encuentra en la lista
    @Test
    public void testBuscarMedicamentoFalse() throws Exception {
        Medicamento m = new Medicamento(10,"Acetaminofen","es una medicamento 3");
        assertFalse(this.listaMedicamentos.buscarMedicamento(m));
    }

    //16
    //elimina todos los elementos de la lista de medicamentos
    @Test
    public void testLimpiarListaMedicamentos() throws Exception {
        this.listaMedicamentos.limpiarListaMedicamentos();
        assertEquals(0,this.listaMedicamentos.lenListaMedicamentos());
    }


}