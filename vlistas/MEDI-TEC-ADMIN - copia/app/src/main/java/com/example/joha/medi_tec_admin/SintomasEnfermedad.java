package com.example.joha.medi_tec_admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bryan on 6/10/2017.
 */

public class SintomasEnfermedad {
    private int idEnfermedad;
    private int idSintoma;

    public SintomasEnfermedad() {

    }

    public void insertar(Enfermedad enfermedad, List<String> listaSintomasLocal, int position){
        for (int i = 0; i < Global.listaSintomas.size(); i++) {
            if (Global.listaSintomas.get(i).getNombre().equals(listaSintomasLocal.get(position))) {
                enfermedad.setSintomas(Global.listaSintomas.get(i));
            }
        }
    }

    public void cargarLista(List<String>listaSintomasLocal){
        for (int i = 0; i < Global.listaSintomas.size(); i++) {
            listaSintomasLocal.add(Global.listaSintomas.get(i).getNombre());
        }
    }
    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public int getIdSintoma() {
        return idSintoma;
    }

    public void setIdSintoma(int idSintoma) {
        this.idSintoma = idSintoma;
    }

}
