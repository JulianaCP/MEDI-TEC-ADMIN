package com.example.joha.medi_tec_admin;

/**
 * Created by Bryan on 17/5/2017.
 */

public class Medicamento {
    private int idMedicamento;
    private String nombre;
    private String descripcion;

    public Medicamento(int idMedicamento, String nombre, String descripcion) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
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
