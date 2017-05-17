package com.example.pruebas.diseno;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class enfermedades_lista extends AppCompatActivity {

    ListView enfermedades_lista_listView_enfermedades;
    Bundle bundle;
    ArrayAdapter<String> adapter;
    ArrayList<Enfermedad> arrayListEnfermedadesClass;
    ArrayList<String> arrayListEnfermedadesString;
    int posicionItemPopuMenuPresionado;
    MenuInflater inflayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_lista);

        enfermedades_lista_listView_enfermedades = (ListView)findViewById(R.id.enfermedades_lista_listView_enfermedades);




        llenarGlobal();


        llenarListViewEnfermedades();


        enfermedades_lista_listView_enfermedades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Global.posicionItemListViewPresionado = i;
                showPopupMenu(view);
            }
        });
    }

    @Override
    protected void onResume() {
        llenarListViewEnfermedades();
        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crud,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuOpcionAgregar){

            Intent intent = new Intent(getApplicationContext(),enfermedades_Agregar.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void llenarListViewEnfermedades(){
        arrayListEnfermedadesClass = Global.listaEnfermedades;
        arrayListEnfermedadesString = convertirClass_String();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayListEnfermedadesString);
        enfermedades_lista_listView_enfermedades.setAdapter(adapter);
    }

    public void llenarGlobal(){



        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servidor servidor = retrofit.create(Servidor.class);

        Call<List<Enfermedad>> call = servidor.obtenerEnfermedades();

        call.enqueue(new Callback<List<Enfermedad>>() {
            @Override
            public void onResponse(Call<List<Enfermedad>> call, Response<List<Enfermedad>> response) {

            }

            @Override
            public void onFailure(Call<List<Enfermedad>> call, Throwable t) {

            }
        });
    }
    public ArrayList<String> convertirClass_String(){
        ArrayList<String> listaTemp = new ArrayList<String>();
        String valor;

        for (int i=0;i<arrayListEnfermedadesClass.size();i++){
            valor = arrayListEnfermedadesClass.get(i).getNombre();
            listaTemp.add(valor);
        }

        return listaTemp;
    }

    public void showPopupMenu(final View view){

        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                posicionItemPopuMenuPresionado = menuItem.getItemId();
                if(posicionItemPopuMenuPresionado == R.id.menuOpcionEditar){

                    bundle = new Bundle();
                    bundle.putString("valor",Integer.toString(Global.posicionItemListViewPresionado));
                    Intent intent = new Intent(getApplicationContext(),editar_enfermedades.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    new AlertDialog.Builder(enfermedades_lista.this)

                            .setMessage("¿Desea Eliminar?")
                            .setTitle("Confirmacion")
                            .setPositiveButton("Confirmar", new DialogInterface.OnClickListener()  {
                                public void onClick(DialogInterface dialog, int id) {
                                    Log.i("Dialogos", "Elemento Eliminado.");
                                    dialog.cancel();
                                    eliminar(Global.posicionItemListViewPresionado);

                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Log.i("Dialogos", "Confirmacion Cancelada.");
                                    dialog.cancel();
                                }
                            }).create().show();

                }
                return true;
            }
        });
        inflayer = getMenuInflater();
        inflayer.inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.show();
    }
    public void eliminar(int posicion){
        Global.listaEnfermedades.remove(posicion);
        onResume();
    }
}
