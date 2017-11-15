package com.example.joha.medi_tec_admin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RendimientoSintoma {
    private ListaSintomas listaSintomas;
    private Sintoma sintoma;
    private List<String> lista;

    @Before
    public void setUp() throws Exception {
        this.listaSintomas = new ListaSintomas();
        for(int i= 0; i < 200; i ++){
            this.sintoma = new Sintoma(i,"simtona nuevo");
            this.listaSintomas.addSintomaListaSintomas(sintoma);
        }
    }

    @After
    public void tearDown() throws Exception {
        this.listaSintomas=null;
        Global.listaSintomas.clear();
    }

    //(2) Carga la lista de síntomas con 200 datos en menos de 1.5
    @Test
    public void cargaListaDeSintomaEnMenosDeX() throws Exception {
        ArrayList<Sintoma>lis;
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial"+startTime);
            lis=listaSintomas.cargarLista();
            double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime/1000);

        assertTrue(lis.size()== 200);
        assertTrue(1.5*1000 >= endTime);
    }

    //(6) Inserta un síntoma en menos de  0.8 s
    @Test
    public void insertaEnSintomaEnMenosDeX() throws Exception {
        this.sintoma = new Sintoma(201,"simtona nuevo");

        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial"+startTime);
        listaSintomas.addSintomaListaSintomas(sintoma);
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
            listaSintomas.editarSintoma(199,"sintoma actualizado");
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

        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial: "+startTime);
            lista = new ArrayList<>();
            SintomasEnfermedad nuevo= new SintomasEnfermedad();
            nuevo.cargarLista(lista);
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
