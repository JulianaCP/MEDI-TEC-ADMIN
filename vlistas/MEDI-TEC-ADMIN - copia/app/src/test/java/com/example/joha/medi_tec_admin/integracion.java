package com.example.joha.medi_tec_admin;

import android.content.Context;
import android.content.Intent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Bryan on 11/11/2017.
 */

public class integracion {
    Enfermedad nuevaEnf;

    @Before
    public void setUp() throws Exception {
        nuevaEnf= new Enfermedad(0,"nueva","desc");
    }

    @After
    public void tearDown() throws Exception {
        this.nuevaEnf =null;
    }

    //(18) Asignar medicamento a enfermedad.
    @Test
    public void asignarMedicamentoAEnfermedad() throws Exception {
        MedicamentosEnfermedad mockitoEnf= mock(MedicamentosEnfermedad.class);
        mockitoEnf.setIdEnfermedad(nuevaEnf.getIdEnfermedad());
        mockitoEnf.setIdMedicamento(0);
        assertEquals(0,mockitoEnf.getIdEnfermedad());
        assertEquals(0,mockitoEnf.getIdMedicamento());
        SintomasListaFragment sintomasListaFragment = new SintomasListaFragment();
        mockitoEnf.insertar(sintomasListaFragment.getContext());
        verify(mockitoEnf).insertar(sintomasListaFragment.getContext());
    }

    //(19) Asignar síntoma a una enfermedad.
    @Test
    public void asignarSintomaAEnfermedad() throws Exception {
        SintomasEnfermedad mockitoEnf= mock(SintomasEnfermedad.class);
        mockitoEnf.setIdEnfermedad(nuevaEnf.getIdEnfermedad());
        mockitoEnf.setIdSintoma(0);
        assertEquals(0,mockitoEnf.getIdEnfermedad());
        assertEquals(0,mockitoEnf.getIdSintoma());
        SintomasListaFragment sintomasListaFragment = new SintomasListaFragment();
        mockitoEnf.insertar(sintomasListaFragment.getContext());
        verify(mockitoEnf).insertar(sintomasListaFragment.getContext());
    }
}
