package com.example.joha.medi_tec_admin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bryan on 6/10/2017.
 */

public class ListaMedicamentosEnfermedad {
    private static ListaMedicamentosEnfermedad instancia = null;
    private ArrayList<MedicamentosEnfermedad> listaMedicamentosEnfermedad;

    public static ListaMedicamentosEnfermedad getInstancia() {
        if (instancia == null) {
            instancia = new ListaMedicamentosEnfermedad();
        }
        return instancia;
    }


}
