package com.example.joha.medi_tec_admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Juliana on 15/05/2017.
 */
public class Enfermedad {
    int idEnfermedad;
    String nombre;
    String descripcion;
    private ArrayList<Sintoma> listaSintomas;
    private ArrayList<Medicamento> listaMedicamentos;
    private String peligrosidad;
    public Enfermedad(int idVar, String nombreVar, String descripVar){
        this.idEnfermedad = idVar;
        this.nombre = nombreVar;
        this.descripcion = descripVar;
        this.listaMedicamentos = new ArrayList<Medicamento>();
        this.listaSintomas = new ArrayList<Sintoma>();
    }
    public String getPeligrosidad() {
        return peligrosidad;
    }

    public void editarPeligrosidad(String peligrosidad) {
        this.peligrosidad = peligrosidad;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEnfermedad() {
        return this.idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMedicamentos(Medicamento medicamento){
        listaMedicamentos.add(medicamento);
    }

    public ArrayList<Medicamento> getMedicamentos(){
        return listaMedicamentos;
    }

    public void setSintomas(Sintoma sintoma){
        listaSintomas.add(sintoma);
    }

    public ArrayList<Sintoma> getSintomas(){
        return listaSintomas;
    }

}
