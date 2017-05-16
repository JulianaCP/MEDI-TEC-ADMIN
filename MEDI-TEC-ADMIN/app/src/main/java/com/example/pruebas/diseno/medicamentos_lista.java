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

public class medicamentos_lista extends AppCompatActivity {

    MenuInflater inflayer;
    ArrayList<Medicamento> arrayListaMedicamentosClass;
    ArrayList<String> arrayListaMedicamentosString;
    ArrayAdapter<String> adapter;
    ListView medicamentos_lista_ListViewMedicamentos;
    int posicionItemPopuMenuPresionado;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos_lista);

        medicamentos_lista_ListViewMedicamentos = (ListView)findViewById(R.id.medicamentos_lista_listView_medicamentos);

        llenarGlobal();

        llenarListViewMedicamentos();

        medicamentos_lista_ListViewMedicamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Global.posicionItemListViewPresionado = i;
                showPopupMenu(view);
            }
        });
    }
    @Override
    protected void onResume() {
        llenarListViewMedicamentos();
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
            Intent intent = new Intent(getApplicationContext(),medicamentos_agregar.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void llenarGlobal(){
        Medicamento me1= new Medicamento(1,"Medicamento_1","Esta es el Medicamento 1");
        Medicamento me2= new Medicamento(2,"Medicamento_2","Esta es el Medicamento 2");
        Medicamento me3= new Medicamento(3,"Medicamento_3","Esta es el Medicamento 3");
        Medicamento me4= new Medicamento(4,"Medicamento_4","Esta es el Medicamento 4");

        Global.listaMedicamentos.add(me1);
        Global.listaMedicamentos.add(me2);
        Global.listaMedicamentos.add(me3);
        Global.listaMedicamentos.add(me4);
    }

    public void llenarListViewMedicamentos(){
        arrayListaMedicamentosClass = Global.listaMedicamentos;
        arrayListaMedicamentosString = convertirClass_String();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayListaMedicamentosString);
        medicamentos_lista_ListViewMedicamentos.setAdapter(adapter);
    }
    public ArrayList<String> convertirClass_String(){
        ArrayList<String> listaTemp = new ArrayList<String>();
        String valor;

        for (int i=0;i<arrayListaMedicamentosClass.size();i++){
            valor = arrayListaMedicamentosClass.get(i).getNombre();
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
                    Intent intent = new Intent(getApplicationContext(),editar_medicamentos.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    new AlertDialog.Builder(medicamentos_lista.this)

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
        Global.listaMedicamentos.remove(posicion);
        onResume();
    }
}
