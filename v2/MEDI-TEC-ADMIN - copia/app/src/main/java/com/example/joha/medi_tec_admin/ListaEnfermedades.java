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

    public ArrayList<Enfermedad> leer (Context context){
        this.listaEnfermedades = new ArrayList<Enfermedad>();
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_NOMBRE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_DESCRIPCION,
        };

        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_ENFERMEDAD, // tabla
                projection, // columnas
                null, // where
                null, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );
        int idEnfermedad;
        String nombre;
        String descripcion;
        Enfermedad enfermedad;
        if (cursor.moveToFirst()){
            idEnfermedad = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD));
            nombre = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_NOMBRE));
            descripcion = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_DESCRIPCION));
            enfermedad = new Enfermedad(idEnfermedad, nombre, descripcion);
            this.listaEnfermedades.add(enfermedad);
            while(cursor.moveToNext()){
                idEnfermedad = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD));
                nombre = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_NOMBRE));
                descripcion = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_DESCRIPCION));
                enfermedad = new Enfermedad(idEnfermedad, nombre, descripcion);
                this.listaEnfermedades.add(enfermedad);
            }
        }
        return listaEnfermedades;
    }

    public ListaEnfermedades() {
        this.listaEnfermedades = new ArrayList<>();
    }

    public int lenListaEnfermedades(){
        return this.listaEnfermedades.size();
    }
    public void eliminarEnfermedadListaEnfermedades(Enfermedad e){
        this.listaEnfermedades.remove(e);
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

