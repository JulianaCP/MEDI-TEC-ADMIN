package com.example.joha.medi_tec_admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Bryan on 6/10/2017.
 */

public class SintomasEnfermedad {
    private int idEnfermedad;
    private int idSintoma;

    public SintomasEnfermedad(int idEnfermedad, int idSintoma) {
        this.idEnfermedad = idEnfermedad;
        this.idSintoma = idSintoma;
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
