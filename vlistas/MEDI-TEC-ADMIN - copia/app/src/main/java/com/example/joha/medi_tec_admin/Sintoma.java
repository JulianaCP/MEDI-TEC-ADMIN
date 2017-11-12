package com.example.joha.medi_tec_admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Juliana on 13/05/2017.
 */
public class Sintoma {
    int id;
    String nombre;
    public Sintoma(int idVar, String nom){
        this.id = idVar;
        this.nombre = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
