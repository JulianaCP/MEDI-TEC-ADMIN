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


/**
 * A simple {@link Fragment} subclass.
 */
public class SintomasListaFragment extends Fragment {

    private View rootView;
    private MenuInflater inflayer;
    private ArrayList<Sintoma> arrayListaSintomasClass;
    private ArrayList<String> arrayListaSintomasString;
    private ArrayAdapter<String> adapter;
    private ListView sintomasListViewSintomas;
    private int posicionItemPopuMenuPresionado;

    public SintomasListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_sintomas_lista, container, false);

        sintomasListViewSintomas = (ListView) rootView.findViewById(R.id.sintomasLisViewListaSintomas);

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

        FloatingActionButton btnAgregarSintoma = (FloatingActionButton) rootView.findViewById(R.id.btnAgregarSintoma);
        btnAgregarSintoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarSintomasFragment agregarSintomasFragment = new AgregarSintomasFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.ContentForFragments, agregarSintomasFragment).commit();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {

        llenarListViewSintomas();
        super.onResume();
    }

    public void llenarGlobal(){
        Sintoma sin1= new Sintoma(1,"Sintoma_1");
        Sintoma sin2= new Sintoma(2,"Sintoma_2");
        Sintoma sin3= new Sintoma(3,"Sintoma_3");
        Sintoma sin4= new Sintoma(4,"Sintoma_4");

        Global.listaSintomas.add(sin1);
        Global.listaSintomas.add(sin2);
        Global.listaSintomas.add(sin3);
        Global.listaSintomas.add(sin4);
    }
    public void llenarListViewSintomas(){
        arrayListaSintomasClass = Global.listaSintomas;
        arrayListaSintomasString = convertirClass_String();
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,arrayListaSintomasString);
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

        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                posicionItemPopuMenuPresionado = menuItem.getItemId();
                if(posicionItemPopuMenuPresionado == R.id.menuOpcionEditar){

                    /*bundle = new Bundle();
                    bundle.putString("valor",Integer.toString(Global.posicionItemListViewPresionado));
                    Intent intent = new Intent(getApplicationContext(),editar_Sintomas.class);
                    intent.putExtras(bundle);
                    startActivity(intent);*/
                    EditarSintomasFragment editarSintomasFragment = new EditarSintomasFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.ContentForFragments, editarSintomasFragment).commit();
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
        Global.listaSintomas.remove(posicion);
        onResume();
    }
}
