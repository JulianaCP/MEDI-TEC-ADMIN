package com.example.joha.medi_tec_admin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RendimientoSintoma {
    private ListaSintomas listaSintomas;
    private Sintoma sintoma;

    @Before
    public void setUp() throws Exception {
        this.listaSintomas = new ListaSintomas();
        for(int i= 0; i < 200; i ++){
            this.sintoma = new Sintoma(i,"simtona nuevo");
            this.listaSintomas.addSintomaListaSintomas(sintoma);
            Global.listaSintomas.add(sintoma);
        }

    }

    @After
    public void tearDown() throws Exception {
        this.listaSintomas=null;
    }

    //(2) Carga la lista de síntomas con 200 datos en menos de 1.5
    @Test
    public void cargaListaDeSintomaEnMenosDeX() throws Exception {
        ListaSintomas mockito= mock(ListaSintomas.class);
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial"+startTime);
        MedicamentosListaFragment nuevo= new MedicamentosListaFragment();
        mockito.leer(nuevo.getContext());
        double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime/1000);
        verify(mockito).leer(nuevo.getContext());
        assertTrue(listaSintomas.lenListaSintomas() == 200);
        assertTrue(1.5*1000 >= endTime);
    }

    //(6) Inserta un síntoma en menos de  0.8 s
    @Test
    public void insertaEnSintomaEnMenosDeX() throws Exception {
        this.sintoma = new Sintoma(201,"simtona nuevo");

        double startTime = System.currentTimeMillis();
        listaSintomas.addSintomaListaSintomas(sintoma);
        System.out.print("\n\nTiempo inicial"+startTime);
        System.out.print("\n\nTiempo currente"+System.currentTimeMillis());
        double endTime = System.currentTimeMillis() - startTime;

        System.out.print("\n\nTiempo final: "+endTime/1000);
        assertTrue(listaSintomas.lenListaSintomas()== 201);
        assertTrue(0.8*1000 >= endTime);
    }

    //(9) Proceso de edición de síntomas realizado en menos de 1 s
    @Test
    public void edicionSintomaEnMenosDeX() throws Exception {
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial"+startTime);
        sintoma.setNombre("sintoma actualizado");
        double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime);

        assertEquals("si se actualizo","sintoma actualizado",sintoma.getNombre());
        assertTrue(1*1000 >= endTime);
    }


    //(12) Proceso de eliminación de síntomas realizado en menos de 1 s
    @Test
    public void eliminacionDeSintomaEnMenosDeX() throws Exception {
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial: "+startTime);
        listaSintomas.eliminarSintomaListaSintomas(sintoma);
        System.out.print("\n\nTiempo currente: "+System.currentTimeMillis());
        double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime);

        assertEquals(199,listaSintomas.lenListaSintomas());
        assertTrue(endTime <= 1*1000);
    }


    //(14) Carga lista de síntomas de una enfermedad en menos de 1.2 s
    @Test
    public void cargaDeDatoSintomaEnfsEnMenosDeX() throws Exception {
        Enfermedad nuevaEnf= new Enfermedad(1,"nueva","desc");
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial: "+startTime);
        ListaSintomasEnfermedad mockito= mock(ListaSintomasEnfermedad.class);
        AgregarSintomaEnfermedadFragment nuevo= new AgregarSintomaEnfermedadFragment();
        mockito.leer(nuevo.getContext());
        double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime);
        assertTrue(endTime <= 1.2*1000);
    }

    //(16) Carga de datos en edición de síntoma realizado en menos de 0.5 s
    @Test
    public void cargaDeDatosEnMenosDeX() throws Exception {
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial: "+startTime);
        assertEquals("no son iguales","simtona nuevo",sintoma.getNombre());
        double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime);
        assertTrue(endTime <= 0.5*1000);
    }
}
