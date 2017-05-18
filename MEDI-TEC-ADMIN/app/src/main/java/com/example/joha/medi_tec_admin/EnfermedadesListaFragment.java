package com.example.joha.medi_tec_admin;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnfermedadesListaFragment extends Fragment {
    private View rootView;
    ListView enfermedades_lista_listView_enfermedades;
    Bundle bundle;
    ArrayAdapter<String> adapter;
    ArrayList<Enfermedad> arrayListEnfermedadesClass;
    ArrayList<String> arrayListEnfermedadesString;
    int posicionItemPopuMenuPresionado;
    MenuInflater inflayer;

    public EnfermedadesListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment}
        rootView = inflater.inflate(R.layout.fragment_enfermedades_lista, container, false);
        enfermedades_lista_listView_enfermedades = (ListView) rootView.findViewById(R.id.enfermedades_lista_listView_enfermedades);
        //llenarGlobal();
        llenarListViewEnfermedades();
        enfermedades_lista_listView_enfermedades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Global.posicionItemListViewPresionado = i;
                showPopupMenu(view);
            }
        });

        FloatingActionButton btnAgregarMedicamento = (FloatingActionButton) rootView.findViewById(R.id.btnAgregarEnfermedad);
        btnAgregarMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarEnfermedadFragment agregarEnfermedadFragment = new AgregarEnfermedadFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.ContentForFragments, agregarEnfermedadFragment).commit();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        llenarListViewEnfermedades();
        super.onResume();
    }

    public void llenarListViewEnfermedades(){
        arrayListEnfermedadesClass = Global.listaEnfermedades;
        arrayListEnfermedadesString = convertirClass_String();
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,arrayListEnfermedadesString);
        enfermedades_lista_listView_enfermedades.setAdapter(adapter);
    }

    /*public void llenarGlobal(){



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
    }*/
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

        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                posicionItemPopuMenuPresionado = menuItem.getItemId();
                if(posicionItemPopuMenuPresionado == R.id.menuOpcionEditar){

                    /*bundle = new Bundle();
                    bundle.putString("valor",Integer.toString(Global.posicionItemListViewPresionado));
                    Intent intent = new Intent(getApplicationContext(),editar_enfermedades.class);
                    intent.putExtras(bundle);
                    startActivity(intent);*/
                    EditarEnfermedadFragment editarEnfermedadFragment = new EditarEnfermedadFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.ContentForFragments, editarEnfermedadFragment).commit();
                }
                else{
                    new AlertDialog.Builder(getActivity())

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
        inflayer = getActivity().getMenuInflater();
        inflayer.inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.show();
    }
    public void eliminar(int posicion){
        Global.listaEnfermedades.remove(posicion);
        onResume();
    }

}
