package com.example.joha.medi_tec_admin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bryan on 29/5/2017.
 */


public class ListaEnfermedades {
    private static ListaEnfermedades instancia = null;
    private ArrayList<Enfermedad> listaEnfermedades = new ArrayList<>();

    public static ListaEnfermedades getInstancia() {
        if (instancia == null) {
            instancia = new ListaEnfermedades();
        }
        return instancia;
    }

    public void editar(int valorInt, String stringNombre, String stringDescripcion){
        Global.listaEnfermedades.get(valorInt).setNombre(stringNombre);
        Global.listaEnfermedades.get(valorInt).setDescripcion(stringDescripcion);
    }

    public ArrayList<Enfermedad> obtenerDatos(){
        return Global.listaEnfermedades;
    }

    public ListaEnfermedades() {
        this.listaEnfermedades = new ArrayList<>();
    }

    public int lenListaEnfermedades(){
        return this.listaEnfermedades.size();
    }

    public void eliminarEnfermedadListaEnfermedades(Enfermedad e){
        this.listaEnfermedades.remove(e);
        Global.listaEnfermedades.remove(e);
    }
    public void addEnfermedadListaEnfermedades(Enfermedad enfermedad){
        boolean valido = true;
        for (Enfermedad e : this.listaEnfermedades){
            if(e.getIdEnfermedad() == enfermedad.getIdEnfermedad() && e.getNombre().equals(enfermedad.getNombre())
                    && e.getDescripcion().equals(enfermedad.getDescripcion())){
                valido=false;
            }
        }
        if(valido){
            this.listaEnfermedades.add(enfermedad);
            Global.listaEnfermedades.add(enfermedad);
        }
    }
    public void limpiarListaEnfermedades(){
        this.listaEnfermedades.clear();

    }
    public boolean buscarEnfermedad(Enfermedad e){
        for(Enfermedad enfermedad : this.listaEnfermedades){
            if(enfermedad.getIdEnfermedad() == e.getIdEnfermedad() && e.getNombre().equals(enfermedad.getNombre())
                    && e.getDescripcion().equals(enfermedad.getDescripcion())) {
                return true;
            }
        }
        return false;
    }
}

