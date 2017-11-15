package com.example.joha.medi_tec_admin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bryan on 29/5/2017.
 */

public class ListaMedicamentos {
    private ArrayList<Medicamento> listaMedicamentos = new ArrayList<>();
    private static ListaMedicamentos instancia = null;

    public ArrayList<Medicamento> obtenerDatos(){
        return  Global.listaMedicamentos;
    }

    public static ListaMedicamentos getInstancia() {
        if (instancia == null) {
            instancia = new ListaMedicamentos();
        }
        return instancia;
    }

    public void editarMedicamento(int valorInt, String stringNombre, String stringDescripcion){
        Global.listaMedicamentos.get(valorInt).setNombre(stringNombre);
        Global.listaMedicamentos.get(valorInt).setDescripcion(stringDescripcion);
    }

    public ListaMedicamentos() {
        this.listaMedicamentos = new ArrayList<>();
    }

    public int lenListaMedicamentos(){
        return this.listaMedicamentos.size();
    }
    public void eliminarMedicamentoListaMedicamentos(Medicamento m){
        this.listaMedicamentos.remove(m);
        Global.listaMedicamentos.remove(m);
    }
    public void addMedicamentoListaMedicamentos(Medicamento medicamento){
        boolean valido = true;
        for (Medicamento m : this.listaMedicamentos){
            if(m.getIdMedicamento() == medicamento.getIdMedicamento() && m.getNombre().equals(medicamento.getNombre())
                    && m.getDescripcion().equals(medicamento.getDescripcion())){
                valido=false;
            }
        }
        if(valido){
            this.listaMedicamentos.add(medicamento);
            Global.listaMedicamentos.add(medicamento);
        }

    }
    public void limpiarListaMedicamentos(){
        this.listaMedicamentos.clear();

    }
    public boolean buscarMedicamento(Medicamento m){
        for(Medicamento medicamento : this.listaMedicamentos){
            if(m.getIdMedicamento() == medicamento.getIdMedicamento() && m.getNombre().equals(medicamento.getNombre())
                    && m.getDescripcion().equals(medicamento.getDescripcion())) {
                return true;
            }
        }
        return false;
    }
}

