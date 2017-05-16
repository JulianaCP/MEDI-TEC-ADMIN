package com.example.pruebas.diseno;

/**
 * Created by Pruebas on 16/05/2017.
 */
public class Medicamento {
    int idMedicamento;
    String nombre;
    String descripcion;
    public Medicamento(int idVar,String nombreVar,String descripcionVar){
        this.idMedicamento = idVar;
        this.nombre = nombreVar;
        this.descripcion = descripcionVar;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
