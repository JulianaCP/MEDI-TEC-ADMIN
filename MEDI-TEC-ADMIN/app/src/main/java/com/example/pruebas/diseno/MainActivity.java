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

public class MainActivity extends AppCompatActivity {

    MenuInflater inflayer;
    ArrayList<Sintoma> arrayListaSintomasClass;
    ArrayList<String> arrayListaSintomasString;
    ArrayAdapter<String>adapter;
    ListView sintomasListViewSintomas;
    int posicionItemPopuMenuPresionado;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sintomasListViewSintomas = (ListView)findViewById(R.id.sintomasLisViewListaSintomas);

        //borrar TEMPORAL - OBTENER DESPUES DE LA BASE
        llenarGlobal();

        llenarListViewSintomas();

        sintomasListViewSintomas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Global.posicionItemListViewPresionado = i;
                showPopupMenu(view);
            }
        });


    }

    @Override
    protected void onResume() {

        llenarListViewSintomas();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sintoma_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.sintomasMenuOpcionAgregar){
            Intent intent = new Intent(getApplicationContext(),sintomas_agregar.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void llenarGlobal(){
        Sintoma sin1= new Sintoma(1,"Sintoma_1");
        Sintoma sin2= new Sintoma(2,"Sintoma_2");
        Sintoma sin3= new Sintoma(3,"Sintoma_3");
        Sintoma sin4= new Sintoma(4,"Sintoma_4");

        Global.lista.add(sin1);
        Global.lista.add(sin2);
        Global.lista.add(sin3);
        Global.lista.add(sin4);
    }
    public void llenarListViewSintomas(){
        arrayListaSintomasClass = Global.lista;
        arrayListaSintomasString = convertirClass_String();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayListaSintomasString);
        sintomasListViewSintomas.setAdapter(adapter);
    }
    public ArrayList<String> convertirClass_String(){
        ArrayList<String> listaTemp = new ArrayList<String>();
        String valor;

        for (int i=0;i<arrayListaSintomasClass.size();i++){
            valor = arrayListaSintomasClass.get(i).getNombre();
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
                if(posicionItemPopuMenuPresionado == R.id.sintomasMenuOpcionEditar){

                    bundle = new Bundle();
                    bundle.putString("valor",Integer.toString(Global.posicionItemListViewPresionado));
                    Intent intent = new Intent(getApplicationContext(),editar_Sintomas.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    new AlertDialog.Builder(MainActivity.this)

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
        inflayer.inflate(R.menu.sintomas_popup_menu,popupMenu.getMenu());
        popupMenu.show();
    }
    public void eliminar(int posicion){
        Global.lista.remove(posicion);
        onResume();
    }
}
