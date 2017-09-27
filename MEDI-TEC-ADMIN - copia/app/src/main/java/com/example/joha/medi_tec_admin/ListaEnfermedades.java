package com.example.joha.medi_tec_admin;

import java.util.ArrayList;

/**
 * Created by Bryan on 29/5/2017.
 */

public class ListaEnfermedades {
    private static ListaEnfermedades instancia = null;
    private ArrayList<Sintoma> listaSintomas;

    public static ListaEnfermedades getInstancia() {
        if (instancia == null) {
            instancia = new ListaEnfermedades();
        }
        return instancia;
    }



}
