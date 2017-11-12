package com.example.joha.medi_tec_admin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bryan on 6/10/2017.
 */

public class ListaSintomasEnfermedad {
    private static ListaSintomasEnfermedad instancia = null;
    private ArrayList<SintomasEnfermedad> listaSintomasEnfermedad;

    public static ListaSintomasEnfermedad getInstancia() {
        if (instancia == null) {
            instancia = new ListaSintomasEnfermedad();
        }
        return instancia;
    }

}
