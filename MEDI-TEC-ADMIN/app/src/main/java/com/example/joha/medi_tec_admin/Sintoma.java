package com.example.joha.medi_tec_admin;

/**
 * Created by Juliana on 13/05/2017.
 */
public class Sintoma {
    int idSintoma;
    String nombre;
    public Sintoma(int idVar, String nom){
        this.idSintoma = idVar;
        this.nombre = nom;
    }

    public int getId() {
        return idSintoma;
    }

    public void setId(int id) {
        this.idSintoma = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
