package com.example.joha.medi_tec_admin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bryan on 29/5/2017.
 */

public class ListaSintomas {
    private static ListaSintomas instancia = null;
    private ArrayList<Sintoma> listaSintomas = new ArrayList<>();

    public static ListaSintomas getInstancia() {
        if (instancia == null) {
            instancia = new ListaSintomas();
        }
        return instancia;
    }

    public ArrayList<Sintoma> leer (Context context){
        this.listaSintomas = new ArrayList<Sintoma>();
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_NOMBRE,
        };

        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_SINTOMA, // tabla
                projection, // columnas
                null, // where
                null, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );
        int idSintoma;
        String nombre;
        Sintoma sintoma;
        if (cursor.moveToFirst()){
            idSintoma = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA));
            nombre = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_NOMBRE));
            sintoma = new Sintoma(idSintoma, nombre);
            this.listaSintomas.add(sintoma);
            while(cursor.moveToNext()){
                idSintoma = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA));
                nombre = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_NOMBRE));
                sintoma = new Sintoma(idSintoma, nombre);
                this.listaSintomas.add(sintoma);
            }
        }
        return this.listaSintomas;
    }

    public ListaSintomas() {
        this.listaSintomas = new ArrayList<>();
    }
    public int lenListaSintomas(){
        return this.listaSintomas.size();
    }
    public void eliminarSintomaListaSintomas(Sintoma s){
        this.listaSintomas.remove(s);
    }
    public void addSintomaListaSintomas(Sintoma sintoma){
        boolean valido = true;
        for (Sintoma s : this.listaSintomas){
            if(s.getId() == sintoma.getId() && s.getNombre().equals(sintoma.getNombre())){
                valido=false;
            }
        }
        if(valido){
            this.listaSintomas.add(sintoma);
        }
    }
    public void limpiarListaSintomas(){
        this.listaSintomas.clear();

    }
    public boolean buscarSintoma(Sintoma s){
        for(Sintoma sintoma : this.listaSintomas){
            if(s.getId() == sintoma.getId() && s.getNombre().equals(sintoma.getNombre())) {
                return true;
            }
        }
        return false;
    }
}
