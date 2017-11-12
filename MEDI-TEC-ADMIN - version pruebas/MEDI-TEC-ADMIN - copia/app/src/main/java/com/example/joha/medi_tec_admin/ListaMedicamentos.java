package com.example.joha.medi_tec_admin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bryan on 29/5/2017.
 */

public class ListaMedicamentos {
    private ArrayList<Medicamento> listaMedicamentos = new ArrayList<>();
    private static ListaMedicamentos instancia = null;

    public static ListaMedicamentos getInstancia() {
        if (instancia == null) {
            instancia = new ListaMedicamentos();
        }
        return instancia;
    }

    public ArrayList<Medicamento> leer (Context context){
        this.listaMedicamentos = new ArrayList<Medicamento>();
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_NOMBRE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_DESCRIPCION,
        };

        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_MEDICAMENTO, // tabla
                projection, // columnas
                null, // where
                null, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );
        int idMedicamento;
        String nombre;
        String descripcion;
        Medicamento medicamento;
        if (cursor.moveToFirst()){

            idMedicamento = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO));
            nombre = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_NOMBRE));
            descripcion = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_DESCRIPCION));
            medicamento = new Medicamento(idMedicamento, nombre, descripcion);
            this.listaMedicamentos.add(medicamento);
            while(cursor.moveToNext()){
                idMedicamento = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO));
                nombre = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_NOMBRE));
                descripcion = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_DESCRIPCION));
                medicamento = new Medicamento(idMedicamento, nombre, descripcion);
                this.listaMedicamentos.add(medicamento);
            }
        }
        return listaMedicamentos;
    }

    public ListaMedicamentos() {
        this.listaMedicamentos = new ArrayList<>();
    }

    public int lenListaMedicamentos(){
        return this.listaMedicamentos.size();
    }
    public void eliminarMedicamentoListaMedicamentos(Medicamento m){
        this.listaMedicamentos.remove(m);
    }
    public void addMedicamentoListaMedicamentos(Medicamento medicamento){
        boolean valido = true;
        for (Medicamento m : this.listaMedicamentos){
            if(m.getIdMedicamento() == medicamento.getIdMedicamento() && m.getNombre().equals(medicamento.getNombre())
                    && m.getDescripcion().equals(medicamento.getDescripcion())){
                valido=false;
            }
        }
        if(valido){
            this.listaMedicamentos.add(medicamento);
        }

    }
    public void limpiarListaMedicamentos(){
        this.listaMedicamentos.clear();

    }
    public boolean buscarMedicamento(Medicamento m){
        for(Medicamento medicamento : this.listaMedicamentos){
            if(m.getIdMedicamento() == medicamento.getIdMedicamento() && m.getNombre().equals(medicamento.getNombre())
                    && m.getDescripcion().equals(medicamento.getDescripcion())) {
                return true;
            }
        }
        return false;
    }
}

