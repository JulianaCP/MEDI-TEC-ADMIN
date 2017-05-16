package com.example.pruebas.diseno;

/**
 * Created by Juliana on 15/05/2017.
 */
public class Enfermedad {
    int idEnfermedad;
    String nombre;
    public Enfermedad(int idVar,String nombreVar){
        this.idEnfermedad = idVar;
        this.nombre = nombreVar;
    }

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
