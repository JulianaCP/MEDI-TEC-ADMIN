package com.example.joha.medi_tec_admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Bryan on 2/10/2017.
 */

public class MedicamentosEnfermedad {
    private int idEnfermedad;
    private int idMedicamento;

    public MedicamentosEnfermedad(int idEnfermedad, int idMedicamento) {
        this.idEnfermedad = idEnfermedad;
        this.idMedicamento = idMedicamento;
    }

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

}
