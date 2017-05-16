package com.example.pruebas.diseno;

/**
 * Created by Juliana on 13/05/2017.
 */
public class Sintoma {
    int id;
    String nombre;
    public Sintoma(int idVar,String nom){
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
