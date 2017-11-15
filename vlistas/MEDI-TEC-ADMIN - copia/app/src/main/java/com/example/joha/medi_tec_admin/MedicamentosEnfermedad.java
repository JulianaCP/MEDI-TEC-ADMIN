package com.example.joha.medi_tec_admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Bryan on 2/10/2017.
 */

public class MedicamentosEnfermedad {
    private int idEnfermedad;
    private int idMedicamento;

    public MedicamentosEnfermedad() {

    }

    public void leer(List<String> listaMedicamentosLocal){
        for (int i = 0; i < Global.listaMedicamentos.size(); i++) {
            listaMedicamentosLocal.add(Global.listaMedicamentos.get(i).getNombre());
        }
    }

    public void insertar(Enfermedad enfermedad,List<String> listaMedicamentosLocal, int position){
        for (int i = 0; i < Global.listaMedicamentos.size(); i++) {
            if (Global.listaMedicamentos.get(i).getNombre().equals(listaMedicamentosLocal.get(position))) {
                enfermedad.setMedicamentos(Global.listaMedicamentos.get(i));
            }
        }
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
