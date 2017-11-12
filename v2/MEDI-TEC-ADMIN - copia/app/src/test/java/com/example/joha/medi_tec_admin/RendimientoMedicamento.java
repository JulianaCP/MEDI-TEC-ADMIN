package com.example.joha.medi_tec_admin;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class RendimientoMedicamento {
    private ListaMedicamentos listaMedicamentos;
    private Medicamento medicamento;

    @Before
    public void setUp() throws Exception {
        this.listaMedicamentos = new ListaMedicamentos();
        for(int i= 0; i < 200; i ++){
            this.medicamento = new Medicamento(i,"nuevo medicamento","para aliviar los malestares");
            this.listaMedicamentos.addMedicamentoListaMedicamentos(medicamento);
            Global.listaMedicamentos.add(medicamento);
        }
    }

    @After
    public void tearDown() throws Exception {
        this.listaMedicamentos =null;
    }

    //(3) Carga la lista de medicamentos menos de 1.5
    @Test
    public void cargaListaDeMedicEnMenosDeX() throws Exception {
        ListaMedicamentos mockito= mock(ListaMedicamentos.class);
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial"+startTime);
        MedicamentosListaFragment nuevo= new MedicamentosListaFragment();
        mockito.leer(nuevo.getContext());
        double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime/1000);
        verify(mockito).leer(nuevo.getContext());
        assertTrue(listaMedicamentos.lenListaMedicamentos() == 200);
        assertTrue(1.5*1000 >= endTime);
    }

    //(5)  Inserta un medicamento en menos de  0.8 s
    @Test
    public void insertaEnMedicamentoEnMenosDeX() throws Exception {
        this.medicamento = new Medicamento(201,"nuevo medicamento","para aliviar los malestares");

        double startTime = System.currentTimeMillis();
        listaMedicamentos.addMedicamentoListaMedicamentos(medicamento);
        System.out.print("\n\nTiempo inicial"+startTime);
        System.out.print("\n\nTiempo currente"+System.currentTimeMillis());
        double endTime = System.currentTimeMillis() - startTime;

        System.out.print("\n\nTiempo final: "+endTime/1000);
        assertTrue(listaMedicamentos.lenListaMedicamentos()== 201);
        assertTrue(0.8*1000 >= endTime);
    }

    //(8) Proceso de edición de medicamento realizado en menos de 1 s
    @Test
    public void edicionSintomaEnMenosDeX() throws Exception {
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial"+startTime);
        medicamento.setNombre("medicamneto actualizado");
        medicamento.setDescripcion("actualizado");
        double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime/1000);

        assertEquals("Actualizacion fallida","medicamneto actualizado",medicamento.getNombre());
        assertEquals("Actualizacion fallida","actualizado",medicamento.getDescripcion());
        assertTrue(1*1000 >= endTime);
    }


    //(11) Proceso de eliminación de medicamento realizado en menos de 1 s
    @Test
    public void eliminacionDeMedicamnetoEnMenosDeX() throws Exception {
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial: "+startTime);
        listaMedicamentos.eliminarMedicamentoListaMedicamentos(medicamento);
        System.out.print("\n\nTiempo currente: "+System.currentTimeMillis());
        double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime/1000);

        assertEquals(199,listaMedicamentos.lenListaMedicamentos());
        assertTrue(endTime <= 1*1000);
    }

    //(13) Carga lista de medicamento de una enfermedad en menos de 1.2 s
    @Test
    public void cargaDeDatoMedicamentoEnfeEnMenosDeX() throws Exception {
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial: "+startTime);
        ListaMedicamentosEnfermedad mockito= mock(ListaMedicamentosEnfermedad.class);
        AgregarMedicamentoEnfermedadFragment nuevo= new AgregarMedicamentoEnfermedadFragment();
        mockito.leer(nuevo.getContext());
        double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime/1000);
        assertTrue(endTime <= 1.2*1000);
    }

    //(17) Carga de datos en edición de medicamento realizado en menos de 0.5 s
    @Test
    public void cargaDeDatosEnMenosDeX() throws Exception {
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial: "+startTime);
        assertEquals("no son iguales","nuevo medicamento",medicamento.getNombre());
        assertEquals("no son iguales","para aliviar los malestares",medicamento.getDescripcion());
        double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime);
        assertTrue(endTime <= 0.5*1000);
    }
}