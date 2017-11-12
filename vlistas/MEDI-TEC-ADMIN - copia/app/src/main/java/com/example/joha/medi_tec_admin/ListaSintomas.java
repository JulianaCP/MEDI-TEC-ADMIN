package com.example.joha.medi_tec_admin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bryan on 29/5/2017.
 */

public class ListaSintomas {
    private static ListaSintomas instancia = null;
    private ArrayList<Sintoma> listaSintomas = new ArrayList<>();

    public static ListaSintomas getInstancia() {
        if (instancia == null) {
            instancia = new ListaSintomas();
        }
        return instancia;
    }


    public ListaSintomas() {
        this.listaSintomas = new ArrayList<>();
    }
    public int lenListaSintomas(){
        return this.listaSintomas.size();
    }
    public void eliminarSintomaListaSintomas(Sintoma s){
        this.listaSintomas.remove(s);
    }
    public void addSintomaListaSintomas(Sintoma sintoma){
        boolean valido = true;
        for (Sintoma s : this.listaSintomas){
            if(s.getId() == sintoma.getId() && s.getNombre().equals(sintoma.getNombre())){
                valido=false;
            }
        }
        if(valido){
            this.listaSintomas.add(sintoma);
        }
    }
    public void limpiarListaSintomas(){
        this.listaSintomas.clear();

    }
    public boolean buscarSintoma(Sintoma s){
        for(Sintoma sintoma : this.listaSintomas){
            if(s.getId() == sintoma.getId() && s.getNombre().equals(sintoma.getNombre())) {
                return true;
            }
        }
        return false;
    }
}
