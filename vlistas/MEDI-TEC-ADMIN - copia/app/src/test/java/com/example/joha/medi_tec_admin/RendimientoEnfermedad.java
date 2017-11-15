package com.example.joha.medi_tec_admin;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RendimientoEnfermedad {
    private ListaEnfermedades listaEnfermedades;
    private Enfermedad enfermedad;

    @Before
    public void setUp() throws Exception {
        this.listaEnfermedades = new ListaEnfermedades();
        for(int i= 0; i < 200; i ++){
            this.enfermedad = new Enfermedad(i,"Asma","es una enfermedad");
            this.listaEnfermedades.addEnfermedadListaEnfermedades(enfermedad);
        }
    }

    @After
    public void tearDown() throws Exception {
        this.listaEnfermedades=null;
    }

    //(1) Carga la lista de enfermedades con 200 datos en menos de 1.5 segundos
    @Test
    public void cargaListaDeEnfermedadesEnMenosDeX() throws Exception {
        ArrayList <Enfermedad> lista;
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial"+startTime);
            lista= listaEnfermedades.obtenerDatos();
            double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime/1000);

        assertTrue(lista.size() == 200);
        assertTrue(1.5*1000 >= endTime);
    }


    //(4) Inserta una enfermedad en menos de  0.8 s
    @Test
    public void insertaEnfermedadEnMenosDeX() throws Exception {
        this.enfermedad = new Enfermedad(201,"Asma","es una enfermedad");

        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial"+startTime);
            listaEnfermedades.addEnfermedadListaEnfermedades(enfermedad);
            double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime/1000);

        assertTrue(listaEnfermedades.lenListaEnfermedades()== 201);
        assertTrue(0.8*1000 >= endTime);
    }

    //(7) Proceso de edición de enfermedad realizado en menos de 1 s
    @Test
    public void edicionEnfermedadEnMenosDeX() throws Exception {
        double startTime = System.currentTimeMillis();

        System.out.print("\n\nTiempo inicial"+startTime);
            listaEnfermedades.editar(199,"Asma","es una enfermedad mala");
            double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime/1000);

        assertEquals("actualizacion fallida","es una enfermedad mala",enfermedad.getDescripcion());
        assertTrue(1*1000 >= endTime);
    }

    //(10) Proceso de eliminación de enfermedad realizado en menos de 1 s
    @Test
    public void eliminacionDeEnfermedadEnMenosDeX() throws Exception {
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial: "+startTime);
        listaEnfermedades.eliminarEnfermedadListaEnfermedades(enfermedad);
        double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime/1000);
        assertEquals(199,listaEnfermedades.lenListaEnfermedades());
        assertTrue(endTime <= 1*1000);
    }

    //(15)Carga de datos en edición de enfermedad realizado en menos de 0.5 s
    @Test
    public void cargaDeDatosEnMenosDeX() throws Exception {
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial: "+startTime);
        assertEquals("no son iguales","Asma",enfermedad.getNombre());
        assertEquals("no son iguales","es una enfermedad", enfermedad.getDescripcion());
        double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime/1000);
        assertTrue(endTime <= 0.5*1000);
    }

    //(20) Asignar peligrosidad de una enfermedad.
    @Test
    public void asignarPeligrosidad() throws Exception {
        double startTime = System.currentTimeMillis();
        System.out.print("\n\nTiempo inicial: "+startTime);
            enfermedad.editarPeligrosidad("Alta");
            assertEquals("no iguales","Alta",enfermedad.getPeligrosidad());
            double endTime = System.currentTimeMillis() - startTime;
        System.out.print("\n\nTiempo final: "+endTime);
        assertEquals("no iguales","Alta",enfermedad.getPeligrosidad());
        assertTrue(endTime <= 0.5*1000);
    }
}