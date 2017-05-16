package com.example.pruebas.diseno;

/**
 * Created by Juliana on 15/05/2017.
 */
public class Enfermedad {
    int idEnfermedad;
    String nombre;
    String descripcion;
    public Enfermedad(int idVar,String nombreVar,String descripVar){
        this.idEnfermedad = idVar;
        this.nombre = nombreVar;
        this.descripcion = descripVar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
